create table RSV_ADM_CONF
(
  RAC_ADL_KBN      integer     not null,
  RAC_ADL_YEAR     integer,
  RAC_ADL_MONTH    integer,
  RAC_HOUR_DIV     integer,
  RAC_AUID         integer     not null,
  RAC_ADATE        timestamp  not null,
  RAC_EUID         integer     not null,
  RAC_EDATE        timestamp  not null,
  RAC_DTM_KBN      integer     not null default 0,
  RAC_DTM_FR       integer     not null default 9,
  RAC_DTM_TO       integer     not null default 18,
  RAC_INI_EDIT_KBN integer     not null default 0,
  RAC_INI_EDIT     integer     not null default 0,
  RAC_SMAIL_SEND_KBN integer     not null default 0,
  RAC_SMAIL_SEND     integer     not null default 0
);