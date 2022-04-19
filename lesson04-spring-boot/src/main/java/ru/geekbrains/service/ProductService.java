package ru.geekbrains.service;

import org.springframework.data.domain.Page;
import ru.geekbrains.dto.ProductDto;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    Page<ProductDto> findAll(Integer page, Integer size);

    Optional<ProductDto> findById(long id);

    void save(ProductDto product);

    void deleteById(long id);
}
