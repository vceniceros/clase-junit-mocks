package org.algo3.modelo.proveedor;

import org.algo3.modelo.Chiste;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ProveedorWeb implements Proveedor {
    private final String apiBaseUrl = "https://v2.jokeapi.dev/joke/";
    public Chiste solicitarChiste(String categoria, String idioma) {
        String url = String.format(apiBaseUrl+"%s?lang=%s&format=json&type=twopart",categoria,idioma);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonObject = new JSONObject(response.body());

            return new Chiste(jsonObject.getString("category"),jsonObject.getString("setup"),jsonObject.getString("delivery"));
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
