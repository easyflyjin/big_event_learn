package com.example.bigevent.interceptor;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
//import org.apache.ibatis.plugin.Interceptor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.bigevent.utils.JwtUtil;
import com.example.bigevent.utils.ThreadLocalUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class LoginInterceptor implements HandlerInterceptor{
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        
        String token = request.getHeader("Authorization");
        try {
            Map<String,Object> claim = JwtUtil.parseToken(token);
            ThreadLocalUtil.set(claim);
            ValueOperations<String,String> ops = stringRedisTemplate.opsForValue();
            String redisToken = ops.get(token);
            if(redisToken == null) throw new RuntimeException();
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable Exception ex) throws Exception {
        ThreadLocalUtil.remove();
    }

}
