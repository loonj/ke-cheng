package cn.zhanx.ke.cheng;

import cn.zhanx.ke.cheng.domain.User;
import cn.zhanx.ke.cheng.service.UserService;
import cn.zhanx.ke.cheng.utils.JWTUtils;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KeChengApplicationTests {

	@Autowired
	private UserService userService;
	@Test
	void contextLoads() {
		User user=userService.findByPhone("13395555451");
		String token= JWTUtils.generateJsonWebToken(user);
		System.out.println(token);

		Claims claims=JWTUtils.checkJWT(token);
		System.out.println(claims.get("name"));
		System.out.println(claims.get("head_img"));


	}

}
