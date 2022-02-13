import com.google.gson.Gson;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Main {
    static final IArgParser parser = new SimpleArgParser();

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        var parsedArgs = parser.parseArgs(args);
        var hasKey = parsedArgs.containsKey("--key");
        var hasFileName = parsedArgs.containsKey("--file");
        if (!hasKey || !hasFileName) {
            System.out.println("Wrong call arguments, please use '--key=<key> --file=<file name>' in program call.");
            return;
        }
        var key = parsedArgs.get("--key");
        var fileName = parsedArgs.get("--file");
        var params = Map.of("country", "pl", "category", "business", "pageSize", "100");
        var client = new ApiClient(key);
        String apiUri = "https://newsapi.org/v2/top-headlines";
        var responseBody = client.sendRequest(apiUri, params);

        Gson gson = new Gson();
        List<Article> outList = gson.fromJson(responseBody, Response.class).getArticles();

        StringBuilder sb = new StringBuilder();
        for (var art : outList) {
            sb.append(art.toString()).append("\n");
        }

        Files.writeString(Paths.get(fileName), sb.toString());
    }
}
