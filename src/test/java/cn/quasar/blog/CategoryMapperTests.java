package cn.quasar.blog;

import cn.quasar.blog.mapper.CategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoryMapperTests {


    @Autowired
    private CategoryMapper categoryMapper;

//    @Test
//    @Order(1)
//    void categorySelectAll() {
//        for (Category category : categoryMapper.queryCategories()) {
//            System.out.println(category);
//        }
//
//    }
//
//    @Test
//    @Order(2)
//    void categorySelectByName() {
//        Category category = categoryMapper.selectCategoryByName("it编程");
//        assert category.getCategoryName().equals("it编程");
//    }
//
//    @Test
//    @Order(3)
//    void categorySelectById() {
//        Category category = categoryMapper.selectCategoryById(4);
//        assert category.getId() == 4;
//    }
//
//    @Test
//    @Order(4)
//    void addCategory() {
//        Category category = new Category();
//        category.setCategoryName("React教程");
//        int affectRow = categoryMapper.addCategory(category);
//        assert affectRow == 1;
//    }
//
//    @Test
//    @Order(5)
//    void updateCategoryById() {
//        Category byName = categoryMapper.selectCategoryByName("React教程");
//        assert !"".equals(byName.getCategoryName());
//        Category category = new Category();
//        category.setCategoryName("quasar教程");
//        int affectRow = categoryMapper.updateCategoryById(category, byName.getId());
//        assert affectRow == 1;
//    }
//
//    @Test
//    @Order(6)
//    void updateCategoryByName() {
//        Category byName = categoryMapper.selectCategoryByName("quasar教程");
//        assert !"".equals(byName.getCategoryName());
//        Category category = new Category();
//        category.setId(20);
//        int affectRow = categoryMapper.updateCategoryByName(category, byName.getCategoryName());
//        assert affectRow == 1;
//    }
//
//    @Test
//    @Order(7)
//    void deleteCategoryById() throws InterruptedException {
//        Category byName = categoryMapper.selectCategoryByName("quasar教程");
//        assert byName != null;
//        int affectRow = categoryMapper.deleteCategoryById(byName.getId());
//        assert affectRow == 1;
//    }
//
//    @Test
//    @Order(8)
//    void deleteCategoryByName() {
//        int affectRow = categoryMapper.deleteCategoryByName("quasar教程");
//        assert affectRow == 0;
//    }

    @Test
    @Order(9)
    void deleteAllCategory() {

    }
}
