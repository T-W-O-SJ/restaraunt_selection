package com.git.selection.repository;

import org.hibernate.SessionFactory;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JpaUtil {
    @PersistenceContext
  private   EntityManager em;
    public void evict2levelCash(){
        Session session =(Session) em.getDelegate();
        SessionFactory sf =session.getSessionFactory();
        sf.getCache().evictAllRegions();
    }
}
