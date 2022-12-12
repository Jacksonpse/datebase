package com.example.safefdu.utils;


import org.springframework.stereotype.Component;


@Component
public class TokenUtils {

//    private static UserService staticUserService;
//
//    @Resource
//    private UserService userService;
//
//    @PostConstruct
//    public void setUserService() {
//        staticUserService = userService;
//    }
//
//    /**
//     * 生成token
//     *
//     * @return String
//     */
//    public static String genToken(String uid, String sign) {
//        return JWT.create().withAudience(uid) // 将 uid 保存到 token 里面,作为载荷
//                .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) // 2小时后token过期
//                .sign(Algorithm.HMAC256(sign)); // 以 password 作为 token 的密钥
//    }
//
//    /**
//     * 获取当前登录的用户信息
//     *
//     * @return user对象
//     */
//    public static User getCurrentUser() {
//        try {
//            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//            String token = request.getHeader("token");
//            if (StrUtil.isNotBlank(token)) {
//                String uid = JWT.decode(token).getAudience().get(0);
//                return staticUserService.getById(uid);
//            }
//        } catch (Exception e) {
//            return null;
//        }
//        return null;
//    }
}
