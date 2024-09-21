package com.example.bigevent.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.bigevent.pojo.Result;
import com.example.bigevent.pojo.User;

import jakarta.servlet.http.HttpServletRequest;

@Service
public interface UserService {

    Result<?> register(String username, String password);

    Result login(String username, String password);

    Result<?> getUserInfo();

    Result<?> updateUserInfo(User u);

    Result<?> updateAvatar(String avatarUrl);

    Result<?> updatePwd(Map<String, String> map, String token);

    Result<?> Logout(HttpServletRequest request);

}
