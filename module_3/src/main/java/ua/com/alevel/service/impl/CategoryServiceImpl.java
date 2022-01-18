package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.CategoryDao;
import ua.com.alevel.persistence.dao.TransactionDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Category;
import ua.com.alevel.service.CategoryService;
import ua.com.alevel.util.WebResponseUtil;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;
    private final TransactionDao transactionDao;

    public CategoryServiceImpl(CategoryDao categoryDao, TransactionDao transactionDao) {
        this.categoryDao = categoryDao;
        this.transactionDao = transactionDao;
    }

    @Override
    public void create(Category entity) {
        categoryDao.create(entity);
    }

    @Override
    public void update(Category entity) {
        categoryDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        Category category = categoryDao.findById(id);
        if(!transactionDao.isExistCategoryInTransactions(category)) {
            try {
                categoryDao.delete(id);
            } catch (Exception e) {
                System.out.println("Cannot delete category");
            }
        }
    }

    @Override
    public Category findById(Long id) {
        return categoryDao.findById(id);
    }

    @Override
    public DataTableResponse<Category> findAll(DataTableRequest request) {
        DataTableResponse<Category> dataTableResponse = categoryDao.findAll(request);
        long count = categoryDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }

    @Override
    public List<Category> findMapCategories() {
        return categoryDao.findMapCategories();
    }
}
