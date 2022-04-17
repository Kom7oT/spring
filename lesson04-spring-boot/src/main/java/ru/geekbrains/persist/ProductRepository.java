package ru.geekbrains.persist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> priceBetweenFilter(@Param("min") String min, @Param("max") String max);
    }
