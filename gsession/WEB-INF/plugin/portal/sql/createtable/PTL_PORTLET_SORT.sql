create table PTL_PORTLET_SORT
(
  PLT_SID          integer           not null,
  PLS_SORT         integer           not null,
  PLS_VIEW         smallint          not null,

  primary key (PLT_SID, PLS_SORT)
);