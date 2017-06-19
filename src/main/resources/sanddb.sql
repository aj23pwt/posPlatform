/*
Navicat MySQL Data Transfer

Source Server         : Mysql_localhost
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : sanddb

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-06-19 10:57:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_catalog`
-- ----------------------------
DROP TABLE IF EXISTS `sys_catalog`;
CREATE TABLE `sys_catalog` (
  `id_` int(20) NOT NULL AUTO_INCREMENT COMMENT '流水号',
  `cascade_id` varchar(255) NOT NULL COMMENT '节点语义ID',
  `root_key` varchar(255) NOT NULL COMMENT '科目标识键',
  `root_name` varchar(255) NOT NULL COMMENT '科目名称',
  `name_` varchar(255) NOT NULL COMMENT '分类名称',
  `hotkey_` varchar(255) DEFAULT NULL COMMENT '热键',
  `parent_id` varchar(64) NOT NULL COMMENT '父节点流水号',
  `is_leaf_` varchar(255) NOT NULL DEFAULT '1' COMMENT '是否叶子节点',
  `is_auto_expand` varchar(255) NOT NULL DEFAULT '0' COMMENT '是否自动展开',
  `icon_name` varchar(255) DEFAULT NULL COMMENT '图标文件名称',
  `sort_no` int(10) DEFAULT NULL COMMENT '排序号',
  PRIMARY KEY (`id_`),
  UNIQUE KEY `sys_catalog_ukey` (`cascade_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='分类表';

-- ----------------------------
-- Records of sys_catalog
-- ----------------------------
INSERT INTO `sys_catalog` VALUES ('1', '0.001', 'PARAM_TYPE', '参数分类科目', '参数分类', null, '0', '0', '0', 'book.png', '0');
INSERT INTO `sys_catalog` VALUES ('2', '0.002', 'DIC_TYPE', '词典分类科目', '数据字典分类', null, '0', '0', '0', 'book.png', '2');
INSERT INTO `sys_catalog` VALUES ('3', '0.001.001', 'PARAM_TYPE', '参数分类科目', '业务参数', null, '1', '1', '0', 'user20.png', '2');
INSERT INTO `sys_catalog` VALUES ('4', '0.001.002', 'PARAM_TYPE', '参数分类科目', '系统参数', null, '1', '0', '1', 'folder22.png', '1');
INSERT INTO `sys_catalog` VALUES ('5', '0.002.001', 'DIC_TYPE', '词典分类科目', '系统管理', null, '2', '0', '1', 'folder22.png', '2');
INSERT INTO `sys_catalog` VALUES ('6', '0.002.002', 'DIC_TYPE', '词典分类科目', '全局通用', null, '2', '1', '0', 'folder24.png', '3');
INSERT INTO `sys_catalog` VALUES ('7', '0.002.006', 'DIC_TYPE', '词典分类科目', '平台配置', null, '2', '1', '0', 'folder2.png', '1');
INSERT INTO `sys_catalog` VALUES ('8', '0.001.002.001', 'PARAM_TYPE', '参数分类科目', '验证码', null, '4', '1', '0', 'ok3.png', '2');
INSERT INTO `sys_catalog` VALUES ('9', '0.001.002.002', 'PARAM_TYPE', '参数分类科目', '界面显示', null, '4', '1', '0', 'icon59.png', '1');
INSERT INTO `sys_catalog` VALUES ('10', '0.001.002.003', 'PARAM_TYPE', '参数分类科目', '其它', null, '4', '1', '0', 'icon150.png', '9');
INSERT INTO `sys_catalog` VALUES ('11', '0.001.002.004', 'PARAM_TYPE', '参数分类科目', '导航与菜单', null, '4', '1', '0', 'icon152.png', '3');
INSERT INTO `sys_catalog` VALUES ('12', '0.002.001.001', 'DIC_TYPE', '词典分类科目', '工作流', null, '5', '1', '0', 'folder6.png', '1');

-- ----------------------------
-- Table structure for `sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id_` int(20) NOT NULL AUTO_INCREMENT COMMENT '部门编号',
  `dept_name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `parent_id` int(20) DEFAULT NULL COMMENT '上级部门编号',
  `enable_` char(1) DEFAULT NULL COMMENT '启用状态',
  `sort_no` int(3) DEFAULT NULL COMMENT '排序号',
  `leaf_` int(1) DEFAULT NULL COMMENT '叶子节点(0:树枝节点;1:叶子节点)',
  `remark_` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_dic`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dic`;
CREATE TABLE `sys_dic` (
  `id_` int(20) NOT NULL AUTO_INCREMENT,
  `index_id` int(20) DEFAULT NULL,
  `code_` varchar(50) DEFAULT NULL,
  `code_text` varchar(100) DEFAULT NULL,
  `enable_` int(1) NOT NULL DEFAULT '1',
  `sort_no` int(2) DEFAULT NULL,
  `editable_` int(1) NOT NULL DEFAULT '1',
  `remark_` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `field_id_code` (`index_id`,`code_`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dic
-- ----------------------------
INSERT INTO `sys_dic` VALUES ('1', '1', '0', '未知', '1', '1', '0', '1');
INSERT INTO `sys_dic` VALUES ('2', '1', '1', '男', '1', '2', '0', '1');
INSERT INTO `sys_dic` VALUES ('3', '1', '2', '女', '1', '3', '0', '1');
INSERT INTO `sys_dic` VALUES ('4', '2', '0', '激活', '1', '1', '0', '1');
INSERT INTO `sys_dic` VALUES ('5', '2', '1', '锁定', '1', '2', '0', '1');
INSERT INTO `sys_dic` VALUES ('6', '3', '1', '业务角色', '1', '1', '0', '1');
INSERT INTO `sys_dic` VALUES ('7', '3', '2', '管理角色', '1', '2', '0', '1');
INSERT INTO `sys_dic` VALUES ('8', '3', '3', '系统内置角色', '1', '3', '0', '1');
INSERT INTO `sys_dic` VALUES ('9', '4', '0', '树枝节点', '1', '1', '0', '1');
INSERT INTO `sys_dic` VALUES ('10', '4', '1', '叶子节点', '1', '2', '0', '1');
INSERT INTO `sys_dic` VALUES ('11', '5', '0', '只读', '1', '1', '0', '1');
INSERT INTO `sys_dic` VALUES ('12', '5', '1', '可编辑', '1', '2', '0', '1');
INSERT INTO `sys_dic` VALUES ('13', '6', '0', '禁用', '1', '1', '0', '1');
INSERT INTO `sys_dic` VALUES ('14', '6', '1', '启用', '1', '2', '0', '1');
INSERT INTO `sys_dic` VALUES ('15', '7', '1', '访问权限', '1', '1', '0', '1');
INSERT INTO `sys_dic` VALUES ('16', '7', '2', '管理权限', '1', '2', '0', '1');
INSERT INTO `sys_dic` VALUES ('17', '8', '1', '系统菜单', '1', '1', '0', '1');
INSERT INTO `sys_dic` VALUES ('18', '8', '2', '业务菜单', '1', '2', '0', '1');
INSERT INTO `sys_dic` VALUES ('19', '9', '1', '经办员', '1', '1', '0', '1');
INSERT INTO `sys_dic` VALUES ('20', '9', '2', '管理员', '1', '2', '0', '1');
INSERT INTO `sys_dic` VALUES ('21', '9', '3', '系统内置用户', '1', '3', '0', '1');
INSERT INTO `sys_dic` VALUES ('22', '10', '0', '收缩', '1', '1', '0', '1');
INSERT INTO `sys_dic` VALUES ('23', '10', '1', '展开', '1', '2', '0', '1');

-- ----------------------------
-- Table structure for `sys_dic_index`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dic_index`;
CREATE TABLE `sys_dic_index` (
  `id_` int(20) NOT NULL AUTO_INCREMENT,
  `catalog_id` int(20) NOT NULL DEFAULT '0',
  `key_` varchar(50) DEFAULT NULL,
  `name_` varchar(200) DEFAULT NULL,
  `remark_` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `code` (`key_`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='代码表';

-- ----------------------------
-- Records of sys_dic_index
-- ----------------------------
INSERT INTO `sys_dic_index` VALUES ('1', '6', 'SEX', '性别', null);
INSERT INTO `sys_dic_index` VALUES ('2', '6', 'LOCKED', '锁定', null);
INSERT INTO `sys_dic_index` VALUES ('3', '5', 'ROLETYPE', '角色类型', null);
INSERT INTO `sys_dic_index` VALUES ('4', '6', 'LEAF', '节点类型', null);
INSERT INTO `sys_dic_index` VALUES ('5', '5', 'EDITABLE', '编辑模式', null);
INSERT INTO `sys_dic_index` VALUES ('6', '5', 'ENABLE', '启用状态', null);
INSERT INTO `sys_dic_index` VALUES ('7', '5', 'AUTHORIZELEVEL', '权限级别', null);
INSERT INTO `sys_dic_index` VALUES ('8', '5', 'MENUTYPE', '菜单类型', null);
INSERT INTO `sys_dic_index` VALUES ('9', '5', 'USERTYPE', '人员类型', null);
INSERT INTO `sys_dic_index` VALUES ('10', '6', 'EXPAND', '展开状态', null);

-- ----------------------------
-- Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id_` int(11) NOT NULL AUTO_INCREMENT,
  `type_key_` int(2) DEFAULT NULL COMMENT '日志类型',
  `type_value_` varchar(30) DEFAULT NULL COMMENT '日志类型值',
  `content_` varchar(500) DEFAULT NULL COMMENT '日志内容',
  `result_` char(1) DEFAULT '1' COMMENT '结果：1成功 0失败',
  `operator_` int(11) DEFAULT NULL COMMENT '操作人',
  `operation_time_` datetime DEFAULT NULL COMMENT '操作时间',
  `failure_cause_` varchar(500) DEFAULT NULL COMMENT '失败原因',
  `ip_` varchar(20) DEFAULT NULL COMMENT '请求ip地址',
  `params_` varchar(1000) DEFAULT NULL COMMENT '请求参数',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='系统日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1', '4', null, '登录系统', '0', '2', null, '账号或密码错误！', '192.168.12.229', null);
INSERT INTO `sys_log` VALUES ('2', '4', '登录', '登录系统', '0', '2', null, '账号或密码错误！', '192.168.12.229', null);
INSERT INTO `sys_log` VALUES ('3', '4', '登录', '登录系统', '0', '2', '2016-05-11 14:06:50', '账号或密码错误！', '192.168.12.229', null);
INSERT INTO `sys_log` VALUES ('4', '4', '登录', '登录系统', '1', '2', '2016-05-11 14:07:45', null, '192.168.12.229', null);
INSERT INTO `sys_log` VALUES ('5', '6', '锁定', '账户信息锁定', '1', '2', '2016-05-11 14:08:07', null, '192.168.12.229', null);
INSERT INTO `sys_log` VALUES ('6', '7', '解除锁定', '账户信息解除锁定', '1', '2', '2016-05-11 14:08:18', null, '192.168.12.229', null);
INSERT INTO `sys_log` VALUES ('7', '4', '登录', '登录系统', '1', '2', null, null, '192.168.12.229', '{\"params0\":{\"account\":\"admin\",\"password\":\"Llf678rVJnvTkZdk8CMaHv+s20wa37zzPidtDjQj7cE\\u003d\"}}');
INSERT INTO `sys_log` VALUES ('8', '7', '解除锁定', '账户信息解除锁定', '1', '2', null, null, '192.168.12.229', '{\"params0\":{\"id\":2}}');
INSERT INTO `sys_log` VALUES ('9', '6', '锁定', '账户信息锁定', '1', '2', null, null, '192.168.12.229', '{\"params0\":{\"id\":2}}');
INSERT INTO `sys_log` VALUES ('10', '1', '修改', '账户信息修改', '0', '2', null, '### Error updating database.  Cause: java.sql.SQLException: sql injection violation, syntax error: syntax error, error in :\'\n    where id_ = ?\',expect SET, actual WHERE where : update sys_user\n      \n    where id_ = ?\r\n### SQL: update sys_user            where id_ = ?\r\n### Cause: java.sql.SQLException: sql injection violation, syntax error: syntax error, error in :\'\n    where id_ = ?\',expect SET, actual WHERE where : update sys_user\n      \n    where id_ = ?\n; uncategorized SQLException for SQL', '192.168.12.229', '{\"params0\":{}}');
INSERT INTO `sys_log` VALUES ('11', '1', '修改', '账户信息修改', '0', '2', null, '服务器内部错误!', '192.168.12.229', '{\"params0\":{}}');
INSERT INTO `sys_log` VALUES ('12', '4', '登录', '登录系统', '1', '2', '2017-06-14 15:14:38', null, '127.0.0.1', '{\"params0\":{\"account\":\"admin\",\"password\":\"admin\"}}');
INSERT INTO `sys_log` VALUES ('13', '4', '登录', '登录系统', '1', '2', '2017-06-14 15:15:40', null, '127.0.0.1', '{\"params0\":{\"account\":\"admin\",\"password\":\"admin\"}}');
INSERT INTO `sys_log` VALUES ('14', '4', '登录', '登录系统', '1', '2', '2017-06-14 15:15:58', null, '127.0.0.1', '{\"params0\":{\"account\":\"admin\",\"password\":\"Llf678rVJnvTkZdk8CMaHv+s20wa37zzPidtDjQj7cE\\u003d\"}}');
INSERT INTO `sys_log` VALUES ('15', '4', '登录', '登录系统', '1', '2', '2017-06-14 15:16:04', null, '127.0.0.1', '{\"params0\":{\"account\":\"admin\",\"password\":\"Llf678rVJnvTkZdk8CMaHv+s20wa37zzPidtDjQj7cE\\u003d\"}}');
INSERT INTO `sys_log` VALUES ('16', '4', '登录', '登录系统', '1', '2', '2017-06-14 15:16:55', null, '127.0.0.1', '{\"params0\":{\"account\":\"admin\",\"password\":\"Llf678rVJnvTkZdk8CMaHv+s20wa37zzPidtDjQj7cE\\u003d\"}}');
INSERT INTO `sys_log` VALUES ('17', '4', '登录', '登录系统', '1', '2', '2017-06-16 21:23:30', null, '127.0.0.1', '{\"params0\":{\"account\":\"admin\",\"password\":\"Llf678rVJnvTkZdk8CMaHv+s20wa37zzPidtDjQj7cE\\u003d\"}}');
INSERT INTO `sys_log` VALUES ('18', '4', '登录', '登录系统', '1', '2', '2017-06-19 10:35:03', null, '127.0.0.1', '{\"params0\":{\"id\":null,\"account\":\"admin\",\"password\":\"Llf678rVJnvTkZdk8CMaHv+s20wa37zzPidtDjQj7cE=\",\"sex\":null,\"userName\":null,\"avatar\":null,\"userType\":null,\"deptId\":null,\"locked\":null,\"usable\":null,\"createTime\":null}}');
INSERT INTO `sys_log` VALUES ('19', '4', '登录', '登录系统', '1', '2', '2017-06-19 10:36:10', null, '127.0.0.1', '{\"params0\":{\"id\":null,\"account\":\"admin\",\"password\":\"Llf678rVJnvTkZdk8CMaHv+s20wa37zzPidtDjQj7cE=\",\"sex\":null,\"userName\":null,\"avatar\":null,\"userType\":null,\"deptId\":null,\"locked\":null,\"usable\":null,\"createTime\":null}}');
INSERT INTO `sys_log` VALUES ('20', '4', '登录', '登录系统', '0', '2', '2017-06-19 10:38:00', '服务器内部错误!', '127.0.0.1', '{\"params0\":{\"id\":null,\"account\":\"admin\",\"password\":\"Llf678rVJnvTkZdk8CMaHv+s20wa37zzPidtDjQj7cE=\",\"sex\":null,\"userName\":null,\"avatar\":null,\"userType\":null,\"deptId\":null,\"locked\":null,\"usable\":null,\"createTime\":null}}');
INSERT INTO `sys_log` VALUES ('21', '4', '登录', '登录系统', '1', '2', '2017-06-19 10:44:45', null, '127.0.0.1', '{\"params0\":{\"id\":null,\"account\":\"admin\",\"password\":\"Llf678rVJnvTkZdk8CMaHv+s20wa37zzPidtDjQj7cE=\",\"sex\":null,\"userName\":null,\"avatar\":null,\"userType\":null,\"deptId\":null,\"locked\":null,\"usable\":null,\"createTime\":null}}');
INSERT INTO `sys_log` VALUES ('22', '4', '登录', '登录系统', '1', '2', '2017-06-19 10:45:51', null, '127.0.0.1', '{\"params0\":{\"id\":null,\"account\":\"admin\",\"password\":\"Llf678rVJnvTkZdk8CMaHv+s20wa37zzPidtDjQj7cE=\",\"sex\":null,\"userName\":null,\"avatar\":null,\"userType\":null,\"deptId\":null,\"locked\":null,\"usable\":null,\"createTime\":null}}');
INSERT INTO `sys_log` VALUES ('23', '4', '登录', '登录系统', '1', '2', '2017-06-19 10:54:45', null, '127.0.0.1', '{\"params0\":{\"id\":null,\"account\":\"admin\",\"password\":\"Llf678rVJnvTkZdk8CMaHv+s20wa37zzPidtDjQj7cE=\",\"sex\":null,\"userName\":null,\"avatar\":null,\"userType\":null,\"deptId\":null,\"locked\":null,\"usable\":null,\"createTime\":null}}');

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id_` int(20) NOT NULL AUTO_INCREMENT COMMENT '菜单编号',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `menu_type` char(1) DEFAULT '0' COMMENT '菜单类型(1:系统菜单;0:业务菜单)',
  `parent_id` int(20) DEFAULT NULL COMMENT '上级菜单编号',
  `iconcls_` varchar(50) DEFAULT NULL COMMENT '节点图标CSS类名',
  `request_` varchar(100) DEFAULT NULL COMMENT '请求地址',
  `expand_` int(1) NOT NULL DEFAULT '0' COMMENT '展开状态(1:展开;0:收缩)',
  `sort_no` int(2) DEFAULT NULL COMMENT '排序号',
  `leaf_` int(1) NOT NULL DEFAULT '0' COMMENT '叶子节点(0:树枝节点;1:叶子节点)',
  `remark_` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_param`
-- ----------------------------
DROP TABLE IF EXISTS `sys_param`;
CREATE TABLE `sys_param` (
  `id_` int(20) NOT NULL AUTO_INCREMENT COMMENT '参数编号',
  `param_key` varchar(50) DEFAULT NULL COMMENT '参数键名',
  `param_value` varchar(100) DEFAULT NULL COMMENT '参数键值',
  `catalog_id` int(20) DEFAULT NULL,
  `remark_` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全局参数表';

-- ----------------------------
-- Records of sys_param
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id_` int(20) NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `dept_id` int(20) DEFAULT NULL COMMENT '所属部门编号',
  `role_type` char(1) NOT NULL DEFAULT '1' COMMENT '角色类型(1:业务角色;2:管理角色 ;3:系统内置角色)',
  `enable_` char(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  `remark_` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id_` int(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(20) DEFAULT NULL,
  `menu_id` int(20) DEFAULT NULL,
  `authorize_` int(1) NOT NULL DEFAULT '1' COMMENT '权限级别(1:访问权限;2:管理权限)',
  `operate_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `operator_id` int(20) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_`),
  UNIQUE KEY `role_id_menu_id` (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色授权表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id_` int(20) NOT NULL AUTO_INCREMENT,
  `account_` varchar(20) NOT NULL COMMENT '登陆帐户',
  `password_` varchar(50) NOT NULL COMMENT '密码',
  `sex_` char(1) DEFAULT '0' COMMENT '性别(0:未知;1:男;2:女)',
  `user_name` varchar(30) DEFAULT NULL COMMENT '用户名',
  `avatar_` varchar(50) DEFAULT NULL,
  `user_type` char(1) DEFAULT '1' COMMENT '人员类型(1:经办员;2:管理员;3:系统内置人员;)',
  `dept_id` int(20) DEFAULT '1' COMMENT '部门编号',
  `locked_` char(1) DEFAULT '0' COMMENT '锁定标志(1:锁定;0:激活)',
  `usable_` char(1) DEFAULT '1',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `account` (`account_`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('2', 'admin', 'Llf678rVJnvTkZdk8CMaHv+s20wa37zzPidtDjQj7cE=', '0', 'admin', null, '3', '1', '1', '1', '2016-05-06 10:06:52');

-- ----------------------------
-- Table structure for `sys_user_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_menu`;
CREATE TABLE `sys_user_menu` (
  `id_` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) DEFAULT NULL,
  `menu_id` int(20) DEFAULT NULL,
  `operate_time` timestamp NULL DEFAULT NULL,
  `operator_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `user_id_menu_id` (`user_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户授权表';

-- ----------------------------
-- Records of sys_user_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id_` int(20) NOT NULL,
  `user_id` int(20) DEFAULT NULL,
  `role_id` int(20) DEFAULT NULL,
  `operate_time` timestamp NULL DEFAULT NULL,
  `operator_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `user_id_role_id` (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户授权表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user_thirdparty`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_thirdparty`;
CREATE TABLE `sys_user_thirdparty` (
  `id_` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) NOT NULL,
  `provider_` varchar(50) NOT NULL COMMENT '第三方类型',
  `open_id` varchar(50) NOT NULL COMMENT '第三方Id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`),
  UNIQUE KEY `user_id_provider__open_id` (`user_id`,`provider_`,`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='第三方用户';

-- ----------------------------
-- Records of sys_user_thirdparty
-- ----------------------------
