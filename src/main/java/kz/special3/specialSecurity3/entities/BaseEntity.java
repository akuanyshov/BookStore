package kz.special3.specialSecurity3.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@MappedSuperclass
@Data
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at")
    private Date created_at;

    @Column(name = "updated_at")
    private Date updated_at;

    @Column(name = "removed_at")
    private Date removed_at;

    @PrePersist
    public void prePersist(){this.created_at=new Date(); }

    @PreUpdate
    public void preUpdate(){this.updated_at=new Date(); }

    @PreRemove
    public void preRemove(){this.removed_at=new Date(); }
}
