package cn.quasar.blog.controller;

import cn.quasar.blog.dto.MessageResult;
import cn.quasar.blog.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping(value = "/all")
    public MessageResult queryCategories() {
        return categoriesService.getCategories();
    }

    @GetMapping(value = "/id/{categoriesId}")
    public MessageResult queryCategoriesById(@PathVariable int categoriesId) {
        return categoriesService.getCategoriesById(categoriesId);
    }

    @GetMapping(value = "/category_id/{categoryId}")
    public MessageResult queryCategoriesByCategoryId(@PathVariable int categoryId) {
        return categoriesService.getCategoriesByCategoryId(categoryId);
    }

    @GetMapping(value = "/article_id/{articleId}")
    public MessageResult queryCategoriesByArticleId(@PathVariable int articleId) {
        return categoriesService.getCategoriesByArticleId(articleId);
    }

}
