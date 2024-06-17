CREATE TABLE juser (
  id INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  name VARCHAR(50)  NOT NULL  ,
  lastname VARCHAR(50)  NOT NULL  ,
  email VARCHAR(50)  NOT NULL  ,
  jpassword VARCHAR(20)  NOT NULL    ,
PRIMARY KEY(id));



CREATE TABLE jproduct (
  id INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  name VARCHAR(50)  NOT NULL  ,
  price FLOAT  NOT NULL  ,
  image BLOB  NOT NULL  ,
  stock INTEGER UNSIGNED  NOT NULL    ,
PRIMARY KEY(id));



CREATE TABLE jadmin (
  id INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  email VARCHAR(50)  NOT NULL  ,
  jpassword VARCHAR(20)  NOT NULL    ,
PRIMARY KEY(id));



CREATE TABLE jwishlist (
  id INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  jproduct_id INTEGER UNSIGNED  NOT NULL  ,
  juser_id INTEGER UNSIGNED  NOT NULL    ,
PRIMARY KEY(id)  ,
INDEX jwishlist_FKIndex1(juser_id)  ,
INDEX jwishlist_FKIndex2(jproduct_id),
  FOREIGN KEY(juser_id)
    REFERENCES juser(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(jproduct_id)
    REFERENCES jproduct(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE jcart (
  id INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  jproduct_id INTEGER UNSIGNED  NOT NULL  ,
  juser_id INTEGER UNSIGNED  NOT NULL    ,
PRIMARY KEY(id)  ,
INDEX jcart_FKIndex1(juser_id)  ,
INDEX jcart_FKIndex2(jproduct_id),
  FOREIGN KEY(juser_id)
    REFERENCES juser(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(jproduct_id)
    REFERENCES jproduct(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE jhistory (
  id INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  jproduct_id INTEGER UNSIGNED  NOT NULL  ,
  juser_id INTEGER UNSIGNED  NOT NULL  ,
  buy_date DATE  NOT NULL    ,
PRIMARY KEY(id)  ,
INDEX jhistory_FKIndex1(juser_id)  ,
INDEX jhistory_FKIndex2(jproduct_id),
  FOREIGN KEY(juser_id)
    REFERENCES juser(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(jproduct_id)
    REFERENCES jproduct(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);




