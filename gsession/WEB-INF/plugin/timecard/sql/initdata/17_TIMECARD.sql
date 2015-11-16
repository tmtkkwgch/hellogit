delete from TCD_TIMEZONE;
delete from CMN_SAIBAN where SBN_SID='timecard';
 insert 
 into TCD_TIMEZONE(TTZ_SID, TTZ_KBN, TTZ_FRTIME, TTZ_TOTIME)
           values (1, 1, '09:00:00', '12:00:00');
 insert 
 into TCD_TIMEZONE(TTZ_SID, TTZ_KBN, TTZ_FRTIME, TTZ_TOTIME)
           values (2, 1, '13:00:00', '18:00:00');
 insert 
 into TCD_TIMEZONE(TTZ_SID, TTZ_KBN, TTZ_FRTIME, TTZ_TOTIME)
           values (3, 2, '05:00:00', '08:00:00');
 insert 
 into TCD_TIMEZONE(TTZ_SID, TTZ_KBN, TTZ_FRTIME, TTZ_TOTIME)
           values (4, 2, '18:30:00', '22:00:00');
 insert 
 into TCD_TIMEZONE(TTZ_SID, TTZ_KBN, TTZ_FRTIME, TTZ_TOTIME)
           values (5, 3, '23:00:00', '00:00:00');
 insert 
 into TCD_TIMEZONE(TTZ_SID, TTZ_KBN, TTZ_FRTIME, TTZ_TOTIME)
           values (6, 3, '00:00:00', '02:00:00');
 insert 
 into TCD_TIMEZONE(TTZ_SID, TTZ_KBN, TTZ_FRTIME, TTZ_TOTIME)
           values (7, 3, '03:00:00', '05:00:00');
 insert 
 into TCD_TIMEZONE(TTZ_SID, TTZ_KBN, TTZ_FRTIME, TTZ_TOTIME)
           values (8, 3, '22:00:00', '22:30:00');
 insert 
 into TCD_TIMEZONE(TTZ_SID, TTZ_KBN, TTZ_FRTIME, TTZ_TOTIME)
           values (9, 4, '02:00:00', '3:00:00');
 insert 
 into TCD_TIMEZONE(TTZ_SID, TTZ_KBN, TTZ_FRTIME, TTZ_TOTIME)
           values (10, 4, '08:00:00', '09:00:00');
 insert 
 into TCD_TIMEZONE(TTZ_SID, TTZ_KBN, TTZ_FRTIME, TTZ_TOTIME)
           values (11, 4, '12:00:00', '13:00:00');
 insert 
 into TCD_TIMEZONE(TTZ_SID, TTZ_KBN, TTZ_FRTIME, TTZ_TOTIME)
           values (12, 4, '18:00:00', '18:30:00');
 insert 
 into TCD_TIMEZONE(TTZ_SID, TTZ_KBN, TTZ_FRTIME, TTZ_TOTIME)
           values (13, 4, '22:30:00', '23:00:00');
 insert
 into CMN_SAIBAN(SBN_SID, SBN_SID_SUB, SBN_NUMBER, SBN_STRING, SBN_AID, SBN_ADATE, SBN_EID, SBN_EDATE)
           values ('timecard', 'tcdzone', 13, 'tcdzone', 0, current_timestamp, 0, current_timestamp);
