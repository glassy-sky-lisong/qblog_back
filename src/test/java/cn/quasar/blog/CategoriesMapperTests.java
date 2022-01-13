package cn.quasar.blog;

import cn.quasar.blog.mapper.CategoriesMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoriesMapperTests {

    @Autowired
    private CategoriesMapper categoriesMapper;

//    @Test
//    @Order(1)
//    void queryCategories() {
//        List<Categories> categories = categoriesMapper.queryCategories();
//        assert categories.get(0).getId() != 0;
//        for (Categories categoriess :
//          categories) {
//            System.out.println(categoriess);
//        }
//    }
//
//    @Test
//    @Order(2)
//    void selectCategoriesById() {
//        Categories categories = categoriesMapper.selectCategoriesById(1);
//        assert categories.getId() == 1;
//    }
//
//    @Test
//    @Order(3)
//    void selectCategoriesByArticleId() {
//        for (Categories categories : categoriesMapper.selectCategoriesByArticleId(1)) {
//          System.out.println(categories);
//          assert categories.getArticleId() == 1;
//        }
//    }
//
//    @Test
//    @Order(4)
//    void selectCategoriesByCategoryId() {
//        for (Categories categories : categoriesMapper.selectCategoriesByCategoryId(1)) {
//            System.out.println(categories);
//            assert categories.getCategoryId() == 1;
//        }
//    }
//
//    @Test
//    @Order(5)
//    void selectCategoriesByArticleIdAndCategoryId() {
//        Categories categories = categoriesMapper.selectCategoriesByArticleIdAndCategoryId(1, 2);
//        assert categories != null;
//    }
//
//    @Test
//    @Order(6)
//    void addCategories() {
//        Categories categories = new Categories();
//        categories.setArticleId(1);
//        categories.setCategoryId(4);
//        int affectRow = categoriesMapper.addCategories(categories);
//        assert affectRow == 1;
//    }
//
//    @Test
//    @Order(7)
//    void updateCategoriesById() {
//        Categories categories = categoriesMapper.selectCategoriesByArticleIdAndCategoryId(1, 4);
//        assert categories != null;
//        Categories condition = new Categories();
//        condition.setId(20);
//        int affectRow = categoriesMapper.updateCategoriesById(condition, categories.getId());
//        assert affectRow == 1;
//    }
//
//    @Test
//    @Order(8)
//    void updateCategoriesByArticleIdAndCategoryId() {
//        Categories categories = categoriesMapper.selectCategoriesByArticleIdAndCategoryId(1, 4);
//        assert categories != null && categories.getId() == 20;
//        Categories condition = new Categories();
//        condition.setId(21);
//        int affectRow = categoriesMapper.updateCategoriesByArticleIdAndCategoryId(condition, categories.getArticleId(), categories.getCategoryId());
//        assert affectRow == 1;
//    }
//
//    @Test
//    @Order(10)
//    void deleteCategoriesById() {
//        Categories categories = categoriesMapper.selectCategoriesByArticleIdAndCategoryId(1, 4);
//        assert categories != null && categories.getId() == 21;
//        int affectRow = categoriesMapper.deleteCategoriesById(21);
//        assert affectRow == 1;
//    }
//
//    @Test
//    @Order(11)
//    void deleteCategoriesByArticleIdAndCategoryId() {
//        int affectRow = categoriesMapper.deleteCategoriesByArticleIdAndCategoryId(1, 4);
//        assert affectRow == 0;
//    }
//
//    @Test
//    @Order(12)
//    void deleteAllCategories() {
//
//    }
}
