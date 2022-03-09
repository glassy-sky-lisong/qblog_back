package cn.quasar.blog.mapper;

import cn.quasar.blog.domain.Categories;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoriesMapper {

    List<Categories> queryCategories();

    Categories selectCategoriesById(int id);

    List<Categories> selectCategoriesByArticleId(int articleId);

    List<Categories> selectCategoriesByCategoryId(int categoryId);

    Categories selectCategoriesByArticleIdAndCategoryId(int articleId, int categoryId);

    int addCategories(Categories categories);


    /**
     * 指定id更新categories中间表category_id,id
     *
     * @param categories
     * @param newId      新id， categories.id只作为条件，不作为更新字段
     * @return
     */
    int updateCategoriesById(Categories categories, int id);

    int updateCategoriesByArticleIdAndCategoryId(Categories categories, int articleId, int categoryId);

    int deleteCategoriesById(int id);

    int deleteCategoriesByArticleIdAndCategoryId(int articleId, int categoryId);

    int deleteAllCategories();

    int realDeleteCategoriesZeroValueByArticleId(int articleId);
}
