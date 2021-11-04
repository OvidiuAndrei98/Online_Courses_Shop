DROP TABLE IF EXISTS public.orders CASCADE ;
CREATE TABLE public.orders (
                                   id serial NOT NULL PRIMARY KEY,
                                   customer_id integer NOT NULL,
                                   order_date date NOT NULL,
                                   shipment_date date NOT NULL,
                                   order_status varchar NOT NULL,
                                   shipment_status varchar NOT NULL
);

DROP TABLE IF EXISTS public.customers CASCADE ;
CREATE TABLE public.customers (
                               id serial NOT NULL PRIMARY KEY,
                               first_name varchar NOT NULL,
                               last_name varchar NOT NULL,
                               address text,
                               email varchar,
                               password varchar
);

DROP TABLE IF EXISTS public.order_details CASCADE ;
CREATE TABLE public.order_details (
                                id serial NOT NULL PRIMARY KEY,
                               order_id integer NOT NULL,
                               product_id integer NOT NULL,
                               price float,
                               quantity integer
);

DROP TABLE IF EXISTS public.products CASCADE ;
CREATE TABLE public.products (
                                      id serial NOT NULL PRIMARY KEY,
                                      name varchar NOT NULL,
                                      price double precision NOT NULL,
                                      quantity_in_stock integer NOT NULL,
                                      quantity_sold integer NOT NULL,
                                      supplier_id integer NOT NULL,
                                      category_id integer NOT NULL,
                                      currency varchar,
                                      description varchar


);

DROP TABLE IF EXISTS public.products_category CASCADE ;
CREATE TABLE public.products_category (
                                 id serial NOT NULL PRIMARY KEY,
                                 category varchar NOT NULL,
                                 department varchar NOT NULL,
                                 description varchar NOT NULL
);

DROP TABLE IF EXISTS public.suppliers CASCADE ;
CREATE TABLE public.suppliers (
                                          id serial NOT NULL PRIMARY KEY,
                                          name varchar UNIQUE NOT NULL,
                                          description text NOT NULL
);

DROP TABLE IF EXISTS public.cart_items CASCADE ;
CREATE TABLE public.cart_items (
                                  id serial NOT NULL PRIMARY KEY,
                                  cartId integer NOT NULL,
                                  lineItem json
);

INSERT INTO suppliers (name, description) VALUES ('Udemy', 'Learn for your future');
INSERT INTO suppliers (name, description) VALUES ('Coursera', 'Build Skills with Online Courses from Top Institutions');
INSERT INTO suppliers (name, description) VALUES ('CodeWithMosh', 'Master the Coding Skills to Become an Engineer Companies LOVE to Hire');

INSERT INTO products_category (category, department, description) VALUES ('JavaScript','JS course','Javascript courses');
INSERT INTO products_category (category,department, description) VALUES ('Html','JS course','Javascript courses');
INSERT INTO products_category (category,department, description) VALUES ('Sql','JS course','Javascript courses');
INSERT INTO products_category (category,department, description) VALUES ('Css','JS course','Javascript courses');

INSERT INTO products (name, price, quantity_in_stock, quantity_sold, supplier_id, category_id, currency, description) VALUES ('The Complete JavaScript Course 2021: From Zero to Expert!',19,10,0,1,1,'USD','The modern JavaScript course for everyone! Master JavaScript with projects, challenges and theory.');
INSERT INTO products (name, price, quantity_in_stock, quantity_sold, supplier_id, category_id, currency, description) VALUES ('The Ultimate JavaScript Mastery Series',15,10,0,3,1,'USD','Master the Fundamentals of JavaScript - The Language Behind Millions of Websites & Apps.');
INSERT INTO products (name, price, quantity_in_stock, quantity_sold, supplier_id, category_id, currency, description) VALUES ('HTML, CSS, and Javascript for Web Developers',50,10,0,2,1,'USD','Learn the basic tools that every web page coder needs to know.');
INSERT INTO products (name, price, quantity_in_stock, quantity_sold, supplier_id, category_id, currency, description) VALUES ('HTML, CSS, and Javascript for Web Developers',50,10,0,2,2,'USD','Learn the basic tools that every web page coder needs to know.');
INSERT INTO products (name, price, quantity_in_stock, quantity_sold, supplier_id, category_id, currency, description) VALUES ('The Ultimate HTML/CSS Mastery Series',49,10,0,3,2,'USD','Everything you need to build fast and beautiful websites with HTML5 and CSS3 in one bundle.');
INSERT INTO products (name, price, quantity_in_stock, quantity_sold, supplier_id, category_id, currency, description) VALUES ('Build Responsive Real World Websites with HTML5 and CSS3',19,10,0,1,2,'USD','The easiest way to learn modern web design, HTML5 and CSS3 step-by-step from scratch. Design AND code a huge project.');
INSERT INTO products (name, price, quantity_in_stock, quantity_sold, supplier_id, category_id, currency, description) VALUES ('HTML, CSS, and Javascript for Web Developers',50,10,0,2,4,'USD','Learn the basic tools that every web page coder needs to know.');
INSERT INTO products (name, price, quantity_in_stock, quantity_sold, supplier_id, category_id, currency, description) VALUES ('The Ultimate HTML/CSS Mastery Series',49,10,0,3,4,'USD','Everything you need to build fast and beautiful websites with HTML5 and CSS3 in one bundle.');
INSERT INTO products (name, price, quantity_in_stock, quantity_sold, supplier_id, category_id, currency, description) VALUES ('Build Responsive Real World Websites with HTML5 and CSS3',19,10,0,1,4,'USD','The easiest way to learn modern web design, HTML5 and CSS3 step-by-step from scratch. Design AND code a huge project.');
INSERT INTO products (name, price, quantity_in_stock, quantity_sold, supplier_id, category_id, currency, description) VALUES ('Learn SQL Basics for Data Science Specialization',50,10,0,2,3,'USD','Apply SQL creatively to analyze and explore data; demonstrate efficiency in writing queries or create data analysis datasets.');
INSERT INTO products (name, price, quantity_in_stock, quantity_sold, supplier_id, category_id, currency, description) VALUES ('SQL and PostgreSQL: The Complete Developers Guide',15,10,0,1,3,'USD','Become an expert with SQL and PostgreSQL! Store and fetch data, tune queries, and design efficient database structures!');
INSERT INTO products (name, price, quantity_in_stock, quantity_sold, supplier_id, category_id, currency, description) VALUES ('Complete SQL Mastery',29,10,0,3,3,'USD','Everything You Need to Design and Query Databases in One Course.');



-- de pus foreign_keys
