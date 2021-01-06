create table LoginDetails
(
	serial varchar(50) not null,
	username varchar(50) not null,
	password varchar(10) not null,
)
drop table LoginDetails
select * from LoginDetails
insert into LoginDetails(serial,username,password) 
values('1111-2222-3333-4444','admin','sg12345678')

create table ProductDetails
(
	productID int primary key ,
	name varchar(50) not null,
	vendorname varchar(50) not null,
	price int not null,
	quantity int not null,
	netamt int not null,
	gst int not null,
	total varchar(10)not null,
	date varchar(20) not null,
	time varchar(20) not null,
)
create table FIRDetails
(
	FID int primary key ,
	name varchar(20) not null,
	incident varchar(50) not null,
	location varchar(30)not null,
	station varchar(30)not null,
	description varchar(500) not null,
	status varchar(50) not null, 
	date varchar(20) not null,
	time varchar(20) not null,
)
drop table FIRDetails
select * from FIRDetails

use MyProjectJavaDB
select * from ProductDetails
drop table VendorDetails
create table VendorDetails
(
	vendorID int primary key ,
	name varchar(50) not null,
	address varchar(50) not null,
	city varchar(50) not null,
	phone char(10) unique not null,
	email varchar(250)unique,
	
)
create table PassengerDetails
(
	passengerID int primary key ,
	name varchar(50) not null,
	address varchar(50) not null,
	city varchar(50) not null,
	phone char(10) unique not null,
	email varchar(250)unique,
	password varchar(10) not null,
)

use MyProjectJavaDB
select * from PassengerDetails
select * from CustomerPaymentDetails
create table CustomerDetails
(
	
	customerID int primary key ,
	name varchar(50) not null,
	address varchar(50) not null,
	city varchar(15) not null,
	phone char(10) unique not null,
	email varchar(250)null,
	
)

create table StationDetails
(
	
	stationID int primary key ,
	name varchar(50) not null,
	address varchar(50) not null,
	city varchar(15) not null,
	phone char(10) unique not null,
	incharge varchar(20)null,
)
select * from StationDetails
drop table StationDetails

create table CustomerPaymentDetails
(
	billno int primary key,
	customerID int not null ,
	name varchar(50) not null,
	totalquantity varchar(20) not null,
	total varchar(20) not null,
	date varchar(20) not null,
	time varchar(20) not null,
)
use MyProjectJavaDB
select * from CustomerDetails
select * from CustomerPaymentDetails


create table PurchasedItems
(
	billno int not null ,
	name varchar(50)not null,
	price varchar(20) not null,
	quantity varchar(20) not null,
	netamt varchar(20) not null,
	gst varchar(20) not null,
	total varchar(20) not null,
)
drop table CustomerPaymentDetails
truncate table CustomerPaymentDetails
truncate table Purchaseditems
select * from PurchasedItems