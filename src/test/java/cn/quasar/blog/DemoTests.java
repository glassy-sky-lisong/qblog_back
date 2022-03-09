package cn.quasar.blog;

import cn.quasar.blog.domain.Article;
import cn.quasar.blog.domain.Categories;
import cn.quasar.blog.domain.Category;
import cn.quasar.blog.mapper.ArticleMapper;
import cn.quasar.blog.mapper.CategoriesMapper;
import cn.quasar.blog.mapper.CategoryMapper;
import cn.quasar.blog.service.ArticleService;
import cn.quasar.blog.service.CategoriesService;
import cn.quasar.blog.service.CategoryService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DemoTests {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoriesMapper categoriesMapper;

    @Autowired
    private CategoriesService categoriesService;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleService articleService;

//    ------------------  category   --------------------------
//    @Test
//    @Order(1)
//    void getAllcategory() {
//        for (Category category : categoryService.queryAllCategory()) {
//            System.out.println(category);
//            assert category != null;
//        }
//
//    }
//
//    @Test
//    @Order(2)
//    void queryCategoryById() {
//        Category category = categoryService.queryCategoryById(9999);
//
//        System.out.println(category);
//        assert category.getCategoryName().equals("");
//    }
//
//    @Test
//    @Order(3)
//    void queryCategoryByName() {
//        Category category = categoryService.queryCategoryByName("");
//
//        System.out.println(category);
//        assert category.getId() == 9999;
//    }
//
//    @Test
//    @Order(4)
//    void addCategory() {
//        String s = categoryService.addCategory("测试分类");
//
//        assert s.indexOf("成功") != -1;
//        Category name = categoryService.queryCategoryByName("测试分类");
//        System.out.println(name);
//
//        String s1 = categoryService.addCategory("");
//
//        assert s1.indexOf("存在") != -1;
//    }
//
//    @Test
//    @Order(5)
//    void deleteCategoryByName() {
//        categoryService.deleteCategoryByName("测试分类");
//    }
//
////    ------------------------    categories      ------------------------------------
//
//    @Test
//    @Order(6)
//    void getAllCategories() {
//        List<Categories> list = categoriesService.getAllCategories();
//
//        System.out.println(list.size());
//    }
//
//    @Test
//    @Order(7)
//    void getCategoriesByCategoryId() {
//        for (Categories categories : categoriesService.getCategoriesByCategoryId(9999)) {
//            System.out.println(categories);
//        }
//
//    }
//
//    @Test
//    @Order(8)
//    void getCategoriesByArticleId() {
//        for (Categories categories : categoriesService.getCategoriesByArticleId(26)) {
//            System.out.println(categories);
//        }
//
//    }
//
//    @Test
//    @Order(9)
//    void addCategories() {
//        String s = categoriesService.addCategories(26, "测试分类");
//        assert s.indexOf("成功") != -1;
//
//    }
//
//    @Test
//    @Order(10)
//    void deleteCategoriesByArticleIdAndCategoryId() {
//        String s = categoriesService.deleteCategoriesByArticleIdAndCategoryId(26, 10007);
//
//        assert s.indexOf("成功") != -1;
//    }
//
//    @Test
//    @Order(11)
//    void updateCategoriesById() {
//        Categories categories = new Categories();
//        categories.setCategoryId(5);
//        categoriesService.updateCategoriesById(categories,36);
//    }
//
//
////    ----------------------     article      -------------------------------------
//
//    @Test
//    @Order(12)
//    @Rollback(false)
//    void addArticle() {
//        Article article = new Article();
//        article.setArticleName("1");
//        article.setContent("babalba");
//        article.setDescription("fasfasfaf");
//        article.setAuthorId(1);
//        article.setAuthorName("admin");
//        article.setArticleStatus(1);
//        String s = articleService.addArticle(article);
//
//        assert s.indexOf("成功") != -1;
//    }
//
//    @Test
//    @Order(13)
//    void selectArticleByArticleName() {
//        Article article = articleService.selectArticleByArticleName("1");
//
//        System.out.println(article);
//    }
//
//    @Test
//    @Order(14)
//    void updateArticle() {
//        Article article = new Article();
//        article.setCategory("Vue教程,        it教程, ");
//        String s = articleService.editArticleById(article, 58);
//
//        System.out.println(s);
//    }
}
