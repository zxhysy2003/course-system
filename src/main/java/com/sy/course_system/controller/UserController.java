package com.sy.course_system.controller;

import com.sy.course_system.common.Result;
import com.sy.course_system.dto.LoginDTO;
import com.sy.course_system.dto.RegisterDTO;
import com.sy.course_system.service.UserService;
import com.sy.course_system.vo.UserVO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public Result<List<UserVO>> list() {
        return Result.success(userService.listUsers());
    }

    @GetMapping("/{id}")
    public Result<UserVO> detail(@PathVariable Long id) {
        UserVO userVO = userService.getUserById(id);
        if (userVO == null) {
            return Result.error(404, "用户不存在");
        }
        return Result.success(userVO);
    }

    @PostMapping("/register")
    public Result<String> register(@RequestBody RegisterDTO registerDTO) {
        Integer res = userService.register(registerDTO);
        if (res == null) {
            return Result.error(500, "注册失败");
        } else if (res == -1) {
            return Result.error(400, "用户名已存在");
        }
        return Result.success("注册成功");
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginDTO loginDTO) {
        String res = userService.login(loginDTO);
        if (res == null) {
            return Result.error(401, "用户名或密码错误");
        }
        return Result.success(res);
    }


}
