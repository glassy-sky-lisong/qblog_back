package cn.quasar.blog.controller;

import cn.quasar.blog.dto.MessageResult;
import cn.quasar.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/all")
    public MessageResult getAllCategory() {
        return categoryService.queryAllCategory();
    }

    @GetMapping(value = "/id/{id}")
    public MessageResult getCategoryById(@PathVariable int id) {
        return categoryService.queryCategoryById(id);
    }

    @GetMapping(value = "/name/{articleName}")
    public MessageResult getCategoryByName(@PathVariable String articleName) {
        return categoryService.queryCategoryByName(articleName);
    }

}
