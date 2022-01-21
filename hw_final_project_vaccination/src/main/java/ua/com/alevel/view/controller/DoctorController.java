package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.facade.DoctorFacade;

@Controller
@RequestMapping("/doctors")
public class DoctorController extends BaseController {

    private long update_id;
    private final DoctorFacade doctorFacade;
    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("Прізвище", "surname", "surname"),
            new HeaderName("Ім'я", "name", "name"),
            new HeaderName("По-батькові", "patronymic", "patronymic"),
            new HeaderName("Локація", "balance", "balance"),
            new HeaderName("Деталі", null, null),
            new HeaderName("Видалити", null, null)
    };

    public DoctorController(DoctorFacade doctorFacade) {
        this.doctorFacade = doctorFacade;
    }
}
