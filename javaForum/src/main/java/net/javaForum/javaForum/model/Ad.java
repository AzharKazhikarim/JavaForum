package net.javaForum.javaForum.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ad")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ad_id")
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String par;
    @JoinColumn(name = "users_id")
    @ManyToOne
    private User user;
}
