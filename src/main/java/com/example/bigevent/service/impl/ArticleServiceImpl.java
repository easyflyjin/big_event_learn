package com.example.bigevent.service.impl;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bigevent.mapper.ArticleMapper;
import com.example.bigevent.pojo.Article;
import com.example.bigevent.pojo.PageBean;
import com.example.bigevent.pojo.Result;
import com.example.bigevent.service.ArticleService;
import com.example.bigevent.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service
public class ArticleServiceImpl implements ArticleService{
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public Result<?> postArticle(Article article) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        article.setCreateUser(userId);
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        articleMapper.postArticle(article);
        return Result.success();
    }
    @Override
    public Result<?> updateArticle(Article article) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        
        article.setUpdateTime(LocalDateTime.now());
        article.setCreateUser(userId);

        articleMapper.updateArticle(article);
        return Result.success();
    }
    @Override
    public Result getArticleDetail(Integer id) {
        
        Article article = articleMapper.getArticleDetail(id);
        return Result.success(article);
    }
    @Override
    public Result<?> deleteArticle(Integer id) {
        articleMapper.deleteArticle(id);
        return Result.success();
    }
    @Override
    public Result<PageBean<Article>> getPagedArticle(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        PageBean<Article> pb = new PageBean<>();
        
        PageHelper.startPage(pageNum, pageSize);

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Article> as = articleMapper.list(userId, categoryId, state);
        Page<Article> p = (Page<Article>) as;
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        
        return Result.success(pb);
    }

}
