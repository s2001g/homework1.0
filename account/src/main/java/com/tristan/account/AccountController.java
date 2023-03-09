package com.tristan.account;
import com.tristan.po.Account;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
public class AccountController {
    @RequestMapping("/create")
    public Account create() throws IOException {
        AccountService service = new AccountService();

        return service.createAccount();
    }

    @RequestMapping("/select")
    public Account select(Integer id) throws IOException {
        AccountService service = new AccountService();

        return service.selectAccount(id);
    }
}
