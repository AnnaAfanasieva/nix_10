package ua.com.alevel.dto.group;

import ua.com.alevel.dto.RequestDto;

public class GroupRequestDto extends RequestDto {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
