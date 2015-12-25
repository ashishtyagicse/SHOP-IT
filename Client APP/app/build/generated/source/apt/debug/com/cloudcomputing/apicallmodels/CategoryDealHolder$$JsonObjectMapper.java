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
public final class CategoryDealHolder$$JsonObjectMapper extends JsonMapper<CategoryDealHolder> {
  private static final JsonMapper<Deals> COM_CLOUDCOMPUTING_MODELS_DEALS__JSONOBJECTMAPPER = LoganSquare.mapperFor(Deals.class);

  @Override
  public CategoryDealHolder parse(JsonParser jsonParser) throws IOException {
    CategoryDealHolder instance = new CategoryDealHolder();
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
  public void parseField(CategoryDealHolder instance, String fieldName, JsonParser jsonParser) throws IOException {
    if ("deal".equals(fieldName)) {
      instance.deal = COM_CLOUDCOMPUTING_MODELS_DEALS__JSONOBJECTMAPPER.parse(jsonParser);
    }
  }

  @Override
  public void serialize(CategoryDealHolder object, JsonGenerator jsonGenerator, boolean writeStartAndEnd) throws IOException {
    if (writeStartAndEnd) {
      jsonGenerator.writeStartObject();
    }
    if (object.getDeal() != null) {
      jsonGenerator.writeFieldName("deal");
      COM_CLOUDCOMPUTING_MODELS_DEALS__JSONOBJECTMAPPER.serialize(object.getDeal(), jsonGenerator, true);
    }
    if (writeStartAndEnd) {
      jsonGenerator.writeEndObject();
    }
  }
}
