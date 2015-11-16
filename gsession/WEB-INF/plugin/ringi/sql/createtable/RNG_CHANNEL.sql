create table RNG_CHANNEL
(
  RNG_SID		integer		not null,
  USR_SID		integer		not null,
  RNC_SORT		integer		not null,
  RNC_STATUS	integer		not null,
  RNC_COMMENT 	varchar(300),
  RNC_RCVDATE 	timestamp,
  RNC_CHKDATE	timestamp,
  RNC_TYPE		integer		not null,
  RNC_AUID		integer		not null,
  RNC_ADATE		timestamp      not null,
  RNC_EUID		integer		not null,
  RNC_EDATE		timestamp      not null,
  primary key (RNG_SID, USR_SID)
) ;