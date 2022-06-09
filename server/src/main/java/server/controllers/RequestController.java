package server.controllers;

import Commands.CommandsManager;
import Commands.Information;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import requests.CommandExecuteRequest;
import requests.CommandInfoRequest;
import requests.InitialRequest;
import requests.Request;
import responses.Response;
import deserializes.RequestDeserializer;
import server.ServerCommandsManager;
import server.nio.SelfPrint;
import server.nio.ServerNIO;

public class RequestController {

    private ServerNIO nio;
    private final ServerCommandsManager serverCommandsManager;

    public RequestController(ServerNIO nio, ServerCommandsManager serverCommandsManager) {
        this.nio = nio;
        this.serverCommandsManager = serverCommandsManager;
    }

    public void handleRequest () throws Exception {
        String requestString = nio.scanString();

//        System.out.println(requestString);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new SimpleModule().addDeserializer(Request.class, new RequestDeserializer()));

        Request request = objectMapper.readValue(requestString, Request.class);

        if (request.type.equals(CommandInfoRequest.class)) {
            Response response = handleCommandInfoRequest((CommandInfoRequest) request.content);
            String line = objectMapper.writeValueAsString(response);
            nio.println(line);
        }

        if (request.type.equals(CommandExecuteRequest.class)){
            Response response = handleExecuteCommandRequest((CommandExecuteRequest) request.content);
            String line = objectMapper.writeValueAsString(response);
            nio.println(line);
        }

        if (request.type.equals(InitialRequest.class)){
            Response response = handleInitialRequest((InitialRequest) request.content);
            String line = objectMapper.writeValueAsString(response);
            nio.println(line);
        }
    }

    public Response handleCommandInfoRequest (CommandInfoRequest request) {
        Response response = new Response();

        Information information;
        try {
            information = serverCommandsManager.info(request);
        } catch (Exception exception) {
            response.error = true;
            response.errorMessage = "Wrong command";
            return response;
        }
        response = new Response(information);
        return response;
    }

    public Response handleExecuteCommandRequest(CommandExecuteRequest commandExecuteRequest) throws Exception {
        SelfPrint selfPrint = new SelfPrint();
        try {
            serverCommandsManager.commandConstructor(commandExecuteRequest.commandName, commandExecuteRequest.arguments, commandExecuteRequest.information, selfPrint).execute();
        } catch (Exception e){
            selfPrint.println(e.getMessage());
        }

        Response response = new Response(selfPrint.getString());
        return response;
    }

    public Response handleInitialRequest(InitialRequest initialRequest){
        Response response = new Response(initialRequest.number);
        return response;
    }

}
