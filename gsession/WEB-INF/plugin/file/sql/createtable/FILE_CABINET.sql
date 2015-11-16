 create table FILE_CABINET
   (
     FCB_SID          integer     not null,
     FCB_NAME         varchar(150) not null,
     FCB_ACCESS_KBN   integer     not null,
     FCB_CAPA_KBN     integer     not null,
     FCB_CAPA_SIZE    integer,
     FCB_CAPA_WARN    integer,
     FCB_VER_KBN      integer     not null,
     FCB_VERALL_KBN   integer,
     FCB_BIKO         varchar(3000),
     FCB_SORT         integer     not null,
     FCB_MARK         bigint      not null,
     FCB_AUID         integer     not null,
     FCB_ADATE        timestamp   not null,
     FCB_EUID         integer     not null,
     FCB_EDATE        timestamp   not null,
     primary key (FCB_SID)
   );
