create table RNG_ACONF
(
  RAR_DEL_AUTH     integer not null,
  RAR_AUID         integer not null,
  RAR_ADATE        timestamp not null,
  RAR_EUID         integer not null,
  RAR_EDATE        timestamp not null,
  RAR_SML_NTF     integer    not null,
  RAR_SML_NTF_KBN integer
) ;