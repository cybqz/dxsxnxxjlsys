/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : clegstdehalearning

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 29/01/2020 13:21:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for accumulate_points
-- ----------------------------
DROP TABLE IF EXISTS `accumulate_points`;
CREATE TABLE `accumulate_points`  (
  `id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `user_id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关联用户ID',
  `parame_id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '兴趣编码',
  `points` int(4) NULL DEFAULT 0 COMMENT '积分',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '积分表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of accumulate_points
-- ----------------------------
INSERT INTO `accumulate_points` VALUES ('d9e01ff4-544f-4d48-bed7-7a8f84d7352f', '1bd50bb2-2c95-4e60-94ea-a9f378986487', 'd2fe9609-7ef8-469f-bc57-f9364cd4e0be', 10, '2020-01-29 13:13:35');

-- ----------------------------
-- Table structure for interest
-- ----------------------------
DROP TABLE IF EXISTS `interest`;
CREATE TABLE `interest`  (
  `id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `user_id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关联用户ID',
  `parame_id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '兴趣编码',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_interest_user_id`(`user_id`) USING BTREE,
  INDEX `fk_interest_parames_id`(`parame_id`) USING BTREE,
  CONSTRAINT `fk_interest_parames_id` FOREIGN KEY (`parame_id`) REFERENCES `parames` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_interest_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '兴趣表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of interest
-- ----------------------------
INSERT INTO `interest` VALUES ('11492e3b-42fb-4356-aeb6-57d3a2761a83', '1bd50bb2-2c95-4e60-94ea-a9f378986487', '0c29716a-5c39-4582-829c-814a8ba909b2', '2020-01-25 16:46:51');
INSERT INTO `interest` VALUES ('43942a68-c9e3-4844-b84c-44675e256a3b', '1bd50bb2-2c95-4e60-94ea-a9f378986487', '16e93f1a-5e0f-4b00-9198-06ebf1fa7d3c', '2020-01-25 16:46:52');
INSERT INTO `interest` VALUES ('525ae481-bbe9-4431-8ebc-8e811c863792', '1bd50bb2-2c95-4e60-94ea-a9f378986487', '80509827-ff83-43f2-af48-f36c3bcc673c', '2020-01-25 16:46:52');
INSERT INTO `interest` VALUES ('6a38efe6-c8d7-4b43-aee0-2cad209ce1dd', '1bd50bb2-2c95-4e60-94ea-a9f378986487', '812a3bbb-437c-4214-bca4-76605eaf4ac0', '2020-01-25 16:46:52');
INSERT INTO `interest` VALUES ('d0a72e47-615b-4f64-947e-20bf8c511567', '1bd50bb2-2c95-4e60-94ea-a9f378986487', '37d1a27e-1a1d-49a2-b0ef-1cc67a20ed90', '2020-01-25 16:46:52');

-- ----------------------------
-- Table structure for parames
-- ----------------------------
DROP TABLE IF EXISTS `parames`;
CREATE TABLE `parames`  (
  `id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值',
  `group` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分组',
  `order` int(2) NULL DEFAULT NULL COMMENT '排序',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of parames
-- ----------------------------
INSERT INTO `parames` VALUES ('02aaf5a8-5f84-45bf-8b96-229bff874d6d', 'INTEREST', '看电影', 'INTEREST', 1, NULL);
INSERT INTO `parames` VALUES ('0c29716a-5c39-4582-829c-814a8ba909b2', 'INTEREST', '看书', 'INTEREST', 2, NULL);
INSERT INTO `parames` VALUES ('16e93f1a-5e0f-4b00-9198-06ebf1fa7d3c', 'INTEREST', '逛街', 'INTEREST', 3, NULL);
INSERT INTO `parames` VALUES ('37d1a27e-1a1d-49a2-b0ef-1cc67a20ed90', 'INTEREST', '跳舞', 'INTEREST', 4, NULL);
INSERT INTO `parames` VALUES ('4a760c99-c782-4321-ab98-9c2e3c4159cf', 'INTEREST', '演奏乐器', 'INTEREST', 5, NULL);
INSERT INTO `parames` VALUES ('80509827-ff83-43f2-af48-f36c3bcc673c', 'INTEREST', '健身', 'INTEREST', 6, NULL);
INSERT INTO `parames` VALUES ('812a3bbb-437c-4214-bca4-76605eaf4ac0', 'INTEREST', '玩网络游戏', 'INTEREST', 7, NULL);
INSERT INTO `parames` VALUES ('8b5e52b4-0188-4c54-ae72-c0689c4538e9', 'INTEREST', '听歌', 'INTEREST', 8, NULL);
INSERT INTO `parames` VALUES ('9e438b72-357e-4d06-be2f-6ff4daec61f0', 'INTEREST', '玩羽毛球', 'INTEREST', 9, NULL);
INSERT INTO `parames` VALUES ('d2fe9609-7ef8-469f-bc57-f9364cd4e0be', 'SIGNIN', '签到', 'POINTS', 1, NULL);
INSERT INTO `parames` VALUES ('d564adc6-2b72-4094-8dab-4a8b61153e94', 'INTEREST', '乒乓球', 'INTEREST', 10, NULL);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'name',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'name',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('a00b4b4b-842c-4e9f-815d-f9d7fd053091', 'admin', '管理员');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `role_id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联角色',
  `permission_id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联权限',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  INDEX `permission_id`(`permission_id`) USING BTREE,
  CONSTRAINT `permission_id` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for signin
-- ----------------------------
DROP TABLE IF EXISTS `signin`;
CREATE TABLE `signin`  (
  `id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `user_id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关联用户ID',
  `datetime` timestamp(0) NULL DEFAULT NULL COMMENT '签到日期',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk_signin_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `fk_signin_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '签到表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of signin
-- ----------------------------
INSERT INTO `signin` VALUES ('fbe3f514-ccec-4f22-995e-9f92ebf27529', '1bd50bb2-2c95-4e60-94ea-a9f378986487', '2020-01-29 13:13:03');

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team`  (
  `id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `user_id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关联创建用户ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组队名称',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  INDEX `id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '队伍表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team
-- ----------------------------
INSERT INTO `team` VALUES ('7d1a75ab-9c5b-4a77-804a-546e753a3c5f', '1bd50bb2-2c95-4e60-94ea-a9f378986487', 'gfj1对', '2020-01-26 13:15:06');

-- ----------------------------
-- Table structure for team_member
-- ----------------------------
DROP TABLE IF EXISTS `team_member`;
CREATE TABLE `team_member`  (
  `id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `team_id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '队伍ID',
  `user_id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关联创建用户ID',
  `is_captain` int(1) NULL DEFAULT NULL COMMENT '是否是队长 0：否，1：是',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  INDEX `fk_team_member_team_id`(`team_id`) USING BTREE,
  INDEX `fk_team_member_user_id`(`user_id`) USING BTREE,
  CONSTRAINT `fk_team_member_team_id` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_team_member_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '队员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team_member
-- ----------------------------
INSERT INTO `team_member` VALUES ('ea29958f-1f5d-4d01-88c7-1605cb270957', '7d1a75ab-9c5b-4a77-804a-546e753a3c5f', '1bd50bb2-2c95-4e60-94ea-a9f378986487', 1, '2020-01-26 13:15:06');
INSERT INTO `team_member` VALUES ('dfc733c5-a521-4279-9486-489d148240c4', '7d1a75ab-9c5b-4a77-804a-546e753a3c5f', '16a5fbdc-fd87-4824-b03e-6a137051bb87', 0, '2020-01-26 13:46:41');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `sex` int(1) NULL DEFAULT NULL COMMENT '性别',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '头像',
  `introduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '介绍',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('16a5fbdc-fd87-4824-b03e-6a137051bb87', NULL, 'cyb', '64c8b1e43d8ba3115ab40bcea57f010b', NULL, NULL, 0, NULL, '/headportrait/gril.png', NULL);
INSERT INTO `user` VALUES ('1bd50bb2-2c95-4e60-94ea-a9f378986487', 'paopao', 'gfj', '64c8b1e43d8ba3115ab40bcea57f010b', '123', '123', 0, 'address', '/headportrait/gril.png', '我是障障');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `user_id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联用户',
  `role_id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联角色',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('235213f7-f69e-44c4-8d24-b5ef0e736a3a', '1bd50bb2-2c95-4e60-94ea-a9f378986487', 'a00b4b4b-842c-4e9f-815d-f9d7fd053091', 'gfj_admin');
INSERT INTO `user_role` VALUES ('5ec26942-e148-40fa-8ac7-8f18006d2b90', '194a78dd-b57c-4438-b1db-09aa556d8aa3', 'a00b4b4b-842c-4e9f-815d-f9d7fd053091', 'cyb_admin');

SET FOREIGN_KEY_CHECKS = 1;
