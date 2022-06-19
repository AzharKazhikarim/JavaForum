package net.javaForum.javaForum.service;

import net.javaForum.javaForum.model.Ad;
import net.javaForum.javaForum.model.Answer;
import net.javaForum.javaForum.model.Question;
import net.javaForum.javaForum.model.User;
import net.javaForum.javaForum.repository.AdRepo;
import net.javaForum.javaForum.repository.AnswerRepo;
import net.javaForum.javaForum.repository.QuestionRepo;
import net.javaForum.javaForum.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    QuestionRepo questionRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    AnswerRepo answerRepo;
    @Autowired
    AdRepo adRepo;

    public PostService(QuestionRepo questionRepo, UserRepo userRepo, AnswerRepo answerRepo, AdRepo adRepo) {
        this.questionRepo = questionRepo;
        this.userRepo = userRepo;
        this.answerRepo = answerRepo;
        this.adRepo = adRepo;
    }

    //POST A QUESTION AND AD
    public boolean saveQuestion(String username, Question question) {
        if (userRepo.existsByUsername(username)) {
            question.setUser(userRepo.getByUsername(username));
            questionRepo.save(question);
            User user = userRepo.getByUsername(username);
            user.getQuestionList().add(question);
            userRepo.save(user);
            return true;
        }
        return false;
    }

    public boolean postAd(String username, Ad ad) {
        if (userRepo.existsByUsername(username)) {
            ad.setUser(userRepo.getByUsername(username));
            adRepo.save(ad);
            User user = userRepo.getByUsername(username);
            user.getAdList().add(ad);
            userRepo.save(user);
            return true;
        }
        return false;
    }

    //GET LIST OF QUE AND AD
    public List<Question> getListQue() {
        return questionRepo.findAll();
    }

    public List<Ad> getListAd() {
        return adRepo.findAll();
    }

    //DELETE POST and QUESTION
    public boolean deleteAd(Long id, String username) {
        if (adRepo.existsById(id)) {
            if (adRepo.getById(id).getUser().getUsername().equals(username)) {
                adRepo.deleteById(id);
                return true;
            }
        }
        return false;
    }

    public boolean deleteQue(Long id, String username) {
        if (questionRepo.existsById(id)) {
            if (questionRepo.getById(id).getUser().getUsername().equals(username)) {
                questionRepo.deleteById(id);
                return true;
            }
        }
        return false;
    }

    //DELETE ANSWER
    public boolean deleteAnswer(Long id, String username) {
        if (answerRepo.existsById(id)) {
            if (answerRepo.getById(id).getUser().getUsername().equals(username)) {
                answerRepo.deleteById(id);
                return true;
            }
        }
        return false;
    }

    // GET SPECIFIC QUE AND AD
    public Ad getAd(Long id) {
        return adRepo.getById(id);
    }

    public Question getQuestion(Long id) {
        return questionRepo.getById(id);
    }

    //SAVE ANSWER TO QUESTION
    public boolean saveAnswer(Long id, Answer answer, String username) {

        if (userRepo.existsByUsername(username)) {
            if (questionRepo.existsById(id)) {
                questionRepo.getById(id).answers.add(answer);
                answer.setUser(userRepo.getByUsername(username));
                answer.setQue_id(id);
                answerRepo.save(answer);
                return true;
            }
        }
        return false;
    }
}
