package com.example.currencyaccountapi.nbp;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

public class NBPService {
    //TODO Cache exchange rate

    NBPCaller nbPcaller;

    public NBPService() {
        nbPcaller = new NBPCaller();
    }

    public BigDecimal getExchangeRate() throws InterruptedException, IOException, URISyntaxException {
        return nbPcaller.getRate();
    }

}
