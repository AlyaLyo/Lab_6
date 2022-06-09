package client;

import Commands.Arguments;
import Commands.Information;
import Exceptions.InputException;
import IO.FileScan;
import IO.Printable;
import IO.Scannable;
import client.io.ClientPrint;
import client.io.ClientScan;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import deserializes.ResponseDeserializer;
import requests.CommandExecuteRequest;
import requests.Request;
import requests.RequestContent;
import responses.Response;

public class Client {

    private ClientScan clientScan;
    private ClientPrint clientPrint;
    private static final int arraySize = 65536;

    public Client(ClientScan clientScan, ClientPrint clientPrint) {
        this.clientScan = clientScan;
        this.clientPrint = clientPrint;
    }

    public String serializeRequest(Request request) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.writeValueAsString(request);
    }

    public Response deserializeResponse(String responseString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new SimpleModule().addDeserializer(Response.class, new ResponseDeserializer()));
        Response response = objectMapper.readValue(responseString, Response.class);

        return response;
    }

    public void request(String line, Scannable scannable, Printable printable, boolean isConsole) throws Exception {
        ClientCommandsManager clientCommandsManager = new ClientCommandsManager();
        if (line.isEmpty())
            throw new InputException("There is no command");

        if (line.trim().split(" ")[0].equals("exit")) {
            System.exit(0);
        }
        if (line.trim().split(" ")[0].equals("execute_script")){
            FileScan fileScan = new FileScan(line.trim().split(" ")[1]);
            while (fileScan.hasNextLine())
                this.request(fileScan.scanString(), fileScan, printable, false);
            return;
        }

        String requestString = serializeRequest(clientCommandsManager.getCommandInfoRequest(clientCommandsManager.getCommandName(line)));
        clientPrint.println(requestString);

        String responseString = receive();
        Response response = deserializeResponse(responseString);

        if (response.error){
            throw new InputException(response.errorMessage);
        }

        Information information = (Information) response.content;

        Arguments arguments = clientCommandsManager.scanArguments(information, printable, scannable, line, isConsole);

        CommandExecuteRequest commandExecuteRequest = new CommandExecuteRequest();
        commandExecuteRequest.arguments = arguments;
        commandExecuteRequest.commandName = line.trim().split(" ")[0];
        commandExecuteRequest.information = information;

        Request request = new Request(commandExecuteRequest);

        requestString = serializeRequest(request);

        clientPrint.println(requestString);

        responseString = receive();
        response = deserializeResponse(responseString);

        System.out.println(response.content);
    }

    public String receive() throws Exception {
        return clientScan.scanString();
    }

}
