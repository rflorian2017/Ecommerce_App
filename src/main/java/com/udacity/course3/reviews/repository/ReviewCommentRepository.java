package com.udacity.course3.reviews.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.model.ReviewDocument;

public interface ReviewCommentRepository extends MongoRepository<ReviewDocument, String>{
	/**
	 * Method to find all reviews by product
	 * @param product
	 * @return list of all reviews
	 */
	List<Review> findAllByProduct(Product product);

}
