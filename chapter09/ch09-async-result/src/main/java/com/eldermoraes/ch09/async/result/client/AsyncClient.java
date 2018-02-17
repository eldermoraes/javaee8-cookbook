package com.eldermoraes.ch09.async.result.client;

import com.eldermoraes.ch09.async.result.User;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author eldermoraes
 */
public class AsyncClient {

    private static final WebTarget TARGET = ClientBuilder
            .newClient().target("http://localhost:8080/ch09-async-result/asyncResultService/asyncResult");

    public static void main(String[] args) {
        TARGET.request().async().get(new InvocationCallback<User>() {
            @Override
            public void completed(User rspns) {
                System.out.println("completed: " + rspns);
            }

            @Override
            public void failed(Throwable thrwbl) {
                System.err.println("failed: " + thrwbl.getMessage());
            }
        });
    }
}
