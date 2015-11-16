 create table FILE_FILE_REKI
   (
     FDR_SID          integer      not null,
     FDR_VERSION      integer      not null,
     FFR_FNAME        varchar(255) not null,
     FFR_KBN          integer      not null,
     FFR_PARENT_SID   integer      not null,
     FFR_UP_CMT       varchar(3000),
     FFR_EUID         integer      not null,
     FFR_EDATE        timestamp    not null,
     FFR_EGID         integer,
     primary key (FDR_SID, FDR_VERSION)
   );