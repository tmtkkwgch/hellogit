create table RNG_TEMPLATE
(
  RTP_SID		integer			not null,
  RTP_TYPE		integer			not null,
  USR_SID		integer,
  RTP_TITLE		varchar(100)	not null,
  RTP_RNG_TITLE varchar(100),
  RTP_CONTENT	varchar(1000)	not null,
  RTP_SORT      integer         not null,
  RTP_AUID		integer			not null,
  RTP_ADATE		timestamp		not null,
  RTP_EUID		integer			not null,
  RTP_EDATE		timestamp		not null,
  RTC_SID		integer		    not null,
  primary key (RTP_SID)
) ;