package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.TransactionFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Category;
import ua.com.alevel.persistence.entity.Transaction;
import ua.com.alevel.service.AccountService;
import ua.com.alevel.service.CategoryService;
import ua.com.alevel.service.TransactionService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.TransactionRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.TransactionResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionFacadeImpl implements TransactionFacade {

    private final TransactionService transactionService;
    private final AccountService accountService;
    private final CategoryService categoryService;

    public TransactionFacadeImpl(TransactionService transactionService, AccountService accountService, CategoryService categoryService) {
        this.transactionService = transactionService;
        this.accountService = accountService;
        this.categoryService = categoryService;
    }

    @Override
    public void create(TransactionRequestDto transactionRequestDto) {
        Account account = accountService.findById(transactionRequestDto.getAccount_id());
        Category category = categoryService.findById(transactionRequestDto.getCategory_id());
        Transaction transaction = createTransactionEntity(transactionRequestDto, new Transaction(), account, category);
        transactionService.create(transaction);
    }

    @Override
    public void update(TransactionRequestDto transactionRequestDto, Long id) {
        Account account = accountService.findById(transactionRequestDto.getAccount_id());
        Category category = categoryService.findById(transactionRequestDto.getCategory_id());
        Transaction transaction = transactionService.findById(id);
        if (transaction != null && account != null && category != null) {
            transaction = createTransactionEntity(transactionRequestDto, transaction, account, category);
            transactionService.update(transaction);
        }
    }

    @Override
    public void delete(Long id) {
        transactionService.delete(id);
    }

    @Override
    public TransactionResponseDto findById(Long id) {
        return new TransactionResponseDto(transactionService.findById(id));
    }

    @Override
    public PageData<TransactionResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Transaction> tableResponse = transactionService.findAll(dataTableRequest);

        List<TransactionResponseDto> transactions = tableResponse.getItems().stream().
                map(TransactionResponseDto::new).
                collect(Collectors.toList());

        PageData<TransactionResponseDto> pageData = (PageData<TransactionResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(transactions);

        return pageData;
    }

    private Transaction createTransactionEntity(
            TransactionRequestDto transactionRequestDto,
            Transaction transaction,
            Account account,
            Category category) {
        transaction.setAccount(account);
        transaction.setCategory(category);
        transaction.setSum(transactionRequestDto.getSum());
        return transaction;
    }
}
