package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account register(Account account) throws Exception {
        if(account.getUsername() == null || account.getUsername().isEmpty()) {
            throw new Exception("Username cannot be blank");
        }
        if(account.getPassword() == null || account.getPassword().length() < 4) {
            throw new Exception("Password must be at least 4 characters long");
        }
        if(accountRepository.findByUsername(account.getUsername()).isPresent()) {
            throw new Exception("Username already exists");
        }
        return accountRepository.save(account);
    }

    public Optional<Account> login(String username, String password){
        Optional<Account> account = accountRepository.findByUsername(username);
        return account.filter(acc -> acc.getPassword().equals(password));
    }
}
