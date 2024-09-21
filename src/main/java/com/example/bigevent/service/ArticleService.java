package com.example.bigevent.service;

import org.springframework.stereotype.Service;

import com.example.bigevent.pojo.Article;
import com.example.bigevent.pojo.PageBean;
import com.example.bigevent.pojo.Result;

@Service
public interface ArticleService {

    Result<?> postArticle(Article article);

    Result<?> updateArticle(Article article);

    Result getArticleDetail(Integer id);

    Result<?> deleteArticle(Integer id);

    Result<PageBean<Article>> getPagedArticle(Integer pageNum, Integer pageSize, Integer categoryId, String state);

}
