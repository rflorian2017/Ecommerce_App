package com.udacity.course3.reviews.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.udacity.course3.reviews.model.Comment;
import com.udacity.course3.reviews.model.Review;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String>{
	
	/**
	 * Method used to find all comments for a review
	 * @param Review
	 * @return
	 */
	List<Comment> findAllByReview(Review review);

}
