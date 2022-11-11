DROP TABLE IF EXISTS accounts;

create table accounts (
    "ID"      integer not null auto_increment,
    "NUMBER"  integer,
    "NAME"    varchar(255),
    "BALANCE" double,
    PRIMARY KEY ("ID")
);