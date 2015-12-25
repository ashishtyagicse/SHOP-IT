package com.cloudcomputing.apicallmodels;

import com.bluelinelabs.logansquare.JsonMapper;
import com.bluelinelabs.logansquare.LoganSquare;
import com.cloudcomputing.models.Hits;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings("unsafe,unchecked")
public final class Test$$JsonObjectMapper extends JsonMapper<Test> {
  private static final JsonMapper<Hits> COM_CLOUDCOMPUTING_MODELS_HITS__JSONOBJECTMAPPER = LoganSquare.mapperFor(Hits.class);

  @Override
  public Test parse(JsonParser jsonParser) throws IOException {
    Test instance = new Test();
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
  public void parseField(Test instance, String fieldName, JsonParser jsonParser) throws IOException {
    if ("hits".equals(fieldName)) {
      instance.hits = COM_CLOUDCOMPUTING_MODELS_HITS__JSONOBJECTMAPPER.parse(jsonParser);
    }
  }

  @Override
  public void serialize(Test object, JsonGenerator jsonGenerator, boolean writeStartAndEnd) throws IOException {
    if (writeStartAndEnd) {
      jsonGenerator.writeStartObject();
    }
    if (object.getHits() != null) {
      jsonGenerator.writeFieldName("hits");
      COM_CLOUDCOMPUTING_MODELS_HITS__JSONOBJECTMAPPER.serialize(object.getHits(), jsonGenerator, true);
    }
    if (writeStartAndEnd) {
      jsonGenerator.writeEndObject();
    }
  }
}
