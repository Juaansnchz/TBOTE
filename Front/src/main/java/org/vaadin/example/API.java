package org.vaadin.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class API {



    private static final String urlPrefix1 = "http://localhost:8090/registro";
    private static final String urlPrefix2 = "http://localhost:8090/login";
    private static final String urlPrefix3 = "http://localhost:8090/newpasswd";
    private static final String urlPrefix4 = "http://localhost:8090/versaldo";

    public String createUsuario(String dni, String passwd, String respuesta) throws URISyntaxException, IOException, InterruptedException {

        Map<String, String> userData = new HashMap<>();
        userData.put("dni", dni);
        userData.put("passwd", passwd);
        userData.put("respuesta", respuesta);
        String jsonBody = new ObjectMapper().writeValueAsString(userData);

        HttpRequest request = HttpRequest.newBuilder() // Builder de la request
                .uri(new URI(urlPrefix1))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response);

        return response.toString();
    }
    public String updateUsuario(String DNI, String new_passwd, String respuesta) throws URISyntaxException, IOException, InterruptedException {
        Map<String, String> userData = new HashMap<>();
        userData.put("dni", DNI);
        userData.put("passwd", new_passwd);
        userData.put("respuesta", respuesta);
        String jsonBody = new ObjectMapper().writeValueAsString(userData);

        HttpRequest request = HttpRequest.newBuilder() // Builder de la request
                .uri(new URI(urlPrefix3))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response);

        return response.toString();
    }
    public String getUsuario(String DNI) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder() // Builder de la request
                .uri(new URI(urlPrefix1))
                .GET()
                .build();

        HttpResponse<String> response = HttpClient // Respuesta de la URL
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public String login(String dni, String passwd) throws URISyntaxException, IOException, InterruptedException {

        Map<String, String> userData = new HashMap<>();
        userData.put("dni", dni);
        userData.put("passwd", passwd);
        String jsonBody = new ObjectMapper().writeValueAsString(userData);

        HttpRequest request = HttpRequest.newBuilder() // Builder de la request
                .uri(new URI(urlPrefix2))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<String> response = HttpClient
                .newBuilder()
                .build()
                .send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response);

        return response.toString();

    }

    public String saldo(String dni) {
        try {
            // Crear el cuerpo de la solicitud JSON
            Map<String, String> userData = new HashMap<>();
            userData.put("dni", dni);
            String jsonBody = new ObjectMapper().writeValueAsString(userData);

            // Configurar la solicitud HTTP
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(urlPrefix4))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            // Enviar la solicitud HTTP y obtener la respuesta
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // Imprimir la respuesta en la consola (opcional)
            System.out.println(response);

            // Retornar la respuesta como cadena
            return response.body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            e.printStackTrace(); // Manejo básico de excepciones, imprime la traza en consola
            return "Error en la solicitud: " + e.getMessage();
        }
    }

}