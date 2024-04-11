package com.betrybe.agrix.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * JWT Token Service.
 */

@Service
public class TokenService {

  private final Algorithm algorithm;

  public TokenService(@Value("${api.security.token.secret}") String secret) {
    this.algorithm = Algorithm.HMAC256(secret);
  }

  /**
   * Generates a JWT Token.
   */

  public String generateToken(String username) {
    return JWT.create()
            .withSubject(username)
            .withExpiresAt(generateExpiration())
            .sign(algorithm);
  }

  /**
   * Generates expiration for an JWT Token.
   */

  private Instant generateExpiration() {
    return Instant.now()
            .plus(2, ChronoUnit.HOURS);
  }

  /**
   * Validates a JWT Token.
   */

  public String validateToken(String token) {
    return JWT.require(algorithm)
            .build()
            .verify(token)
            .getSubject();
  }
}
