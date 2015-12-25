package com.cloudcomputing.models;

import com.bluelinelabs.logansquare.JsonMapper;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings("unsafe,unchecked")
public final class Deals$$JsonObjectMapper extends JsonMapper<Deals> {
  @Override
  public Deals parse(JsonParser jsonParser) throws IOException {
    Deals instance = new Deals();
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
  public void parseField(Deals instance, String fieldName, JsonParser jsonParser) throws IOException {
    if ("category_name".equals(fieldName)) {
      instance.setCategory_name(jsonParser.getValueAsString(null));
    } else if ("description".equals(fieldName)) {
      instance.setDescription(jsonParser.getValueAsString(null));
    } else if ("expires_at".equals(fieldName)) {
      instance.setExpires_at(jsonParser.getValueAsString(null));
    } else if ("image_url".equals(fieldName)) {
      instance.setImage_url(jsonParser.getValueAsString(null));
    } else if ("online".equals(fieldName)) {
      instance.setOnline(jsonParser.getValueAsBoolean());
    } else if ("price".equals(fieldName)) {
      instance.setPrice(jsonParser.getValueAsInt());
    } else if ("short_title".equals(fieldName)) {
      instance.setShort_title(jsonParser.getValueAsString(null));
    } else if ("title".equals(fieldName)) {
      instance.setTitle(jsonParser.getValueAsString(null));
    } else if ("untracked_url".equals(fieldName)) {
      instance.setUntracked_url(jsonParser.getValueAsString(null));
    } else if ("url".equals(fieldName)) {
      instance.setUrl(jsonParser.getValueAsString(null));
    } else if ("value".equals(fieldName)) {
      instance.setValue(jsonParser.getValueAsInt());
    }
  }

  @Override
  public void serialize(Deals object, JsonGenerator jsonGenerator, boolean writeStartAndEnd) throws IOException {
    if (writeStartAndEnd) {
      jsonGenerator.writeStartObject();
    }
    if (object.getCategory_name() != null) {
      jsonGenerator.writeStringField("category_name", object.getCategory_name());
    }
    if (object.getDescription() != null) {
      jsonGenerator.writeStringField("description", object.getDescription());
    }
    if (object.getExpires_at() != null) {
      jsonGenerator.writeStringField("expires_at", object.getExpires_at());
    }
    if (object.getImage_url() != null) {
      jsonGenerator.writeStringField("image_url", object.getImage_url());
    }
    jsonGenerator.writeBooleanField("online", object.isOnline());
    jsonGenerator.writeNumberField("price", object.getPrice());
    if (object.getShort_title() != null) {
      jsonGenerator.writeStringField("short_title", object.getShort_title());
    }
    if (object.getTitle() != null) {
      jsonGenerator.writeStringField("title", object.getTitle());
    }
    if (object.getUntracked_url() != null) {
      jsonGenerator.writeStringField("untracked_url", object.getUntracked_url());
    }
    if (object.getUrl() != null) {
      jsonGenerator.writeStringField("url", object.getUrl());
    }
    jsonGenerator.writeNumberField("value", object.getValue());
    if (writeStartAndEnd) {
      jsonGenerator.writeEndObject();
    }
  }
}
