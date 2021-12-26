package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.JpaConfig;
import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StudentDaoImpl implements StudentDao {

    private final JpaConfig jpaConfig;

    public StudentDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    private final static String CREATE_STUDENT_QUERY = "insert into students values (default,?,?,?,?,?)";
    private final static String UPDATE_STUDENT_QUERY = "update students set updated = ?, name = ?, age = ?, groupId = ? where id = ";
    private final static String DELETE_STUDENT_BY_ID_QUERY = "delete from students where id = ";
    private final static String DELETE_STUDENT_BY_GROUP_QUERY = "delete from students where group_id = ";
    private final static String EXIST_STUDENT_BY_ID_QUERY = "select count(*) from students where id = ";
    private final static String FIND_STUDENT_BY_ID_QUERY = "select * from students as st join groups as gr on gr.id = st.group_id where st.id = ";
    private final static String FIND_ALL_STUDENTS_QUERY = "select * from students as st join groups as gr on gr.id = st.group_id";
    private final static String FIND_ALL_STUDENTS_BY_DEPARTMENT_QUERY = "select * from students as st join groups as gr on gr.id = st.group_id where st.group_id = ";

    @Override
    public void create(Student entity) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_STUDENT_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setString(3, entity.getName());
            preparedStatement.setInt(4, entity.getAge());
            preparedStatement.setLong(5, entity.getGroup().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Student entity) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(UPDATE_STUDENT_QUERY + entity.getId())) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setInt(3, entity.getAge());
            preparedStatement.setLong(4, entity.getGroup().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_STUDENT_BY_ID_QUERY + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean existById(Long id) {
        int count = 0;
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(EXIST_STUDENT_BY_ID_QUERY + id)) {
            if (resultSet.next()) {
                count = resultSet.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count == 1;
    }

    @Override
    public Student findById(Long id) {
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_STUDENT_BY_ID_QUERY + id)) {
            if (resultSet.next()) {
                return convertResultSetToStudent(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_STUDENTS_QUERY)) {
            while (resultSet.next()) {
                students.add(convertResultSetToStudent(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public void deleteAllByGroupId(Long groupId) {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_STUDENT_BY_GROUP_QUERY + groupId)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> findAllByGroupId(Long groupId) {
        List<Student> students = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_STUDENTS_BY_DEPARTMENT_QUERY + groupId)) {
            while (resultSet.next()) {
                students.add(convertResultSetToStudent(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    private Student convertResultSetToStudent(ResultSet resultSet) throws SQLException {
        Long studentId = resultSet.getLong("st.id");
        Timestamp employeeCreated = resultSet.getTimestamp("st.created");
        Timestamp employeeUpdated = resultSet.getTimestamp("st.updated");
        String studentName = resultSet.getString("st.name");
        int age = resultSet.getInt("age");

        Long groupId = resultSet.getLong("gr.id");
        Timestamp groupCreated = resultSet.getTimestamp("gr.created");
        Timestamp groupUpdated = resultSet.getTimestamp("gr.updated");
        String groupName = resultSet.getString("gr.name");

        Group group = new Group();
        group.setId(groupId);
        group.setCreated(new Date(groupCreated.getTime()));
        group.setUpdated(new Date(groupUpdated.getTime()));
        group.setName(groupName);

        Student student = new Student();
        student.setId(studentId);
        student.setCreated(new Date(employeeCreated.getTime()));
        student.setUpdated(new Date(employeeUpdated.getTime()));
        student.setName(studentName);
        student.setAge(age);
        student.setGroup(group);
        return student;
    }
}
