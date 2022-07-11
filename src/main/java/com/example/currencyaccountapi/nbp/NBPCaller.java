package com.example.currencyaccountapi.nbp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public class NBPCaller {

    String url = "http://api.nbp.pl/api/exchangerates/rates/a/usd/";

    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
        NBPCaller nbPcaller = new NBPCaller();
        nbPcaller.callNBP();
    }

    public String callNBP() throws URISyntaxException, IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .timeout(Duration.of(5, SECONDS))
                .GET()
                .build();

        HttpClient client = HttpClient.newBuilder()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

        System.out.println(readRate(response.body()));

        return response.body();
    }

    public BigDecimal getRate() throws InterruptedException, IOException, URISyntaxException {
        String exchangeRateAsString = readRate(callNBP());
        return new BigDecimal(exchangeRateAsString);
    }

    private String readRate(final String json) throws JsonProcessingException {
        JsonNode parent = new ObjectMapper().readTree(json);
        return parent.path("rates").path(0).path("mid").asText();
    }
}
