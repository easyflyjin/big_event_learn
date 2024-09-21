package com.example.bigevent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bigevent.pojo.Category;
import com.example.bigevent.pojo.Result;
import com.example.bigevent.service.CategoryService;





@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public Result<?> getCategoryByUser() {
        return categoryService.getCategoryByUser();
    }
    
    @PostMapping("")
    public Result addCategory(@RequestBody @Validated(Category.Add.class) Category category) {        
        return categoryService.addCategory(category);
    }

    @PutMapping("")
    public Result updateCategory(@RequestBody @Validated(Category.Update.class) Category category) {
        return categoryService.updateCategory(category);
    }

    @GetMapping("/detail")
    public Result<?> getCategoryDetail(Integer id) {
        return categoryService.getCategoryDetail(id);
    }

    @DeleteMapping("")
    public Result<?> deleteCategory(Integer id){
        return categoryService.deleteCategory(id);
    }
}
