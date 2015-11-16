create table WML_LABEL
(
  WLB_SID   integer      not null,
  USR_SID   integer      not null,
  WLB_NAME  varchar(100) not null,
  WLB_TYPE  integer      not null,
  WAC_SID   integer      not null,
  WLB_ORDER integer      not null,
  primary key(WLB_SID)
);