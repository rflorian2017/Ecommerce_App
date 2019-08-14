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

import com.udacity.course3.reviews.model.Product;
import com.udacity.course3.reviews.repository.ProductRepository;

/**
 * Spring REST controller for working with product entity.
 */
@RestController
@RequestMapping("/products")
public class ProductsController {

	// TODO: Wire JPA repositories here
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	protected ProductsController(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	/**
	 * Creates a product.
	 *
	 * 1. Accept product as argument. Use {@link RequestBody} annotation. 2. Save
	 * product.
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		if (product.isIncomplete()) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.ok(productRepository.save(product));
		}
	}

	/**
	 * Finds a product by id.
	 *
	 * @param id The id of the product.
	 * @return The product if found, or a 404 not found.
	 */
	@RequestMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable("id") Integer id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			return ResponseEntity.ok(product.get());

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Lists all products.
	 *
	 * @return The list of products.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Product> listProducts() {
		return productRepository.findAll();
	}
}