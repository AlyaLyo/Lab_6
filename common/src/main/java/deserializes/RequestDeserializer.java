package deserializes;

import Commands.Arguments;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import requests.Request;
import requests.RequestContent;

import java.io.IOException;
import java.util.Iterator;

public class RequestDeserializer extends StdDeserializer<Request> {

    public RequestDeserializer() {
        this(null);
    }

    public RequestDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Request deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
        JsonNode node = context.readTree(parser);
        Iterator<JsonNode> iterator = node.elements();
        String typeString = iterator.next().toString();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new SimpleModule().addDeserializer(Arguments.class, new ArgumentsDeserializer()));

        Class<? extends RequestContent> requestType = objectMapper.readValue(typeString,Class.class);
        String stringRequest = iterator.next().toString();
        RequestContent requestContent = objectMapper.readValue(stringRequest, requestType);

        Request request = new Request(requestContent);
        return request;
    }
}
