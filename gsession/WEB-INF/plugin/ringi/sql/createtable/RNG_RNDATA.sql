create table RNG_RNDATA
(
  RNG_SID		 integer		not null,
  RNG_TITLE 	 varchar(100)	not null,
  RNG_CONTENT 	 varchar(1000) 	not null,
  RNG_MAKEDATE 	 timestamp 		not null,
  RNG_APPLICATE  integer,
  RNG_APPLDATE	 timestamp,
  RNG_STATUS	 integer		not null,
  RNG_COMPFLG 	 integer		not null,
  RNG_ADMCOMMENT varchar(300),
  RNG_AUID		 integer		not null,
  RNG_ADATE		 timestamp 		not null,
  RNG_EUID 		 integer 		not null,
  RNG_EDATE 	 timestamp		not null,
  primary key 	 (RNG_SID)
);