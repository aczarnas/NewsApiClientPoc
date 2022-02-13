import java.util.Map;

public interface IArgParser {
    Map<String, String> parseArgs(final String[] args);
}
