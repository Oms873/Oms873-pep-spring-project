package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account register(Account account) {
        // Check if the username already exists
        if (accountRepository.findByUsername(account.getUsername()) != null) {
            return null; // Username already taken
        }

        // Save the new account
        return accountRepository.save(account);
    }

    public Account login(String username, String password) {
        // Find the account by username and password
        Account account = accountRepository.findByUsernameAndPassword(username, password);
        return account;
    }
}
