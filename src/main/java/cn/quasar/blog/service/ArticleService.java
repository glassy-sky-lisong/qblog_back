package cn.quasar.blog.service;

import cn.quasar.blog.domain.Article;
import cn.quasar.blog.domain.Category;
import cn.quasar.blog.dto.MessageResult;
import cn.quasar.blog.dto.MessageStatus;
import cn.quasar.blog.exception.CustomException;
import cn.quasar.blog.mapper.ArticleMapper;
import cn.quasar.blog.mapper.CategoriesMapper;
import cn.quasar.blog.mapper.CategoryMapper;
import cn.quasar.blog.utils.StrUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CategoriesMapper categoriesMapper;

    @Autowired
    private CategoryMapper categoryMapper;

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

    @Transactional
    public MessageResult deleteArticlesByName(List<String> names) {
        if (names.size() > 0) {

            for (int i =0; i < names.size(); i++) {

                Article name = articleMapper.selectArticleByName(names.get(i));
                if (name == null) {
                    throw new CustomException("文章《" + names.get(i) + "》不存在，数据正在回滚");
                }
                List<String> categories = StrUtils.splitStr(name.getCategory(), ",");

                for (int j = 0; j < categories.size(); j++) {
                    Category category = categoryMapper.selectCategoryByName(categories.get(j));
                    categoriesMapper.deleteCategoriesByArticleIdAndCategoryId(name.getId(), category.getId());
                }

                int row = articleMapper.deleteArticleByArticleName(names.get(i));

            }

            return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), "删除成功", "");
        }
        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), "nothing", "");
    }
}
