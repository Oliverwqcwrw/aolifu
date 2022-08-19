package com.aolifu.elasticsearch.controller;

import com.aolifu.elasticsearch.entity.ArticleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description : article控制类
 * @Version : V1.0.0
 * @Date : 2021/12/22 14:11
 */
@RestController
@RequestMapping("/elk")
public class ArticleController {
    
    @Autowired
    private ElasticsearchRestTemplate template;

    /**
     * 根据文档id查询数据
     *
     * @param id 文档id
     * @return 文档详情
     */
    @GetMapping("/byId")
    public String findById(@RequestParam String id) {
        final ArticleEntity articleEntity = template.get(id, ArticleEntity.class);
        return articleEntity.getContent();
    }

    /**
     * 保存文档信息
     *
     * @param article 文档详情
     * @return 保存的文档信息
     */
    @PostMapping("/saveArticle")
    public String saveArticle(@RequestBody ArticleEntity article) {
        final ArticleEntity result = template.save(article);
        return result.toString();
    }

	@DeleteMapping("/deleteById")
    public String deleteArticle(@RequestParam String id) {
        final String delete = template.delete(id, ArticleEntity.class);
        return "success";
    }
}
