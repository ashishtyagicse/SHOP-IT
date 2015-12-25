package com.cloudcomputing.apicallmodels;

import com.bluelinelabs.logansquare.JsonMapper;
import com.bluelinelabs.logansquare.LoganSquare;
import com.cloudcomputing.models.Deals;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings("unsafe,unchecked")
public final class DealHolder$$JsonObjectMapper extends JsonMapper<DealHolder> {
  private static final JsonMapper<Deals> COM_CLOUDCOMPUTING_MODELS_DEALS__JSONOBJECTMAPPER = LoganSquare.mapperFor(Deals.class);

  @Override
  public DealHolder parse(JsonParser jsonParser) throws IOException {
    DealHolder instance = new DealHolder();
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
  public void parseField(DealHolder instance, String fieldName, JsonParser jsonParser) throws IOException {
    if ("_source".equals(fieldName)) {
      instance._source = COM_CLOUDCOMPUTING_MODELS_DEALS__JSONOBJECTMAPPER.parse(jsonParser);
    } else if ("_type".equals(fieldName)) {
      instance._type = jsonParser.getValueAsString(null);
    }
  }

  @Override
  public void serialize(DealHolder object, JsonGenerator jsonGenerator, boolean writeStartAndEnd) throws IOException {
    if (writeStartAndEnd) {
      jsonGenerator.writeStartObject();
    }
    if (object._source != null) {
      jsonGenerator.writeFieldName("_source");
      COM_CLOUDCOMPUTING_MODELS_DEALS__JSONOBJECTMAPPER.serialize(object._source, jsonGenerator, true);
    }
    if (object.get_type() != null) {
      jsonGenerator.writeStringField("_type", object.get_type());
    }
    if (writeStartAndEnd) {
      jsonGenerator.writeEndObject();
    }
  }
}
