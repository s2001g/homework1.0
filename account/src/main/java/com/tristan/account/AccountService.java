package com.tristan.account;

import com.tristan.dao.AccountDAO;
import com.tristan.po.Account;

import java.io.IOException;

public class AccountService {
    public Account createAccount() throws IOException {
        AccountDAO dao = new AccountDAO();
        Account record = AccountManager.readomAccount();
        AccountManager.rabbitMQProvider(record);
        return dao.createAccount(record);
    }

    public Account selectAccount(Integer id) throws IOException {
        AccountDAO dao = new AccountDAO();
        return dao.selectAccount(id);
    }
}
