package ru.geekbrains.dao;

import ru.geekbrains.model.Customer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;

public class CustomerDao {

    private final EntityManagerFactory emFactory;

    public CustomerDao(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public Optional<Customer> findById(long id) {
        try {
            EntityManager em = emFactory.createEntityManager();
            return Optional.ofNullable(em.find(Customer.class, id));
        } finally {
            emFactory.close();
        }
    }


    public List<Customer> findAll() {
        try {
            EntityManager em = emFactory.createEntityManager();
            return em.createQuery("FROM Customer c", Customer.class).getResultList();
        } finally {
            emFactory.close();
        }
    }

    public void saveOrUpdate(Customer customer) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            if (customer.getId() == null) {
                em.persist(customer);
            } else {
                em.merge(customer);
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
        em.createQuery("delete from Customer where id = :id")
                .setParameter("id", id)
                .executeUpdate();
        em.getTransaction().commit();
        emFactory.close();
    }
}
