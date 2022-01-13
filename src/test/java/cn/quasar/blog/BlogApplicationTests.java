package cn.quasar.blog;

import cn.quasar.blog.domain.User;
import cn.quasar.blog.mapper.RoleMapper;
import cn.quasar.blog.mapper.RolesMapper;
import cn.quasar.blog.mapper.UserMapper;
import cn.quasar.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@Slf4j
class BlogApplicationTests {


	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private RolesMapper rolesMapper;

	@Autowired
	UserService userService;


	@Test
	void contextLoads() {
	}

	@Test
	void getUsers() {
		for (User user : userMapper.getUsers()) {
			System.out.println(user);
		}

	}

	@Test
	void queryUserByName() {
		User user = userMapper.queryUserByName("mark");
		System.out.println(user);
	}

	@Test
	void insertUser() {
//        User u = new User();
//        u.setUsername("abc");
//        String s = passwordEncoder.encode("123456");
//        u.setPassword(s);
//        u.setBirthday(new Timestamp(new Date().getTime()));
//        u.setEmail("1990691193@qq.com");
//        u.setCredentialsNonExpired(true);
//        u.setAccountNonLocked(true);
//        u.setAccountNonExpired(true);
//        u.setEnabled(true);
//        int row = userMapper.insertUser(u);
//        System.out.println(row);
	}

	@Test
	void insertRole() {
//        int row = roleMapper.insertRole(new Role("god", "vip"));
//        System.out.println(row);
	}

	@Test
	void insertRoles() {
//        int row = rolesMapper.insertRoleAndUser(4, 4);
//        System.out.println(row);
	}

	@Test
	void testUserService() {
//        FormUser user = new FormUser();
//        String s = passwordEncoder.encode("123456");
//        user.setUsername("mark");
//        user.setPassword(s);
//        user.setBirthday(new Timestamp(new Date().getTime()));
//        user.setGender(1);
//        user.setEmail("1990691193@qq.com");
//        user.setRole("user");
//        userService.registryService(user);
	}

	@Test
	void selectUserByName() {
//        User admin = userMapper.selectUserByName("admin");
//        System.out.println(admin);
	}

//    @Test
//    void comparekeys() {
//        SecretKey key1 = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//        SecretKey key2 = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//
//        System.out.println(key1.toString());
//        System.out.println(key2.toString());
//    }

//    @Test
//    void JwttDemo() throws InterruptedException {
//        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//        String token = Jwts.builder()
//          .claim("authories", "admin")
//          .setSubject("jack")
//          .setIssuedAt(new Date(System.currentTimeMillis() + 3 * 60 * 1000))
//          .setIssuer("jack")
//          .setSubject("{\"username\": \"jack\", \"age\": \"12\"}")
//          .signWith(key)
//          .compact();
//
//        System.out.println(token);
//
//        Jws<Claims> claims = Jwts.parser().setSigningKey(key)
//          .parseClaimsJws(token);
//
//        Thread.sleep(4 * 60 * 1000);
//
//        String subject = claims.getBody().getSubject();
//        System.out.println(subject);
//        Date atDate = claims.getBody().getIssuedAt();
//        boolean isExpiration = atDate.before(new Date(System.currentTimeMillis()));
//        System.out.println(isExpiration);
//    }

//    @Test
//    void updateUserById() {
//        User user = new User();
//
//        user.setId(17);
//        user.setUsername("ttd");
//        user.setGender(0);
//        userMapper.updateUserById(user);
//    }

}
