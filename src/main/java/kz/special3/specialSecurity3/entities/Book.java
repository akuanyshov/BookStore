package kz.special3.specialSecurity3.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "t_book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "genre")
    private String genre;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @Column(name = "author")
    private String author;

    @Column(name = "added_date")
    private Date addedDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Countries country;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Categories> categories;



}
