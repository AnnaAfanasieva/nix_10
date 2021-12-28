package ua.com.alevel.controller;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.group.GroupRequestDto;
import ua.com.alevel.dto.group.GroupResponseDto;
import ua.com.alevel.facade.GroupFacade;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@Controller
@RequestMapping("/groups")
public class GroupController {

    private final GroupFacade groupFacade;
    private long new_id;

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

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        groupFacade.delete(id);
        return "redirect:/groups";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @Valid @Min(value = 1, message = "idiot!!!") @NotNull() Long id, Model model) {
        GroupResponseDto dto = groupFacade.findById(id);
        model.addAttribute("group", dto);
        return "pages/groups/group_details";
    }

    @GetMapping("/update/{id}")
    public String updateGroupPage(@PathVariable @Valid @Min(value = 1, message = "idiot!!!") @NotNull() Long id, @ModelAttribute("group") GroupRequestDto dto) {
        new_id = id;
        return "pages/groups/group_update";
    }

    @PostMapping("/update")
    public String updateGroup(@ModelAttribute("group") GroupRequestDto dto) {
        groupFacade.update(dto, new_id);
        return "redirect:/groups";
    }
}
