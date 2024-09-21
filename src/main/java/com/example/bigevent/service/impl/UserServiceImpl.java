package com.example.bigevent.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.example.bigevent.mapper.UserMapper;
import com.example.bigevent.pojo.Result;
import com.example.bigevent.pojo.User;
import com.example.bigevent.service.UserService;
import com.example.bigevent.utils.JwtUtil;
import com.example.bigevent.utils.Md5Util;
import com.example.bigevent.utils.ThreadLocalUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private User findUserByusername(String username) {
        User u = userMapper.findUserByusername(username);
        return u; 
    }

    @Override
    public Result<?> register(String username, String password) {
        User u = findUserByusername(username);
        
        String md5password = Md5Util.getMD5String(password);
        if(u == null){
            User user = new User();
            user.setCreateTime(LocalDateTime.now());
            user.setUpdateTime(LocalDateTime.now());
            userMapper.register(username, md5password);
            return Result.success();
        }else{
            return Result.error("用户名已被使用");
        }
            
    }

    @Override
    public Result login(String username, String password) {
            User u = findUserByusername(username);
            if (u == null) return Result.error("用户名不存在！");
            if(!u.getPassword().equals(Md5Util.getMD5String(password))) return Result.error("密码错误！");
            Map<String,Object> claim = new HashMap<>();
            claim.put("username", u.getUsername());
            claim.put("id", u.getId());
            String token = JwtUtil.genToken(claim);
            
            ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();
            ops.set(token, token ,1,TimeUnit.HOURS);
            return Result.success(token);
    }

    @Override
    public Result<?> getUserInfo() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        User u = userMapper.getUserInfo(userId);
        return Result.success(u);
    }

    @Override
    public Result<?> updateUserInfo(User u) {
        
        Map<String,Object> claim = ThreadLocalUtil.get();
        Integer userId = (Integer) claim.get("id");
        if(!userId.equals(u.getId())) return Result.error("无法修改别人的信息!");
        u.setUpdateTime(LocalDateTime.now());
        
        
        userMapper.updateUserInfo(u);
        return Result.success();
   }

    @Override
    public Result<?> updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        
        userMapper.updateAvatar(avatarUrl,userId);

        return Result.success();
    }

    @Override
    public Result<?> updatePwd(Map<String, String> map, String token) {
        String oldPwd = map.get("old_pwd");
        String newPwd = map.get("new_pwd");
        String rePwd = map.get("re_pwd");
        log.info(oldPwd);
        Map<String,Object> userInfoMap = ThreadLocalUtil.get();
        Integer userId = (Integer) userInfoMap.get("id");
        String userName = (String) userInfoMap.get("username");
        User u = findUserByusername(userName);

        if(!Md5Util.getMD5String(oldPwd).equals(u.getPassword())) return Result.error("原密码不正确");
        if(!newPwd.equals(rePwd)) return Result.error("两次输入密码不一致");

        String md5Password = Md5Util.getMD5String(newPwd);
        
        userMapper.updatePwd(userId, md5Password);
        ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();
        ops.getOperations().delete(token);
        return Result.success();
    }

    @Override
    public Result<?> Logout(HttpServletRequest request) {
        //如何获取token
        String token = request.getHeader("Authorization");
        System.out.println(token);
        stringRedisTemplate.opsForValue().getOperations().delete(token);

        return Result.success();
    }


}
