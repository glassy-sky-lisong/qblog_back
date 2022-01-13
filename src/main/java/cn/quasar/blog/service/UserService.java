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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        int i = userMapper.updateUserById(user);
        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), "更新成功", "");
    }
}
