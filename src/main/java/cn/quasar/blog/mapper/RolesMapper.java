package cn.quasar.blog.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RolesMapper {

    int insertRoleAndUser(int roleId, int userId);
}
