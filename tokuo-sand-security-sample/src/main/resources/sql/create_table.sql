CREATE TABLE login_user(
  user_id INT(8) AUTO_INCREMENT,
  name VARCHAR(16) NOT NULL,
  email VARCHAR(64) NOT NULL,
  password VARCHAR(32) NOT NULL,
  admin BOOLEAN DEFAULT false,
  PRIMARY KEY (user_id)
);
