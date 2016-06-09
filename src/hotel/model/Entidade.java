package hotel.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class Entidade {

    private static final long serialVersionUID = -5534001310501339988L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`num_pk`")
    protected Long id;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}