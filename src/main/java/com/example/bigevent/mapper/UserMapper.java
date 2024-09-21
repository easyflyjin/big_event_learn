package com.example.bigevent.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.bigevent.pojo.User;

import io.lettuce.core.dynamic.annotation.Param;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User findUserByusername(@Param("username")String username);
    
    @Insert("insert into user(username,password,create_time,update_time) values (#{username},#{password},now(),now())")
    void register(@Param("username")String username,@Param("password") String password);
    
    @Select("select * from user where id = #{userId}")
    User getUserInfo(@Param("userId") Integer userId);

    @Update("update user set nickname = #{nickname}, email = #{email}, update_time = #{updateTime} where id = #{id}")
    void updateUserInfo(User u);
    
    @Update("update user set user_pic = #{avatarUrl} where id = #{userId}")
    void updateAvatar(@Param("avatarUrl")String avatarUrl, @Param("userId") Integer userId);
    
    @Update("update user set password = #{md5Password}, update_time = now() where id = #{userId}")
    void updatePwd(@Param("userId")Integer userId, @Param("md5Password") String md5Password);
    

}   
