package com.xfy.apiall.api.controller;

import com.xfy.apiall.db.domain.LitemallUser;
import com.xfy.apiall.db.service.LitemallUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.servlet.ServletException;
import java.util.Date;

/**
 * 用户登录验证获取token
 */
@RestController
@RequestMapping("/")
@Validated
public class UserController {

	@Autowired
	private LitemallUserService userService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(LitemallUser login) throws ServletException {

		String jwtToken = "";

		if (login.getUsername() == null || login.getPassword() == null) {
			throw new ServletException("Please fill in username and password");
		}

		String username = login.getUsername();
		String password = login.getPassword();

        System.out.print(username);

		LitemallUser user = userService.findByUsername(username);

		if (user == null) {
			throw new ServletException("User email not found.");
		}

		String pwd = user.getPassword();

		if (!password.equals(pwd)) {
			throw new ServletException("Invalid login. Please check your name and password.");
		}

		jwtToken = Jwts.builder().setSubject(user.getId().toString()).claim("username", username).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();

		return jwtToken;
	}




}
