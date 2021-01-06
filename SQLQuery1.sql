SQL

DDL		CREATE, alter, drop
DML		insert, update, delete
DCL		grant, invoke, revoke, transaction, commit, etc.
DQL		select

Constraints
-----------------
1) Primary key
2) Unique
3) Foreign key
4) default
5) check


create database MyProjectJavaDB

use MyProjectJavaDB

create table UserDetails
(
	userID int primary key,
	userCode varchar(10) unique, 
	name varchar(50) not null,
	address varchar(50),
	city varchar(50) default('Karnal'),
	phone char(10) not null,
	email varchar(250),
	password varchar(20),
	age int check(age>15)
)


alter table Userdetails 
alter column fatherName char(50)

alter table UserDetails
add fatherName varchar(50) not null

alter table UserDetails
drop column fatherName

drop table Userdetails

insert into UserDetails(userID, address, name, city, phone, email, password, age, fatherName, userCode) 
values(2, 'Uska Ghar', 'ABC', 'Karnal', '5435435', 'abc@gmail.com', 'abc', 50, 'abc ke papa', 'U002'),
(3, 'iska Ghar', 'XYZ', 'Panipat', '5345435', 'xyz@gmail.com', 'abc', 150, 'xyz ke papa', 'U003'),
(4, 'kiska Ghar', 'Akram', 'Sonepat', '6546546', 'aku@gmail.com', 'abc', 250, 'aku ke papa', 'U004'),
(5, 'pata nhi', 'Alli baba', 'Delhi', '6546546', 'allu@gmail.com', 'abc', 150, 'allu ke papa', 'U005'),
(6, 'bataya nhi', 'Pritam', 'Karnal', '234234234', 'pritu@gmail.com', 'abc', 550, 'pritu ke papa', 'U006'),
(7, 'dekha nhi', 'Pappu', 'Karnal', '54354354', 'pappu@gmail.com', 'abc', 150, 'pappu ke papa', 'U007'),
(8, 'dhundh lo', 'Nikka', 'Delhi', '543543535', 'nikku@gmail.com', 'abc', 60, 'nikku ke papa', 'U008'),
(9, 'gum ho gya', 'Sumo', 'Sonepat', '23423432', 'sumu@gmail.com', 'abc', 1090, 'sumo ke papa', 'U009')


update userDetails set userCode='U001', name='Manoj Dembla', 
email='manoj.dembla@gmail.com', password='min', age=30, 
fatherName='Sh. IJD'  
where userID=1

delete userDetails

delete userDetails where userID=1


select * from UserDetails

aggregate functions
---------------------
sum
count
max
min
avg
Literals
---------------
select 'Minimum value'=min(age), max(age) as 'Max Value', 
count(age) as 'Total', SUM(age) as 'Total Value', 
AVG(age) as 'Average Value' from userDetails where age>100

--group by clause
---------------------
select city, SUM(age) from userDetails group by city

select city, SUM(age) from userDetails group by city
having SUM(age)>200

list operator
in
not in

select * from userDetails where 
city in('Karnal', 'Delhi', 'Ambala', 'Sonepat')

select * from userDetails where 
city not in('Karnal', 'Delhi', 'Ambala', 'Sonepat')

select * from userDetails where age between 100 and 200

select * from userDetails where age not between 100 and 200


joins
------------
inner join
outer join
	left outer
	right outer
	full outer
equi join
cross join


create table departmentDetails
(
	departmentID int primary key identity(1,1), 
	departmentName varchar(50),
	location varchar(50)
)
use MyProjectJavaDB
create table employeeDetails
(
	empID int primary key identity(1,1),
	name varchar(50),
	address varchar(150),
	city varchar(50),
	phone varchar(10),
	salary money,
	regDate datetime default(getdate()),
	departmentID int foreign key references departmentDetails(departmentID),
)
create table employeeDetails1
(
	empID int primary key identity(1000,1),
	name varchar(50),
	address varchar(150),
	city varchar(50),
	phone varchar(10),
	salary money,
	regDate datetime default(getdate()),
	departmentID int foreign key references departmentDetails(departmentID),
)


insert into departmentDetails values('Accounts', 'Karnal'),
('Management', 'Karnal'),
('Sales', 'Panipat'),
('Order', 'Delhi'),
('Purchase', 'Karnal')

insert into employeeDetails1(name, address, city, phone, salary, departmentID)
values('Seventh', 'Seventh ka ghar', 'Karnal', '234234234', 534544, null),
('Eighth', 'Eighth ka ghar', 'Panipat', '543543554', 20000, null),
('fifth', 'First ka ghar', 'Sonipat', '435435435', 34000, 1),
('fourth', 'First ka ghar', 'Delhi', '54353555', 55000, 2),
('Third', 'First ka ghar', 'Delhi', '543543554', 60000, 2),
('Second', 'First ka ghar', 'Karnal', '234324324', 23000, 3)
	
Msg 547, Level 16, State 0, Line 2
The INSERT statement conflicted with the FOREIGN KEY constraint "FK__employeeD__depar__0EA330E9". The conflict occurred in database "MyProjectJavaDB", table "dbo.departmentDetails", column 'departmentID'.	
use MyProjectJavaDB	
select * from departmentDetails
select * from employeeDetails1

select name, city, phone, salary, departmentName, location
from departmentDetails inner join employeeDetails on departmentDetails.departmentID=employeeDetails.departmentID


select name, city, phone, salary, departmentName, location
from departmentDetails left outer join employeeDetails on departmentDetails.departmentID=employeeDetails.departmentID

select name, city, phone, salary, departmentName, location
from departmentDetails right outer join employeeDetails on departmentDetails.departmentID=employeeDetails.departmentID

select name, city, phone, salary, departmentName, location
from departmentDetails full outer join employeeDetails on departmentDetails.departmentID=employeeDetails.departmentID

select * from departmentDetails join employeeDetails
on departmentDetails.departmentID=employeeDetails.departmentID 

select * from departmentDetails cross join UserDetails

wild cards [] % ^ _  like

select * from UserDetails where name like '%A%'

select * from UserDetails where city like 'K__nal'

select * from UserDetails where city like '[A-K]%'

select * from UserDetails where name like '[A, P, N]%'

select * from UserDetails where name like '[^A, P, N]%'





char(6)		ANU1
varchar(6)	ANU1
float
double
decimal
int
smallint
tinyint
bigint
money
bit
datetime
text


