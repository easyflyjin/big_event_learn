package com.example.bigevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bigevent.pojo.Article;
import com.example.bigevent.pojo.PageBean;
import com.example.bigevent.pojo.Result;
import com.example.bigevent.service.ArticleService;





@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @PostMapping("")
    public Result<?> postArticle(@RequestBody @Validated(Article.Add.class) Article article) {
        return articleService.postArticle(article);    
    }
    @PutMapping("")
    public Result<?> updateArticle(@RequestBody @Validated(Article.Update.class)Article article) {
        
        return articleService.updateArticle(article);
    }
    @GetMapping("/detail")
    public Result getArticleDetail(Integer id) {
        return articleService.getArticleDetail(id);
    }
    
    @DeleteMapping("")
    public Result<?> deleteArticle(Integer id){
        return articleService.deleteArticle(id);
    }
    @GetMapping("")
    public Result<PageBean<Article>> getPagedArticle(@RequestParam Integer pageNum,@RequestParam Integer pageSize,@RequestParam(required = false) Integer categoryId,@RequestParam(required = false) String state) {
        return articleService.getPagedArticle(pageNum, pageSize, categoryId, state);
    }
    
}
