package com.aolifu.elasticsearch.entity;

import cn.hutool.core.date.DateTime;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 * @Description : 文档模型
 * @Version : V1.0.0
 * @Date : 2021/12/22 14:08
 */
@Document(indexName = "article")
@Data
public class ArticleEntity {

    @Id
    private String id;

    private String title;

    private String content;

    private Integer userId;

    private Date createTime = DateTime.now();
}
