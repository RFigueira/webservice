package br.com.codepampa.service;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ServiceFactory {

    private static EntityManager entityManager;
    private static EntityManagerFactory entityManagerFactory;
    private static final String PERSISTENCE_UNIT_NAME = "br.com.codepampa_webservicePU";


    private EntityManagerFactory getConexaoJPA() {
        if(entityManagerFactory == null || !entityManagerFactory.isOpen()) {
            entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return entityManagerFactory;
    }

    public void closeConexao() {
        if(entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

    public EntityManager getEntityManager() {
        return entityManager = getConexaoJPA().createEntityManager();
    }
}
