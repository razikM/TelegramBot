package org.groupOne.nbu_api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

class NbuUtill {
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static List<BankResponse> getNbuCourses(String url) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> send = HTTP_CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        List<NbuCourses> list = GSON.fromJson(send.body(), new TypeToken<List<NbuCourses>>() {
        }.getType());

        List<BankResponse> result = new ArrayList<>();
        for (NbuCourses l : list) {
            if (l.getCc().equals("EUR") || l.getCc().equals("USD") || l.getCc().equals("RUB")) {
                result.add(new BankResponse(l.getBankName(), l.getCc(), l.getRate(), l.getRate()));
            }
        }
        return result;
    }
}