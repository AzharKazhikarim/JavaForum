package net.javaForum.javaForum.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.javaForum.javaForum.controller.userNamebyToken.Func;
import net.javaForum.javaForum.model.Ad;
import net.javaForum.javaForum.model.Answer;
import net.javaForum.javaForum.model.Question;
import net.javaForum.javaForum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {
    @Autowired
    PostService postService;
    Func func = new Func();

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //SAVE QUE AND AD
    @PostMapping("/save-que")
    public ResponseEntity<?> saveQue(@RequestBody Question question, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = func.getUsernameByToken(request, response);
        if (postService.saveQuestion(username, question)) {
            return new ResponseEntity<>(postService.getQuestion(question.getId()), HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("Bad request");
    }

    @PostMapping("/save-ad")
    public ResponseEntity<?> saveQue(@RequestBody Ad ad, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = func.getUsernameByToken(request, response);
        if (postService.postAd(username, ad)) {
            return new ResponseEntity<>(postService.getAd(ad.getId()), HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("Bad request");
    }

    //ANSWER TO SPECIFIC QUESTION
    @PostMapping("/answer")
    public ResponseEntity<?> saveAnswer(@RequestParam Long queId, @RequestBody Answer answer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = func.getUsernameByToken(request, response);
        if (postService.saveAnswer(queId, answer, username)) {

            return new ResponseEntity<>(postService.getQuestion(answer.getQue_id()), HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("Bad request");
    }


    // GET SPECIFIC QUE AND AD
    @GetMapping("/get-que")
    public ResponseEntity<?> getQue(@RequestParam Long id) {
        Question questionReq = postService.getQuestion(id);
        if (questionReq != null) {
            return new ResponseEntity<>(questionReq, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("Bad request");

    }

    @GetMapping("/get-ad")
    public ResponseEntity<?> getAd(@RequestParam Long id) {
        Ad reqAd = postService.getAd(id);
        if (reqAd != null) {
            return new ResponseEntity<>(reqAd, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body("Bad request");

    }

    //DELETE SPECIFIC QUE, AD, ANSWER
    @DeleteMapping("/delete-ad")
    public boolean deleteAd(@RequestParam Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String username = func.getUsernameByToken(request, response);
        return postService.deleteAd(id, username);
    }

    @DeleteMapping("/delete-que")
    public boolean deleteQue(@RequestParam Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = func.getUsernameByToken(request, response);
        return postService.deleteQue(id, username);
    }

    @DeleteMapping("/delete-ans")
    public boolean deleteAnswer(@RequestParam Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = func.getUsernameByToken(request, response);
        return postService.deleteAnswer(id, username);
    }

    //GET LISTS OF ADS AN QUESTIONS
    @GetMapping("/get-list-que")
    public ResponseEntity<?> getListQue() {
        return new ResponseEntity<>(postService.getListQue(), HttpStatus.OK);
    }

    @GetMapping("/get-list-ad")
    public ResponseEntity<?> getListAd() {
        return new ResponseEntity<>(postService.getListAd(), HttpStatus.OK);
    }
}
