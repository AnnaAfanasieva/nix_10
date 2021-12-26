package ua.com.alevel.dto.group;

import ua.com.alevel.dto.ResponseDto;
import ua.com.alevel.entity.Group;

public class GroupResponseDto extends ResponseDto {

    private String name;

    public GroupResponseDto() {
    }

    public GroupResponseDto(Group group) {
        this.name = group.getName();
        super.setId(group.getId());
        super.setCreated(group.getCreated());
        super.setUpdated(group.getUpdated());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
