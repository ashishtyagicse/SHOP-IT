package com.example.rest;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

//http://api.sqoot.com/v2/deals?api_key=c8zpjy&location=40.6936235,-73.98609259999999
@Path("/mywebservice")
public class Hello {
	
	@GET
	@Path("/login")
	//@Produces(MediaType.APPLICATION_JSON)
	public String login( @QueryParam("email") String email,@QueryParam("password") String password,@QueryParam("isfacebook") boolean isfacebook)
	{
		DBManager databaseObject=null;
		boolean result=false;
		
		try
		{
			databaseObject=DBManager.getInstance();
			if(isfacebook==false)
			{
				result=databaseObject.authenticateWebsiteUser(email, password);
			}
			else
			{
				result=databaseObject.authenticateFacebookUser(email);
			}
			
		  
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage().toString());
		}
		
		 return result+"";
	}
	
	
	
	@GET
	@Path("/signup")
	//@Produces(MediaType.APPLICATION_JSON)
	public String signup( @QueryParam("email") String email,@QueryParam("password") String password,@QueryParam("username") String username)
	{
		DBManager databaseObject=null;
		String result="";
		try
		{
			databaseObject=DBManager.getInstance();
			int userexist=databaseObject.checkUserExistence(email);
			if(userexist>0)
			{
				System.out.println("user already exist");
				result="user already exist";
			}
			else
			{
				databaseObject.insert(email, password, username);
				result="user successfully created";
			}
			
		  
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage().toString());
		}
		
		 return result;
	}
	
	
	
	
	
	@GET
	@Path("/getdealsbycategory")
	//@Produces(MediaType.APPLICATION_JSON)
	public String getDealsByCategory( @QueryParam("location") String inputlocation,@QueryParam("category") String category,@QueryParam("radius") String radius)
	{
		String output = null;
		JsonArray categorydealsarray=null;
		try
		{
			ClientConfig config = new ClientConfig();
		    Client client = ClientBuilder.newClient(config);
		    WebTarget target = client.target(getBaseURI());
		    output= target.path("deals").queryParam("category_slugs", category).queryParam("location", inputlocation).
		    		queryParam("radius", radius).
		              request().accept(MediaType.APPLICATION_JSON).get(String.class);
		    JsonParser parser = new JsonParser();
			JsonObject  categorydeals = parser.parse(output).getAsJsonObject();
			categorydealsarray = categorydeals.get("deals").getAsJsonArray();
			
			//JsonObject mainobject= alldealsarray.get(0).getAsJsonObject().get("deal").getAsJsonObject();
			
		   
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage().toString());
		}
		return categorydealsarray.toString();
		//return output;
		
	}
	
	
	@GET
	@Path("/getdealbyid")
	//@Produces(MediaType.APPLICATION_JSON)
	
	public String getDealById( @QueryParam("id") String dealid)
	{//, @Context Request request
//		String requestobject=null;
//		return requestobject;
		String output = null;
		JsonObject dealobject=null;
		try
		{
			ClientConfig config = new ClientConfig();
		    Client client = ClientBuilder.newClient(config);
		    WebTarget target = client.target(getBaseURI());
		    output= target.path("deals").path(dealid).
		              request().accept(MediaType.APPLICATION_JSON).get(String.class);
		    JsonParser parser = new JsonParser();
			JsonObject  mydealjson = parser.parse(output).getAsJsonObject();
			dealobject = mydealjson.get("deal").getAsJsonObject();
			//JsonObject mainobject= alldealsarray.get(0).getAsJsonObject().get("deal").getAsJsonObject();
			
		   
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage().toString());
		}
		return dealobject.toString();
		
	}
	
	
	
	@GET
	@Path("/getdeals")
	//@Produces(MediaType.APPLICATION_JSON)
	public String getDeals( @QueryParam("location") String inputlocation,@QueryParam("radius") String radius)
	{
		String output = null;
		JsonArray alldealsarray=null;
		try
		{
			ClientConfig config = new ClientConfig();
		    Client client = ClientBuilder.newClient(config);
		    WebTarget target = client.target(getBaseURI());
		    output= target.path("deals").queryParam("location", inputlocation).
		    		queryParam("radius", radius).
		              request().accept(MediaType.APPLICATION_JSON).get(String.class);
//		    JsonParser parser = new JsonParser();
//			JsonObject  alldealjson = parser.parse(output).getAsJsonObject();
//			alldealsarray = alldealjson.get("deals").getAsJsonArray();
			//JsonObject mainobject= alldealsarray.get(0).getAsJsonObject().get("deal").getAsJsonObject();
			
		   
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage().toString());
		}
		//return alldealsarray.toString();
		return output;
		
	}

	
	@GET
	@Path("/responsehello")
	@Produces(MediaType.APPLICATION_JSON)
	  public String sayHtmlHello() {
		String output=null;
		JsonObject jsonobject1;
		JsonObject jsonobject2=new JsonObject();
		Gson gson =new Gson();
		try{
			ClientConfig config = new ClientConfig();

		    Client client = ClientBuilder.newClient(config);

		    WebTarget target = client.target(getBaseURI());
		    
		    
//		    String response=gson.toJson(target.
//		              request().accept(MediaType.TEXT_PLAIN).get().getEntity());
		    
//		    String response = target.
//		              request().
//		              accept(MediaType.APPLICATION_JSON_TYPE).get(Response.class).getEntity()
//		              .toString();
		    
		   output= target.path("deals").path("4089460").
		              request().accept(MediaType.APPLICATION_JSON).get(String.class);
		   JsonParser parser = new JsonParser();
		   jsonobject1 = parser.parse(output).getAsJsonObject();
		   jsonobject2=jsonobject1.get("deal").getAsJsonObject();
//		    jsonobject2=gson.fromJson("deal",String.class);
//		   output=jsonobject.get("title").toString();
		   // output=gson.toJson(jsonobject1) ;
		   
		    System.out.println(jsonobject2.get("merchant").getAsJsonObject().get("name").toString());

		}
		catch(Exception e)
		{
			System.out.println(e.getMessage().toString());
		}
		return jsonobject2.toString();
	  }
	
	
	  private static URI getBaseURI() {
		    return UriBuilder.fromUri("http://api.sqoot.com/v2?api_key=c8zpjy").build();
		  }

}
