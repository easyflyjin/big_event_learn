package com.example.bigevent.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.bigevent.pojo.Category;

import io.lettuce.core.dynamic.annotation.Param;

@Mapper
public interface CategoryMapper {
    @Select("select * from category where create_user = #{userId}")
    List<Category> getCategoryByUser(@Param("userId") Integer userId);

    @Select("select category_name from category")
    List<String> findAllCategories();

    @Insert("insert into category(category_name,category_alias,create_time,create_user,update_time) values (#{categoryName},#{categoryAlias},#{createTime},#{createUser},#{updateTime})")
    void addCategory(Category category);

    @Update("update category set category_name = #{categoryName}, category_alias = #{categoryAlias}, update_time = #{updateTime}, create_user = #{createUser} where id = #{id}")
    void updateCategory(Category category);
    
    @Select("select * from category where id = #{cateId}")
    Category getCategoryDetail(@Param("cateId") Integer cateId);
    
    @Delete("delete from category where id = #{id}")
    void deleteCategory(Integer id);
    
    
}
