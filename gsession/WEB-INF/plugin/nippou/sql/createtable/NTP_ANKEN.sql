create table NTP_ANKEN
(
    NAN_SID               integer           not null,
    NAN_CODE              varchar(8)        not null,
    NAN_NAME              varchar(100)      not null,
    NAN_DETIAL            varchar(1000)     not null,
    NAN_DATE              timestamp         not null,
    ACO_SID               integer           not null,
    ABA_SID               integer           not null,
    NGP_SID               integer                   ,
    NAN_MIKOMI            integer                   ,
    NAN_KIN_MITUMORI      integer                   ,
    NAN_KIN_JUTYU         integer                   ,
    NAN_SYODAN            integer                   ,
    NAN_STATE             integer                   ,
    NCN_SID               integer                   ,
    NAN_AUID              integer                   ,
    NAN_ADATE             timestamp         not null,
    NAN_EUID              integer                   ,
    NAN_EDATE             timestamp         not null,
    NAN_MITUMORI_DATE     timestamp         not null,
    NAN_JUTYU_DATE        timestamp         not null,
    primary key (NAN_SID)
);
