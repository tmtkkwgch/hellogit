create table RSV_SIS_ADM
(
  RSG_SID           integer not null,
  USR_SID           integer not null,
  GRP_SID           integer not null,
  RSA_AUID          integer not null,
  RSA_ADATE         timestamp not null,
  RSA_EUID          integer not null,
  RSA_EDATE         timestamp not null,
  RAC_DTM_KBN       integer,
  RAC_DTM_FR        integer,
  RAC_DTM_TO        integer,
  RAC_INI_EDIT_KBN  integer,
  RAC_INI_EDIT      integer,
  primary key  (RSG_SID, USR_SID, GRP_SID)
);
