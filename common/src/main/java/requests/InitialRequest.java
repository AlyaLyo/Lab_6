package requests;

public class InitialRequest implements RequestContent {
    public int number;

    public InitialRequest() {
    }

    public InitialRequest(int number) {
        this.number = number;
    }
}
