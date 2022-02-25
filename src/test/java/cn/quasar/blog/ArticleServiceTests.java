package cn.quasar.blog;

import cn.quasar.blog.domain.Article;
import cn.quasar.blog.dto.MessageResult;
import cn.quasar.blog.service.ArticleService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArticleServiceTests {

    @Autowired
    private ArticleService articleService;

//    @Test
//    @Rollback(true)
//    void deleteArticlesByName() {
//        List<String> datas = new ArrayList<String>();
//        datas.add("Vue初体验");
////        datas.add("balabala");
//        MessageResult result = articleService.deleteArticlesByName(datas);
//        System.out.println(result);
//    }

//    @Test
//    @Rollback(true)
//    void addArticle() {
//        Article article = new Article();
//        article.setArticleName("文章名");
//        article.setDescription("文章描述");
//        article.setAuthorId(1);
//        article.setAuthorName("admin");
//        article.setContent("1233424124");
//        article.setArticleStatus(1);
//        article.setCategory("Vue教程,it编程");
//
//        MessageResult result = articleService.addArticle(article);
//        assert ((String)result.getData()).equals("添加成功");
//    }
}
