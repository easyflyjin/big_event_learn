package com.example.bigevent.service;

import org.springframework.stereotype.Service;

import com.example.bigevent.pojo.Category;
import com.example.bigevent.pojo.Result;

@Service
public interface CategoryService {

    Result<?> getCategoryByUser();

    Result addCategory(Category category);



    Result updateCategory(Category category);

    Result<?> getCategoryDetail(Integer cateId);

    Result<?> deleteCategory(Integer id);

}
