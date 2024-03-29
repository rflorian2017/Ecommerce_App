package com.udacity.course3.reviews.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.course3.reviews.model.Comment;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.model.ReviewDocument;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ReviewCommentRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    // TODO: Wire needed JPA repositories here
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private ReviewCommentRepository reviewCommentRepository;
	

	@Autowired
    public CommentsController(CommentRepository commentRepository, ReviewRepository reviewRepository, ReviewCommentRepository reviewCommentRepository) {
		this.commentRepository = commentRepository;
		this.reviewRepository = reviewRepository;
		this.reviewCommentRepository = reviewCommentRepository;
	}

	/**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<Comment> createCommentForReview(@PathVariable("reviewId") Integer reviewId, @RequestBody Comment comment) {
    	if (comment.isIncomplete()) {
			return ResponseEntity.badRequest().build();
		}
    	Optional<Review> review = reviewRepository.findById(reviewId);
    	if(review.isPresent()) {
    		comment.setReview(review.get());
    		Optional<ReviewDocument> reviewDocument = reviewCommentRepository.findById(reviewId.toString());
    		
    		ReviewDocument actualReviewDocument = reviewDocument.get();
    		actualReviewDocument.getComments().add(comment);
    		reviewCommentRepository.save(actualReviewDocument);
    		
    		return ResponseEntity.ok(commentRepository.save(comment));

    	}
    	else {
    		return ResponseEntity.notFound().build();
    	}
    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
    	Optional<Review> review = reviewRepository.findById(reviewId);
    	if(review.isPresent()) {
    		commentRepository.findAllByReview(review.get());
    		return ResponseEntity.ok(commentRepository.findAllByReview(review.get()));

    	}
    	else {
    		return ResponseEntity.notFound().build();
    	}
    	
        
    }
}