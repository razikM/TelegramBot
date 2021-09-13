package org.groupOne;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class NBU_API {

    private static final String URL_NAME = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(NbuUtill.getNbuCourses(URL_NAME));

    }

}

class NbuCourses {

    static private final String BANK_NAME = "НБУ";

    public static String getBankName() {
        return BANK_NAME;
    }

    private String cc;
    private BigDecimal rate;

    public String getCc() {
        return cc;
    }

    public void setCc(String сс) {
        this.cc = сс;
    }


    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "NbuCourses{" +
                "cc='" + cc + '\'' +
                ", rate=" + rate +
                '}';
    }
}

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
