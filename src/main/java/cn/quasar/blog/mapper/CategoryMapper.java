package cn.quasar.blog.mapper;

import cn.quasar.blog.domain.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    List<Category> queryCategories();

    Category selectCategoryByName(String categoryName);

    Category selectCategoryById(int id);

    int addCategory(Category category);

    int updateCategoryById(Category category, int id);

    int updateCategoryByName(Category category, String name);

    int deleteCategoryById(int id);

    int deleteCategoryByName(String categoryName);

    int deleteAllCategory();
}
