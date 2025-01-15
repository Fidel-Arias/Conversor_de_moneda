package Modelos;

import MyExceptions.MyException;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Server {
    private HttpClient client;
    private HttpRequest request;
    private HttpResponse<String> response;
    private String apiKey;

    public Server(String apiKey) {
        this.client = null;
        this.request = null;
        this.response = null;
        this.apiKey = apiKey;
    }

    public ExchangeRateApi consultaDeConversion(String url) {
        try {
            this.client = HttpClient.newHttpClient();
            this.request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Authorization", "Bearer " + this.apiKey)
                    .build();
            this.response = client.send(this.request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(this.response.body(), ExchangeRateApi.class);
        } catch (IOException | InterruptedException e) {
            throw new MyException("Error al realizar la consulta");
        }
    }
}
