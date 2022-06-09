package deserializes;

import Commands.Arguments;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.Iterator;

public class ArgumentsDeserializer extends StdDeserializer<Arguments> {
    public ArgumentsDeserializer() {
        this(null);
    }

    public ArgumentsDeserializer(Class vc) {
        super(vc);
    }

    @Override
    public Arguments deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode jsonNode = deserializationContext.readTree(jsonParser);
        Iterator<JsonNode> iterator = jsonNode.elements();
        String typesString = iterator.next().toString();
        Class<?>[] types = new ObjectMapper().readValue(typesString, Class[].class);

        if (types == null) {
            Arguments commandArguments = new Arguments();
            commandArguments.arguments = new Object[0];
            commandArguments.types = new Class[0];
            return commandArguments;
        }
        Iterator<JsonNode> argsIterator = iterator.next().elements();
        Object[] args = new Object[types.length];
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        for (int i = 0; i < types.length; i++) {
            String jsonArg = argsIterator.next().toString();
            args[i] = objectMapper.readValue(jsonArg, types[i]);
        }
        Arguments commandArguments = new Arguments();
        commandArguments.arguments = args;
        commandArguments.types = types;
        return commandArguments;
    }
}
