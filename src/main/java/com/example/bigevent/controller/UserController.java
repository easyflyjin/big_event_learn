package com.example.bigevent.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bigevent.pojo.Result;
import com.example.bigevent.pojo.User;
import com.example.bigevent.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService; 

    // @GetMapping("/find")
    // public Result<?> findUserByusername(String username) {
    //     return userService.findUserByusername(username);
    // }

    @PostMapping("/register")
    public Result<?> register(@Pattern(regexp = "^\\S{0,16}$") String username,@Pattern(regexp = "^\\S{0,16}$") String password) {
    
        return userService.register(username,password);
    }
    @PostMapping("/login")
    public Result Login(@Pattern(regexp = "^\\S{0,16}$") String username,@Pattern(regexp = "^\\S{0,16}$") String password) {
        return userService.login(username,password);
    }
    @GetMapping("/userInfo")
    public Result<?> getUserInfo() {
        return userService.getUserInfo();
    }
    @PutMapping("/update")
    public Result<?> updateUserInfo(@RequestBody @Validated(User.update.class) User u) {

        return userService.updateUserInfo(u);

    }
    @PatchMapping("/avatar")
    public Result<?> updateAvatar(@RequestParam String avatarUrl){
        return userService.updateAvatar(avatarUrl);
    }
    @PatchMapping("/updatePwd")
    public Result<?> updatePwd(@RequestBody Map<String,String> map,@RequestHeader("Authorization")String token){
        return userService.updatePwd(map,token);
    }

    @GetMapping("/logout")
    public Result<?> Logout(HttpServletRequest request) {
        return userService.Logout(request);
    }
    

    
}