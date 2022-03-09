package cn.quasar.blog.service;

import cn.quasar.blog.domain.Article;
import cn.quasar.blog.domain.Categories;
import cn.quasar.blog.domain.Category;
import cn.quasar.blog.exception.CustomException;
import cn.quasar.blog.mapper.ArticleMapper;
import cn.quasar.blog.utils.AssertUtils;
import cn.quasar.blog.utils.StrUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
    @Lazy
    private CategoriesService categoriesService;

    @Autowired
    @Lazy
    private CategoryService categoryService;

    @Autowired
    AssertUtils utils;

    public void ArticleChecker(Article article) {
        ArrayList<String> errors = new ArrayList<>();

        utils.customExceptionHandler(article == null, "缺少参数article");

        if (article.getArticleName() == null || article.getArticleName().equals("")) {
            errors.add("文章名不能更新为空值");
        }
        if (article.getDescription() == null) {
            article.setDescription("");
        }
        if(article.getContent() != null && article.getContent().equals("")) {
            errors.add("文章内容不能更新为空值");
        }
        if(errors.size() > 0) {
            throw new CustomException(errors.toString());
        }

    }


    public void affectRowHandler(int affectRow, String thinZero, String equalZero) {
        utils
                .customExceptionHandler(affectRow < 0 && !thinZero.equals(""), thinZero)
                .customExceptionHandler(affectRow == 0 && !equalZero.equals(""), equalZero);

    }

    public List<Article> queryArticles() {
        return articleMapper.queryArticles();
    }

    public Article queryArticleById(int id) {
        utils.customExceptionHandler(id == 0, "缺少参数id");

        return articleMapper.queryArticleById(id);
    }

    public Article quertArticleByArticleName(String name) {
        utils.customExceptionHandler(name == null || name.equals(""), "请提供参数articleName");

        return articleMapper.queryArticleByName(name);
    }


    public Article selectArticleById(int articleId) {
        utils.customExceptionHandler(articleId == 0, "articleId不能为空");

        return articleMapper.selectArticleById(articleId);
    }

    public Article selectArticleByArticleName(String articleName) {
        if ("".equals(articleName)) {
            throw new CustomException("查询的文章名不能为空");
        }
        utils.customExceptionHandler(articleName == null || articleName.equals(""), "查询的文章名不能为空");

        return articleMapper.selectArticleByName(articleName);
    }

    public List<Article> selectArticleByAuthorId(int authorId) {
        utils.customExceptionHandler(authorId == 0, "authorId不能为空");

        return  articleMapper.selectArticlesByAuthor(authorId);
    }

    public List<Article> selectArticleByCategoryName(String categoryName) {
        utils.customExceptionHandler("".equals(categoryName), "查询的分类名categoryName不能为空");

        return articleMapper.selectArticlesByCategory(categoryName);
    }

    public String deleteCategoriesByCategoryNames(int articleId, List<String> names) {
        utils.customExceptionHandler(names == null, "categoryList未初始化或为空值");
        if (names.size() > 0) {
            for (String category:
                 names) {
                utils.customExceptionHandler(category.contains(","), "不支持处理的categoryList");

                Category category1 = categoryService.queryCategoryByName(category);
                categoriesService.deleteCategoriesByArticleIdAndCategoryId(articleId, category1.getId());
            }

            return "删除成功";
        } else {
            return "没有分类";
        }
    }

    @Transactional
    public String deleteArticlesByArticleNames(List<String> names) {
        if (names.size() > 0) {
           for (int i = 0; i <names.size(); i++) {
               Article article = selectArticleByArticleName(names.get(i));

               utils.customExceptionHandler(article == null, "文章《" + names.get(i) + "》不存在");

               deleteCategoriesByCategoryNames(article.getId(), StrUtils.splitStr(article.getCategory(), ","));

               categoriesService.realDeleteCategoriesZeroValue(article.getId());

               articleMapper.deleteArticleByArticleName(names.get(i));
           }

            return  "删除成功";
        } else {
            return "未删除";
        }
    }

    @Transactional
    public String addArticle(Article article) {
        utils
                .customExceptionHandler(article == null, "代添加文章不可为空值")
                .customExceptionHandler(article.getArticleName().equals(""), "文章名不可为空")
                .customExceptionHandler(article.getContent().equals(""), "文章内容不可为空")
                .customExceptionHandler(article.getAuthorName().equals(""), "作者名不可为空")
                .customExceptionHandler(article.getAuthorId() == 0, "请提供作者id")
                .IfHandler(article.getArticleStatus() == null, () -> article.setArticleStatus(0))
                .IfHandler(article.getCategory() == null,() -> article.setCategory(""));

        int i = articleMapper.addArticle(article);

        affectRowHandler(i, "文章添加失败", "文章添加失败");

        System.out.println(article.getArticleName());
        Article article1 = articleMapper.queryArticleByName(article.getArticleName());

        categoriesService.addCategories(article1.getId(), article.getCategory());

        return "添加成功";
    }

    public String editArticleById(Article article, int id) {
        Article s1 = articleMapper.selectArticleById(id);

        utils.customExceptionHandler(s1 == null, "所更新文章不存在")
                .customExceptionHandler(article == null, "缺少更新参数article")
                .customExceptionHandler(article.getArticleName() != null && article.getArticleName().equals(""), "文章名不可更新为空值");


        int affectRow = articleMapper.updateArticleById(article, id);

        affectRowHandler(affectRow, "更新失败", "未更新");

        if (article.getCategory() != null) {
            if (article.getCategory().equals("")) {
                categoriesService.deleteCategoriesByArticleId(s1.getId());
            } else if (article.getCategory().indexOf(",") == -1) {
                categoriesService.deleteCategoriesByArticleId(s1.getId());
                categoriesService.realDeleteCategoriesZeroValue(s1.getId());
               categoriesService.addCategories(s1.getId(), article.getCategory());
           } else {
                List<String> sList = StrUtils.splitStr(s1.getCategory(), ",");
                List<String> cList = StrUtils.splitStr(article.getCategory(), ",");

                for (int i = 0; i < cList.size(); i++) {
                    System.out.println(cList.get(i));
                    if (!sList.contains(cList.get(i))) {
                        System.out.println(cList.get(i));
                        categoriesService.addCategories(s1.getId(), cList.get(i));
                    }
                }

                for (int i = 0; i < sList.size(); i++) {
                    System.out.println(sList.get(i));
                    if(!cList.contains(sList.get(i))) {
                        Category category = categoryService.queryCategoryByName(sList.get(i));
                        categoriesService.deleteCategoriesByArticleIdAndCategoryId(s1.getId(), category.getId());
                    }
                }

                List<Categories> list = categoriesService.getCategoriesByArticleId(s1.getId());
                if(list.size() > 1) {
                    Categories demo = categoriesService.getCategoriesByArticleIdAndCategoryId(s1.getId(), categoryService.CATEGORY_ZERO_ID);
                    if (demo != null) {
                        categoriesService.realDeleteCategoriesZeroValue(s1.getId());
                    }
                }
            }
        }

        return "更新成功";
    }

    public String editArticleByArticleName(Article article, String articleName) {
        utils.customExceptionHandler(article == null, "缺少更新参数article")
                .customExceptionHandler(articleName == null ||articleName.equals(""), "请提供articleName");

        Article s1 = articleMapper.selectArticleByName(articleName);

        utils.customExceptionHandler(s1 == null, "所更新文章不存在");

        ArticleChecker(article);

        int affectRow = articleMapper.updateArticleById(article, s1.getId());

        affectRowHandler(affectRow, "更新失败", "未更新");

        if (article.getCategory() != null) {
            if (article.getCategory().equals("")) {
                categoriesService.deleteCategoriesByArticleId(s1.getId());
            } else if (article.getCategory().indexOf(",") == -1) {
                categoriesService.deleteCategoriesByArticleId(s1.getId());
                categoriesService.addCategories(s1.getId(), article.getCategory());
            } else {
                List<String> sList = StrUtils.splitStr(s1.getCategory(), ",");
                List<String> cList = StrUtils.splitStr(article.getCategory(), ",");

                for (int i = 0; i < cList.size(); i++) {
                    if (!sList.contains(cList.get(i))) {
                        categoriesService.addCategories(s1.getId(), cList.get(i));
                    }
                }

                for (int i = 0; i < sList.size(); i++) {
                    if(!cList.contains(sList.get(i))) {
                        categoriesService.deleteCategoriesByArticleId(s1.getId());
                    }
                }
            }
        }

        return "更新成功";
    }

}
