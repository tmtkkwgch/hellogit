create table ENQ_PRI_CONF
(
        USR_SID         integer         not null,
        EPC_MAIN_DSP    integer                 ,
        EPC_LIST_CNT    integer                 ,
        EPC_AUID        integer         not null,
        EPC_ADATE       timestamp       not null,
        EPC_EUID        integer         not null,
        EPC_EDATE       timestamp       not null,

        primary key (USR_SID)
);