package com.example.currencyaccountapi.service;

import com.example.currencyaccountapi.model.Currency;
import com.example.currencyaccountapi.nbp.NBPService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


class AccountServiceTest {

    @Mock
    NBPService nbpServiceMock;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void changeCurrencyFromPlnToUsd() throws InterruptedException, IOException, URISyntaxException {
        assertNotNull(nbpServiceMock);
        when(nbpServiceMock.getExchangeRate()).thenReturn(BigDecimal.valueOf(2));

        AccountService accountService = new AccountService();
        //use the mocked nbpService
        accountService.nbpService = nbpServiceMock;
        accountService.upsertAccount("80112233444", "Jan", "Kowalski", new BigDecimal("100"));
        accountService.changeCurrencyFromPlnToUsd("80112233444", new BigDecimal("100"));

        assertEquals(50, accountService.accounts.get("80112233444").getCurrencies().get(Currency.USD).intValue());
    }

}