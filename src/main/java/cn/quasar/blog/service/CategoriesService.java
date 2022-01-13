package cn.quasar.blog.service;

import cn.quasar.blog.domain.Categories;
import cn.quasar.blog.dto.MessageResult;
import cn.quasar.blog.dto.MessageStatus;
import cn.quasar.blog.exception.CustomException;
import cn.quasar.blog.mapper.CategoriesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesMapper categoriesMapper;

    public MessageResult getCategories() {
        List<Categories> categories = categoriesMapper.queryCategories();

        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), categories, "");
    }

    public MessageResult getCategoriesById(int categoriesId) {
        if (categoriesId == 0) throw new CustomException("categoriesId能不为空");
        Categories categories = categoriesMapper.selectCategoriesById(categoriesId);

        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), categories, "");
    }

    public MessageResult getCategoriesByArticleId(int articleId) {
        if (articleId == 0) throw new CustomException("articleId能不为空");
        List<Categories> categories = categoriesMapper.selectCategoriesByArticleId(articleId);

        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), categories, "");
    }

    public MessageResult getCategoriesByCategoryId(int categoryId) {
        if (categoryId == 0) throw new CustomException("categoryId不能为空");
        List<Categories> categories = categoriesMapper.selectCategoriesByCategoryId(categoryId);

        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), categories, "");
    }
}
