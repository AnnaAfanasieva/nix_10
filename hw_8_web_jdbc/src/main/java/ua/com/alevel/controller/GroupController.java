package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.group.GroupRequestDto;
import ua.com.alevel.dto.group.GroupResponseDto;
import ua.com.alevel.facade.GroupFacade;

import java.util.List;

@Validated
@Controller
@RequestMapping("/groups")
public class GroupController {

    private final GroupFacade groupFacade;

    public GroupController(GroupFacade groupFacade) {
        this.groupFacade = groupFacade;
    }

    @GetMapping
    public String findAll(Model model) {
        List<GroupResponseDto> groups = groupFacade.findAll();
        model.addAttribute("groups", groups);
        return "pages/groups/groups_all";
    }

    @GetMapping("/new")
    public String redirectToNewGroupPage(Model model) {
        model.addAttribute("group", new GroupRequestDto());
        return "pages/groups/group_new";
    }

    @PostMapping("/new")
    public String createNewGroup(@ModelAttribute("group") GroupRequestDto dto) {
        groupFacade.create(dto);
        return "redirect:/groups";
    }
//
//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable Long id) {
//        groupFacade.delete(id);
//        return "redirect:/groups";
//    }
}
