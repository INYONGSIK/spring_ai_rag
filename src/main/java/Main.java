import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

public class Main {
    public static void main(String[] args) {

        OpenAIClient client = OpenAIOkHttpClient.builder()
                .apiKey("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")
                .build();

        ResponseCreateParams params = ResponseCreateParams.builder()
                .model("gpt-5.4")
                .input("Say this is a test")
                .build();

        Response response = client.responses().create(params);
        System.out.println(response.output());
    }
}