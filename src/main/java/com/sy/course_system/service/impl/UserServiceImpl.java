package com.sy.course_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sy.course_system.dto.LoginDTO;
import com.sy.course_system.dto.RegisterDTO;
import com.sy.course_system.entity.User;
import com.sy.course_system.mapper.UserMapper;
import com.sy.course_system.service.UserService;
import com.sy.course_system.utils.JwtUtil;
import com.sy.course_system.vo.UserVO;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<UserVO> listUsers() {
        List<User> users = userMapper.selectList(null);
        List<UserVO> userVOs = users.stream().map(user -> {
            UserVO userVO = new UserVO();
            userVO.setId(user.getId());
            userVO.setUsername(user.getUsername());
            userVO.setNickname(user.getNickname());
            userVO.setEmail(user.getEmail());
            userVO.setPhone(user.getPhone());
            userVO.setRole(user.getRole());
            userVO.setStatus(user.getStatus());
            return userVO;
        }).toList();
        return userVOs;
    }

    @Override
    public UserVO getUserById(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setUsername(user.getUsername());
        userVO.setNickname(user.getNickname());
        userVO.setEmail(user.getEmail());
        userVO.setPhone(user.getPhone());
        userVO.setRole(user.getRole());
        userVO.setStatus(user.getStatus());
        return userVO;
    }

    @Override
    public Integer register(RegisterDTO registerDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", registerDTO.getUsername());

        if (userMapper.selectOne(queryWrapper) != null) {
            return -1; // 用户名已存在
        }
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(registerDTO.getPassword());
        user.setEmail(registerDTO.getEmail());
        user.setPhone(registerDTO.getPhone());
        user.setRole("STUDENT");
        user.setStatus(1);
        userMapper.insert(user);
        return 1;
    }

    @Override
    public String login(LoginDTO loginDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", loginDTO.getUsername());
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            return null; // 用户不存在
        }
        if (!user.getPassword().equals(loginDTO.getPassword())) {
            return null; // 密码错误
        }
        
        // 生成简单的token（实际应用中应使用更安全的方式）
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("username", user.getUsername());
        claims.put("role", user.getRole());

        return JwtUtil.generateToken(claims);
    }

}
