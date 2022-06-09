package deserializes;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import requests.Request;
import requests.RequestContent;
import responses.Response;

import java.io.IOException;
import java.util.Iterator;

public class ResponseDeserializer extends StdDeserializer<Response> {

    public ResponseDeserializer() {
        this(null);
    }

    public ResponseDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Response deserialize(JsonParser parser, DeserializationContext context) throws IOException, JacksonException {
        JsonNode node = context.readTree(parser);
        Iterator<JsonNode> iterator = node.elements();
        String errorString = iterator.next().toString();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        boolean booleanError = objectMapper.readValue(errorString,boolean.class);

        String errorMessageString = iterator.next().toString();
        String errorMessage = objectMapper.readValue(errorMessageString,String.class);

        if (booleanError){
            Response response = new Response();
            response.error = true;
            response.errorMessage = errorMessage;
            return response;
        }
        String contentTypeString = iterator.next().toString();
        Class<?> contentType = objectMapper.readValue(contentTypeString, Class.class);

        String contentString = iterator.next().toString();
        Object content = objectMapper.readValue(contentString, contentType);

        Response response = new Response();
        response.error = booleanError;
        response.errorMessage = errorMessage;
        response.contentType = contentType;
        response.content = content;

        return response;
    }

}
