package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.group.GroupRequestDto;
import ua.com.alevel.dto.group.GroupResponseDto;
import ua.com.alevel.entity.Group;
import ua.com.alevel.facade.GroupFacade;
import ua.com.alevel.service.GroupService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupFacadeImpl implements GroupFacade {

    private final GroupService groupService;

    public GroupFacadeImpl(GroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    public void create(GroupRequestDto groupRequestDto) {
        Group group = new Group();
        group.setName(groupRequestDto.getName());
        groupService.create(group);
    }

    @Override
    public void update(GroupRequestDto groupRequestDto, Long id) {
        Group group = groupService.findById(id);
        if (group != null) {
            group.setName(groupRequestDto.getName());
            groupService.update(group);
        }
    }

    @Override
    public void delete(Long id) {
        groupService.delete(id);
    }

    @Override
    public GroupResponseDto findById(Long id) {
        return new GroupResponseDto(groupService.findById(id));
    }

    @Override
    public List<GroupResponseDto> findAll() {
        return groupService.findAll()
                .stream()
                .map(GroupResponseDto::new)
                .collect(Collectors.toList());
    }
}
