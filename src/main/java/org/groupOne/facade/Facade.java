package org.groupOne.facade;

import org.groupOne.BankResponse;
import org.groupOne.Services.button_enum.ButtonName;
import org.groupOne.mono.MonoAPI;
import org.groupOne.nbu_api.NBU_API;
import org.groupOne.privat_api.PrivatApi;
import static org.groupOne.Services.button_enum.ButtonName.*;

import java.io.IOException;
import java.util.List;

public class Facade {

    private PrivatApi privatAPI;
    private NBU_API nbuAPI;
    private MonoAPI monoAPI;
//
    public Facade(PrivatApi privatAPI, NBU_API nbuAPI , MonoAPI monoAPI) {
        this.privatAPI = privatAPI;
        this.nbuAPI = nbuAPI;
        this.monoAPI = monoAPI;
    }

    public void setPrivatAPI(PrivatApi privatAPI) {
        this.privatAPI = privatAPI;
    }
    public void setNbuAPI(NBU_API nbuAPI) {
        this.nbuAPI = nbuAPI;
    }

    public void setMonoAPI(MonoAPI monoAPI) {
        this.monoAPI = monoAPI;
    }

    public List<BankResponse> getInfo(ButtonName bankName) throws IOException, InterruptedException {
        if(bankName.equals(PRIVAT_BANK)){
            return privatAPI.getPrivatRates();
        } else if (bankName.equals(NBU)){
            return nbuAPI.getInfo();
        } else if(bankName.equals(MONO_BANK)){
            return monoAPI.getInfo();
        }else {
            throw new RuntimeException("Unknown bank name");
        }
    }
}
