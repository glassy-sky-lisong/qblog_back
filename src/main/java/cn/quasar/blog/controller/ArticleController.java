package cn.quasar.blog.controller;

import cn.quasar.blog.domain.Article;
import cn.quasar.blog.dto.MessageResult;
import cn.quasar.blog.dto.MessageStatus;
import cn.quasar.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value = "/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping(value = "/all")
    public MessageResult getArticles() {
        List<Article> articles = articleService.queryArticles();
        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), articles, "");
    }

    @GetMapping(value = "/id/{articleId}")
    public MessageResult getArticleById(@PathVariable int articleId) {
        Article article = articleService.selectArticleById(articleId);

        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), article, "");
    }

    @GetMapping(value = "/name/{articleName}")
    public MessageResult getArticleByArticleName(@PathVariable String articleName) {
        Article article = articleService.selectArticleByArticleName(articleName);

        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), article, "");
    }

    @GetMapping(value = "/author/{authorId}")
    public MessageResult getArticleByAuthorId(@PathVariable int authorId) {
        List<Article> articles = articleService.selectArticleByAuthorId(authorId);

        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), articles, "");
    }

    @GetMapping(value = "/category/{categoryName}")
    public MessageResult getArticleByCategoryName(@PathVariable String categoryName) {
        List<Article> articles = articleService.selectArticleByCategoryName(categoryName);

        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), articles, "");
    }

    @PostMapping(value = "/delete/s")
    public MessageResult deleteArticlesByArticleName(@RequestBody List<String> articles) {
        String s = articleService.deleteArticlesByArticleNames(articles);

        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), s, "");
    }

    @PostMapping(value = "/add")
    public MessageResult addArticle(@RequestBody Article article) {
        String s = articleService.addArticle(article);

        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), s, "");
    }

    @PostMapping(value = "/edit/id/{id}")
    public MessageResult editorArticleById(@RequestBody Article article, @PathVariable int id) {
        String s = articleService.editArticleById(article, id);

        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), s, "");
    }

    @PostMapping(value = "/edit/name/{name}")
    public MessageResult editorArticleById(@RequestBody Article article, @PathVariable String name) {
        String s = articleService.editArticleByArticleName(article, name);

        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), s, "");
    }
}
