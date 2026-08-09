package com.example.learn_english_app.config.jwt;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class JwtHandler {
    private final RSAPrivateKey pri;

    private final RSAPublicKey pub;

    public JwtHandler(@Value("${jwt.key.pri}") RSAPrivateKey pri,
                      @Value("${jwt.key.pub}") RSAPublicKey pub) {
        this.pri = pri;
        this.pub = pub;
    }

    @Bean
    public JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(pub).build();
    }
    @Bean
    public JwtEncoder jwtEncoder(){
        var jwt = new RSAKey.Builder(pub).privateKey(pri).build();
        var jwts = new ImmutableJWKSet<>(new JWKSet(jwt));
        return new NimbusJwtEncoder(jwts);
    }
}
