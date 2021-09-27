package com.everisbootcamp.accountcommission.Web;

import com.everisbootcamp.accountcommission.Constants.Path;

import org.springframework.web.reactive.function.client.WebClient;

public class Consumer {

    public static final WebClient webClientAccount = WebClient.create(Path.ACCOUNT_PATH);
}
