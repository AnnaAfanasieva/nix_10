package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.StudentFacade;
import ua.com.alevel.facade.SubjectFacade;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.persistence.entity.Subject;
import ua.com.alevel.view.dto.request.SubjectRequestDto;
import ua.com.alevel.view.dto.response.SubjectResponseDto;

import java.util.Set;

@Controller
@RequestMapping("/subjects")
public class SubjectController extends BaseController{

    private long new_id;
    private final SubjectFacade subjectFacade;
    private final StudentFacade studentFacade;
    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("name", "name", "name"),
            new HeaderName("details", null, null),
            new HeaderName("delete", null, null)
    };

    public SubjectController(SubjectFacade subjectFacade, StudentFacade studentFacade) {
        this.subjectFacade = subjectFacade;
        this.studentFacade = studentFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        initDataTable(subjectFacade.findAll(request), columnNames, model);
        model.addAttribute("createUrl", "/subjects/all");
        model.addAttribute("createNew", "/subjects/new");
        model.addAttribute("cardHeader", "All Subjects");
        return "pages/subjects/subjects_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "subjects");
    }

    @GetMapping("/new")
    public String redirectToNewSubjectsPage(Model model) {
        model.addAttribute("subject", new SubjectRequestDto());
        return "pages/subjects/subject_new";
    }

    @PostMapping("/new")
    public String createNewSubjects(@ModelAttribute("subject") SubjectRequestDto dto) {
        subjectFacade.create(dto);
        return "redirect:/subjects";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        subjectFacade.delete(id);
        return "redirect:/subjects";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("subject", subjectFacade.findById(id));
        model.addAttribute("students", subjectFacade.findAllStudentsBySubjectId(id));
        return "pages/subjects/subject_details";
    }

    @GetMapping("/update/{id}")
    public String updateSubjectPage(@PathVariable Long id, @ModelAttribute("subject") SubjectRequestDto dto) {
        new_id = id;
        return "pages/subjects/subject_update";
    }

    @PostMapping("/update")
    public String updateGroup(@ModelAttribute("subject") SubjectRequestDto dto) {
        Set<Student> students = subjectFacade.findById(new_id).getStudents();
        dto.setStudents(students);
        subjectFacade.update(dto, new_id);
        return "redirect:/subjects";
    }

    @GetMapping("/add/{id}")
    public String addStudentToSubjectPage(@PathVariable Long id, @ModelAttribute("subject") SubjectRequestDto dto, Model model, WebRequest request) {
        new_id = id;
        model.addAttribute("studentsList", studentFacade.findAll(request));
        return "pages/subjects/subject_add_student";
    }

    @PostMapping("/add")
    public String addStudentToSubject(@ModelAttribute("subject") SubjectRequestDto dto) {
        SubjectResponseDto subjectResponseDto = subjectFacade.findById(new_id);
        dto.setName(subjectResponseDto.getName());
        Set<Student> students = subjectResponseDto.getStudents();
        Student student = dto.getStudents().stream().toList().get(0);

        boolean isExistingStudent = false;
        for (Student studentItem : students) {
            if (studentItem.getName().equals(student.getName())) {
                isExistingStudent = true;
            }
        }
        if (!isExistingStudent) {
            students.add(student);
            dto.setStudents(students);
            subjectFacade.update(dto, new_id);
        }
        return "redirect:/subjects/details/" + new_id;
    }
}
