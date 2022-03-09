package cn.quasar.blog.service;

import cn.quasar.blog.domain.Categories;
import cn.quasar.blog.domain.Category;
import cn.quasar.blog.dto.MessageResult;
import cn.quasar.blog.dto.MessageStatus;
import cn.quasar.blog.exception.CustomException;
import cn.quasar.blog.mapper.CategoryMapper;
import cn.quasar.blog.utils.AssertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    final int CATEGORY_ZERO_ID = 9999;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private AssertUtils utils;

    public void handleAffectRow(int affectRow, String thinZero, String equalZero) {

        utils
                .customExceptionHandler(affectRow < 0 && !thinZero.equals(""), thinZero)
                .customExceptionHandler(affectRow == 0 && !equalZero.equals(""), equalZero);
    }

    public List<Category> queryAllCategory() {
        List<Category> categories = categoryMapper.queryCategories();

        return categories;
    }


    public Category queryCategoryByName(String categoryName) {
        utils.customExceptionHandler(categoryName == null, "category名字不能为空");

        return categoryMapper.selectCategoryByName(categoryName);
    }

    public Category queryCategoryById(int categoryId) {
        utils.customExceptionHandler(categoryId == 0, "categoryId不能为空");

        return categoryMapper.selectCategoryById(categoryId);
    }

    public String addCategory(String category) {
        utils.customExceptionHandler(category == null, "请提供分类名称");

        Category res = categoryMapper.selectCategoryByName(category);
        if (res == null) {
            Category data = new Category();
            data.setCategoryName(category);
            int i = categoryMapper.addCategory(data);

            handleAffectRow(i, "分类添加失败", "分类添加失败");

            return "添加成功";
        } else {
            return "分类已存在";
        }
    }

    public String updateCategoryByName(Category condition,String categoryName) {

        utils
                .customExceptionHandler(categoryName == null, "缺少参数: categoryName")
                .customExceptionHandler(condition == null, "缺少更新字段：condition");

        Category category = categoryMapper.selectCategoryByName(categoryName);

        utils.customExceptionHandler(category == null, "更新分类不存在");

        int i = categoryMapper.updateCategoryByName(condition, categoryName);

        handleAffectRow(i, "分类更新失败", "分类未更新");

        return "更新成功";
    }

    public String updateCategoryById(Category condition, int categoryId) {
        utils.customExceptionHandler(condition == null, "更新条件不可为空值");

        if (categoryId != 0) {
            categoryMapper.updateCategoryById(condition, categoryId);

            return "更新成功";
        } else {
            return "未更新";
        }
    }

    public void deleteCategoryByName(String categoryName) {
        categoryMapper.deleteCategoryByName(categoryName);
    }

    public void deleteCategoryById(int categoryId) {
        categoryMapper.deleteCategoryById(categoryId);
    }
}
