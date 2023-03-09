package com.tristan.order;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.tristan.order.po.Account;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class OrderManager {
    public Account getAccountFromService(int accountId){
        String url = "http://localhost:8081/account/select?id="+accountId;
        try {
            String result = HttpClientUtils.get(url);
            return new Account(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
