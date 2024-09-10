package com.kdu.smarthome.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtSecurityProviderService {


    private static final String SECRET_KEY = "dd307dd1f7e60eb498704935c0c6438a18700e0bcb1aea06cbd50f6e43d94593";

    /**
     * Extracts the username from the JWT token.
     * @param token The JWT token from which to extract the username.
     * @return The username extracted from the token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    /**
     * Extracts a claim from the JWT token.
     * @param token           The JWT token from which to extract the claim.
     * @param claimsResolver  The function to resolve the claim from the token's claims.
     * @param <T>             The type of the claim.
     * @return The extracted claim.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    /**
     * Generates a JWT token for the given UserDetails.
     * @param userDetails The UserDetails for which to generate the token.
     * @return The generated JWT token.
     */
    public String generateToken(UserDetails userDetails) {
        return Jwts
                .builder()
                .header()
                .add("typ", "JWT")
                .and()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSignInKey())
                .compact();
    }
    /**
     * Validates if a JWT token is valid for the given UserDetails.
     * @param token       The JWT token to validate.
     * @param userDetails The UserDetails to validate against.
     * @return True if the token is valid for the UserDetails, otherwise false.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
    /**
     * Checks if a JWT token is expired.
     *
     * @param token The JWT token to check for expiration.
     * @return True if the token is expired, otherwise false.
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    /**
     * Extracts the expiration date from the JWT token.
     * @param token The JWT token from which to extract the expiration date.
     * @return The expiration date extracted from the token.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    /**
     * Extracts all claims from the JWT token.
     * @param token The JWT token from which to extract all claims.
     * @return All claims extracted from the token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Retrieves the secret key used for signing and verifying JWT tokens.
     * @return The secret key used for JWT token operations.
     */
    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
