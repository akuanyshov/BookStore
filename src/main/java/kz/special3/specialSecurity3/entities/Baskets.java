package kz.special3.specialSecurity3.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "t_baskets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Baskets implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBuy=new Date();

    @Column(name = "Amount")
    private int amount;

    @ManyToOne(fetch = FetchType.EAGER)
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    private Art art;

    @ManyToOne(fetch = FetchType.EAGER)
    private SchoolSuplies schoolSupplies;

}
