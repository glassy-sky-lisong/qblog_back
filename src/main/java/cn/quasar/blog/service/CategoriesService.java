package cn.quasar.blog.service;

import cn.quasar.blog.domain.Article;
import cn.quasar.blog.domain.Categories;
import cn.quasar.blog.domain.Category;
import cn.quasar.blog.mapper.CategoriesMapper;
import cn.quasar.blog.mapper.CategoryMapper;
import cn.quasar.blog.utils.AssertUtils;
import cn.quasar.blog.utils.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesMapper categoriesMapper;

    @Autowired
    @Lazy
    private CategoryService categoryService;

    @Autowired
    @Lazy
    private ArticleService articleService;

    @Autowired
    private AssertUtils utils;

    public void affectRowHandler(int affectRow, String thinZero, String equalZero) {
        utils
                .customExceptionHandler(affectRow < 0 && !thinZero.equals(""), thinZero)
                .customExceptionHandler(affectRow == 0 && !equalZero.equals(""), equalZero);
    }

    public List<Categories> getAllCategories() {

        return categoriesMapper.queryCategories();
    }

    public Categories getCategoriesById(int categoriesId) {
        utils.customExceptionHandler(categoriesId == 0, "categoriesId能不为空");

        return categoriesMapper.selectCategoriesById(categoriesId);
    }

    public List<Categories> getCategoriesByArticleId(int articleId) {
        utils.customExceptionHandler(articleId == 0, "articleId能不为空");

        return categoriesMapper.selectCategoriesByArticleId(articleId);
    }

    public List<Categories> getCategoriesByCategoryId(int categoryId) {
        utils.customExceptionHandler(categoryId == 0, "categoryId不能为空");

        return categoriesMapper.selectCategoriesByCategoryId(categoryId);
    }

    public Categories getCategoriesByArticleIdAndCategoryId(int articleId, int categoryId) {
        utils.customExceptionHandler(articleId == 0, "空值参数：articleId")
                .customExceptionHandler(categoryId == 0, "空值参数： categoryId");

        return categoriesMapper.selectCategoriesByArticleIdAndCategoryId(articleId, categoryId);
    }

    private String addCategoriesOne(int articleId, String category) {
        utils.customExceptionHandler( category.indexOf(",") != -1, "仅支持单查")
                .customExceptionHandler(category == null, "category不可为空值")
                .customExceptionHandler(articleId == 0, "articleId不可为空值");


        Category res = categoryService.queryCategoryByName(category);

        if (res == null) {
            categoryService.addCategory(category);
            res = categoryService.queryCategoryByName(category);
        }


        Categories categories = new Categories();
        categories.setArticleId(articleId);
        categories.setCategoryId(res.getId());
        int i = categoriesMapper.addCategories(categories);

        affectRowHandler(i, "添加失败", "分类未添加");

        return "添加成功";
    }

    private String addCategoriesOne(String articleName, String category) {
        utils.customExceptionHandler( category.indexOf(",") != -1, "仅支持单查")
                .customExceptionHandler(category == null, "category不可为空值")
                .customExceptionHandler(articleName == null || articleName.equals(""), "articleName不可为空值");


        Category res = categoryService.queryCategoryByName(category);

        if (res == null) {
            categoryService.addCategory(category);
            res = categoryService.queryCategoryByName(category);
        }


        Article article = articleService.selectArticleByArticleName(articleName);

        utils.customExceptionHandler(article == null, "输入的文章名不存在");

        for (Categories c : getCategoriesByArticleId(article.getId())) {
            if(c.getCategoryId() == categoryService.CATEGORY_ZERO_ID) {
                deleteCategoriesById(c.getId());
            }
        }

        Categories categories = new Categories();
        categories.setArticleId(article.getId());
        categories.setCategoryId(res.getId());
        int i = categoriesMapper.addCategories(categories);

        affectRowHandler(i, "添加失败", "分类未添加");

        return "添加成功";
    }

    public String addCategories(int articleId, String category) {
        utils
                .customExceptionHandler(category == null, "缺少参数：category")
                .customExceptionHandler(articleId == 0, "此对象不能为空: articleId");


        if (category.indexOf(",") != -1) {
            addCategoriesOne(articleId, category);
        } else {
            List<String> list = StrUtils.splitStr(category, ",");

            for (int i = 0; i <list.size(); i++) {
                addCategoriesOne(articleId, list.get(i));
            }
        }

        return "添加成功";
    }

    public String addCategories(String articleName, String category) {
        utils.customExceptionHandler(articleName == null || articleName.equals(""), "articleName不能为空")
                .customExceptionHandler(category == null, "category不可为空");

        if (category.indexOf(",") == -1) {
            addCategoriesOne(articleName, category);
        } else {
            List<String> list = StrUtils.splitStr(category, ",");

            for (int i = 0; i <list.size(); i++) {
                addCategoriesOne(articleName, list.get(i));
            }
        }

        return "添加成功";
    }

    public String updateCategoriesById(Categories categories, int id) {
        utils
                .customExceptionHandler(id == 0, "缺少更新字段: id")
                .customExceptionHandler(categories == null, "缺少更新字段：categories");

        Categories res = getCategoriesById(id);

        utils.customExceptionHandler(res == null, "被更新记录不存在");

        int i = categoriesMapper.updateCategoriesById(categories, id);

        affectRowHandler(i, "分类更新失败", "分类更新失败");

        return "更新成功";
    }

    public String updateCategoriesByArticleNameAndCategoryId(Categories categories, int articleId, int categoryId) {
        utils.customExceptionHandler(categories == null, "空值参数：categories")
                .customExceptionHandler(articleId == 0, "空值参数： articleId")
                .customExceptionHandler(categoryId == 0, "空值参数: categoryId");

        Categories res = getCategoriesByArticleIdAndCategoryId(articleId, categoryId);

        utils.customExceptionHandler(res == null, "不存在的对象： categories");

        int i = categoriesMapper.updateCategoriesByArticleIdAndCategoryId(categories, articleId, categoryId);

        affectRowHandler(i, "更新失败", "更新失败");

        return "更新成功";
    }

    public String deleteCategoriesById(int id) {
        Categories categories = categoriesMapper.selectCategoriesById(id);

        if (categories != null) {
            int i = 1;
            List<Categories> categoriesList = categoriesMapper.selectCategoriesByArticleId(categories.getArticleId());
            if (categoriesList.size() == 1) {
                if (categoriesList.get(0).getCategoryId() != categoryService.CATEGORY_ZERO_ID) {
                    i = categoriesMapper.deleteCategoriesById(id);

                    Categories categories1 = new Categories();
                    categories1.setArticleId(categories.getArticleId());
                    categories1.setCategoryId(categoryService.CATEGORY_ZERO_ID);

                    categoriesMapper.addCategories(categories1);
                } else {
                    return "没有分类可删除";
                }
            } else if (categoriesList.size() == 0) {
                return "没有分类可删除";
            } else {
                i = categoriesMapper.deleteCategoriesById(id);
            }

            affectRowHandler(i, "删除失败", "删除失败");
        }


        return "删除成功";
    }

    public String deleteCategoriesByArticleIdAndCategoryId(int articleId, int categoryId) {
        Categories categories = categoriesMapper.selectCategoriesByArticleIdAndCategoryId(articleId, categoryId);

        if (categories != null) {
            List<Categories> categoriesList = categoriesMapper.selectCategoriesByArticleId(categories.getArticleId());
            int i = 1;

            if (categoriesList.size() == 1) {
               if (categoriesList.get(0).getCategoryId() != categoryService.CATEGORY_ZERO_ID) {
                   i = categoriesMapper.deleteCategoriesByArticleIdAndCategoryId(articleId, categoryId);

                   Categories categories1 = new Categories();
                   categories1.setArticleId(categories.getArticleId());
                   categories1.setCategoryId(categoryService.CATEGORY_ZERO_ID);

                   categoriesMapper.addCategories(categories1);
               }
            } else if (categoriesList.size() != 0) {
                i = categoriesMapper.deleteCategoriesByArticleIdAndCategoryId(articleId, categoryId);
            } else {
                return "没有分类可删除";
            }

            affectRowHandler(i, "删除失败", "删除失败");
        }

        return "删除成功";
    }

    public String realDeleteCategoriesZeroValue(int articleId) {
        Article article = articleService.selectArticleById(articleId);

        utils.customExceptionHandler(article == null, "此文章不存在");

        List<Categories> list = getCategoriesByArticleId(articleId);

        utils.customExceptionHandler(list.size() == 0, "文章与分类关系异常，请管理员重新核对");

        if (list.size() == 1 && list.get(0).getCategoryId() == categoryService.CATEGORY_ZERO_ID) {
            categoriesMapper.realDeleteCategoriesZeroValueByArticleId(articleId);

            return "删除成功";
        } else {
            return "分类不为空，无法执行";
        }
    }


    public String deleteCategoriesByArticleName(String articleName) {
        utils.customExceptionHandler(articleName == null || articleName.equals(""), "articleName不能为空");

        Article article = articleService.selectArticleByArticleName(articleName);

        utils.customExceptionHandler(article == null, "带删除文章不存在");

        return deleteCategoriesByArticleId(article.getId());
    }

    public String deleteCategoriesByArticleId(int articleId) {
        utils.customExceptionHandler(articleId == 0, "articleId不能为空");

        List<Categories> list = getCategoriesByArticleId(articleId);

        for (Categories c:
             list) {
            deleteCategoriesByArticleIdAndCategoryId(c.getArticleId(), c.getCategoryId());
        }

        return "删除成功";
    }

    public void deleteAllCategories() {
        categoriesMapper.deleteAllCategories();
    }

}
