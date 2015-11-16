 create table SML_LOG_COUNT_ADEL
 (
  SLD_DEL_KBN            integer           not null,
  SLD_DEL_YEAR           integer,
  SLD_DEL_MONTH          integer,
  SLD_AUID               integer           not null,
  SLD_ADATE              timestamp         not null,
  SLD_EUID               integer           not null,
  SLD_EDATE              timestamp         not null
 );