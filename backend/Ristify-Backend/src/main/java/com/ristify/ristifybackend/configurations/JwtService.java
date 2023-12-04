package com.ristify.ristifybackend.configurations;

import com.ristify.ristifybackend.exception.ApiRequestException;
import com.ristify.ristifybackend.utils.AppUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "e94e6954fa0c94d2aadb8dfecc4093a1bf03bdd479c5a954d73fedcbce90fa6";

    public Optional<String> extractUsername(final String token) throws ApiRequestException {
        return extractClaim(token, Claims::getSubject);
    }

    private Optional<Date> extractExpiration(final String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> Optional<T> extractClaim(final String token, final Function<Claims, T> claimsResolver) {
        Optional<Claims> claimsOptional = extractAllClaims(token);
        return claimsOptional.map(claimsResolver);
    }

    public String generateToken(final UserDetails userDetails) {
        return generateToken(null, userDetails);
    }

    public String generateToken(final Map<String, Object> claims, final UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims != null ? claims : Map.of())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(AppUtils.now())
                .setExpiration(AppUtils.nowWithDelay(AppUtils.DEFAULT_JWT_EXPIRY))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Optional<Claims> extractAllClaims(final String token) {
        try {
            return Optional.of(
                    Jwts.parserBuilder()
                            .setSigningKey(getSigningKey())
                            .build()
                            .parseClaimsJws(token)
                            .getBody());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isValidToken(final String token, final UserDetails userDetails) {
        return extractUsername(token)
                .filter(username -> username.equals(userDetails.getUsername()) && !isExpiredToken(token))
                .isPresent();
    }

    private boolean isExpiredToken(final String token) {
        return extractExpiration(token)
                .map(date -> date.before(AppUtils.now()))
                .orElse(false);
    }
}