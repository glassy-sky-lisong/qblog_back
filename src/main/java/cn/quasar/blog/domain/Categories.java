package cn.quasar.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分类与文章的关系中间表model
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categories {

    private int id;
    private int categoryId;
    private int articleId;
}
