ALTER TABLE USERS MODIFY (ID NUMBER DEFAULT USER_SEQUENCE.NEXTVAL);

commit;

CREATE SEQUENCE  "SPRING"."TRANSACTION_SEQUENCE"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;