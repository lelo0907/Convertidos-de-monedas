import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BusquedaMoneda {
    public Currency buscarMoneda(String tipoDeMoneda){
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/cc2a2f2bd8552c33ad7e7cde/latest/"+tipoDeMoneda);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(),Currency.class);
        } catch (Exception e) {
            throw new RuntimeException("No existe la moneda");
           }

    }
    }

