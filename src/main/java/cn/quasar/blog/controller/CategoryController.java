package cn.quasar.blog.controller;

import cn.quasar.blog.domain.Category;
import cn.quasar.blog.dto.MessageResult;
import cn.quasar.blog.dto.MessageStatus;
import cn.quasar.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/all")
    public MessageResult getAllCategory() {
        List<Category> list = categoryService.queryAllCategory();

        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), list, "");
    }

    @GetMapping(value = "/id/{id}")
    public MessageResult getCategoryById(@PathVariable int id) {
        Category category = categoryService.queryCategoryById(id);

        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), category, "");
    }

    @GetMapping(value = "/name/{articleName}")
    public MessageResult getCategoryByName(@PathVariable String articleName) {
        Category category = categoryService.queryCategoryByName(articleName);

        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), category, "");
    }

}
