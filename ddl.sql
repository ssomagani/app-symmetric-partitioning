file -inlinebatch CREATE_TABLE

create table test (partitionKey integer not null, key integer not null, value varchar(10));
partition table test on column partitionKey;

CREATE_TABLE

load classes classes.jar;

file -inlinebatch CREATE_PROC

create procedure from class db.InsertProcedure;
partition procedure InsertProcedure on table test column partitionKey parameter 0;

CREATE_PROC
