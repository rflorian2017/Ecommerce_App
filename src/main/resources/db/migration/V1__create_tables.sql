CREATE TABLE review (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  content VARCHAR(5000) NOT NULL,
  product_id INT NOT NULL
  );
  
  CREATE TABLE product (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(5000) NOT NULL
  );
  
  
  CREATE TABLE comment (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  content VARCHAR(5000) NOT NULL,
  review_id INT NOT NULL
  );
  
  ALTER TABLE review add constraint fk_product_id foreign key (product_id) references product (id);

  ALTER TABLE comment add constraint fk_review_id_fk foreign key (review_id) references review (id);