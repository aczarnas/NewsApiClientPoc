import java.util.HashMap;
import java.util.Map;

public class SimpleArgParser implements IArgParser {
    @Override
    public Map<String, String> parseArgs(String[] args) {
        Map<String, String> parsed = new HashMap<>();
        for (String arg : args){
            var splitArg = arg.split("=", 2);
            if (splitArg.length != 2) {
                throw new IllegalArgumentException("Wrong format of argument in args");
            }
            parsed.put(splitArg[0], splitArg[1]);
        }
        return parsed;
    }
}
