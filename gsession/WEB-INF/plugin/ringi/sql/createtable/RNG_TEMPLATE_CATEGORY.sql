 create table RNG_TEMPLATE_CATEGORY
 (
  RTC_SID    integer      not null,
  RTC_TYPE   integer      not null,
  USR_SID    integer,
  RTC_SORT    integer   not null,
  RTC_NAME  varchar(20)  not null,
  RTC_AUID   integer      not null,
  RTC_ADATE  timestamp    not null,
  RTC_EUID   integer      not null,
  RTC_EDATE  timestamp    not null,
  primary key (RTC_SID)
  );