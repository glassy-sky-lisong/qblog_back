package cn.quasar.blog;

import cn.quasar.blog.mapper.CategoriesMapper;
import cn.quasar.blog.service.CategoriesService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoriesMapperTests {

    @Autowired
    private CategoriesMapper categoriesMapper;

    @Autowired
    private CategoriesService categoriesService;

    @Test
    void deleteCategoriesByArticleId() {
        categoriesService.deleteCategoriesById(53);
    }

    @Test
    void deleteCategoriesByArticleIdAndCategoryId() {
        System.out.println("hello");
    }
}
