package com.everisbootcamp.accountcommission.Constans;

public class Path {

    private static final String IPR = "localhost";
    private static final String PORT = "9500";

    private static final String MS_PATH = "/microservices";

    private static final String HTTP_CONSTANT = "http://";
    private static final String GATEWAY = IPR.concat(":").concat(PORT);

    public static final String ACCOUNT_PATH = HTTP_CONSTANT
        .concat(GATEWAY)
        .concat(MS_PATH)
        .concat("/accounts");
}
