 create table WML_LOG_COUNT_ADEL
 (
  WLD_DEL_KBN            integer           not null,
  WLD_DEL_YEAR           integer,
  WLD_DEL_MONTH          integer,
  WLD_AUID               integer           not null,
  WLD_ADATE              timestamp         not null,
  WLD_EUID               integer           not null,
  WLD_EDATE              timestamp         not null
 );