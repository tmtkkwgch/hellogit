create table NTP_AN_SHOHIN_HISTORY
(
      NAH_SID          integer           not null,
      NAN_SID          integer           not null,
      NHN_SID          integer           not null,
      NAS_AUID         integer                   ,
      NAS_ADATE        timestamp         not null,
      NAS_EUID         integer                   ,
      NAS_EDATE        timestamp         not null,
      primary key (NAH_SID, NAN_SID, NHN_SID)
);