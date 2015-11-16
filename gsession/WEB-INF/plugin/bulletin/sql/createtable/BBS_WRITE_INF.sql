create table BBS_WRITE_INF
(
        BWI_SID        integer not null,
        BFI_SID        integer not null,
        BTI_SID        integer not null,
        BWI_VALUE      varchar(6000) not null,
        BWI_AUID       integer not null,
        BWI_ADATE      timestamp not null,
        BWI_EUID       integer not null,
        BWI_EDATE      timestamp not null,
        BWI_AGID       integer,
        BWI_EGID       integer,
        primary key (BWI_SID)
) ;