package ua.com.alevel.view.controller;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.StudentFacade;
import ua.com.alevel.facade.SubjectFacade;
import ua.com.alevel.persistence.entity.Subject;
import ua.com.alevel.view.dto.request.StudentRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.StudentResponseDto;
import ua.com.alevel.view.dto.response.SubjectResponseDto;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/students")
public class StudentController extends BaseController {

    private Long update_id = 0L;

    private final StudentFacade studentFacade;
    private final SubjectFacade subjectFacade;
    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("name", "name", "name"),
            new HeaderName("age", "age", "age"),
            new HeaderName("details", null, null),
            new HeaderName("delete", null, null)
    };

    public StudentController(StudentFacade studentFacade, SubjectFacade subjectFacade) {
        this.studentFacade = studentFacade;
        this.subjectFacade = subjectFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<StudentResponseDto> response = studentFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/students/all");
        model.addAttribute("createNew", "null");
        model.addAttribute("cardHeader", "All Students");
        return "pages/students/students_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "students");
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @Valid @Min(value = 1, message = "invalid id") @NotNull() Long id, Model model) {
        model.addAttribute("student", studentFacade.findById(id));
        model.addAttribute("subjects", studentFacade.findAllSubjectsByStudentId(id).stream().toList());
        return "pages/students/student_details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        studentFacade.delete(id);
        return "redirect:/students";
    }

    @GetMapping("/update/{id}")
    public String updateStudentPage(@PathVariable @Valid @Min(value = 1, message = "invalid id") @NotNull() Long id, @ModelAttribute("student") StudentRequestDto dto) {
        update_id = id;
        return "pages/students/student_update";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute("student") StudentRequestDto dto) {
        Set<Subject> subjects = studentFacade.findById(update_id).getSubjects();
        dto.setSubjects(subjects);
        studentFacade.update(dto, update_id);
        return "redirect:/students/details/" + update_id;
    }

    @GetMapping("/add/{id}")
    public String addSubjectToStudentPage(@PathVariable @Valid @Min(value = 1, message = "invalid id") @NotNull() Long id, @ModelAttribute("student") StudentRequestDto dto, Model model, WebRequest request) {
        update_id = id;
        model.addAttribute("subjectsList", subjectFacade.findAll(request));
        return "pages/students/student_add_subject";
    }

    @PostMapping("/add")
    public String addSubjectToStudent(@ModelAttribute("student") StudentRequestDto dto) {
        StudentResponseDto studentResponseDto = studentFacade.findById(update_id);
        dto.setName(studentResponseDto.getName());
        dto.setAge(studentResponseDto.getAge());
        Set<Subject> subjects = studentResponseDto.getSubjects();
        Subject subject = dto.getSubjects().stream().toList().get(0);
        boolean isExistingSubject = false;
        for (Subject subjectItem : subjects) {
            if (subjectItem.getName().equals(subject.getName())) {
                isExistingSubject = true;
            }
        }
        if (!isExistingSubject) {
            subjects.add(subject);
            dto.setSubjects(subjects);
            studentFacade.update(dto, update_id);
        }
        return "redirect:/students/details/" + update_id;
    }

    @GetMapping("/new/{id}")
    public String redirectToNewStudentPage(Model model, @PathVariable Long id) {
        StudentRequestDto student = new StudentRequestDto();
        Set<Subject> subjects = new HashSet<>();
        SubjectResponseDto subjectResponseDto = subjectFacade.findById(id);
        if (subjectResponseDto != null) {
            Subject subject = new Subject();
            subject.setId(subjectResponseDto.getId());
            subject.setCreated(subjectResponseDto.getCreated());
            subject.setUpdated(subjectResponseDto.getUpdated());
            subject.setName(subjectResponseDto.getName());
            subjects.add(subject);
        } else {
            throw new RuntimeException("invalid subject");
        }
        student.setSubjects(subjects);
        model.addAttribute("student", student);
        return "pages/students/student_new";
    }

    @PostMapping("/new")
    public String createNewStudent(@ModelAttribute("subject") StudentRequestDto dto) {
        studentFacade.create(dto);
        String url = "redirect:/students";
        return url;
    }
}
