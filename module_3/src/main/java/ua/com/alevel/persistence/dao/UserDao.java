package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;

import java.util.Set;

public interface UserDao extends BaseDao<User> {

    Set<Account> findSetAccountsByUserId(Long userId);
}
