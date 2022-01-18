package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Category;
import ua.com.alevel.persistence.entity.Transaction;

import java.util.Set;

public interface TransactionDao extends BaseDao<Transaction> {

    void deleteAllByAccount(Account account);
    boolean isExistCategoryInTransactions(Category category);
    DataTableResponse<Transaction> findAllTransactionsByAccount(DataTableRequest request, Long accountId, Account account);
    Set<Transaction> findSetTransactionsByAccountId(Long accountId, Account account);
}
