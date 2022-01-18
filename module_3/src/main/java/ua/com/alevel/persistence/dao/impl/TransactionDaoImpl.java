package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.config.JpaConfig;
import ua.com.alevel.persistence.dao.TransactionDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Account;
import ua.com.alevel.persistence.entity.Category;
import ua.com.alevel.persistence.entity.Transaction;
import ua.com.alevel.persistence.util.CategoryType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class TransactionDaoImpl implements TransactionDao {

    JpaConfig jpaConfig;

    TransactionDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    private static final String FIND_ALL_TRANSACTIONS_BY_ACCOUNT_ID = "select * from transactions_table tr join category_table ct on ct.id = tr.category_id where account_id = ";
    private static final String COUNT_CATEGORY_IN_TRANSACTIONS = "select count(*) from transactions_table where category_id = ";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Transaction entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Transaction entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(Long id) {
        entityManager.createQuery("delete from Transaction where id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public boolean existById(Long id) {
        Query query = entityManager.createQuery("select count(id) from Transaction where id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Transaction findById(Long id) {
        return entityManager.find(Transaction.class, id);
    }

    @Override
    public DataTableResponse<Transaction> findAll(DataTableRequest request) {
        int fr = (request.getCurrentPage() - 1) * request.getPageSize();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> criteriaQuery = criteriaBuilder.createQuery(Transaction.class);
        Root<Transaction> from = criteriaQuery.from(Transaction.class);
        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }
        List<Transaction> transactions = entityManager.createQuery(criteriaQuery)
                .setFirstResult(fr)
                .setMaxResults(request.getPageSize())
                .getResultList();
        DataTableResponse<Transaction> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(transactions);
        return dataTableResponse;
    }

    @Override
    public long count() {
        Query query = entityManager.createQuery("select count(id) from Transaction ");
        return (Long) query.getSingleResult();
    }

    @Override
    public void deleteAllByAccount(Account account) {
        entityManager.createQuery("delete from Transaction where account =:account")
                .setParameter("account", account)
                .executeUpdate();
    }

    @Override
    public boolean isExistCategoryInTransactions(Category category) {
        int count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(COUNT_CATEGORY_IN_TRANSACTIONS + category.getId())) {
            if (resultSet.next()) {
                count = resultSet.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count != 0;
    }

    @Override
    public DataTableResponse<Transaction> findAllTransactionsByAccount(DataTableRequest request, Long accountId, Account account) {
        List<Transaction> transactions = new ArrayList<>();
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
        String sortBy;
        if (request.getSort().equals("id") || request.getSort().equals("created") || request.getSort().equals("updated")){
            sortBy = "tr." + request.getSort();
        } else {
            sortBy = request.getSort();
        }
        String sqlRequest = FIND_ALL_TRANSACTIONS_BY_ACCOUNT_ID +
                accountId + " order by " +
                sortBy + " " +
                request.getOrder() + " limit " +
                limit + "," + request.getPageSize();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sqlRequest)) {
            while (resultSet.next()) {
                transactions.add(convertResultSetToTransactionEntity(resultSet, account));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DataTableResponse<Transaction> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(transactions);
        return dataTableResponse;
    }

    @Override
    public Set<Transaction> findSetTransactionsByAccountId(Long accountId, Account account) {
        Set<Transaction> transactions = new HashSet<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_TRANSACTIONS_BY_ACCOUNT_ID + accountId)) {
            while (resultSet.next()) {
                transactions.add(convertResultSetToTransactionEntity(resultSet, account));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    private Transaction convertResultSetToTransactionEntity(ResultSet resultSet, Account account) throws SQLException {
        Long transactionId = resultSet.getLong("tr.id");
        Timestamp transactionCreated = resultSet.getTimestamp("tr.created");
        Timestamp transactionUpdated = resultSet.getTimestamp("tr.updated");
        Long sum = resultSet.getLong("sum");

        Long categoryId = resultSet.getLong("category_id");
        Timestamp categoryCreated = resultSet.getTimestamp("ct.created");
        Timestamp categoryUpdated = resultSet.getTimestamp("ct.updated");
        String categoryName = resultSet.getString("category_name");
        int categoryTypeId = resultSet.getInt("category_type");
        CategoryType categoryType;
        if(categoryTypeId == 0) {
            categoryType = CategoryType.income;
        } else if (categoryTypeId == 1) {
            categoryType = CategoryType.expense;
        } else {
            throw new RuntimeException("wrong Category type");
        }

        Category category = new Category();
        category.setId(categoryId);
        category.setCreated(categoryCreated);
        category.setUpdated(categoryUpdated);
        category.setCategoryName(categoryName);
        category.setCategoryType(categoryType);

        Transaction transaction = new Transaction();
        transaction.setId(transactionId);
        transaction.setCreated(transactionCreated);
        transaction.setUpdated(transactionUpdated);
        transaction.setSum(sum);
        transaction.setAccount(account);
        transaction.setCategory(category);

        return transaction;
    }
}
