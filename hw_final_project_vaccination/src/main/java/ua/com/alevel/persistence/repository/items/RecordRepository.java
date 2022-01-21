package ua.com.alevel.persistence.repository.items;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.Record;
import ua.com.alevel.persistence.repository.BaseRepository;

@Repository
public interface RecordRepository extends BaseRepository<Record> {
}
