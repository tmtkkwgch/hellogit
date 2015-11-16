 create table BBS_LOG_COUNT_ADEL
 (
  BLD_DEL_KBN            integer           not null,
  BLD_DEL_YEAR           integer,
  BLD_DEL_MONTH          integer,
  BLD_AUID               integer           not null,
  BLD_ADATE              timestamp         not null,
  BLD_EUID               integer           not null,
  BLD_EDATE              timestamp         not null
 );