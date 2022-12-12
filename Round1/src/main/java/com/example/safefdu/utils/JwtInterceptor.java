package com.example.safefdu.utils;

import org.springframework.web.servlet.HandlerInterceptor;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// 处理 Jwt 拦截器结果 验证 user 身份

// implements 方式继承父类 HandlerInterceptor
// HandlerInterceptor 来自 Spring 框架的方法类 用于处理拦截器
// HandlerInterceptor 包含待实现接口 preHandle

public class JwtInterceptor implements HandlerInterceptor {

//    @Autowired
//    private StudentService studentService;
//    @Autowired
//    private InstructorService instructorService;
//
//    private static Map<String,String> map = new HashMap<>();
//
//    /*	preHandle 处理请求之前调用（在Controller处理前） 预处理
//     	preHandle 参数
//    		HttpServletRequest 接收请求 request
//    		HttpServletResponse 流程中断后通过 response 进行响应
//    		handler 表示负责响应的 Controller
//    */
//
//    public static void addUserToken(String uid,String token){
//        map.put(uid,token);
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//
//        // HttpServletRequest getHeader 方法用于获取一个指定头字段的值
//        String token = request.getHeader("token");
//
//        // 判断是否映射到方法 如果不是映射到方法直接通过
//        if(!(handler instanceof HandlerMethod)){
//            return true;
//        }
//
//
//        // 执行认证
//        if (StrUtil.isBlank(token)) {	// StrUil：hutool 提供的字符串工具类 isBlank方法判断是否为空字符串
//            throw new ServiceException(Constants.CODE_401, "无token，请重新登录");
//        }
//
//        // 获取 token 中的 user id
//        String userId;
//        try {
//            // 使用 auth0 中的 jwt 解析方法
//            userId = JWT.decode(token).getAudience().get(0);
//            if(!Objects.equals(map.get(userId), token))
//                throw new ServiceException(Constants.CODE_402, "验证已过期，请重新登录");
//        } catch (JWTDecodeException j) {
//            throw new ServiceException(Constants.CODE_401, "token验证失败，请重新登录");
//        }
//
//        // 根据token中的userid查询数据库
//        Student student = studentService.getById(userId);
//        Instructor instructor = instructorService.getById(userId);
//        if (student == null && instructor == null) {
//            throw new ServiceException(Constants.CODE_401, "用户不存在，请重新登录");
//        }
//
//        // 用户密码加签验证 token
//        // JWTVerifier：auth0 提供的验证方法
//        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
//        try {
//            jwtVerifier.verify(token); // 验证token
//        } catch (JWTVerificationException e) {
//            throw new ServiceException(Constants.CODE_401, "token验证失败，请重新登录");
//        }
//        return true;
//    }
}
