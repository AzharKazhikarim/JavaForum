package net.javaForum.javaForum.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaForum.javaForum.controller.userNamebyToken.Func;
import net.javaForum.javaForum.model.Ad;
import net.javaForum.javaForum.model.Question;
import net.javaForum.javaForum.model.Role;
import net.javaForum.javaForum.model.User;
import net.javaForum.javaForum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController

@RequestMapping("/user")
@Slf4j
public class UserController{

    @Autowired
    UserService userService;
    Func func = new Func();
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //CREATE
    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        boolean saving = userService.createUser(user);
        if (saving) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return   ResponseEntity.badRequest().body("Bad request");
    }

    //UPDATE
    @PutMapping("/update")
    public ResponseEntity<?> update(HttpServletRequest request, HttpServletResponse response,@RequestBody User user) throws IOException {
        String username = func.getUsernameByToken(request,response);
        User requestUser = userService.update(username,user);
        if(requestUser!=null){
            return new ResponseEntity(user,HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("User does not exists");
    }

    //GET
    @GetMapping("/get-user/{username}")
    public ResponseEntity<?> read(@PathVariable String username) throws IOException {
        User user = userService.getUser(username);
        if(user!=null){
            return new ResponseEntity(user,HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("User does not exists");
    }



    @GetMapping("/get-profile")
    public ResponseEntity<?> getProfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = func.getUsernameByToken(request,response);
        User user = userService.getUser(username);
        if(user!=null){
            return new ResponseEntity(user,HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("User does not exists");
    }

    //DELETE
    @DeleteMapping("/delete-user")
    public boolean delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = func.getUsernameByToken(request,response);
        return userService.delete(username);
    }


    @GetMapping("/get-list-que")
    public ResponseEntity<Question> getListQue(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = func.getUsernameByToken(request,response);

        return new ResponseEntity(userService.getQuestionByEmail(username),HttpStatus.OK);
    }

    @GetMapping("/get-list-ad")
    public ResponseEntity<Ad> getListAd(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = func.getUsernameByToken(request,response);
        return new ResponseEntity(userService.getAdByEmail(username),HttpStatus.OK);
    }


    @PostMapping("role/save")
    public ResponseEntity<Role> saveRole (@RequestBody Role role){
        return ResponseEntity.ok(userService.saveRole(role));
    }
    @PostMapping("/role/addtouser")
    public ResponseEntity<Role>addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getEmail(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer ")){
            try{
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userService.getUser(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);

                Map<String,String> tokens = new HashMap<>();
                tokens.put("access_token",access_token);
                tokens.put("refresh_token",refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);

            }catch (Exception e){
                response.setHeader("error",e.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String,String> error = new HashMap<>();
                error.put("error_message",e.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),error);
            }

        }else{
          throw new RuntimeException("Refresh token is missing");
        }
    }



}

@Data
class RoleToUserForm{
    private String email;
    private String roleName;
}