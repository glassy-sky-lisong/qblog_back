package cn.quasar.blog.mapper;

import cn.quasar.blog.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> getUsers();

    /**
     *
     * @param userName stirng
     * @param pwd md5 string
     * @param birthday datetime string
     * @param gender 0 - man, 1 - woman
     * @param gender 0 - man, 1 - woman
     * @param email user email
     * @return affect row
     */
    int insertUser(User user);

    /**
     *  查询认证信息
     * @param username
     * @return
     */
    User queryUserByName(String username);

    /**
     * 查询用户表
     * @param username
     * @return
     */
    User selectUserByName(String username);

    /**
     * 注销用户
     * @param username
     * @return
     */
    int deleteUser(String username);

    /**
     * 修改某id的用户字段信息
     * @param user
     * @return
     */
    int updateUserById(User user);
}
