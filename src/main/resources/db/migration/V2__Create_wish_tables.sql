CREATE TABLE wishlist_tbl (
  ID int NOT NULL AUTO_INCREMENT,
  user_id int NOT NULL,
  description varchar(255) DEFAULT NULL,
  create_time TIMESTAMP NOT NULL,
  due_time TIMESTAMP NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (user_id) REFERENCES user_tbl(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE wish_tbl (
  ID int NOT NULL AUTO_INCREMENT,
  wish_list_id int NOT NULL,
  description varchar(255) DEFAULT NULL,
  create_time TIMESTAMP NOT NULL,
  last_update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  wish_status varchar(255) DEFAULT NULL,
  implementor_open_id varchar(100) DEFAULT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (wish_list_id) REFERENCES wishlist_tbl(ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;