package br.com.codepampa.model;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity implements BaseModel<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;

    @Override
    public boolean isNew() {
        return this.getPk() == null;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public Long getPk() {
        return pk;
    }

}
