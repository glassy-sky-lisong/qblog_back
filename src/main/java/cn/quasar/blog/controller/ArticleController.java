package cn.quasar.blog.controller;

import cn.quasar.blog.dto.MessageResult;
import cn.quasar.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "/all")
    public MessageResult getArticles() {
        return articleService.queryArticles();
    }

    @GetMapping(value = "/id/{articleId}")
    public MessageResult getArticleById(@PathVariable int articleId) {
        return articleService.selectArticleById(articleId);
    }

    @GetMapping(value = "/name/{articleName}")
    public MessageResult getArticleByArticleName(@PathVariable String articleName) {
        return articleService.selectArticleByArticleName(articleName);
    }

    @GetMapping(value = "/author/{authorId}")
    public MessageResult getArticleByAuthorId(@PathVariable int authorId) {
        return articleService.selectArticleByAuthorId(authorId);
    }

    @GetMapping(value = "/category/{categoryName}")
    public MessageResult getArticleByCategoryName(@PathVariable String categoryName) {
        return articleService.selectArticleByCategoryName(categoryName);
    }

}
