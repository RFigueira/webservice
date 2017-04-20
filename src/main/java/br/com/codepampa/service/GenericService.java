package br.com.codepampa.service;

import br.com.codepampa.model.BaseModel;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;


public abstract class GenericService<T extends BaseModel<PK>, PK extends Serializable> extends ServiceFactory {

    protected EntityManager entityManager = getEntityManager();

    private static final String FROM = "FROM ";

    protected GenericService(){
    }

    private void persist(T objeto){
        entityManager.persist(objeto);
    }

    private T merge(T objeto) {
        return entityManager.merge(objeto);
    }

    public T save(T objeto) {
        entityManager.getTransaction().begin();
        if(objeto.isNew()) {
            persist(objeto);
        } else {
            objeto = merge(objeto);
        }
        entityManager.flush();
        entityManager.getTransaction().commit();

        return objeto;
    }

    public void removeByPk(PK pk) {
        T objeto = entityManager.getReference(getTypeClass(), pk);
        refresh(objeto);
        entityManager.remove(objeto);
    }

    //Atualizo a instancia do objeto com o que tem no banco
    private void refresh(T objeto) {
        entityManager.refresh(objeto);
    }


    public void remove(T objeto) {
        entityManager.remove(objeto);
    }

    public T findByPk(PK pk) {
        return entityManager.find(getTypeClass(), pk);
    }

    public List<T> findAll(){
        return entityManager.createQuery(FROM+getTypeClass().getName()).getResultList();
    }

    private Class<T> getTypeClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}
