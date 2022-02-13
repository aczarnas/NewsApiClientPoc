import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class ApiClient {
    private final String apiKey;

    ApiClient(String key) {
        this.apiKey = key;
    }

    public void sendRequest(String apiUri, Map<String, String> params) throws URISyntaxException, IOException, InterruptedException {
        StringBuilder sb = new StringBuilder();
        sb.append(apiUri).append("?").append("apiKey=").append(apiKey);
        if (params.size() > 0) {
            for (var entry : params.entrySet()) {
                sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        System.out.println("Request complete uri: " + sb);
        var request = HttpRequest.newBuilder().uri(new URI(sb.toString())).GET().build();
        var response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}
