package com.interview.accounts.controller;

import com.interview.accounts.model.GetAccountsResponseBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@SqlGroup({
//        @Sql(scripts = "/schema-h2.sql"),
//        @Sql("/data-h2.sql")
//})
class AccountsControllerTest {

    @LocalServerPort
    private int port;


    private String BASE_URL = "http://localhost";

    private static RestTemplate restTemplate;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp() {
        BASE_URL = BASE_URL.concat(":").concat(port+"").concat("/accounts");

    }

    @Test
    @Sql(scripts = "/data-h2.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void testAllAccounts() {
        final ResponseEntity<GetAccountsResponseBody> response = restTemplate.getForEntity(BASE_URL, GetAccountsResponseBody.class);

        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertEquals(54, response.getBody().getTotal());
    }
}