CREATE TABLE `user`(
`user_id` bigint(32) NOT NULL AUTO_INCREMENT,
`user_name` varchar(50) NOT NULL,
`real_name` varchar(50) DEFAULT NULL,
`password` varchar(20) NOT NULL,
`tel` int(32) DEFAULT NULL,
PRIMARY KEY(`user_id`) 
)ENGINE=InnoDB AUTO_INCREMENT=00001 DEFAULT CHARSET=utf8;