package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();

        em.persist(new Product("Product 1",  4000));
        em.persist(new Product("Product 2",  40123));
        em.persist(new Product("Product 3",  5454));
        em.close();
    }
}
