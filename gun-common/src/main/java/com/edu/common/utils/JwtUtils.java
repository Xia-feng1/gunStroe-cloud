package com.edu.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

public class JwtUtils {
    //设置token有效期时间单位毫秒
    public static final Long JWT_TTL = 168 * 60 * 60 * 1000L;
    //设置秘钥明文
    public static final String JWT_KEY = "xf";

    //设置jwt的唯一id
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

    /**
     * 生成加密后的秘钥 secretKey
     *
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 构建jwt生成器方法
     *
     * @param subject   要放入jwt中的信息
     * @param ttlMillis jwt有效期时间
     * @param uuid      jwt的唯一的id
     * @return
     */
    public static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        //配置加密算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        //设置签发时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //判断是否是输入了有效期时间,没有则使用默认的时间
        if (ttlMillis == null) {
            ttlMillis = JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)              //唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer("xf")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate); //设置过期时间
    }

    /**
     * 重载生成方法 只传要放入的字符串
     *
     * @param subject token中要存放的数据（json格式）
     * @return
     */
    public static String createJWT(String subject) {
        JwtBuilder jwtBuilder = getJwtBuilder(subject, null, getUUID());
        String token = jwtBuilder.compact();
        return token;
    }

    /**
     * 重载生成方法2 只传要放入的字符串和过期时间
     *
     * @param subject   token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return
     */
    public static String createJWT(String subject, Long ttlMillis) {
      JwtBuilder jwtBuilder = getJwtBuilder(subject, ttlMillis, getUUID());
      String token = jwtBuilder.compact();
        return token;
    }

    /**
     * 解析jwt字符串
     * @param jwt 待解析的的jwt字符串
     * @return
     */
    public static Claims parseJwt(String jwt) throws Exception{
        //获取私钥
        final SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)//设置解密私钥
                .parseClaimsJws(jwt)//放入待解密的jwt字符串
                .getBody();//获取存入的信息体
    }
//测试方法
    public static void main(String[] args) throws Exception {
        //生成jwt
//        final String admin = createJWT("admin1111111111111");
//        System.out.println(admin);
        //解析jwt
        System.out.println(parseJwt("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI3MzBmNDJmZmFhMmI0MzA5ODFlZjg4OGJjMWRkMDc0OCIsInN1YiI6IjE3NjUzNTQyNDMxMTE4NDk5ODUiLCJpc3MiOiJ4ZiIsImlhdCI6MTcwOTg4NDQ3NywiZXhwIjoxNzEwNDg5Mjc3fQ.H1r4z2Uj2rv-BvDURqmDcvassJh482GB2BSY8AR9nSs").getSubject());
    }

}
