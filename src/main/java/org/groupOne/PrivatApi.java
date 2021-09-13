package org.groupOne;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PrivatApi implements Serializable {

    public static void main(String[] args) throws IOException, InterruptedException {
        BankResponse response = new BankResponse();
        PrivatApi api = new PrivatApi();
        api.getPrivatRates();
    }

    private static final String privat_url = "https://api.privatbank.ua/p24api/pubinfo?exchange&json&coursid=11";

    public BankResponse getPrivatRates() throws IOException, InterruptedException {
        getJsonFromPrivat();
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get("src/main/java/org/groupOne/PrivatRateArchive/privatRate" + java.time.LocalDate.now() + ".json")));
        System.out.println(json);

        BankResponse privatRates = gson.fromJson(json, BankResponse.class);
        for (int i = 0; i < privatRates.size(); i++) {
            if (!privatRates.get(i).getCcy().equals("USD")
                    && !privatRates.get(i).getCcy().equals("RUR")
                    && !privatRates.get(i).getCcy().equals("EUR")) {
                privatRates.remove(i); //removes BTC rate
            }
        }
        return privatRates; //returns ArrayList of BankResponse objects
    }

    public static void getJsonFromPrivat() throws IOException, InterruptedException {
        File file = new File("src/main/java/org/groupOne/PrivatRateArchive/privatRate" + java.time.LocalDate.now() + ".json");
        if (!file.exists()) {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(privat_url))    //creates file with current date if not exists
                    .GET()
                    .build();

            HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers
                    .ofFile(Path.of("src/main/java/org/groupOne/PrivatRateArchive/privatRate" + java.time.LocalDate.now() + ".json")));
        }
    }


}
