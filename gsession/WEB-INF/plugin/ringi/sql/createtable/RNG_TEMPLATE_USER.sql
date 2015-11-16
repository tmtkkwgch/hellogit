create table RNG_TEMPLATE_USER
(
  RTP_SID   integer   not null,
  USR_SID   integer   not null,
  RTU_SORT  integer   not null,
  RTU_TYPE  integer   not null,
  RTU_AUID  integer   not null,
  RTU_ADATE timestamp not null,
  RTU_EUID  integer   not null,
  RTU_EDATE timestamp not null,
  primary key (RTP_SID, USR_SID)
) ;
