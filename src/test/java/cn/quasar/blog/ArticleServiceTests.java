package cn.quasar.blog;

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

    @Test
    @Rollback(true)
    void deleteArticlesByName() {
        List<String> datas = new ArrayList<String>();
        datas.add("Vue初体验");
        datas.add("balabala");
        MessageResult result = articleService.deleteArticlesByName(datas);
        System.out.println(result);
    }
}
