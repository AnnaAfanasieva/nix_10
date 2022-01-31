package ua.com.alevel.view.dto.request;

import ua.com.alevel.persistence.entity.item.VaccinationPoint;

public class RecordNewRequestDto extends RecordRequestDto {

    private VaccinationPoint vaccinationPoint;

    public VaccinationPoint getVaccinationPoint() {
        return vaccinationPoint;
    }

    public void setVaccinationPoint(VaccinationPoint vaccinationPoint) {
        this.vaccinationPoint = vaccinationPoint;
    }
}
