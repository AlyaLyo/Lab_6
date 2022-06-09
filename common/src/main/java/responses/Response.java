package responses;

public class Response {

    public boolean error = false;
    public String errorMessage;
    public Class<?> contentType;
    public Object content;

    public Response () {
    }

    public Response (Object content) {
        this.content = content;
        contentType = content.getClass();
    }

}
