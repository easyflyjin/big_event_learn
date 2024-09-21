package com.example.bigevent.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.bigevent.pojo.Article;

import io.lettuce.core.dynamic.annotation.Param;

@Mapper
public interface ArticleMapper {

    @Insert("insert into article(title, content, cover_img, state, category_id,create_user,create_time,update_time) values (#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser}, #{createTime},#{updateTime})")
    void postArticle(Article article);
    @Update("update article set title = #{title}, content = #{content}, cover_img = #{coverImg}, state = #{state}, category_id = #{categoryId}, create_user =#{createUser},update_time = #{updateTime} where id = #{id}")
    void updateArticle(Article article);

    @Select("select * from article where id = #{id}")
    Article getArticleDetail(Integer id);

    @Delete("delete from article where id = #{id}")
    void deleteArticle(Integer id);

    List<Article> list(@Param("userId") Integer userId, @Param("categoryId") Integer categoryId, @Param("state") String state);
    

}
