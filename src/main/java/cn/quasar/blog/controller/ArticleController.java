package cn.quasar.blog.controller;

import cn.quasar.blog.domain.Article;
import cn.quasar.blog.dto.MessageResult;
import cn.quasar.blog.mapper.CategoriesMapper;
import cn.quasar.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @PostMapping(value = "/delete/s")
    public MessageResult deleteArticlesByArticleName(@RequestBody List<String> articles) {
        return articleService.deleteArticlesByName(articles);
    }

    @PostMapping(value = "/add")
    public MessageResult addArticle(@RequestBody Article article) {
        return articleService.addArticle(article);
    }

}
