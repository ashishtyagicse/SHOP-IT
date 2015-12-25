package com.cloudcomputing.apicallmodels;

import com.bluelinelabs.logansquare.JsonMapper;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings("unsafe,unchecked")
public final class DealQuery$$JsonObjectMapper extends JsonMapper<DealQuery> {
  @Override
  public DealQuery parse(JsonParser jsonParser) throws IOException {
    DealQuery instance = new DealQuery();
    if (jsonParser.getCurrentToken() == null) {
      jsonParser.nextToken();
    }
    if (jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      jsonParser.skipChildren();
      return null;
    }
    while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
      String fieldName = jsonParser.getCurrentName();
      jsonParser.nextToken();
      parseField(instance, fieldName, jsonParser);
      jsonParser.skipChildren();
    }
    return instance;
  }

  @Override
  public void parseField(DealQuery instance, String fieldName, JsonParser jsonParser) throws IOException {
    if ("categoty_slugs".equals(fieldName)) {
      instance.categoty_slugs = jsonParser.getValueAsString(null);
    } else if ("location".equals(fieldName)) {
      instance.location = jsonParser.getValueAsString(null);
    } else if ("page".equals(fieldName)) {
      instance.page = jsonParser.getValueAsString(null);
    } else if ("provider_slugs".equals(fieldName)) {
      instance.provider_slugs = jsonParser.getValueAsString(null);
    } else if ("query".equals(fieldName)) {
      instance.query = jsonParser.getValueAsString(null);
    } else if ("radius".equals(fieldName)) {
      instance.radius = jsonParser.getValueAsString(null);
    } else if ("updated_after".equals(fieldName)) {
      instance.updated_after = jsonParser.getValueAsString(null);
    }
  }

  @Override
  public void serialize(DealQuery object, JsonGenerator jsonGenerator, boolean writeStartAndEnd) throws IOException {
    if (writeStartAndEnd) {
      jsonGenerator.writeStartObject();
    }
    if (object.categoty_slugs != null) {
      jsonGenerator.writeStringField("categoty_slugs", object.categoty_slugs);
    }
    if (object.location != null) {
      jsonGenerator.writeStringField("location", object.location);
    }
    if (object.page != null) {
      jsonGenerator.writeStringField("page", object.page);
    }
    if (object.provider_slugs != null) {
      jsonGenerator.writeStringField("provider_slugs", object.provider_slugs);
    }
    if (object.query != null) {
      jsonGenerator.writeStringField("query", object.query);
    }
    if (object.radius != null) {
      jsonGenerator.writeStringField("radius", object.radius);
    }
    if (object.updated_after != null) {
      jsonGenerator.writeStringField("updated_after", object.updated_after);
    }
    if (writeStartAndEnd) {
      jsonGenerator.writeEndObject();
    }
  }
}
