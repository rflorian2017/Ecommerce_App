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

import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewRepositoryTest {
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
	

	@Test
	public void injectedComponentsAreNotNull() {
		assertThat(dataSource).isNotNull();
		assertThat(jdbcTemplate).isNotNull();
		assertThat(entityManager).isNotNull();
		assertThat(testEntityManager).isNotNull();
		assertThat(productRepository).isNotNull();
	}

	@Test
	public void testFindByProduct() {
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

		Product actual = productRepository.findById(1).get();
		assertThat(actual).isNotNull();
		assertEquals(product.getId(), actual.getId());
		
		List<Review> actualReview = reviewRepository.findAllByProduct(product);
		assertThat(actualReview).isNotEmpty();
		assertEquals(actualReview.get(0).getProduct().getDescription(), product.getDescription());
		
	}
}
