package com.white.Jwt;

import com.whit.password.IPassWordUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.Serial;
import java.io.Serializable;
import java.security.Key;
import java.util.Date;

/**
 * @author 陈浩
 * @cread Talk is cheap. Show me the code
 * @date 2020/12/26 16:06
 */
@ConfigurationProperties(prefix = "jwt")
@Component
public class JwtTokenUtil implements Serializable {
    @Serial
    private static final long serialVersionUID = -5806646224587899096L;
    private final String secret;
    private Long expiration;
    private String header;

    {
        String temp;
        try {
            temp = System.getProperty("secret").trim();
        } catch (Exception e) {
            IPassWordUtil util = IPassWordUtil.getInstance();
            temp = util.getSalt();
        }
        secret = temp;
    }


    /**
     * 从令牌获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUserNameFromToken(String token) {
        String userName;
        try {
            Claims claims = getClaimsFromToken(token);
            userName = claims.getSubject();
        } catch (Exception e) {
            userName = null;
        }
        return userName;
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expirations = claims.getExpiration();
            return expirations.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 刷新token
     *
     * @param token 旧的令牌
     * @return 新的令牌
     */
    public String redRushToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            refreshedToken = generateToken(claims.getSubject());
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 验证令牌
     *
     * @param token 令牌
     * @return 是否有效
     */
    public Boolean validateToken(String token, String userName) {
        String newUserName = getUserNameFromToken(token);
        return (userName.equals(newUserName) && !isTokenExpired(token));
    }

    /**
     * 从claims生成令牌
     *
     * @return 令牌
     */
    public String generateToken(String userName) {
        final SignatureAlgorithm hs256 = SignatureAlgorithm.HS256;
        Date date = new Date(System.currentTimeMillis());
        Date expirationDateTime = new Date(System.currentTimeMillis() + expiration);
        byte[] pwd = DatatypeConverter.parseBase64Binary(secret);
        Key signingKey = new SecretKeySpec(pwd, hs256.getJcaName());
        return Jwts.builder()
                .setId(userName)
                .setIssuedAt(date)
                .setSubject(userName)
                .setExpiration(expirationDateTime)
                .signWith(signingKey,SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims =
                    Jwts.parser().setSigningKey( DatatypeConverter.parseBase64Binary(secret)).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }


    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
