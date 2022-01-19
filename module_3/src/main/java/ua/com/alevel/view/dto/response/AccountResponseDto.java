package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.User;

public class AccountResponseDto extends ResponseDto {

    private User user;
    private Long balance;

    public AccountResponseDto() {
    }

    public AccountResponseDto(Account account) {
        super.setId(account.getId());
        super.setCreated(account.getCreated());
        super.setUpdated(account.getUpdated());
        this.user = account.getUser();
        this.balance = account.getBalance();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
