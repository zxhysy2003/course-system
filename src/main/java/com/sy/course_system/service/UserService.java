package com.sy.course_system.service;
import com.sy.course_system.dto.LoginDTO;
import com.sy.course_system.dto.UserRegisterDTO;
import com.sy.course_system.vo.UserVO;


import java.util.List;

public interface UserService {
    List<UserVO> listUsers();

    UserVO getUserById(Long id);

    Integer register(UserRegisterDTO registerDTO);

    String login(LoginDTO loginDTO);
    
}
