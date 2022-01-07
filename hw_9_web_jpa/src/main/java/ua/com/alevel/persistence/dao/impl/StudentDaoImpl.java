package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.dao.StudentDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class StudentDaoImpl implements StudentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Student entity) {
        System.out.println(entity);
        entityManager.persist(entity);
//        createRelationGroupStudent(studentId, entity.getGroup().getId());
    }

    @Override
    public void update(Student entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        entityManager.createQuery("delete from Student where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public boolean existById(Long id) {
        Query query = entityManager.createQuery("select count(id) from Student where id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public DataTableResponse<Student> findAll(DataTableRequest request) {
        int fr = (request.getCurrentPage() - 1) * request.getPageSize();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> from = criteriaQuery.from(Student.class);
        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }
        List<Student> students = entityManager.createQuery(criteriaQuery)
                .setFirstResult(fr)
                .setMaxResults(request.getPageSize())
                .getResultList();
        DataTableResponse<Student> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(students);
        return dataTableResponse;
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery("select count(id) from Student");
        return (Long) query.getSingleResult();
    }

//    @Override
//    public long countByGroupId(Long groupId) {
//        Query query = entityManager.createQuery("select count(id) from Group where ");
//        return (Long) query.getSingleResult();
//    }
//
//    @Override
//    public void deleteAllByGroupId(Long groupId) {
//        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_STUDENT_BY_GROUP_QUERY + groupId + ")")) {
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

//    @Override
//    public DataTableResponse<Student> findAllStudentsByGroup(DataTableRequest request, Long groupId) {
//        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
//
////        String sqlFindWithParams = FIND_ALL_STUDENTS_BY_GROUP_QUERY +
////                groupId + " order by st." +
////                request.getSort() + " " +
////                request.getOrder() + " limit " +
////                limit + "," +
////                request.getPageSize();
////        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sqlFindWithParams)) {
////            while (resultSet.next()) {
////                students.add(convertResultSetToStudent(resultSet));
////            }
////        } catch (SQLException e) {
////            System.out.println("problem: = " + e.getMessage());
////        }
//        Set<Student> studentSet = findById()
//
//        DataTableResponse<Student> dataTableResponse = new DataTableResponse<>();
//        dataTableResponse.setItems(students);
//        return dataTableResponse;
//
//
////        List<Student> students = new ArrayList<>();
////        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
////        String sqlFindWithParams = FIND_ALL_STUDENTS_BY_GROUP_QUERY +
////                groupId + " order by st." +
////                request.getSort() + " " +
////                request.getOrder() + " limit " +
////                limit + "," +
////                request.getPageSize();
////        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sqlFindWithParams)) {
////            while (resultSet.next()) {
////                students.add(convertResultSetToStudent(resultSet));
////            }
////        } catch (SQLException e) {
////            System.out.println("problem: = " + e.getMessage());
////        }
////        DataTableResponse<Student> dataTableResponse = new DataTableResponse<>();
////        dataTableResponse.setItems(students);
////        return dataTableResponse;
//        return null;
//    }

//    @Override
//    public void createRelationGroupStudent(Long studentId, Long groupId) {
//        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_RELATION_GROUP_STUDENT_QUERY)) {
//            preparedStatement.setLong(1, studentId);
//            preparedStatement.setLong(2, groupId);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
