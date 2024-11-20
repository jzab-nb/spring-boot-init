drop database if exists init;
CREATE database if not exists init;
use init;

-- 用户表
create table if not exists init.`user`
(
`id` bigint not null auto_increment comment '主键' primary key,
`create_time` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
`update_time` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
`is_deleted` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)',
`username` varchar(256) not null comment '用户名',
`password` varchar(256) not null comment '密码'
) comment '用户表';

insert into init.`user` (`username`, `password`) values ('JkT', 'mSO5');
insert into init.`user` (`username`, `password`) values ('Z3G7F', 'JmcY');
insert into init.`user` (`username`, `password`) values ('aGPaG', 'uyGF');
insert into init.`user` (`username`, `password`) values ('HKoG', 'ofAI');
insert into init.`user` (`username`, `password`) values ('3p4dI', 'Diwi');
insert into init.`user` (`username`, `password`) values ('GqC6', 'xM');
insert into init.`user` (`username`, `password`) values ('pT', 'k0e');
insert into init.`user` (`username`, `password`) values ('g8f', 'DL0zE');
insert into init.`user` (`username`, `password`) values ('zF', 'uei');
insert into init.`user` (`username`, `password`) values ('NEVbL', 'izJ');
insert into init.`user` (`username`, `password`) values ('mpc', 'Nq66v');
insert into init.`user` (`username`, `password`) values ('uBCPd', 'Vy');
insert into init.`user` (`username`, `password`) values ('Xi5', '1nNQc');
insert into init.`user` (`username`, `password`) values ('w5j', 'iYj');
insert into init.`user` (`username`, `password`) values ('lq', 'GJaI');
insert into init.`user` (`username`, `password`) values ('2g', 'Naq');
insert into init.`user` (`username`, `password`) values ('Yh6b5', 'RcHNt');
insert into init.`user` (`username`, `password`) values ('XR3cA', 'Cug2W');
insert into init.`user` (`username`, `password`) values ('lvK4', 'P0MW');
insert into init.`user` (`username`, `password`) values ('n9S', 'R9');