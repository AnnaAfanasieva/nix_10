package ua.com.alevel.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.facade.RecordFacade;

@Controller
@RequestMapping("/records")
public class RecordController extends BaseController {

    //TODO дописать

    private long update_id;
    private final RecordFacade recordFacade;
    private final HeaderName[] columnNames = new HeaderName[]{
            new HeaderName("#", null, null),
            new HeaderName("Прізвище", "surname", "surname"),
            new HeaderName("Ім'я", "name", "name"),
            new HeaderName("По-батькові", "patronymic", "patronymic"),
            new HeaderName("Локація", "balance", "balance"),
            new HeaderName("Вакцина", "vaccine", "vaccine"),

            new HeaderName("User", "user_id", "user"),
            new HeaderName("Balance", "balance", "balance"),


            new HeaderName("Деталі", null, null),
            new HeaderName("Видалити", null, null)
    };

    public RecordController(RecordFacade recordFacade) {
        this.recordFacade = recordFacade;
    }
}
