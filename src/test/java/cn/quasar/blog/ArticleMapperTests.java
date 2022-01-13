package cn.quasar.blog;

import cn.quasar.blog.mapper.ArticleMapper;
import cn.quasar.blog.mapper.CategoriesMapper;
import cn.quasar.blog.mapper.CategoryMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ArticleMapperTests {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CategoriesMapper categoriesMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    //    @Test
    //    @Order(1)
    //    void queryArticles() {
    //        for (Article article : articleMapper.queryArticles()) {
    //            System.out.println(article);
    //        }
    //    }
    //
    //    @Test
    //    @Order(2)
    //    void selectArticleById() {
    //        Article article = articleMapper.selectArticleById(1);
    //        assert article.getId() == 1;
    //    }
    //
    //    @Test
    //    @Order(3)
    //    void selectArticleByName() {
    //        Article article = articleMapper.selectArticleByName("Vue初体验");
    //        assert article.getArticleName().matches("Vue初体验");
    //    }
    //
    //    @Test
    //    @Order(4)
    //    void selectArticlesByAuthor() {
    //        List<Article> articles = articleMapper.selectArticlesByAuthor(1);
    //        assert articles.get(0).getAuthorId() == 1;
    //    }
    //
    //    @Test
    //    @Order(5)
    //    void selectArticlesByCategory() {
    //        for (Article article : articleMapper.selectArticlesByCategory("it编程")) {
    //            System.out.println(article);
    //            assert article.getCategory().matches("it编程");
    //        }
    //    }
    //
    //    @Test
    //    @Order(6)
    //    @Transactional
    //    @Rollback(false)
    //    void addArticle() throws UnsupportedEncodingException {
    //        Article article = new Article();
    //        article.setArticleName("React教程");
    //        article.setDescription("react");
    //        article.setAuthorId(1);
    //        article.setContent("我是react教程".getBytes("utf-8"));
    //        int affectRow = articleMapper.addArticle(article);
    //        assert affectRow == 1;
    //
    //        Article byName = articleMapper.queryArticleByName("React教程");
    //        Categories categories = new Categories();
    //        categories.setArticleId(byName.getId());
    //        categories.setCategoryId(5);
    //        int affectRow1 = categoriesMapper.addCategories(categories);
    //        assert affectRow1 == 1;
    //    }
    //
    //    @Test
    //    @Order(7)
    //    void updateArticleById() {
    //        Article byName = articleMapper.selectArticleByName("React教程");
    //        System.out.println(byName);
    //        assert byName != null;
    //        Article article = new Article();
    //        article.setDescription("我不是react");
    //        int affectRow = articleMapper.updateArticleById(article, byName.getId());
    //        assert affectRow == 1;
    //    }
    //
    //    @Test
    //    @Order(8)
    //    void updateArticleByArticleName() {
    //        Article byName = articleMapper.selectArticleByName("React教程");
    //        assert byName != null;
    //        Article article = new Article();
    //        article.setArticleName("React教程1");
    //        int affectRow = articleMapper.updateArticleByArticleName(article, byName.getArticleName());
    //        assert affectRow == 1;
    //    }
    //
    //    @Test
    //    @Order(9)
    //    @Transactional
    //    @Rollback(false)
    //    void deleteArticleById() {
    //        Article byName = articleMapper.selectArticleByName("React教程1");
    //        assert byName != null;
    //        int affectRow = articleMapper.deleteArticleById(byName.getId());
    //        assert affectRow == 1;
    //        Category category = categoryMapper.selectCategoryByName(byName.getCategory());
    //        int affectRow1 = categoriesMapper.deleteCategoriesByArticleIdAndCategoryId(byName.getId(), category.getId());
    //        assert affectRow1 == 1;
    //    }
    //
    //    @Test
    //    @Order(10)
    //    void deleteArticleByArticleName() {
    //        int affectRow = articleMapper.deleteArticleByArticleName("React教程1");
    //        assert affectRow == 0;
    //    }
}
