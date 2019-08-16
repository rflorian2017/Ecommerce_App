package com.udacity.course3.reviews;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.udacity.course3.reviews.model.Comment;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.model.ReviewDocument;
import com.udacity.course3.reviews.repository.ReviewCommentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoRepositoryTest {
	@Autowired 
	private ReviewCommentRepository reviewRepository;
	
	@Test
	public void testFindByReview() {
		// create Order
		
		Review review = new Review();
		review.setContent("This is an awesome PC");
		review.setTitle("Awesome");
		
		Comment comment = new Comment();
		comment.setContent("I find it usefull");
		comment.setTitle("Very nice!");
		comment.setReview(review);
		
		ReviewDocument reviewDocument = new ReviewDocument();
		reviewDocument.setTitle(review.getTitle());
		reviewDocument.getComments().add(comment);
		reviewDocument.setContent(review.getContent());
		reviewDocument.setId("2");
		
		reviewRepository.save(reviewDocument);
		
		ReviewDocument actualReviewDocument = reviewRepository.findById("2").get();
		assertThat(actualReviewDocument).isNotNull();
		assertEquals(actualReviewDocument.getContent(), review.getContent());
		
	}
}
