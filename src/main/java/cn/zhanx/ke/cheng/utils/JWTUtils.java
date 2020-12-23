package cn.zhanx.ke.cheng.utils;

import cn.zhanx.ke.cheng.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * JWT工具类
 * 注意点 1，生成的token,是可以通过通过base64进行解密出明文信息
 * 2,base64进行解密出明文信息，修改再进行编码，则会解密失败
 * 3，无法作废已颁布的token,除非改秘钥
 */
public class JWTUtils {

    //过期时间，一周
    private static final long EXPIRED = 60000*60*24*7;

    //加密秘钥
    private static final String SECRET  = "www.zhanx.cn";

    //令牌前缀
    private static final String TOKEN_PREFIX  = "zhanx";

    //subject
    private static final String SUBJECT  = "zhanx.cn";

    //根据用户信息生成令牌
    public static String generateJsonWebToken(User user){
        String token= Jwts.builder().setSubject(SUBJECT)
                .claim("head_img",user.getHeadImg())
                .claim("id",user.getId())
                .claim("name",user.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRED))
                .signWith(SignatureAlgorithm.HS256,SECRET).compact();

        return  TOKEN_PREFIX+token;
    }

    //校验token的方法
    public static Claims checkJWT(String token){
        try {
            final Claims claims =
                    Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
            return claims;
        }catch (Exception e){
            return null;
        }
    }
}
