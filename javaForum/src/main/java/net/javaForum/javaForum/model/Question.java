package net.javaForum.javaForum.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "que_id")
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String par;
    @JoinColumn(name = "users_id")
    @ManyToOne
    private User user;
    @OneToMany
    @JoinColumn(name = "que_id")
    public List<Answer> answers = new ArrayList<>();
}
