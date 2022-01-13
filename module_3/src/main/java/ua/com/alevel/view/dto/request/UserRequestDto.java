package ua.com.alevel.view.dto.request;

public class UserRequestDto extends RequestDto {

    private String userName;
    private String email;
    //TODO под вопросом
//    private Set<Account> accounts;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
