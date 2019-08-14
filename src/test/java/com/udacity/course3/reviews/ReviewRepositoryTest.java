package com.udacity.course3.reviews;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.repository.ReviewRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReviewRepositoryTest {
	
	@Autowired 
	private ReviewRepository reviewRepository;
	
	@Test
	public void testFindByReview() {
		// create Order
		
		Product product = new Product();
		product.setDescription("A new PC");
		product.setName("Allienare");
		
		Review review = new Review();
		review.setContent("This is an awesome PC");
		review.setTitle("Awesome");
		review.setProduct(product);
		
		reviewRepository.save(review);
		
		
		List<Review> actualReviews = reviewRepository.findAllByProduct(product);
		assertThat(actualReviews).isNotEmpty();
		assertEquals(actualReviews.get(0).getProduct().getDescription(), product.getDescription());
		
	}
}
