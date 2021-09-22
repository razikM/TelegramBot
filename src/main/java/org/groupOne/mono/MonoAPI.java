package org.groupOne.mono;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.groupOne.BankResponse;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MonoAPI {

    private static final String URL_MONO = "https://api.monobank.ua/bank/currency";
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static int counter = 0;

    public static void main(String[] args) throws IOException, InterruptedException {
        getInfo();
    }

    public static List<BankResponse> getInfo() throws IOException, InterruptedException {
        getJsonFromMonoBank();

        String json = new String(Files.readAllBytes(Paths.get("src/main/java/org/groupOne/mono/MonoRateArchive/" + java.time.LocalDate.now() + " mono.json")));
        List<MonoBank> listFromBank = GSON.fromJson(json, new TypeToken<List<MonoBank>>() {
        }.getType());

        List<BankResponse> ListToTelegram = new ArrayList<>();
        for (MonoBank br : listFromBank) {
            if (br.getCurrency().equals("840") || br.getCurrency().equals("978") || br.getCurrency().equals("643")) {
                if (counter < 3) {
                    for (MonoBank bank : listFromBank) {
                        switch (br.getCurrency()) {
                            case "840":
                                bank.setCurrency("USD");
                                break;
                            case "978":
                                bank.setCurrency("EUR");
                                break;
                            case "643":
                                bank.setCurrency("RUB");
                                break;
                        }
                    }
                    ListToTelegram.add(new BankResponse(br.getBankName(), br.getCurrency(), br.getBuyRate(), br.getSellRate()));
                    counter++;
                }
            }
        }
        counter = 0;
        return ListToTelegram;
    }

    private static void getJsonFromMonoBank() throws IOException, InterruptedException {
        File file = new File("src/main/java/org/groupOne/mono/MonoRateArchive/" + java.time.LocalDate.now() + " mono.json");
        if (!file.exists()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL_MONO))
                    .GET()
                    .build();

            HttpResponse<Path> response = CLIENT.send(request, HttpResponse.BodyHandlers
                    .ofFile(Path.of("src/main/java/org/groupOne/mono/MonoRateArchive/" + java.time.LocalDate.now() + " mono.json")));
        }
    }
}
