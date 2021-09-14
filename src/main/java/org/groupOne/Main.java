package org.groupOne;

import org.groupOne.facade.Facade;
import org.groupOne.nbu_api.NBU_API;
import org.groupOne.privat_api.PrivatApi;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Facade facade = new Facade(new PrivatApi(), new NBU_API());
        System.out.println(facade.getInfo("Privat"));
        System.out.println(facade.getInfo("NBU"));
    }
}
