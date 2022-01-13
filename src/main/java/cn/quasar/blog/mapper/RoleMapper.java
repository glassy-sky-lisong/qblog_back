package cn.quasar.blog.mapper;

import cn.quasar.blog.domain.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper {

    int insertRole(Role role);

    boolean isRole(String roleName);

    Role queryRoleByName(String roleName);
}
