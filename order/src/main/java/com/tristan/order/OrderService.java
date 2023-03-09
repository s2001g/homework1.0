package com.tristan.order;

import com.tristan.order.dao.AccountCache;
import com.tristan.order.dao.AccountDAO;
import com.tristan.order.po.Account;

import java.io.IOException;

public class OrderService {
    private boolean check(int accountId) throws IOException {
        AccountCache cache = new AccountCache();
        OrderManager manager = new OrderManager();
        String accountJson = cache.get(accountId);
        if(accountJson == null){
            AccountDAO dao = new AccountDAO();
            Account account = dao.selectAccountFromDB(accountId);
            if(account != null){
                cache.add(account);
            }else{
                manager.getAccountFromService(accountId);
            }
        }
        return false;
    }
    public String addOrder(int accountId) {
return  null;
    }
    public void addAccount(Account account){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

    }
}
