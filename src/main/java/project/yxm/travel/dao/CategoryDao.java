package project.yxm.travel.dao;

import java.util.List;

import project.yxm.travel.domain.Category;

public interface CategoryDao {
    public List<Category> findAll();
}
