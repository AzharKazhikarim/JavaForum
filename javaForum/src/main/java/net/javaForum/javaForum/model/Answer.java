package net.javaForum.javaForum.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "answer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "answer_id")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String answer;

    @Column
    @JoinColumn(name = "que_id")
    private Long que_id;

    @JoinColumn(name = "users_id")
    @ManyToOne
    private User user;


}
