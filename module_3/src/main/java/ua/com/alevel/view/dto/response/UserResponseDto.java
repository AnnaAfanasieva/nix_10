package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.User;

public class UserResponseDto extends ResponseDto {

    private String userName;
    private String email;

    public UserResponseDto() {
    }

    public UserResponseDto(User user) {
        super.setId(user.getId());
        super.setCreated(user.getCreated());
        super.setUpdated(user.getUpdated());
        this.userName = user.getUserName();
        this.email = user.getEmail();
    }

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
