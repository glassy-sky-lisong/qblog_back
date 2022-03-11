package cn.quasar.blog.service;

import cn.quasar.blog.domain.FormUser;
import cn.quasar.blog.domain.Role;
import cn.quasar.blog.domain.User;
import cn.quasar.blog.dto.MessageResult;
import cn.quasar.blog.dto.MessageStatus;
import cn.quasar.blog.exception.CustomException;
import cn.quasar.blog.mapper.RoleMapper;
import cn.quasar.blog.mapper.RolesMapper;
import cn.quasar.blog.mapper.UserMapper;
import cn.quasar.blog.utils.AssertUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    private RolesMapper rolesMapper;

    @Resource
    private AssertUtils utils;

    public MessageResult registryService(FormUser user) {
        MessageResult result;
//        获取role信息
        Role role = roleMapper.queryRoleByName(user.getRole());

//          不存在就抛出一个异常
        if(role != null) {
            user.setRoleId(role.getRoleId());
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);
            user.setEnabled(true);

            int row = addUser(user);
            if (row == 0) throw new CustomException("注册中断，出现数据库异常，请检查sql");

            User user1 = userMapper.selectUserByName(user.getUsername());

            result = new MessageResult(200, MessageStatus.SUCCESS.getStatus(), "角色注册成功", new ArrayList<String>());
        } else throw new CustomException("注册失败，角色名不存在，请检查后在注册");

        return result;
    }

    public MessageResult cancelUser(String userName) {
        MessageResult result;
        if (!"".equals(userName)) {
            int row = userMapper.deleteUser(userName);

            if (row != 1)  throw new CustomException("注销服务出现异常: 此用户不存在");
            result = new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), "注销成功", "");
        } else throw new CustomException("注销用户名称不能为空");

        return result;
    }

    public int addUser(FormUser user) {
        User user1 = (User) user;

        String s = passwordEncoder.encode(user1.getPassword());
        user1.setPassword(s);

        return userMapper.insertUser(user);
    }

    public MessageResult updateUser(User user) {
        if (user.getId() == 0) throw new CustomException("缺少必要参数user：id");

        if (user.getPassword() != null && user.getPassword().equals(equals(""))) {
//            TODO yanzheng

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            user.setPassword(encoder.encode(user.getPassword()));
        }

        String msg = "";
        int i = userMapper.updateUserById(user);

        if (i <= 0) {
            msg = "更新失败";
        }
        if (i == 1) {
            msg = "更新成功";
        }
        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), msg, "");
    }

    public MessageResult resetUserPwd(User user) {
        utils.customExceptionHandler(user.getUsername() == null || user.getUsername().equals(""), "请提供用户名")
                .customExceptionHandler(user.getEmail() == null || user.getEmail().equals(""), "请提供email地址");

        String msg = "";
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        User newUser = new User();
        User qUser = userMapper.queryUserByName(user.getUsername());

        if (encoder.matches(qUser.getPassword(), encoder.encode("950714ls"))) {
            new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), "暂不需要重置", "");
        }

        utils.customExceptionHandler(qUser == null, "请提供正确的用户名")
                .customExceptionHandler(!user.getEmail().equals(qUser.getEmail()), "邮箱不正确");

        String newPwd = encoder.encode("950714ls");


        newUser.setId(qUser.getId());
        newUser.setPassword(newPwd);

        int i = userMapper.updateUserById(newUser);

        if(i <= 0) {
            msg = "密码重置失败";
        }else {
            msg = "密码重置成功";
        }

        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), msg, "");
    }
}
