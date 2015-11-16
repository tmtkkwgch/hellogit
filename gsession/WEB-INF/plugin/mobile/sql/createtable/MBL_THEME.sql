create table MBL_THEME
(
        MBT_SID        integer         not null,
        MBT_ID         varchar(20)     not null,
        MBT_NAME       varchar(50)     not null,
        MBT_AUID       integer         not null,
        MBT_ADATE      timestamp       not null,
        MBT_EUID       integer         not null,
        MBT_EDATE      timestamp       not null,
        primary key (MBT_SID)
) ;