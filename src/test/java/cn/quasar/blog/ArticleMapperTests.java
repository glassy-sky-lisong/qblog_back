package cn.quasar.blog;

import cn.quasar.blog.domain.Article;
import cn.quasar.blog.mapper.ArticleMapper;
import cn.quasar.blog.mapper.CategoriesMapper;
import cn.quasar.blog.mapper.CategoryMapper;
import cn.quasar.blog.service.ArticleService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArticleMapperTests {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CategoriesMapper categoriesMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ArticleService articleService;

//    @Test
//    @Order(12)
//    void selectArticleById() {
//        Article article = articleMapper.selectArticleById(34);
//
//        System.out.println(article);
//    }
//
//    @Test
//    @Order(13)
//    void selectArticleByArticleName() {
//        Article article = articleMapper.selectArticleByName("1");
//
//        System.out.println(article);
//    }
//
//    @Test
//    @Order(14)
//    void selectArticleByAuthorId() {
//        List<Article> articles = articleMapper.selectArticlesByAuthor(1);
//
//        for (Article a:
//             articles) {
//            System.out.println(a);
//        }
//    }
//
//    @Test
//    @Order(15)
//    void selectArticleByCategory() {
//        for (Article article : articleMapper.selectArticlesByCategory("")) {
//            System.out.println(article);
//        }
//    }
//
//    @Test
//    @Order(16)
//    void deleteArticleByNames() {
//        List<String> list = new ArrayList<>();
//        list.add("1");
//        articleService.deleteArticlesByArticleNames(list);
//    }
}
