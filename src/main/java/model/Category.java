package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Categories")
@Getter
@Setter
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", columnDefinition = "TEXT")
    private String name;

    @Column(name = "DESCRIPTION", columnDefinition = "TEXT")
    private String description;
}