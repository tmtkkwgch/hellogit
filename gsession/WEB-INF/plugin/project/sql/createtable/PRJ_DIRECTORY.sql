 create table PRJ_DIRECTORY
   (
     PRJ_SID         integer      not null,
     PDR_SID         integer      not null,
     PDR_PARENT_SID  integer      not null,
     PDR_KBN         integer      not null,
     PDR_LEVEL       integer      not null,
     PDR_NAME        varchar(50)  not null,
     PDR_NAIYO       varchar(1000),
     PDR_BIKO        varchar(1000),
     BIN_SID         bigint       not null,
     PDR_AUID        integer      not null,
     PDR_ADATE       timestamp    not null,
     PDR_EUID        integer      not null,
     PDR_EDATE       timestamp    not null,
     primary key (PRJ_SID, PDR_SID)
   );
