 create table FILE_LOG_ADEL
 (
  FLD_DEL_KBN            integer           not null,
  FLD_DEL_YEAR           integer,
  FLD_DEL_MONTH          integer,
  FLD_AUID               integer           not null,
  FLD_ADATE              timestamp         not null,
  FLD_EUID               integer           not null,
  FLD_EDATE              timestamp         not null
 );