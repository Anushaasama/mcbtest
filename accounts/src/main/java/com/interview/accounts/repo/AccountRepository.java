package com.interview.accounts.repo;

import com.interview.accounts.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByNumber(int number);

    Optional<Account> findByName(String name);


}
