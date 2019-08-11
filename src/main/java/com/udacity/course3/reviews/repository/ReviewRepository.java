package com.udacity.course3.reviews.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.model.Review;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{
	
	/**
	 * Method to find all reviews by product
	 * @param product
	 * @return list of all reviews
	 */
	List<Review> findAllByProduct(Product product);

}
