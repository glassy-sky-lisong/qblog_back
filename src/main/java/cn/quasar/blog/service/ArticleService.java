package cn.quasar.blog.service;

import cn.quasar.blog.domain.Article;
import cn.quasar.blog.dto.MessageResult;
import cn.quasar.blog.dto.MessageStatus;
import cn.quasar.blog.exception.CustomException;
import cn.quasar.blog.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    public MessageResult queryArticles() {
        List<Article> articles = articleMapper.queryArticles();

        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), articles, "");
    }


    public MessageResult selectArticleById(int articleId) {
        if (articleId == 0) {
            throw new CustomException("articleId不能为空");
        }
        Article article = articleMapper.selectArticleById(articleId);

        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), article, "");
    }

    public MessageResult selectArticleByArticleName(String articleName) {
        if ("".equals(articleName)) {
            throw new CustomException("查询的文章名不能为空");
        }
        Article article = articleMapper.selectArticleByName(articleName);

        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), article, "");
    }

    public MessageResult selectArticleByAuthorId(int authorId) {
        if (authorId == 0) {
            throw new CustomException("authorId不能为空");
        }
        List<Article> articles = articleMapper.selectArticlesByAuthor(authorId);

        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), articles, "");
    }

    public MessageResult selectArticleByCategoryName(String categoryName) {
        if("".equals(categoryName)) {
            throw new CustomException("查询的分类名categoryName不能为空");
        }
        List<Article> articles = articleMapper.selectArticlesByCategory(categoryName);

        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), articles, "");
    }
}
