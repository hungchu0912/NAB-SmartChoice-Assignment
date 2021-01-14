CREATE TABLE publisher (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name NVARCHAR(100) NOT NULL,
    description VARCHAR(200) ,
    homepage_url VARCHAR(200)
);

CREATE TABLE publisher_product_mapping (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    publisher_product_id VARCHAR(100),
    publisher_id INT UNSIGNED,
    product_name NVARCHAR (500),
    FOREIGN KEY (publisher_id) REFERENCES publisher(id) ON DELETE CASCADE,
    UNIQUE (publisher_product_id)
);

CREATE TABLE publisher_product_detail (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    publisher_product_id VARCHAR(100),
    publisher_product_mapping_id INT UNSIGNED,
    publisher_product_name NVARCHAR(500) NOT NULL,
    price DOUBLE,
    discount_rate DOUBLE,
    promotion VARCHAR(100),
    img_url VARCHAR(2000),
    updated_time datetime DEFAULT NOW(),
    FOREIGN KEY (publisher_product_mapping_id) REFERENCES publisher_product_mapping(id) ON DELETE CASCADE
);

INSERT INTO publisher (name)
VALUES ("TIKI");
INSERT INTO publisher (name)
VALUES ("SHOPEE");
INSERT INTO publisher (name)
VALUES ("LAZADA");

INSERT INTO publisher_product_mapping (publisher_product_id, publisher_id, product_name)
VALUES ("tiki_111111", 1, "Dép tổ ong");
INSERT INTO publisher_product_mapping (publisher_product_id, publisher_id, product_name)
VALUES ("tiki_222222", 1, "Bàn chải điện Oral B");
INSERT INTO publisher_product_mapping (publisher_product_id, publisher_id, product_name)
VALUES ("tiki_333333", 1, "Túi xách Nam AZDTN93TI");
INSERT INTO publisher_product_mapping (publisher_product_id, publisher_id, product_name)
VALUES ("tiki_444444", 1, "Tai nghe Sennheister S2212");
INSERT INTO publisher_product_mapping (publisher_product_id, publisher_id, product_name)
VALUES ("tiki_555555", 1, "Ví Nam v1234");
INSERT INTO publisher_product_mapping (publisher_product_id, publisher_id, product_name)
VALUES ("shopee_111111", 2, "Dép tổ ong");
INSERT INTO publisher_product_mapping (publisher_product_id, publisher_id, product_name)
VALUES ("shopee_222222", 2, "Bàn chải điện Oral B");
INSERT INTO publisher_product_mapping (publisher_product_id, publisher_id, product_name)
VALUES ("shopee_333333", 2, "Túi xách Nam AZDTN93TI");
INSERT INTO publisher_product_mapping (publisher_product_id, publisher_id, product_name)
VALUES ("shopee_444444", 2, "Tai nghe Sennheister S2212");
INSERT INTO publisher_product_mapping (publisher_product_id, publisher_id, product_name)
VALUES ("shopee_555555", 2, "Ví Nam v1234");
INSERT INTO publisher_product_mapping (publisher_product_id, publisher_id, product_name)
VALUES ("lzd_111111", 3, "Dép tổ ong");
INSERT INTO publisher_product_mapping (publisher_product_id, publisher_id, product_name)
VALUES ("lzd_222222", 3, "Bàn chải điện Oral B");
INSERT INTO publisher_product_mapping (publisher_product_id, publisher_id, product_name)
VALUES ("lzd_333333", 3, "Túi xách Nam AZDTN93TI");
INSERT INTO publisher_product_mapping (publisher_product_id, publisher_id, product_name)
VALUES ("lzd_444444", 3, "Tai nghe Sennheister S2212");
INSERT INTO publisher_product_mapping (publisher_product_id, publisher_id, product_name)
VALUES ("lzd_555555", 3, "Ví Nam v1234");