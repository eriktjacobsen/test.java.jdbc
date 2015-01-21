CREATE TABLE `channel` (
  `id` varchar(100) NOT NULL DEFAULT '',
  `title` varchar(300) DEFAULT NULL,
  `views` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
