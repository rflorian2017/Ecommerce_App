package com.udacity.course3.reviews;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.udacity.course3.reviews.model.Comment;
import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepositoryTest {
	@Autowired
	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private TestEntityManager testEntityManager;
	@Autowired
	private ProductRepository productRepository;
	@Autowired 
	private ReviewRepository reviewRepository;
	
	@Autowired 
	private CommentRepository commentRepository;
	

	@Test
	public void injectedComponentsAreNotNull() {
		assertThat(dataSource).isNotNull();
		assertThat(jdbcTemplate).isNotNull();
		assertThat(entityManager).isNotNull();
		assertThat(testEntityManager).isNotNull();
		assertThat(productRepository).isNotNull();
	}

	@Test
	public void testFindByReview() {
		// create Order
		Product product = new Product();
		// set fields
		product.setName("Pc");
		product.setDescription("A new PC");

		entityManager.persist(product);
		
		Review review = new Review();
		review.setContent("This is an awesome PC");
		review.setTitle("Awesome");
		review.setProduct(product);
		
		entityManager.persist(review);
		
		Comment comment = new Comment();
		comment.setContent("I find it usefull");
		comment.setTitle("Very nice!");
		comment.setReview(review);
		
		entityManager.persist(comment);
		
		List<Comment> actualComments = commentRepository.findAllByReview(review);
		assertThat(actualComments).isNotEmpty();
		assertEquals(actualComments.get(0).getReview().getContent(), review.getContent());
		
	}
}
