package com.example.gateway;

import org.springframework.boot.web.server.Cookie;
import org.springframework.http.ResponseCookie;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class CsrfCookieWebFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String key = CsrfToken.class.getName();
        Mono<CsrfToken> csrfToken = null != exchange.getAttribute(key) ? exchange.getAttribute(key) : Mono.empty();
        return csrfToken.doOnSuccess(token -> {
            ResponseCookie cookie = ResponseCookie.from("X-XSRF-TOKEN", token.getToken())
                    .maxAge(Duration.ofHours(1))
                    .httpOnly(false)
//                    .secure(true) must be enabled for production use
                    .path("/")
//                    .sameSite(Cookie.SameSite.LAX.attributeValue())
                    .sameSite(Cookie.SameSite.STRICT.attributeValue())
                    .build();
            exchange.getResponse().getCookies().add("X-XSRF-TOKEN", cookie);
        }).then(chain.filter(exchange));
    }
}