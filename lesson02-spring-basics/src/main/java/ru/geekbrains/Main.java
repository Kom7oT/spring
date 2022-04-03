package ru.geekbrains;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepositoryImpl;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductRepositoryImpl productRepository = context.getBean("productRepository", ProductRepositoryImpl.class);

        Scanner sc = new Scanner(System.in);
        for (;;) {
            System.out.print("Enter product name: ");
            String name = sc.nextLine();

            System.out.print("Enter cost: ");
            Double cost = sc.nextDouble();

            try {
                productRepository.save(new Product(null, name, cost));
            } catch (IllegalArgumentException ex) {
                System.out.println("Incorrect name");
            }

            System.out.println("New product added. Now " + productRepository.getCount() + " products in repository");
        }
    }
}
