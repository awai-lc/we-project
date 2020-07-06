CREATE TABLE `chose_expert` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `major_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '专业id',
  `pro_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '项目id',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0否， 1是）',
  `addBy` bigint(20) NOT NULL DEFAULT '0' COMMENT '新增人ID',
  `addByTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `lastModifiedBy` bigint(20) NOT NULL DEFAULT '0' COMMENT ' 最近修改人ID',
  `lastModifiedByTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='选中专家';

CREATE TABLE `chose_major` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `major_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '专业id',
  `major_code` varchar(100) NOT NULL DEFAULT '' COMMENT '专业编码',
  `pro_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '项目id',
  `major_name` varchar(100) NOT NULL DEFAULT '' COMMENT '专业名称',
  `chosed_count` int(10) NOT NULL DEFAULT '0' COMMENT '已抽取专家数',
  `need_count` int(10) NOT NULL DEFAULT '0' COMMENT '需抽取专家数',
  `expert_count` int(10) NOT NULL DEFAULT '0' COMMENT '库存专家数',
  `place_name` varchar(100) NOT NULL DEFAULT '' COMMENT '所属区域名称',
  `is_delete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0否， 1是）',
  `addBy` bigint(20) NOT NULL DEFAULT '0' COMMENT '新增人ID',
  `addByTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `lastModifiedBy` bigint(20) NOT NULL DEFAULT '0' COMMENT ' 最近修改人ID',
  `lastModifiedByTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='选中专业';

CREATE TABLE `expert` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `expert_name` varchar(100) NOT NULL DEFAULT '' COMMENT '专家名称',
  `major_id` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '专业id',
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(100) NOT NULL DEFAULT '' COMMENT '邮箱',
  `address` varchar(500) NOT NULL DEFAULT '' COMMENT '地址',
  `unit` varchar(255) NOT NULL DEFAULT '' COMMENT '工作单位',
  `job_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '在职状态（1退休，2在职）',
  `birth` varchar(20) NOT NULL DEFAULT '' COMMENT '出生年月',
  `title` varchar(255) NOT NULL DEFAULT '' COMMENT '职称',
  `addBy` bigint(20) NOT NULL DEFAULT '0' COMMENT '新增人ID',
  `addByTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `lastModifiedBy` bigint(20) NOT NULL DEFAULT '0' COMMENT ' 最近修改人ID',
  `lastModifiedByTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='专家';

CREATE TABLE `major` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(100) NOT NULL DEFAULT '' COMMENT '专业编码',
  `major_name` varchar(100) NOT NULL DEFAULT '' COMMENT '名称',
  `parent_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '父ID',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_name_parent` (`major_name`,`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='专业';

CREATE TABLE `program_manager` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(100) NOT NULL DEFAULT '' COMMENT '项目编码',
  `name` varchar(100) NOT NULL DEFAULT '' COMMENT '项目名称',
  `pro_status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '项目状态（1底稿，2可抽取，3抽取中，4需补抽，5完成）',
  `purchasing_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '采购单位ID',
  `extraction_unit` varchar(100) NOT NULL DEFAULT '' COMMENT '抽取单位ID',
  `pur_way` bigint(20) NOT NULL DEFAULT '0' COMMENT '采购方式ID',
  `start_review` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '评审开始时间',
  `end_review` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '评审结束时间',
  `gov_pro_record_number` varchar(255) NOT NULL DEFAULT '' COMMENT '采购备案号',
  `gov_pro_person` varchar(100) NOT NULL DEFAULT '' COMMENT '参与采购评审人代表',
  `review_content` text NOT NULL COMMENT '评审内容',
  `budget_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '预算金额',
  `extraction_unit_contact` varchar(100) NOT NULL DEFAULT '' COMMENT '抽取单位联系人',
  `extraction_unit_phone` varchar(20) NOT NULL DEFAULT '' COMMENT '抽取单位电话',
  `address` varchar(255) NOT NULL DEFAULT '' COMMENT '评审地址',
  `supervisory_place_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '项目所属监管地ID',
  `avoid_unit` varchar(500) NOT NULL DEFAULT '' COMMENT '回避专家单位',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `addBy` bigint(20) NOT NULL DEFAULT '0' COMMENT '新增人ID',
  `addByTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `lastModifiedBy` bigint(20) NOT NULL DEFAULT '0' COMMENT ' 最近修改人ID',
  `lastModifiedByTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='项目表';

CREATE TABLE `pur_way` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `pur_way` varchar(30) NOT NULL DEFAULT '' COMMENT '采购方式（公开招标，竞争性谈判，竞争性磋商，询价采购，其他）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='采购方式';

CREATE TABLE `supervisory_place` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `place_name` varchar(100) NOT NULL DEFAULT '' COMMENT '监管地名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_name` (`place_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='监管地';

CREATE TABLE `unit` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `unit_name` varchar(100) NOT NULL DEFAULT '' COMMENT '单位名称',
  `addBy` bigint(20) NOT NULL DEFAULT '0' COMMENT '新增人ID',
  `addByTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_name` (`unit_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='单位';

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(13) DEFAULT NULL COMMENT '手机号',
  `nickname` varchar(45) NOT NULL DEFAULT '' COMMENT '名字',
  `password_hash` varchar(32) DEFAULT '' COMMENT '密码hash',
  `active_session_id` varchar(128) NOT NULL DEFAULT '' COMMENT '活跃sessionId',
  `last_login_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近登录时间',
  `last_active_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近活跃时间',
  `is_online` int(11) NOT NULL DEFAULT '0',
  `addBy` bigint(20) NOT NULL DEFAULT '0' COMMENT '新增人ID',
  `addByTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `lastModifiedBy` bigint(20) NOT NULL DEFAULT '0' COMMENT ' 最近修改人ID',
  `lastModifiedByTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='登录用户表';



