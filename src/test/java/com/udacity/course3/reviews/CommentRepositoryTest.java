package com.udacity.course3.reviews;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.udacity.course3.reviews.model.Comment;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentRepositoryTest {
	
	@Autowired 
	private ReviewRepository reviewRepository;
	
	@Autowired 
	private CommentRepository commentRepository;
	
	@Test
	public void testFindByReview() {
		// create Order
		
		Review review = new Review();
		review.setContent("This is an awesome PC");
		review.setTitle("Awesome");
		
		reviewRepository.save(review);
		
		
		Comment comment = new Comment();
		comment.setContent("I find it usefull");
		comment.setTitle("Very nice!");
		comment.setReview(review);
		
		commentRepository.save(comment);
		
		List<Comment> actualComments = commentRepository.findAllByReview(review);
		assertThat(actualComments).isNotEmpty();
		assertEquals(actualComments.get(0).getReview().getContent(), review.getContent());
		
	}
}
