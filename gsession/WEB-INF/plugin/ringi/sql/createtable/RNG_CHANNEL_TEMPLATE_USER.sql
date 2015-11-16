create table RNG_CHANNEL_TEMPLATE_USER
(
  RCT_SID		integer		not null,
  USR_SID		integer		not null,
  RCU_SORT		integer		not null,
  RCU_TYPE		integer		not null,
  RCU_AUID		integer		not null,
  RCU_ADATE		timestamp      not null,
  RCU_EUID		integer		not null,
  RCU_EDATE		timestamp      not null,
  primary key (RCT_SID, USR_SID)
) ;
