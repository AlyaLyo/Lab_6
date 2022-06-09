package Commands;

import java.util.ArrayList;
import java.util.List;

public class Arguments {

    public Class[] types;
    public Object[] arguments;

    public Arguments(){}

    public Arguments(Object[] arguments) {
        this.arguments = arguments;
        types = new Class[arguments.length];
        for (int i = 0; i < arguments.length; i++) {
            types[i] = arguments[i].getClass();
        }
    }

}
