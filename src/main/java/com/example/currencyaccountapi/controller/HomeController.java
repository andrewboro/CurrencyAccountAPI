package com.example.currencyaccountapi.controller;

import com.example.currencyaccountapi.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

@Controller
public class HomeController {

    AccountService accountService = new AccountService();

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/showCreateAccountForm")
    public String showCreateAccountForm() {
        return "create-account";
    }

    @PostMapping("processAccountCreationForm")
    public String processAccountCreationForm(HttpServletRequest request) {
        accountService.upsertAccount(request.getParameter("pesel"),
                request.getParameter("firstName"),
                request.getParameter("lastName"),
                new BigDecimal(request.getParameter("initialAmount")));
        return "confirm-create-account";
    }

    @GetMapping("/account")
    public @ResponseBody
    String getAccount(HttpServletRequest request) {
        String pesel = request.getParameter("pesel");
        return accountService.getAccountInfo(pesel);
    }

    @GetMapping("/listAccounts")
    public String listAccounts(HttpServletRequest request) {
        request.setAttribute("themeList", accountService.getAccounts().keySet());
        return "list-accounts";
    }


    @GetMapping("/showAccountInformation")
    public String processHelloMessageForm(HttpServletRequest request, Model model) {
        String pesel = request.getParameter("pesel");
        model.addAttribute("message", accountService.getAccountInfo(pesel));
        return "show-account";
    }

    @PostMapping("/exchangePlnToUsd")
    public String exchangePlnToUsd(HttpServletRequest request, Model model) throws InterruptedException, IOException, URISyntaxException {
        String pesel = request.getParameter("pesel");
        BigDecimal amount = new BigDecimal(request.getParameter("amount"));
        accountService.changeCurrencyFromPlnToUsd(pesel, amount);
        model.addAttribute("message", accountService.getAccountInfo(pesel));
        return "show-account";
    }

    @PostMapping("/exchangeUsdToPln")
    public String exchangeUsdToPln(HttpServletRequest request, Model model) throws InterruptedException, IOException, URISyntaxException {
        String pesel = request.getParameter("pesel");
        BigDecimal amount = new BigDecimal(request.getParameter("amount"));
        accountService.changeCurrencyFromUsdToPln(pesel, amount);
        model.addAttribute("message", accountService.getAccountInfo(pesel));
        return "show-account";
    }
}
