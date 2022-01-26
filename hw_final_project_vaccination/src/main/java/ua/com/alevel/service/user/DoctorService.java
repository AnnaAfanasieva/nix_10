package ua.com.alevel.service.user;

import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.item.VaccinationPoint;
import ua.com.alevel.persistence.entity.user.Doctor;
import ua.com.alevel.service.BaseService;

import java.util.List;

public interface DoctorService extends BaseService<Doctor> {

    public DataTableResponse<Doctor> findAllByVaccinationPoint(DataTableRequest request, VaccinationPoint vaccinationPoint);
}
