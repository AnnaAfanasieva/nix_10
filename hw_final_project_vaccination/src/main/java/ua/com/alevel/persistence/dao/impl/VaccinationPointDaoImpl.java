package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.VaccinationPointDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.VaccinationPoint;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
@Transactional
public class VaccinationPointDaoImpl implements VaccinationPointDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(VaccinationPoint entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(VaccinationPoint entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        entityManager.createQuery("delete from VaccinationPoint where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public boolean existById(Long id) {
        Query query = entityManager.createQuery("select count(id) from VaccinationPoint where id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public VaccinationPoint findById(Long id) {
        return entityManager.find(VaccinationPoint.class, id);
    }

    @Override
    public DataTableResponse<VaccinationPoint> findAll(DataTableRequest request) {
        int fr = (request.getCurrentPage() - 1) * request.getPageSize();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<VaccinationPoint> criteriaQuery = criteriaBuilder.createQuery(VaccinationPoint.class);
        Root<VaccinationPoint> from = criteriaQuery.from(VaccinationPoint.class);
        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }
        List<VaccinationPoint> vaccinationPoints = entityManager.createQuery(criteriaQuery)
                .setFirstResult(fr)
                .setMaxResults(request.getPageSize())
                .getResultList();
        DataTableResponse<VaccinationPoint> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(vaccinationPoints);
        return dataTableResponse;
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery("select count(id) from VaccinationPoint ");
        return (Long) query.getSingleResult();
    }
}
