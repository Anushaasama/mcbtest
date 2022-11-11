package com.interview.accounts.service;

import com.interview.accounts.domain.Account;
import com.interview.accounts.exception.ResourceNotFoundException;
import com.interview.accounts.mapper.AccountsMapper;
import com.interview.accounts.model.AccountDTO;
import com.interview.accounts.model.GetAccountsResponseBody;
import com.interview.accounts.repo.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;


import java.util.Optional;


@RequiredArgsConstructor
@Service
@Slf4j
public class AccountService {

    private final AccountRepository repository;

    public GetAccountsResponseBody getAccounts() {

        return new GetAccountsResponseBody(repository.count(), AccountsMapper.map(repository.findAll()));
    }

    public Page<Account> getAllAccounts(Pageable pageable) {
        return repository.findAll(pageable);

    }

    public Account getAccountByNumber(int number) {
        log.info("number: [{}]", number);
        Optional<Account> accountData = repository.findByNumber(number);
        log.info("getAccountByNumber: [{}]", accountData);
        if (!accountData.isPresent()) {
            throw new ResourceNotFoundException("Not found Account with number = " + number);
        }
        return accountData.get();
    }

    public Account getAccountByName(String name) {
        log.info("name: [{}]", name);
        Optional<Account> accountData = repository.findByName(name);
        log.info("getAccountByName: [{}]", accountData);
        if (!accountData.isPresent()) {
            throw new ResourceNotFoundException("Not found Account with name = " + name);
        }
        return accountData.get();
    }

    public Account putAccount(int id, AccountDTO account) {
        Optional<Account> accountData = repository.findById(id);
        log.info("putAccount: [{}]", accountData);
        if (accountData.isPresent()) {
            Account accountObj = accountData.get();
            accountObj.setNumber(account.getNumber());
            accountObj.setName(account.getName());
            accountObj.setBalance(account.getBalance());
            repository.save(accountObj);
            return accountObj;
        }else{
            throw new ResourceNotFoundException("Not found Account with name = " + account.getName());
        }
    }

    public Account postAccount(AccountDTO account) {
        Account accountObj = null;
        try {
            accountObj=new Account();
            accountObj.setNumber(account.getNumber());
            accountObj.setName(account.getName());
            accountObj.setBalance(account.getBalance());
            accountObj= repository.save(accountObj);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ResourceNotFoundException("unable to create new account at this time [{}]" + account);
        }
        return accountObj;
    }
}
