
CREATE database if NOT EXISTS `ncp_scheduler_job` default character set utf8 collate utf8_unicode_ci;
use `ncp_scheduler_job`;

SET NAMES utf8;

CREATE TABLE `xxl_job_info` (
                                `pk_id` bigint(20) NOT NULL AUTO_INCREMENT,
                                `journey_id` varchar(255) NOT NULL,
                                `job_type` varchar(10) NOT NULL,
                                `job_group` bigint(20) NOT NULL COMMENT '执行器主键ID',
                                `job_desc` varchar(255) NOT NULL,
                                `instance_id` varchar(255) NOT NULL,
                                `activity_id` varchar(255) NOT NULL,
                                `add_time` datetime DEFAULT NULL,
                                `update_time` datetime DEFAULT NULL,
                                `author` varchar(64) DEFAULT NULL COMMENT '作者',
                                `alarm_email` varchar(255) DEFAULT NULL COMMENT '报警邮件',
                                `schedule_type` varchar(50) NOT NULL DEFAULT 'NONE' COMMENT '调度类型',
                                `schedule_conf` varchar(128) DEFAULT NULL COMMENT '调度配置，值含义取决于调度类型',
                                `misfire_strategy` varchar(50) NOT NULL DEFAULT 'DO_NOTHING' COMMENT '调度过期策略',
                                `executor_route_strategy` varchar(50) DEFAULT NULL COMMENT '执行器路由策略',
                                `executor_handler` varchar(255) DEFAULT NULL COMMENT '执行器任务handler',
                                `executor_param` varchar(512) DEFAULT NULL COMMENT '执行器任务参数',
                                `executor_block_strategy` varchar(50) DEFAULT NULL COMMENT '阻塞处理策略',
                                `executor_timeout` int(11) NOT NULL DEFAULT '0' COMMENT '任务执行超时时间，单位秒',
                                `executor_fail_retry_count` int(11) NOT NULL DEFAULT '0' COMMENT '失败重试次数',
                                `glue_type` varchar(50) NOT NULL COMMENT 'GLUE类型',
                                `glue_source` mediumtext COMMENT 'GLUE源代码',
                                `glue_remark` varchar(128) DEFAULT NULL COMMENT 'GLUE备注',
                                `glue_updatetime` datetime DEFAULT NULL COMMENT 'GLUE更新时间',
                                `child_jobid` varchar(255) DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
                                `trigger_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '调度状态：0-停止，1-运行，2-人工停止',
                                `trigger_last_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '上次调度时间',
                                `trigger_next_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '下次调度时间',
                                `journey_start_time` datetime DEFAULT NULL COMMENT '调度开始时间',
                                `journey_end_time` datetime DEFAULT NULL COMMENT '调度结束时间',
                                PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xxl_job_log` (
                               `pk_id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `job_group` bigint(20) NOT NULL COMMENT '执行器主键ID',
                               `job_id` bigint(20) NOT NULL COMMENT '任务，主键ID',
                               `journey_id` varchar(255) NOT NULL,
                               `executor_address` varchar(255) DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
                               `executor_handler` varchar(255) DEFAULT NULL COMMENT '执行器任务handler',
                               `executor_param` varchar(512) DEFAULT NULL COMMENT '执行器任务参数',
                               `executor_sharding_param` varchar(20) DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
                               `executor_fail_retry_count` int(11) NOT NULL DEFAULT '0' COMMENT '失败重试次数',
                               `trigger_time` datetime DEFAULT NULL COMMENT '调度-时间',
                               `trigger_code` int(11) NOT NULL COMMENT '调度-结果',
                               `trigger_msg` text COMMENT '调度-日志',
                               `handle_time` datetime DEFAULT NULL COMMENT '执行-时间',
                               `handle_code` int(11) NOT NULL COMMENT '执行-状态',
                               `handle_msg` text COMMENT '执行-日志',
                               `alarm_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
                               PRIMARY KEY (`pk_id`),
                               KEY `I_trigger_time` (`trigger_time`),
                               KEY `I_handle_code` (`handle_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xxl_job_log_report` (
                                      `pk_id` bigint(20) NOT NULL AUTO_INCREMENT,
                                      `trigger_day` datetime DEFAULT NULL COMMENT '调度-时间',
                                      `running_count` int(11) NOT NULL DEFAULT '0' COMMENT '运行中-日志数量',
                                      `suc_count` int(11) NOT NULL DEFAULT '0' COMMENT '执行成功-日志数量',
                                      `fail_count` int(11) NOT NULL DEFAULT '0' COMMENT '执行失败-日志数量',
                                      `update_time` datetime DEFAULT NULL,
                                      PRIMARY KEY (`pk_id`),
                                      UNIQUE KEY `i_trigger_day` (`trigger_day`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xxl_job_logglue` (
                                   `pk_id` bigint(20) NOT NULL AUTO_INCREMENT,
                                   `job_id` bigint(20) NOT NULL COMMENT '任务，主键ID',
                                   `journey_id` varchar(255) NOT NULL,
                                   `glue_type` varchar(50) DEFAULT NULL COMMENT 'GLUE类型',
                                   `glue_source` mediumtext COMMENT 'GLUE源代码',
                                   `glue_remark` varchar(128) NOT NULL COMMENT 'GLUE备注',
                                   `add_time` datetime DEFAULT NULL,
                                   `update_time` datetime DEFAULT NULL,
                                   PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xxl_job_registry` (
                                    `pk_id` bigint(20) NOT NULL AUTO_INCREMENT,
                                    `registry_group` varchar(50) NOT NULL,
                                    `registry_key` varchar(255) NOT NULL,
                                    `registry_value` varchar(255) NOT NULL,
                                    `update_time` datetime DEFAULT NULL,
                                    PRIMARY KEY (`pk_id`),
                                    KEY `i_g_k_v` (`registry_group`,`registry_key`,`registry_value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xxl_job_group` (
                                 `pk_id` bigint(20) NOT NULL AUTO_INCREMENT,
                                 `app_name` varchar(64) NOT NULL COMMENT '执行器AppName',
                                 `title` varchar(12) NOT NULL COMMENT '执行器名称',
                                 `address_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '执行器地址类型：0=自动注册、1=手动录入',
                                 `address_list` text COMMENT '执行器地址列表，多地址逗号分隔',
                                 `update_time` datetime DEFAULT NULL,
                                 PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xxl_job_user` (
                                `pk_id` bigint(20) NOT NULL AUTO_INCREMENT,
                                `username` varchar(50) NOT NULL COMMENT '账号',
                                `password` varchar(50) NOT NULL COMMENT '密码',
                                `role` tinyint(4) NOT NULL COMMENT '角色：0-普通用户、1-管理员',
                                `permission` varchar(255) DEFAULT NULL COMMENT '权限：执行器ID列表，多个逗号分割',
                                PRIMARY KEY (`pk_id`),
                                UNIQUE KEY `i_username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `xxl_job_lock` (
                                `lock_name` varchar(50) NOT NULL COMMENT '锁名称',
                                PRIMARY KEY (`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `xxl_job_group`(`pk_id`, `app_name`, `title`, `address_type`, `address_list`, `update_time`) VALUES (1, 'ncp-scheduler-executor', 'ncp-basic', 0, NULL, '2022-11-03 22:21:31' );
INSERT INTO `xxl_job_user`(`pk_id`, `username`, `password`, `role`, `permission`) VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 1, NULL);
INSERT INTO `xxl_job_lock` ( `lock_name`) VALUES ( 'schedule_lock');
INSERT INTO `xxl_job_info` (`pk_id`,`journey_id`,`job_type`,`job_group`,`job_desc`,`instance_id`,`activity_id`,`add_time`,`update_time`,`author`,`alarm_email`,`schedule_type`,`schedule_conf`,`misfire_strategy`,`executor_route_strategy`,`executor_handler`,`executor_param`,`executor_block_strategy`,`executor_timeout`,`executor_fail_retry_count`,`glue_type`,`glue_source`,`glue_remark`,`glue_updatetime`,`child_jobid`,`trigger_status`,`trigger_last_time`,`trigger_next_time`,`journey_start_time`,`journey_end_time`) VALUES (1,0,'0',1,'start-to-point <non-manual shutdown> all tasks','null','null','2022-11-08 17:13:33','2022-11-09 16:53:01','ncp-scheduler','','CRON','0 * * * * ?','DO_NOTHING','FIRST','basicHttpJobHandler','url:https://scheduleradm.onencp-int-test.gcncp.nikecloud.com.cn/v1/scheduler/autoStart\r\nmethod:GET\r\n','SERIAL_EXECUTION',0,0,'BEAN','','GLUE代码初始化','2022-11-08 17:13:33','',1,1669975559000,1669975560000,NULL,NULL);
INSERT INTO `xxl_job_info` (`pk_id`,`journey_id`,`job_type`,`job_group`,`job_desc`,`instance_id`,`activity_id`,`add_time`,`update_time`,`author`,`alarm_email`,`schedule_type`,`schedule_conf`,`misfire_strategy`,`executor_route_strategy`,`executor_handler`,`executor_param`,`executor_block_strategy`,`executor_timeout`,`executor_fail_retry_count`,`glue_type`,`glue_source`,`glue_remark`,`glue_updatetime`,`child_jobid`,`trigger_status`,`trigger_last_time`,`trigger_next_time`,`journey_start_time`,`journey_end_time`) VALUES (2,0,'0',1,'close-all tasks due','null','null','2022-11-09 16:51:54','2022-11-09 16:52:40','ncp-scheduler','','CRON','0 * * * * ?','DO_NOTHING','FIRST','basicHttpJobHandler','url:https://scheduleradm.onencp-int-test.gcncp.nikecloud.com.cn/v1/scheduler/autoStop\r\nmethod:GET\r\n','SERIAL_EXECUTION',0,0,'BEAN','','GLUE代码初始化','2022-11-09 16:51:54','',1,1669975559000,1669975560000,NULL,NULL);
INSERT INTO `xxl_job_info` (`pk_id`,`journey_id`,`job_type`,`job_group`,`job_desc`,`instance_id`,`activity_id`,`add_time`,`update_time`,`author`,`alarm_email`,`schedule_type`,`schedule_conf`,`misfire_strategy`,`executor_route_strategy`,`executor_handler`,`executor_param`,`executor_block_strategy`,`executor_timeout`,`executor_fail_retry_count`,`glue_type`,`glue_source`,`glue_remark`,`glue_updatetime`,`child_jobid`,`trigger_status`,`trigger_last_time`,`trigger_next_time`,`journey_start_time`,`journey_end_time`) VALUES (3,0,'0',1,'journeyExpiryCheck','null','null','2022-11-09 16:51:54','2022-11-09 16:52:40','ncp-scheduler','','CRON','0 1 0 * * ?','DO_NOTHING','FIRST','basicHttpJobHandler','url:https://journeybuilder.onencp-int-test.gcncp.nikecloud.com.cn/v1/journeys/expiryStatus\r\nmethod:POST\r\n','SERIAL_EXECUTION',0,0,'BEAN','','GLUE代码初始化','2023-05-10 16:24:20','',1,1683734460000,1683820860000,NULL,NULL);
commit;

