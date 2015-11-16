create table ENQ_ADM_CONF
(
        EAC_KBN_TAISYO      integer                 ,
        EAC_MAIN_DSP_USE    integer                 ,
        EAC_MAIN_DSP        integer                 ,
        EAC_LIST_CNT_USE    integer                 ,
        EAC_LIST_CNT        integer                 ,
        EAC_AUID            integer         not null,
        EAC_ADATE           timestamp       not null,
        EAC_EUID            integer         not null,
        EAC_EDATE           timestamp       not null
);