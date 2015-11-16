create table RNG_TEMPLATE_BIN
(
  RTP_SID 	integer		not null,
  BIN_SID 	bigint 		not null,
  primary key (RTP_SID, BIN_SID)
) ;