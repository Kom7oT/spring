package ru.geekbrains;

import ru.geekbrains.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

public class ProductDao {
    private final EntityManagerFactory emFactory;

    public ProductDao(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public Optional<Product> findById(long id) {
        try {
            EntityManager em = emFactory.createEntityManager();
            return Optional.ofNullable(em.find(Product.class, id));
        } finally {
            emFactory.close();
        }
    }

    public List<Product> findAll() {
        try {
            EntityManager em = emFactory.createEntityManager();
            return em.createQuery("FROM Product p", Product.class).getResultList();
        } finally {
            emFactory.close();
        }
    }

    public void saveOrUpdate(Product product) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            if (product.getId() == null) {
                em.persist(product);
            } else {
                em.merge(product);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public void deleteById(long id){
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("delete from Product where id = :id")
                .setParameter("id", id)
                .executeUpdate();
        em.getTransaction().commit();
        emFactory.close();
    }
}
