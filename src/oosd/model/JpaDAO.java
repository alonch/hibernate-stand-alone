/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oosd.model;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author alonch
 */
public abstract class JpaDAO<K, E> {

    private static final String PERSISTENCE_UNIT_NAME = "persistence";
    private Class entityClass;
    private Class keyClass;
    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private static final HashMap<String, EntityManager> managers = new HashMap<>();

    public JpaDAO() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass
                .getActualTypeArguments()[1];

        this.keyClass = (Class<E>) genericSuperclass
                .getActualTypeArguments()[0];
    }

    protected synchronized EntityManager getEntityManager() {
        String key = entityClass.getName();
        EntityManager entityManager = null;
        if (!managers.containsKey(key)) {
            entityManager = factory.createEntityManager();
            managers.put(key, entityManager);
        }
        return managers.get(key);
    }

    public static void close() {
        managers.entrySet().stream().forEach((entry) -> {
            entry.getValue().close();
        });
        managers.clear();
        factory.close();
    }

    public static void setFactory(EntityManagerFactory factory) {
        JpaDAO.factory = factory;
    }

    public void persist(E entity) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    public void remove(E entity) {
        getEntityManager().remove(entity);
    }

    public E merge(E entity) {
        return getEntityManager().merge(entity);
    }

    public void refresh(E entity) {
        getEntityManager().refresh(entity);
    }

    public E findById(K id) {
        return (E) getEntityManager().find(entityClass, id);
    }

    public E flush(E entity) {
        getEntityManager().flush();
        return entity;
    }

    public List<E> findAll() {
        Query q = getEntityManager().createQuery("SELECT h FROM "
                + entityClass.getName() + " h");
        return q.getResultList();
    }

    @SuppressWarnings("unchecked")
    public Integer removeAll() {
        Query q = getEntityManager().createQuery("DELETE FROM " + entityClass.getName()
                + " h");
        return q.executeUpdate();
    }

}
