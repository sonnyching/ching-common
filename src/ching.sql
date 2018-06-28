CREATE TABLE `t_user` (
  `id` int(255) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(150) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

