package com.udacity.course3.reviews.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.model.Review;


@Repository
public interface ReviewRepository extends MongoRepository<Review, String>{
	
	/**
	 * Method to find all reviews by product
	 * @param product
	 * @return list of all reviews
	 */
	List<Review> findAllByProduct(Product product);

}
