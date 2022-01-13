package cn.quasar.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  文章分类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    private int id;
    private String categoryName;
}
