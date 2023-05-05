package dev.sheengo.weatherservice.config.security;


import dev.sheengo.weatherservice.domains.AuthUser;
import dev.sheengo.weatherservice.dto.auth.TokenResponse;
import dev.sheengo.weatherservice.enums.TokenType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import static dev.sheengo.weatherservice.enums.TokenType.*;

import java.security.Key;
import java.util.Date;


@Component
public class JwtTokenUtil {


    @Value("${jwt.access.token.expiry}")
    private long accessTokenExpiry;

    @Value("${jwt.access.token.secret.key}")
    public String ACCESS_TOKEN_SECRET_KEY;


    @Value("${jwt.refresh.token.expiry}")
    private long refreshTokenExpiry;

    @Value("${jwt.refresh.token.secret.key}")
    public String REFRESH_TOKEN_SECRET_KEY;

    public TokenResponse generateToken(@NonNull String username, @NonNull AuthUser.ROLES role) {
        TokenResponse tokenResponse = new TokenResponse();
        generateAccessToken(username, role,tokenResponse);
        generateRefreshToken(username, role, tokenResponse);
        return tokenResponse;
    }

    public void generateRefreshToken(@NonNull String username, @NonNull AuthUser.ROLES role, @NonNull TokenResponse tokenResponse) {
        tokenResponse.setRefreshTokenExpiry(new Date(System.currentTimeMillis() + refreshTokenExpiry));
        String refreshToken = Jwts.builder()
                .setSubject(username+"//"+role)
                .setIssuedAt(new Date())
                .setIssuer("Elbek Kholmatov")
                .setExpiration(tokenResponse.getRefreshTokenExpiry())
                .signWith(signKey(REFRESH), SignatureAlgorithm.HS256)
                .compact();
        tokenResponse.setRefreshToken(refreshToken);
    }

    public TokenResponse generateAccessToken(@NonNull String username, @NonNull AuthUser.ROLES role, @NonNull TokenResponse tokenResponse) {
        tokenResponse.setAccessTokenExpiry(new Date(System.currentTimeMillis() + accessTokenExpiry));
        String accessToken = Jwts.builder()
                .setSubject(username+"//"+role)
                .setIssuedAt(new Date())
                .setIssuer("Elbek Kholmatov")
                .setExpiration(tokenResponse.getAccessTokenExpiry())
                .signWith(signKey(ACCESS), SignatureAlgorithm.HS512)
                .compact();
        tokenResponse.setAccessToken(accessToken);
        return tokenResponse;
    }

    public boolean isValid(@NonNull String token, TokenType tokenType) {
        try {
            Claims claims = getClaims(token, tokenType);
            Date expiration = claims.getExpiration();
            return expiration.after(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getUsername(@NonNull String token, TokenType tokenType) {
        Claims claims = getClaims(token, tokenType);
        String[] split = claims.getSubject().split("//");
        return split[0];
    }

    public AuthUser.ROLES getRole(@NonNull String token, TokenType tokenType) {
        Claims claims = getClaims(token, tokenType);
        String[] split = claims.getSubject().split("//");
        return AuthUser.ROLES.valueOf(split[1]);
    }

    private Claims getClaims(String token, TokenType tokenType) {
        return Jwts.parserBuilder()
                .setSigningKey(signKey(tokenType))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key signKey(TokenType tokenType) {
        byte[] bytes = Decoders.BASE64.decode(tokenType.equals(ACCESS) ? ACCESS_TOKEN_SECRET_KEY : REFRESH_TOKEN_SECRET_KEY);
        return Keys.hmacShaKeyFor(bytes);
    }

    public Date getExpiry(String token, TokenType tokenType) {
        Claims claims = getClaims(token, tokenType);
        return claims.getExpiration();
    }
}
