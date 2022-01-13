package cn.quasar.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private int roleId;
    @NotNull(message = "角色名不可以为空")
    private String roleName;
    @NotNull(message = "角色描述不可以为空")
    private String roleLabel;

    public Role(String roleName, String roleLabel) {
        this.roleName = roleName;
        this.roleLabel = roleLabel;
    }
}
