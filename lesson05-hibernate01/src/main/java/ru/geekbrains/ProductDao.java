package ru.geekbrains;

import javax.persistence.EntityManagerFactory;

public class ProductDao {
    private final EntityManagerFactory emFactory;

    public ProductDao(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }
}
