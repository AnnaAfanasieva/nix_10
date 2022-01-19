package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Category;
import ua.com.alevel.persistence.entity.Transaction;

public class TransactionResponseDto extends ResponseDto {

    private Account account;
    private Category category;
    private Long sum;

    public TransactionResponseDto() {
    }

    public TransactionResponseDto(Transaction transaction) {
        super.setId(transaction.getId());
        super.setCreated(transaction.getCreated());
        super.setUpdated(transaction.getUpdated());
        this.account = transaction.getAccount();
        this.category = transaction.getCategory();
        this.sum = transaction.getSum();
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }
}
