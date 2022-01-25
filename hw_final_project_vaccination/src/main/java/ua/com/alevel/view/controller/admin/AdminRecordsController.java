package ua.com.alevel.view.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.com.alevel.facade.item.RecordFacade;
import ua.com.alevel.view.controller.BaseController;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.RecordResponseDto;

@Controller
@RequestMapping("/admin/records")
public class AdminRecordsController extends BaseController {

    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("№", null, null),
            new HeaderName("Прізвище", "surname", "surname"),
            new HeaderName("Ім'я", "name", "name"),
            new HeaderName("По батькові", "patronymic", "patronymic"),
            new HeaderName("Вакцина", "vaccine", "vaccine"),
            new HeaderName("Прізвище лікаря", "doctor_id", "doctor"),
            new HeaderName("Дата","vaccine_date","vaccineDate"),
            new HeaderName("Час","record_time_id","recordTime"),
            new HeaderName("Деталі", null, null),
            new HeaderName("Видалити", null, null)
    };

    private final RecordFacade recordFacade;

    public AdminRecordsController(RecordFacade recordFacade) {
        this.recordFacade = recordFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<RecordResponseDto> response = recordFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/records/all");
        model.addAttribute("createNew", "null");
        model.addAttribute("cardHeader", "Усі записи");
        return "pages/admin/records/records_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "admin/records");
    }
}
