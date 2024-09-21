package com.example.bigevent.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bigevent.mapper.CategoryMapper;
import com.example.bigevent.pojo.Category;
import com.example.bigevent.pojo.Result;
import com.example.bigevent.service.CategoryService;
import com.example.bigevent.utils.ThreadLocalUtil;
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryMapper categoryMapper;
    private List<String> findAllCategories(){
        return categoryMapper.findAllCategories();
    }
    @Override
    public Result<?> getCategoryByUser() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Category> cat = categoryMapper.getCategoryByUser(userId);
        return Result.success(cat);
    }

    @Override
    public Result addCategory(Category category) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        category.setCreateUser(userId);
        
        for (String s : findAllCategories()) {
            if(s.equals(category.getCategoryName())) return Result.error("不能重复添加类型！");
        }
        
        categoryMapper.addCategory(category);

        return Result.success();
    }

    @Override
    public Result updateCategory(Category category) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId =  (Integer) map.get("id");
        
        category.setCreateUser(userId);
        category.setUpdateTime(LocalDateTime.now());
        
        categoryMapper.updateCategory(category);
        return Result.success();
    }
    @Override
    public Result<?> getCategoryDetail(Integer cateId) {
        
        Category category = categoryMapper.getCategoryDetail(cateId);
        return Result.success(category);
    }
    @Override
    public Result<?> deleteCategory(Integer id) {
        categoryMapper.deleteCategory(id);
        return Result.success();
    }


}
