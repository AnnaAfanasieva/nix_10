package ua.com.alevel.controller;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.group.GroupResponseDto;
import ua.com.alevel.dto.student.StudentRequestDto;
import ua.com.alevel.dto.student.StudentResponseDto;
import ua.com.alevel.facade.GroupFacade;
import ua.com.alevel.facade.StudentFacade;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentFacade studentFacade;
    private final GroupFacade groupFacade;
    long update_id;

    public StudentController(StudentFacade studentFacade, GroupFacade groupFacade) {
        this.studentFacade = studentFacade;
        this.groupFacade = groupFacade;
    }

    @GetMapping
    public String findAll(Model model) {
        List<StudentResponseDto> students = studentFacade.findAll();
        model.addAttribute("students", students);
        return "pages/students/students_all";
    }

    @GetMapping("/groups/{id}")
    public String findAllByGroup(Model model, @PathVariable Long id) {
        List<StudentResponseDto> students = studentFacade.findAllByGroup(id);
        GroupResponseDto group = groupFacade.findById(id);
        model.addAttribute("students", students);
        model.addAttribute("group", group);
        return "pages/students/students_in_group";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @Valid @Min(value = 1, message = "invalid id") @NotNull() Long id, Model model) {
        StudentResponseDto dto = studentFacade.findById(id);
        model.addAttribute("student", dto);
        return "pages/students/student_details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        studentFacade.delete(id);
        return "redirect:/students";
    }

    @GetMapping("/update/{id}")
    public String updateStudentPage(@PathVariable @Valid @Min(value = 1, message = "invalid id") @NotNull() Long id, @ModelAttribute("student") StudentRequestDto dto, Model model) {
        update_id = id;
        List<GroupResponseDto> groups = groupFacade.findAll();
        model.addAttribute("groupsList", groups);
        return "pages/students/student_update";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute("student") StudentRequestDto dto) {
        studentFacade.update(dto, update_id);
        return "redirect:/students";
    }

    @GetMapping("/new/{id}")
    public String redirectToNewStudentPage(Model model, @PathVariable Long id) {
//        List<GroupResponseDto> groups = groupFacade.findAll();
//        model.addAttribute("groupsList", groups);
        StudentRequestDto student = new StudentRequestDto();
        student.setGroupId(id);
        model.addAttribute("student", student);
        return "pages/students/student_new";
    }

    @PostMapping("/new")
    public String createNewStudent(@ModelAttribute("group") StudentRequestDto dto) {
        studentFacade.create(dto);
        String url = "redirect:/students/groups/" + dto.getGroupId();
        return url;
    }
}
