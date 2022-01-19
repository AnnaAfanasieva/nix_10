package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;

import java.util.Set;

public interface UserService extends BaseService<User> {

    Set<Account> findSetAccountsByUserId(Long userId);
}
