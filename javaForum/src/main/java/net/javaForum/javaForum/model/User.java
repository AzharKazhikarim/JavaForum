package net.javaForum.javaForum.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "users_id")
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();
    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "users_id")
    private List<Question> questionList = new ArrayList<>();
    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "users_id")
    private List<Ad> adList = new ArrayList<>();
}
