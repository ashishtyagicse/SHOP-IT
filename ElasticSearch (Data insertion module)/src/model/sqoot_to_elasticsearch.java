package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.TimerTask;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class sqoot_to_elasticsearch extends TimerTask {

	public static final String sqootApiKey = "c8zpjy";
	public static final String sqootEndPoint = "http://api.sqoot.com/v2/deals"; 
	public static final String elasticSearchEndPoint = "http://52.35.165.238:9200/deals"; 
	public sqoot_to_elasticsearch() {
		
	}
	public void run() {
		try{
			int TotalNumberOfRequest = 0;
			int currentPageNo = 1;
			boolean initFlag = true;
			Gson g = new Gson();
			JsonObject response = null;
			getResponse(elasticSearchEndPoint + "/" , "", "PUT");
			String jsonResponse = getSqootResponse(currentPageNo);
			if (jsonResponse != null || !jsonResponse.equals("")) {
				if (jsonResponse.contains("total") && initFlag) {
					response = g.fromJson(jsonResponse, JsonObject.class);
					TotalNumberOfRequest = Integer.parseInt(response.get("query").getAsJsonObject().get("total").toString());
					initFlag = false;
				}
				while(TotalNumberOfRequest  > 0){
					if (jsonResponse != null || !jsonResponse.equals("")) {						
						response = g.fromJson(jsonResponse, JsonObject.class);
						TotalNumberOfRequest -= 10;
						updateElasticSearch(currentPageNo, response.get("deals").getAsJsonArray());
						currentPageNo++;
					} else {
						break;
					}
					jsonResponse = getSqootResponse(currentPageNo);
				}				
			}	
		}catch (Exception e){
			System.out.println(e);
		}
	}
 	
	private void updateElasticSearch(int currentPageNo, JsonArray JsonResponse) {
		
		String mappingString = "{\"properties\": "
				+ "{\"category_name\": {\"type\": \"string\"},"
				+ "\"category_slug\": {\"type\": \"string\"},"
				+ "\"commission\": {\"type\": \"double\"},"
				+ "\"created_at\": {\"type\": \"date\",\"format\": \"strict_date_optional_time||epoch_millis\"},"
				+ "\"description\": {\"type\": \"string\"},"
				+ "\"discount_amount\": {\"type\": \"double\"},"
				+ "\"discount_percentage\": {\"type\": \"double\"},"
				+ "\"expires_at\": {\"type\": \"date\",\"format\": "
				+ "\"strict_date_optional_time||epoch_millis\"},"
				+ "\"fine_print\": {\"type\": \"string\"},"
				+ "\"id\": {\"type\": \"long\"},"
				+ "\"image_url\": {\"type\": \"string\"},"
				+ "\"location\": {\"type\": \"geo_point\"},"
				+ "\"merchant\": {\"properties\": "
				+ "{\"address\": {\"type\": \"string\"},"
				+ "\"country\": {\"type\": \"string\"},"
				+ "\"country_code\": {\"type\": \"string\"},"
				+ "\"id\": {\"type\": \"long\"},"
				+ "\"locality\": {\"type\": \"string\"},"
				+ "\"name\": {\"type\": \"string\"},"
				+ "\"phone_number\": {\"type\": \"string\"},"
				+ "\"postal_code\": {\"type\": \"string\"},"
				+ "\"region\": {\"type\": \"string\"},"
				+ "\"url\": {\"type\": \"string\"}}},"
				+ "\"number_sold\": {\"type\": \"long\"},"
				+ "\"online\": {\"type\": \"boolean\"},"
				+ "\"price\": {\"type\": \"double\"},"
				+ "\"provider_name\": {\"type\": \"string\"},"
				+ "\"provider_slug\": {\"type\": \"string\"},"
				+ "\"short_title\": {\"type\": \"string\"},"
				+ "\"title\": {\"type\": \"string\"},"
				+ "\"untracked_url\": {\"type\": \"string\"},"
				+ "\"updated_at\": {\"type\": \"date\",\"format\": \"strict_date_optional_time||epoch_millis\"},"
				+ "\"url\": {\"type\": \"string\"},"
				+ "\"value\": {\"type\": \"double\"}}}";		
		String elasticSearchEndPoint_current = elasticSearchEndPoint + "/" + currentPageNo;
		
		getResponse(elasticSearchEndPoint + "/_mapping"  + "/" + currentPageNo , mappingString, "PUT");
		for (JsonElement deal : JsonResponse) {
			String dealId = deal.getAsJsonObject().get("deal").getAsJsonObject().get("id").toString();
			double lat = 0.0; 
			double lng = 0.0; 
			if(!deal.getAsJsonObject().get("deal").getAsJsonObject().get("merchant").getAsJsonObject().get("latitude").toString().equals("null")){
				lat = Double.parseDouble(deal.getAsJsonObject().get("deal").getAsJsonObject().get("merchant").getAsJsonObject().get("latitude").toString());				
			}
			if(!deal.getAsJsonObject().get("deal").getAsJsonObject().get("merchant").getAsJsonObject().get("longitude").toString().equals("null")){
				lng = Double.parseDouble(deal.getAsJsonObject().get("deal").getAsJsonObject().get("merchant").getAsJsonObject().get("longitude").toString());				
			}
			if(lat < -90){
				lat = -180 - lat;
				lng = lng + 180;
			} else if(lat > 90){
				lat = 180 - lat;
				lng = lng + 180;
			}
			deal.getAsJsonObject().get("deal").getAsJsonObject().get("merchant").getAsJsonObject().remove("latitude");
			deal.getAsJsonObject().get("deal").getAsJsonObject().get("merchant").getAsJsonObject().remove("longitude");
			JsonObject newobj=new JsonObject();
			newobj.addProperty("lat" , ""+lat+"");
			newobj.addProperty("lon" , ""+lng+"");			
			deal.getAsJsonObject().get("deal").getAsJsonObject().add("location", newobj);			
			String deaildata = deal.getAsJsonObject().get("deal").toString();
			System.out.println("Inserting Deal ID: " + dealId);
	        getResponse(elasticSearchEndPoint_current + "/" + dealId, deaildata, "POST");			
		}		
	}

	private String getSqootResponse(int currentPageNo) throws UnsupportedEncodingException{
		String url = sqootEndPoint + "?api_key=" + URLEncoder.encode(sqootApiKey, "UTF-8") +  "&page=" + currentPageNo;
        return getResponse(url, null, "GET");
	}

	public static String getResponse(String requestUrl, String requestParams, String type) {
		String Jsonresponse = "";
		String response = "";
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setUseCaches(false);
			httpConn.setDoInput(true);
			if (!type.equals("GET")) {
				httpConn.setDoOutput(true);
				httpConn.setRequestMethod(type);
				OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
				writer.write(requestParams);
				writer.flush();
			} 
			InputStream inputStream = httpConn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			String line = "";			
			while ((line = reader.readLine()) != null) {
				Jsonresponse += line;
			}
			reader.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			Gson gson = new Gson();
			if (!Jsonresponse.equals("")) {
				JsonObject output = gson.fromJson(Jsonresponse, JsonObject.class);
				response = output.toString();
			}
		}
		return response;
	}
}
