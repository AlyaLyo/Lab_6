package requests;

public class Request {

    public Class<? extends RequestContent> type;
    public RequestContent content;

    public Request() {

    }

    public Request (RequestContent content) {
        this.content = content;
        this.type = content.getClass();
    }

}
