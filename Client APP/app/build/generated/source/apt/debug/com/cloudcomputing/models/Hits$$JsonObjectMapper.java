package com.cloudcomputing.models;

import com.bluelinelabs.logansquare.JsonMapper;
import com.bluelinelabs.logansquare.LoganSquare;
import com.cloudcomputing.apicallmodels.DealHolder;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unsafe,unchecked")
public final class Hits$$JsonObjectMapper extends JsonMapper<Hits> {
  private static final JsonMapper<DealHolder> COM_CLOUDCOMPUTING_APICALLMODELS_DEALHOLDER__JSONOBJECTMAPPER = LoganSquare.mapperFor(DealHolder.class);

  @Override
  public Hits parse(JsonParser jsonParser) throws IOException {
    Hits instance = new Hits();
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
  public void parseField(Hits instance, String fieldName, JsonParser jsonParser) throws IOException {
    if ("hits".equals(fieldName)) {
      if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
        ArrayList<DealHolder> collection1 = new ArrayList<DealHolder>();
        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
          DealHolder value1;
          value1 = COM_CLOUDCOMPUTING_APICALLMODELS_DEALHOLDER__JSONOBJECTMAPPER.parse(jsonParser);
          collection1.add(value1);
        }
        instance.hits = collection1;
      } else {
        instance.hits = null;
      }
    }
  }

  @Override
  public void serialize(Hits object, JsonGenerator jsonGenerator, boolean writeStartAndEnd) throws IOException {
    if (writeStartAndEnd) {
      jsonGenerator.writeStartObject();
    }
    final List<DealHolder> lslocalhits = object.hits;
    if (lslocalhits != null) {
      jsonGenerator.writeFieldName("hits");
      jsonGenerator.writeStartArray();
      for (DealHolder element1 : lslocalhits) {
        if (element1 != null) {
          COM_CLOUDCOMPUTING_APICALLMODELS_DEALHOLDER__JSONOBJECTMAPPER.serialize(element1, jsonGenerator, true);
        }
      }
      jsonGenerator.writeEndArray();
    }
    if (writeStartAndEnd) {
      jsonGenerator.writeEndObject();
    }
  }
}
