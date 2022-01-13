package cn.quasar.blog.mapper;

import cn.quasar.blog.domain.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {

    List<Article> queryArticles();

    Article selectArticleById(int id);

    Article selectArticleByName(String articleName);

    List<Article> selectArticlesByAuthor(int authorId);

    List<Article> selectArticlesByCategory(String categoryName);

    List<Article> queryArticleByCondition(Article condition);

    Article queryArticleById(int id);

    Article queryArticleByName(String articleName);

    int addArticle(Article article);

    int updateArticleById(Article article, int id);

    int updateArticleByArticleName(Article article, String name);

    int deleteArticleById(int articleId);

    int deleteArticleByArticleName(String articleName);

    int deleteAllArticle();
}
