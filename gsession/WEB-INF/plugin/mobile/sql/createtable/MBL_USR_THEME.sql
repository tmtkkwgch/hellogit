create table MBL_USR_THEME
(
        USR_SID        integer         not null,
        MBT_SID        integer         not null,
        MUT_AUID       integer         not null,
        MUT_ADATE      timestamp       not null,
        MUT_EUID       integer         not null,
        MUT_EDATE      timestamp       not null,
        primary key (USR_SID)
) ;
