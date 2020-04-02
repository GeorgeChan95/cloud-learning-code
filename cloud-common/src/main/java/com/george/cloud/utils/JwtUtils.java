package com.george.cloud.utils;

import cn.hutool.core.codec.Base64;
import com.george.cloud.model.RequestSubject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


/**
 * <p>
 *     JWT工具类
 *     生成token，解析token
 * </p>
 *
 * @author GeorgeChan 2020/2/3 14:48
 * @version 1.0
 * @since jdk1.8
 */
@ConfigurationProperties("jwt.config")
@Data
@Component
public class JwtUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);
    /**
     * 跳过认证的地址
     */
    private String[] skipAuthUrls;
    /**
     * 有效时长
     */
    private long effectiveTime;
    /**
     * token加密的key
     */
    private String secretKey;
    /**
     * 认证签发人
     */
    private String issuer;



    /**
     * 获取字符串加密生成的key
     * @return key
     */
    public SecretKey generaKey() {
        byte[] encodeKey = Base64.decode(secretKey);
        SecretKeySpec key = new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");
        return key;
    }

    /**
     * 创建JWT
     * @param id 设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
     * @param issuer jwt签发人
     * @param subject 代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
     * @param ttlMillis token的超时时长
     * @param claims 自定义放在jwt中携带的数据，不建议把敏感数据放在里面
     * @return token字符串
     */
    public String createJWT(String id, String issuer, String subject, long ttlMillis, Map<String, Object> claims) {
        // 指定签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 生成签名密钥secretKey
        SecretKey key = generaKey();

        // 为payload添加各种标准声明和私有声明了
        // .builder 就是new一个JwtBuilder，设置jwt的body
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setId(id)
                .setIssuedAt(now)
                .setIssuer(issuer)
                .setSubject(subject)
                .signWith(signatureAlgorithm, key);

        // 设置过期时间
        if (ttlMillis > 0) {
            long expMillis = nowMillis + ttlMillis;
            Date expireTime = new Date(expMillis);
            builder.setExpiration(expireTime);
        }
        return builder.compact();
    }

    /**
     * token解析
     * @param jwt token字符串
     * @return 解析后的token对象
     */
    public Claims parseJWT(String jwt) {
        // 签名密钥，和加密时的一致
        SecretKey key = generaKey();
        Claims claims = Jwts.parser() // 得到得到DefaultJwtParser
                .setSigningKey(key)   // 设置签名的秘钥
                .parseClaimsJws(jwt).getBody(); // 设置需要解析的jwt
        return claims;
    }

    /**
     * 检查token是否合法
     * @param jwtToken token字符串
     * @return 是否合法，true是
     */
    public boolean checkToken(String jwtToken) {
        Claims claims = parseJWT(jwtToken);
        // token主体
        String subject = claims.getSubject();
        String issuer = claims.getIssuer();
        Object role = claims.get("role");
        // 校验token的过期时间
        Date expiration = claims.getExpiration();
        if (LocalDate.now().toEpochDay() > expiration.getTime()) {
            throw new RuntimeException("token过期");
        }
        return true;
    }

    /**
     * 根据token获取当前登录用户的信息
     * @param token token字符串
     * @return 返回当前登录用户对象
     */
    public RequestSubject get(String token) {
        RequestSubject subject = new RequestSubject();
        try {
            Claims claims = parseJWT(token);
            ArrayList roles = (ArrayList) claims.get("role");
            subject.setRoles(roles);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return subject;
    }

}
