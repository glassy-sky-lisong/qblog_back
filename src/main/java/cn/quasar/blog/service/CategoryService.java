package cn.quasar.blog.service;

import cn.quasar.blog.domain.Category;
import cn.quasar.blog.dto.MessageResult;
import cn.quasar.blog.dto.MessageStatus;
import cn.quasar.blog.exception.CustomException;
import cn.quasar.blog.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public MessageResult queryAllCategory() {
        List<Category> categories = categoryMapper.queryCategories();

        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), categories, "");
    }

    public MessageResult queryCategoryByName(String categoryName) {
        if ("".equals(categoryName)) {
            throw new CustomException("category名字不能为空");
        }
        Category category = categoryMapper.selectCategoryByName(categoryName);
        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), category, "");
    }

    public MessageResult queryCategoryById(int categoryId) {
        if (categoryId == 0) {
            throw new CustomException("categoryId不能为空");
        }
        Category category = categoryMapper.selectCategoryById(categoryId);

        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), category, "");
    }

    public void addCategory(Category category) {
        if ("".equals(category.getCategoryName())) {
            throw new CustomException("请提供分类名称");
        } else {
            String name = category.getCategoryName();
            Category result = categoryMapper.selectCategoryByName(name);
            if (result == null) {
                categoryMapper.addCategory(category);
            }
        }
    }

    public void updateCategoryByName(Category condition,String categoryName) {
        if (!"".equals(categoryName)) {
            categoryMapper.updateCategoryByName(condition, categoryName);
        }
    }

    public void updateCategoryById(Category condition, int categoryId) {
        if (categoryId != 0) {
            categoryMapper.updateCategoryById(condition, categoryId);
        }
    }

    public void deleteCategoryByName(String categoryName) {
        categoryMapper.deleteCategoryByName(categoryName);
    }

    public void deleteCategoryById(int categoryId) {
        categoryMapper.deleteCategoryById(categoryId);
    }
}
