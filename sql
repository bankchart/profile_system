
create table member_tb 
(
	userid int(3),
	username varchar(10),
	password varchar(10),
	fullname varchar(30),
	birthdate date,
	height float(5, 2),
	weight float(5,2),
	blood_type char(1),
	hobby text,
	eduid int(2),
	phone int(10),
	email varchar(20),
	detail_edu text,
	detail_fav text,
	detail_lsk text,
	picture_path text,
	primary key(userid)
)engine=innodb;

create table education_history_tb
(
	userid int(3),
	eduid int(2),
	primary key(userid, eduid)
)engine=innodb;

create table education_tb 
(
	eduid int(2) not null auto_increment,
	edu_name varchar(30) not null,
	primary key(eduid)		
)engine=innodb;

create table faculty_tb 
(
	facultyid int(2) not null auto_increment,
	faculty_name varchar(50) not null,
	eduid int(2),
	primary key(facultyid)
)engine=innodb;

create table edu_fac_tb 
(
	eduid int(2) not null,
	facultyid int(2) not null,
	primary key(eduid, facultyid)	
)engine=innodb;

create table major_tb
(
	majorid int(2) not null auto_increment,
	major_name varchar(30),
	facultyid int(2),
	primary key(majorid)
)engine=innodb;
