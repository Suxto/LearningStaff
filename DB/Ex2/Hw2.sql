CREATE DATABASE Stu;

CREATE TABLE DEPARTMENT(
    DNo CHAR(4) PRIMARY KEY,
    DName CHAR(20) UNIQUE,
    head CHAR(20)
);

CREATE TABLE DORM(
    DORMNo CHAR(5) PRIMARY KEY,
    TELE CHAR(7)
);

CREATE TABLE COURSE(
    CNo CHAR(2)PRIMARY KEY,
    CName CHAR(20),
    CPno CHAR(2),
    credit INT,
    teacher CHAR(8)
);

CREATE TABLE STUDENT(
    SNo CHAR(6) PRIMARY KEY,
    SName CHAR(8),
    sex CHAR(2),
    SAge INT,
    DNo CHAR(4),
    DORMNo CHAR(5),
    FOREIGN KEY(DNo) REFERENCES DEPARTMENT(DNo),
    FOREIGN KEY(DORMNo) REFERENCES DORM(DORMNo)
);

CREATE TABLE GRADE(
    SNo CHAR(6),
    CNo CHAR(2),
    score DECIMAL(4,1),
    FOREIGN KEY(SNo) REFERENCES STUDENT(SNo),
    FOREIGN KEY(CNo) REFERENCES COURSE(CNo),
    PRIMARY KEY(SNo,CNo)
);

INSERT INTO DEPARTMENT(DNo,DName,head) VALUES
('1','计算机系','王凯峰'), 
('2','数学系','李永军'),
('3','物理系','唐键'), 
('4','中文系','秦峰');

INSERT INTO DORM(DORMNo,tele) VALUES
('2101','8302101'), 
('2202','8302202'),
('2303','8302303'), 
('2404','8302404'),
('2505','8302505');

INSERT INTO COURSE(CNo,CName,CPNo,credit,teacher) VALUES
('01', '数据库原理', '05', 4, '王凯'),
('02', '高等数学', NULL, 6, '张风'),
('03', '信息系统', '01', 2, '李明'),
('04', '操作系统', '06', 4, '许强'),
('05', '数据结构', '07', 4, '路飞'),
('06', '算法设计', NULL, 2, '黄海'),
('07', 'C 语言', '06', 3, '高达');

INSERT INTO STUDENT(SNo,SName,sex,SAge,DNo,DORMNo) VALUES
('990101', '原野', '男', 21, '1', '2101'),
('990102', '张原', '男', 21, '1', '2101'),
('990103', '李军', '男', 20, '1', '2101'),
('990104', '汪远', '男', 20, '1', '2101'),
('990105', '齐欣', '男', 20, '1', '2101'),
('990201', '王大鸣', '男', 19, '2', '2202'),
('980202', '徐东', '男', 19, '2', '2202'),
('980301', '张扬', '女', 21, '1', '2303'),
('990302', '于洋', '女', 20, '3', '2303'),
('990303', '姚志旬', '男', 19, '4', '2404'),
('990401', '高明镜', '男', 19, '4', NULL),
('990402', '明天', '男', 21, '4', '2404');

INSERT INTO GRADE(SNo,CNo,score) VALUES
('990101', '01', 85),
('990101', '03', 65),
('990101', '04', 83),
('990101', '07', 72),
('990102', '02', 80),
('990102', '04', 81),
('990102', '01', NULL),
('990103', '07', 74),
('990103', '06', 74),
('990103', '01', 74),
('990103', '02', 70),
('990103', '04', 70),
('990104', '01', 55),
('990104', '02', 42),
('990104', '03', 0),
('990105', '03', 85),
('990105', '06', NULL),
('980301', '01', 46),
('980301', '02', 70),
('990302', '01', 85),
('990401', '01', 0);