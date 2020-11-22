SET FOREIGN_KEY_CHECKS = 0;

truncate table pet;
truncate table store;

INSERT INTO store(`id`,`name`,`location`,`contact_no`)
VALUES (21,'super store','nassarawa','90845364387'),
(22,'mini store','toronto','563883849'),
(23,'green store','lagos','68384292');

INSERT INTO pet(`id`,`name`,`color`,`breed`,`age`,`pet_gender`,`store_id`)
VALUES (31,'Jill','blue','parrot',6,'MALE',21),
(32,'Jack','black','dog',2,'MALE',21),
(33,'Tris','white','bunny',3,'FEMALE',21),
(34,'Troy','brown','goat',1,'MALE',21),
(35,'Riley','brown','dog',5,'FEMALE',21),
(36,'Randy','red','parrot',2,'MALE',21);

SET FOREIGN_KEY_CHECKS = 1;
