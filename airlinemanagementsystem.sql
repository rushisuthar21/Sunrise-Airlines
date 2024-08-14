create database ams;
use ams;

create table login(username varchar(20), password varchar(20));
ALTER TABLE login ADD role VARCHAR(10);

select * from login;

create table passenger(name varchar(40) UNIQUE, nationality varchar(20), phone BIGINT UNIQUE, address varchar(80), passport varchar(20) UNIQUE, gender varchar(20));
select * from passenger;

create table flight(Flight_id int, Flight_code varchar(20), flightname varchar(30), Source varchar(40), Destination varchar(40));

select * from flight;

create table reservation(PNR varchar(20), TICKET varchar(20), passport varchar(20) UNIQUE, 
name varchar(30), nationality varchar(30), flightname varchar(30), 
flightcode varchar(20), departure varchar(30), destination1 varchar(30), book_date date);

select * from reservation;

create table cancel(pnr varchar(20), name varchar(40), cancelno int, fcode varchar(20), ddate date);

select * from cancel;