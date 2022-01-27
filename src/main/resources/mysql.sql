CREATE TABLE `url_info` (
    `id` int NOT NULL AUTO_INCREMENT,
    `title` varchar(150) NOT NULL COMMENT '标题',
    `content` longtext COMMENT '文章内容',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb3 COMMENT='url内容表'