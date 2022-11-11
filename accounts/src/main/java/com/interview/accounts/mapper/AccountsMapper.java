package com.interview.accounts.mapper;

import com.interview.accounts.domain.Account;
import com.interview.accounts.model.AccountDTO;

import java.util.List;
import java.util.stream.Collectors;

public final class AccountsMapper {

    public static List<AccountDTO> map(List<Account> accounts) {
        return accounts.stream()
                .map(account -> new AccountDTO(account.getNumber(), account.getName(), account.getBalance()))
                .collect(Collectors.toList());
    }
}
