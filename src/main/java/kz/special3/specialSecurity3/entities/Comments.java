package kz.special3.specialSecurity3.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comments implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "addedDate")
    private Date addedDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    private Art art;

    @ManyToOne(fetch = FetchType.EAGER)
    private SchoolSuplies schoolSupplies;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users author;
}
