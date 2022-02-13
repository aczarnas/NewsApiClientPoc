import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    static final IArgParser parser = new SimpleArgParser();

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        var parsedArgs = parser.parseArgs(args);
        var hasK = parsedArgs.containsKey("-k");
        var hasKey = parsedArgs.containsKey("--key");
        String key;
        if (hasK) {
            key = parsedArgs.get("-k");
            System.out.println("Provided key: " + key);
        } else if (hasKey) {
            key = parsedArgs.get("--key");
            System.out.println("Provided key: " + key);
        } else {
            System.out.println("Key not provided in args, please use -k=<key> or --key=<key> in program call.");
            return;
        }
        var params = Map.of("country", "pl", "category", "business", "pageSize", "100");
        var client = new ApiClient(key);
        String apiUri = "https://newsapi.org/v2/top-headlines";
        var responseBody = client.sendRequest(apiUri, params);

        Gson gson = new Gson();
        List<Article> outList = gson.fromJson(responseBody, Response.class).getArticles();


        System.out.println(outList.size());
    }
}
