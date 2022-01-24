package ua.com.alevel.persistence.entity.item;

import ua.com.alevel.persistence.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "record_times_table")
public class RecordTime extends BaseEntity {

    private String time;

    public RecordTime() {
        super();
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
