package cn.quasar.blog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FormUser extends User{

    @NotNull(message="角色名不可以为空")
    private String role;

    public FormUser(String username, String password, Timestamp birthday, int gender, String email, String role) {
        this.role = role;
        super.setUsername(username);
        super.setPassword(password);
        super.setGender(gender);
        super.setBirthday(birthday);
        super.setEmail(email);
        super.setEnabled(true);
        super.setAccountNonExpired(true);
        super.setAccountNonLocked(true);
        super.setCredentialsNonExpired(true);
    }

    public FormUser(String username, String password, Timestamp birthday, int gender, String email, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled, String role) {
        this.role = role;
        super.setUsername(username);
        super.setPassword(password);
        super.setGender(gender);
        super.setBirthday(birthday);
        super.setEmail(email);
        super.setEnabled(true);
        super.setAccountNonExpired(accountNonExpired);
        super.setAccountNonLocked(accountNonLocked);
        super.setCredentialsNonExpired(credentialsNonExpired);
    }
}
