package org.groupOne.service.button;


import org.groupOne.BankResponse;
import org.groupOne.facade.Facade;
import org.groupOne.mono.MonoAPI;
import org.groupOne.nbu_api.NBU_API;
import org.groupOne.privat_api.PrivatApi;
import org.groupOne.service.button.enum_button.ButtonName;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

import static org.groupOne.service.button.enum_button.ButtonName.NBU;

public class GetMessageInfo {
    private List<BankResponse> responses;
    private Facade facade = new Facade(new PrivatApi(),new NBU_API(),new MonoAPI());
    private String[] precisionArray = {"#0", "#0.0", "#0.00", "#0.000", "#0.0000"};

    public String getMessageInfo(ButtonName bank, Settings settings) {
        String message;
        String precision;
        Optional<BankResponse> bankResponseOptional;
        precision = precisionArray[settings.getPrecision()];

        try {
            responses = facade.getInfo(bank);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (responses == null) {
            return "Ошибка запроса в " + bank.getName() + ". Повторите попытку позже или выберете другой банк";
        }

        message = "Курс  " + bank.getName() + ":\n";

        if (!settings.isCheckUSD() && !settings.isCheckEUR() && !settings.isCheckRUR()) {
            settings.setCheckUSD(true);
        }
        if (settings.isCheckUSD()) {
            bankResponseOptional = responses.stream().filter(x -> (x.getCurrency().equals("USD"))).findFirst();
            message += createStringMessage(bankResponseOptional, bank, precision);
        }
        if (settings.isCheckEUR()) {
            bankResponseOptional = responses.stream().filter(x -> (x.getCurrency().equals("EUR"))).findFirst();
            message += createStringMessage(bankResponseOptional, bank, precision);
        }
        if (settings.isCheckRUR()) {
            bankResponseOptional = responses.stream().filter(x -> (x.getCurrency().equals("RUR")) || x.getCurrency().equals("RUB")).findFirst();
            message += createStringMessage(bankResponseOptional, bank, precision);
        }
        return message;
    }

    private String createStringMessage(Optional<BankResponse> bankResponse, ButtonName bank, String precision) {
        String priceBuy;
        String priceSale;
        DecimalFormat format = new DecimalFormat(precision);
        priceBuy = format.format(bankResponse.get().getBuyRate());
        priceSale = format.format(bankResponse.get().getSellRate());
        return bankResponse.get().getCurrency() + "/UAH" + "\n" +
                "Покупка: " + priceBuy + "\n" +
                (bank == NBU ? "\n" : "Продажа: " + priceSale + "\n\n");
    }
}
