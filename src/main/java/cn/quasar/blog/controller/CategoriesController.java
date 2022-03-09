package cn.quasar.blog.controller;

import cn.quasar.blog.domain.Categories;
import cn.quasar.blog.dto.MessageResult;
import cn.quasar.blog.dto.MessageStatus;
import cn.quasar.blog.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping(value = "/all")
    public MessageResult queryCategories() {
        List<Categories> categories = categoriesService.getAllCategories();

        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), categories, "");
    }

    @GetMapping(value = "/id/{categoriesId}")
    public MessageResult queryCategoriesById(@PathVariable int categoriesId) {
        Categories categories = categoriesService.getCategoriesById(categoriesId);

        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), categories, "");
    }

    @GetMapping(value = "/category_id/{categoryId}")
    public MessageResult queryCategoriesByCategoryId(@PathVariable int categoryId) {
        List<Categories> categories = categoriesService.getCategoriesByCategoryId(categoryId);

        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), categories, "");
    }

    @GetMapping(value = "/article_id/{articleId}")
    public MessageResult queryCategoriesByArticleId(@PathVariable int articleId) {
        List<Categories> categories = categoriesService.getCategoriesByArticleId(articleId);

        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), categories, "");
    }

}
