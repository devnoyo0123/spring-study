create table if not exists comment
(
    id         bigint auto_increment
        primary key,
    content    varchar(255) null,
    created_by varchar(255) null,
    post_id    bigint       not null
);

create table if not exists feed_v2
(
    id        bigint auto_increment
        primary key,
    member_id bigint not null
);

create index idx_feed_v2_member_id
    on feed_v2 (member_id);

create table if not exists member
(
    id       bigint auto_increment
        primary key,
    email    varchar(255) null,
    name     varchar(255) null,
    password varchar(255) null
);

create table if not exists feed
(
    id        bigint auto_increment
        primary key,
    member_id bigint null,
    constraint FK1x14ugfrott48p7aeotv7wskb
        foreign key (member_id) references member (id)
);

create table if not exists memberv2
(
    id       bigint auto_increment
        primary key,
    email    varchar(255) null,
    name     varchar(255) null,
    password varchar(255) null
);

create table if not exists post
(
    id         bigint auto_increment
        primary key,
    content    varchar(255) null,
    created_by varchar(255) null,
    title      varchar(255) null
);

create table if not exists study_activity_instance_v2
(
    id                     char(36) not null
        primary key,
    answer                 int      null,
    is_correct             bit      null,
    user_answer            int      null,
    study_unit_instance_id char(36) not null
);

create index study_activity_instance_v2_study_unit_instance_id_idx
    on study_activity_instance_v2 (study_unit_instance_id);

create table if not exists study_module_instance
(
    id            binary(16) not null
        primary key,
    complete_rate int        not null,
    feed_id       bigint     null,
    constraint FK6l3o7rl195ui4jo2tind22q2q
        foreign key (feed_id) references feed (id)
);

create table if not exists study_module_instance_v2
(
    id            char(36) not null
        primary key,
    complete_rate int      not null,
    feed_id       bigint   null
);

create index study_module_instance_v2_feed_id_idx
    on study_module_instance_v2 (feed_id);

create table if not exists study_unit_instance
(
    id                       binary(16) not null
        primary key,
    complete_rate            int        not null,
    study_module_instance_id binary(16) null,
    constraint FKbdtrc02639occhvsouhty7p5j
        foreign key (study_module_instance_id) references study_module_instance (id)
);

create table if not exists study_activity_instance
(
    id                     binary(16) not null
        primary key,
    answer                 int        null,
    is_correct             bit        null,
    user_answer            int        null,
    study_unit_instance_id binary(16) null,
    constraint FKct52n3d38evdkvl9y86jydljt
        foreign key (study_unit_instance_id) references study_unit_instance (id)
);

create table if not exists study_unit_instance_v2
(
    id                       char(36) not null
        primary key,
    complete_rate            int      not null,
    study_module_instance_id char(36) null
);

create index study_unit_instance_v2_study_module_instance_id_idx
    on study_unit_instance_v2 (study_module_instance_id);

create table if not exists team
(
    id   bigint auto_increment
        primary key,
    name varchar(255) null
);

create table if not exists sample_member
(
    id      bigint auto_increment
        primary key,
    name    varchar(255) null,
    team_id bigint       null,
    constraint FKenwhyo3xalhe0rgytja6296dy
        foreign key (team_id) references team (id)
);

