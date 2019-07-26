-- 接口测试项目表
CREATE TABLE `api_test_project` (
  `project_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `project_name` varchar(50) NOT NULL COMMENT '项目名称',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态  0：正常   1：禁用',
  `project_type` varchar(20) COMMENT '项目类型',
  `project_version` varchar(20) COMMENT '项目版本号',
  `operator` varchar(20) COMMENT '项目负责人',
  `remark` varchar(1024) COMMENT '项目描述',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建格林尼治时间',
  `create_by` bigint(20) unsigned COMMENT '提交用户id',
  `gmt_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改格林尼治时间',
  `modify_by` bigint(20) unsigned COMMENT '修改用户id',
  PRIMARY KEY (`project_id`),
  UNIQUE INDEX (`project_name`,`project_type`,`project_version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='接口测试项目表';

-- 测试全局host表
CREATE TABLE `test_host` (
  `host_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `host_name` varchar(50) NOT NULL COMMENT 'host名称',
  `host_ip` varchar(25) NOT NULL COMMENT 'host地址，可以包含:port',
  `project_id` bigint unsigned NOT NULL COMMENT '所关联的项目',
  `host_type` tinyint NOT NULL COMMENT 'host所属类型来分类，至少包括接口测试项目，性能测试项目',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态  0：正常   1：禁用',
  `remark` varchar(1024) COMMENT 'host描述',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建格林尼治时间',
  `create_by` bigint(20) unsigned COMMENT '提交用户id',
  `gmt_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改格林尼治时间',
  `modify_by` bigint(20) unsigned COMMENT '修改用户id',
  PRIMARY KEY (`host_id`),
  UNIQUE INDEX (`host_type`,`project_id`,`host_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='接口测试项目表';

-- 测试全局系统表（未来会有，系统对应多个项目，项目会有详细的接口测试和性能测试界面，首先是接口测试）
-- 此平台是以项目服务的，是以项目为中心，所有全局的host，全局的系统，都是为项目服务的。

-- 接口测试分组表，分层是接口测试项目——多个接口测试分组——多个接口测试请求用例
CREATE TABLE `api_test_group` (
  `group_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `project_id` bigint unsigned NOT NULL COMMENT '所关联的项目',
  `group_name` varchar(50) NOT NULL COMMENT '分组名称',
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '状态  0：正常   1：禁用',
  `group_level` tinyint NOT NULL DEFAULT 1 COMMENT '分组级别，未来可以分级排序，1为级别最高',
  `remark` varchar(1024) COMMENT '分组描述',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建格林尼治时间',
  `create_by` bigint(20) unsigned COMMENT '提交用户id',
  `gmt_modify` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改格林尼治时间',
  `modify_by` bigint(20) unsigned COMMENT '修改用户id',
  PRIMARY KEY (`group_id`),
  UNIQUE INDEX (`project_id`,`group_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='接口测试分组表';