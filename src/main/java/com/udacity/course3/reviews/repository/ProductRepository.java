package com.udacity.course3.reviews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udacity.course3.reviews.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
