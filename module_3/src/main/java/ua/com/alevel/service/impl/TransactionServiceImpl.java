package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.AccountDao;
import ua.com.alevel.persistence.dao.TransactionDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Transaction;
import ua.com.alevel.service.TransactionService;
import ua.com.alevel.util.WebResponseUtil;

import java.util.Set;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionDao transactionDao;
    private final AccountDao accountDao;

    public TransactionServiceImpl(TransactionDao transactionDao, AccountDao accountDao) {
        this.transactionDao = transactionDao;
        this.accountDao = accountDao;
    }

    @Override
    public void create(Transaction entity) {
        transactionDao.create(entity);
    }

    @Override
    public void update(Transaction entity) {
        transactionDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        transactionDao.delete(id);
    }

    @Override
    public Transaction findById(Long id) {
        return transactionDao.findById(id);
    }

    @Override
    public DataTableResponse<Transaction> findAll(DataTableRequest request) {
        DataTableResponse<Transaction> dataTableResponse = transactionDao.findAll(request);
        long count = transactionDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }

    @Override
    public DataTableResponse<Transaction> findAllTransactionsByAccount(DataTableRequest request, Long accountId) {
        Account account = accountDao.findById(accountId);
        DataTableResponse<Transaction> dataTableResponse = transactionDao.findAllTransactionsByAccount(request, accountId, account);
        long count = transactionDao.findSetTransactionsByAccountId(accountId, account).size();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }

    @Override
    public Set<Transaction> findSetTransactionsByAccountId(Long accountId) {
        Account account = accountDao.findById(accountId);
        return transactionDao.findSetTransactionsByAccountId(accountId, account);
    }
}
