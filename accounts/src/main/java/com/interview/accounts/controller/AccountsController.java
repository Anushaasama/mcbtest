package com.interview.accounts.controller;

import com.interview.accounts.domain.Account;
import com.interview.accounts.model.AccountDTO;
import com.interview.accounts.model.GetAccountsResponseBody;
import com.interview.accounts.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@Slf4j
public class AccountsController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<GetAccountsResponseBody> getAccounts() {
        return ResponseEntity.ok(accountService.getAccounts());
    }

    /*1.Create an endpoint to get all accounts. This point should also accept pagination parameters.*/
    @GetMapping("/get/pageable")
    public ResponseEntity<Page<Account>> getAllAccounts(Pageable pageable) {
        return ResponseEntity.ok(accountService.getAllAccounts(pageable));

    }

    /* 2.Create an endpoint to filter to get an Account either by account number or account name. */
    @GetMapping("/get/number/{number}")
    public ResponseEntity<Account> getAccountByNumber(@PathVariable int number) {
        Account accountObj = accountService.getAccountByNumber(number);
        return new ResponseEntity<>(accountObj, HttpStatus.OK);
    }

    @GetMapping("/get/name/{name}")
    public ResponseEntity<Account> getAccountByName(@PathVariable String name) {
        Account accountObj = accountService.getAccountByName(name);
        return new ResponseEntity<>(accountObj, HttpStatus.OK);
    }

    /* 3.Create an endpoint to create a new Account. */

    @PostMapping("/create")
    public ResponseEntity<Account> postAccount(@RequestBody AccountDTO account) {
        Account accountObj = accountService.postAccount(account);
        return new ResponseEntity<>(accountObj, HttpStatus.CREATED);
    }

    /* 4. Create an endpoint to update an existing Account */

    @PutMapping("/update/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") int id, @RequestBody AccountDTO account) {
        Account accountObj = accountService.putAccount(id, account);
        return new ResponseEntity<>(accountObj, HttpStatus.OK);
    }


}
