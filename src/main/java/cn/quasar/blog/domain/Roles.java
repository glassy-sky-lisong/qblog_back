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
public class Roles {

    private int id;
    @NotNull(message = "role_id不可以为空")
    private int roleId;
    @NotNull(message = "user_id不可以为空")
    private int userId;

    public Roles(int roleId, int userId) {
        this.roleId = roleId;
        this.userId = userId;
    }
}
