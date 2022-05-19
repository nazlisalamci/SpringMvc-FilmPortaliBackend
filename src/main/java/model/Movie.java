package model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import java.io.Serializable;

@Entity(name ="Movies")
@Table(name = "Movies")
@Getter
@Setter

public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "name", columnDefinition = "TEXT")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "publicyear")
    private String publicyear; //yayın yılı

    @Column(name = "language", columnDefinition = "TEXT")
    private String language;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
    private Category category;
}
