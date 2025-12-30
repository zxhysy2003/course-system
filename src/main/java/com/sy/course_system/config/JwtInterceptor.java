package com.sy.course_system.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sy.course_system.utils.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    // JWT拦截器的实现可以在这里添加
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 在这里实现JWT验证逻辑

        // 1. 获取请求头 token
        String token = request.getHeader("Authorization");
        // 2. 验证 token 的有效性
        if (token == null || !token.startsWith("Bearer ")) {
            response.setStatus(401);
            response.getWriter().write("未登录或Token缺失");
            return false;
        }

        token = token.replace("Bearer ", "");

        try {
            // 3.解析 token
            Claims claims = JwtUtil.parseToken(token);

            // 4. 可以将用户信息存储在请求属性中，供后续使用
            Number userIdNum = (Number) claims.get("userId");
            request.setAttribute("userId", userIdNum.longValue());
            request.setAttribute("username", claims.get("username"));
            request.setAttribute("role", claims.get("role"));
        } catch (ExpiredJwtException e) {
            response.setStatus(401);
            response.getWriter().write("Token无效或已过期");
            return false;
        } catch (JwtException e) {
            response.setStatus(401);
            response.getWriter().write("Token无效");
            return false;
        }

        return true; // 放行
    }
}
