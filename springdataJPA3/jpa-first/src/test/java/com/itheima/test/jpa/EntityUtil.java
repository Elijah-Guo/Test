package com.itheima.test.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityUtil {
    static class EntityManagerCreater {
        private static EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory("jpa318");

        public static EntityManagerFactory getEntityManagerFactory() {
            return entityManagerFactory;
        }
    }
    public static EntityManager getEntityManager() {
        return EntityManagerCreater.getEntityManagerFactory().createEntityManager();
    }
}
