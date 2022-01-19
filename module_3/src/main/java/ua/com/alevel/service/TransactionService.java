package ua.com.alevel.service;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Transaction;

import java.util.Set;

public interface TransactionService extends BaseService<Transaction> {

    DataTableResponse<Transaction> findAllTransactionsByAccount(DataTableRequest request, Long accountId);
    Set<Transaction> findSetTransactionsByAccountId(Long accountId);
}
