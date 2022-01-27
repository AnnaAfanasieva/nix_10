package ua.com.alevel.service.item;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.item.Record;
import ua.com.alevel.persistence.entity.item.VaccinationPoint;
import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.service.BaseService;

public interface RecordService extends BaseService<Record> {

    public DataTableResponse<Record> findAllByVaccinationPoint(DataTableRequest request, VaccinationPoint vaccinationPoint);
    DataTableResponse<Record> findAllByDoctor(DataTableRequest request, Doctor doctor);
}
