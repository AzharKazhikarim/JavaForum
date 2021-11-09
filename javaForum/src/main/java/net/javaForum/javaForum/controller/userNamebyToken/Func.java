package net.javaForum.javaForum.controller.userNamebyToken;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class Func {

    public String getUsernameByToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(token);
                String username = decodedJWT.getSubject();
                return username;
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("access_token", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }
        return "";
    }

    public DecodedJWT getInfoByToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(token);
                return  decodedJWT;
//                username = decodedJWT.getSubject();
//                roles = decodedJWT.getClaim("roles").asArray(String.class);
//                            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//                            stream(roles).forEach(role -> {
//                                authorities.add(new SimpleGrantedAuthority(role));
//                            });
                /// UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                //SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                //filterChain.doFilter(request, response);
            } catch (Exception exception) {
                throw new Exception();
//                            log.error("Error logging in {}", exception.getMessage());
//                            response.setHeader("error", exception.getMessage());
//                            response.setStatus(FORBIDDEN.value());
//                            //response.sendError(FORBIDDEN.value());
//                            Map<String, String> error = new HashMap<>();
//                            error.put("access_token", exception.getMessage());
                // response.setContentType(APPLICATION_JSON_VALUE);
                // new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new Exception();
        }
    }
}
