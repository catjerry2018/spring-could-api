package com.xfy.apiall.api.controller;


import com.xfy.apiall.core.util.ResponseUtil;
import com.xfy.apiall.db.domain.LitemallUser;
import com.xfy.apiall.db.service.LitemallUserService;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 用户
 */
@RestController
@RequestMapping("/api")
@Validated
public class SecureController{
	@Autowired
	private LitemallUserService userService;

	@GetMapping("list")  //http://localhost:8089/api/list?userid=2&token=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMCIsInVzZXJuYW1lIjoieHh4eCIsImlhdCI6MTU1NTc0MDg4Mn0.bsL3X7Hxyo21GPZ7qBCGM2R7o_rPS-VLdjKsGriREtg
	public Object list(@RequestParam("userid") Integer userId,@RequestAttribute("claims") Claims claims) {
	//public Object list(@LoginUser Integer userId) {
		//获得token claims里面的用户id
		//System.out.println(claims);
		//List<LitemallUser> userList = userService.queryByUid(Integer.valueOf(claims.getSubject()));
		LitemallUser user = userService.findById(Integer.valueOf(claims.getSubject()));
		return ResponseUtil.ok(user);
	}

	//测试Zuul负载均衡
	@GetMapping("load")
	protected Object loadServer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String schema = request.getScheme();
		String serverName = request.getServerName();
		// 端口号返回的是int类型
		int serverPort = request.getServerPort();
		String contextPath = request.getContextPath();

		return ResponseUtil.ok("服务器名称:"+ serverName+"端口号："+serverPort);

     }

}
