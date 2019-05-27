/*
 Navicat Premium Data Transfer

 Source Server         : 10.94.44.77
 Source Server Type    : MySQL
 Source Server Version : 50637
 Source Host           : 10.94.44.77:3306
 Source Schema         : wl_base

 Target Server Type    : MySQL
 Target Server Version : 50637
 File Encoding         : 65001

 Date: 27/05/2019 16:27:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for wl_b_account
-- ----------------------------
DROP TABLE IF EXISTS `wl_b_account`;
CREATE TABLE `wl_b_account`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名（账号）唯一检查',
  `password` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `phone` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像地址',
  `language` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户语言 不使用id 直接使用 配置文件名',
  `state` int(2) NULL DEFAULT 1 COMMENT '用户状态 0禁用 1启用 -9删除（视业务情况做软删除） 默认1启用',
  `operator_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号所属部门',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '平台账号表 通用账号' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of wl_b_account
-- ----------------------------
INSERT INTO `wl_b_account` VALUES (1, 'admin', '$2a$10$MQmm5HuZa80k.OjCzGvpVuOGEslm3lrOydM9BqSZizO8r7rVBTyrq', '13154726233', '13154726233@qq.com', 'http://fms-manage.magicyo.com/FmvB6YRXC5Wl20GLGkMYap9lAHSw', 'zh_CN', 1, 'C001', '2018-12-06 16:33:29', '2019-05-24 10:51:24');
INSERT INTO `wl_b_account` VALUES (2, 'test', '$10$MQmm5HuZa80k.OjCzGvpVuOGEslm3lrOydM9BqSZizO8r7rVBTyrq', '15986868686', '15986868686@qq.com', 'manage.magicyo.com/FmvB6YRXC5Wl20GLGkMYap9lAHSw', 'zh_CN', 1, 'C001', '2019-05-24 13:38:26', '2019-05-24 13:38:53');

-- ----------------------------
-- Table structure for wl_b_role
-- ----------------------------
DROP TABLE IF EXISTS `wl_b_role`;
CREATE TABLE `wl_b_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_comment` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operator_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of wl_b_role
-- ----------------------------
INSERT INTO `wl_b_role` VALUES (1, '超级管理员', '拥有全部权限 不可删除', 'C001');

-- ----------------------------
-- Table structure for wl_b_role_account
-- ----------------------------
DROP TABLE IF EXISTS `wl_b_role_account`;
CREATE TABLE `wl_b_role_account`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) NOT NULL,
  `role_id` bigint(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色与用户关系表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of wl_b_role_account
-- ----------------------------
INSERT INTO `wl_b_role_account` VALUES (1, 1, 1);
INSERT INTO `wl_b_role_account` VALUES (2, 2, 1);

-- ----------------------------
-- Table structure for wl_b_role_url
-- ----------------------------
DROP TABLE IF EXISTS `wl_b_role_url`;
CREATE TABLE `wl_b_role_url`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL,
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色资源权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of wl_b_role_url
-- ----------------------------
INSERT INTO `wl_b_role_url` VALUES (1, 1, '/auth');
INSERT INTO `wl_b_role_url` VALUES (2, 1, '/user/info');

SET FOREIGN_KEY_CHECKS = 1;
