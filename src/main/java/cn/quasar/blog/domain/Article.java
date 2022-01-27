package cn.quasar.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * 文章类model.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private int id;
    private String articleName;
    private String description;
    private String content;
    private int authorId;
    private Timestamp createTime;
    private Timestamp lastTime;
    private String category;
    private Integer articleStatus;
    private String authorName;
}
