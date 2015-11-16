create table ADR_COMPANY
(
  ACO_SID         integer  not null,
  ACO_CODE        varchar(20)  not null,
  ACO_NAME        varchar(50)  not null,
  ACO_NAME_KN     varchar(100)  not null,
  ACO_SINI        varchar(3)  not null,
  ACO_URL         varchar(100),
  ACO_BIKO        varchar(1000),
  ACO_AUID        integer  not null,
  ACO_ADATE       timestamp  not null,
  ACO_EUID        integer  not null,
  ACO_EDATE       timestamp  not null,
  primary key(ACO_SID)
);