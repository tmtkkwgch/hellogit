create table WML_MAILDATA
(
  WMD_MAILNUM  bigint          not null,
  WMD_TITLE    varchar(1000),
  WMD_SDATE    timestamp,
  WMD_FROM     varchar(256),
  WMD_TEMPFLG  integer         not null,
  WMD_STATUS   integer         not null,
  WMD_REPLY    integer         not null,
  WMD_FORWARD  integer         not null,
  WMD_READED   integer         not null,
  WDR_SID      bigint          not null,
  WMD_SIZE     bigint          not null,
  WAC_SID      integer         not null,
  primary key(WMD_MAILNUM)
);