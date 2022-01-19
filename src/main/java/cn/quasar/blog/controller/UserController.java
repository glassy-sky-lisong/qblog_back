package cn.quasar.blog.controller;

import cn.quasar.blog.domain.FormUser;
import cn.quasar.blog.domain.User;
import cn.quasar.blog.dto.MessageResult;
import cn.quasar.blog.dto.MessageStatus;
import cn.quasar.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registry")
    public MessageResult registry(@Valid FormUser user) {
        return userService.registryService(user);
    }

    @GetMapping("/article")
    public MessageResult getUser (@RequestParam("username") String userName) {
        System.out.println(userName);

        return new MessageResult(200, MessageStatus.SUCCESS.getStatus(), userName, "");
    }

    @RequestMapping(value = "/cancel/{username}", method = RequestMethod.GET)
    public MessageResult cancelUser (@PathVariable String username) {
        System.out.println("username = " + username);
        return userService.cancelUser(username);
    }

    @RequestMapping(value = "/current-user", method = RequestMethod.GET)
    public MessageResult getCurrentUser(Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        return new MessageResult(HttpStatus.OK.value(), MessageStatus.SUCCESS.getStatus(), currentUser, "");
    }

    @PostMapping(value = "/update")
    public MessageResult updateUser(User user) {
        return userService.updateUser(user);
    }

}
