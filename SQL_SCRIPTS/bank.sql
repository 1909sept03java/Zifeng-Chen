CREATE TABLE BANK_USER(
    USER_ID INTEGER PRIMARY KEY,
    USER_NAME VARCHAR2(100),
    PASSCODE VARCHAR2(100)
);
ALTER TABLE BANK_USER
ADD PERM INTEGER DEFAULT (0);

ALTER TABLE BANK_ACCOUNT
ADD MONEY INTEGER DEFAULT(0);
CREATE TABLE BANK_ACCOUNT(
    BANK_ACCOUNT_ID INTEGER PRIMARY KEY,
    USER_ID INTEGER,
    MONEY NUMBER
);

CREATE TABLE TRANSACTION_HISTORY (
    TRANSACTION_ID INTEGER PRIMARY KEY,
    ACCOUNT_ID INTEGER,
    TRANSFER NUMBER
    
);

CREATE SEQUENCE SQ_BANK_PK
START WITH 100
INCREMENT BY 1;
/
CREATE SEQUENCE SQ_ACCOUNT_PK
START WITH 10
INCREMENT BY 1;
/
CREATE SEQUENCE SQ_TRANSACTION_PK
START WITH 10
INCREMENT BY 1;
/
CREATE OR REPLACE TRIGGER TR_INSERT_BANK
BEFORE INSERT ON BANK_USER --SPECIFY OPERATION, BEFORE/AFTER, AND TABLE 
FOR EACH ROW
BEGIN
    SELECT SQ_BANK_PK.NEXTVAL INTO :NEW.USER_ID FROM DUAL;
END;
/
CREATE OR REPLACE TRIGGER TR_INSERT_ACCOUNT
BEFORE INSERT ON BANK_ACCOUNT
FOR EACH ROW
BEGIN  
    SELECT SQ_ACCOUNT_PK.NEXTVAL INTO :NEW.BANK_ACCOUNT_ID FROM DUAL;
END;
/
CREATE OR REPLACE TRIGGER TR_INSERT_TRANSACTION
BEFORE INSERT ON TRANSACTION_HISTORY
FOR EACH ROW
BEGIN
    SELECT SQ_TRANSACTION_PK.NEXTVAL INTO :NEW.TRANSACTION_ID FROM DUAL;
END;


ALTER TABLE BANK_ACCOUNT ADD
    CONSTRAINT FK_BANK_ACCOUNT
    FOREIGN KEY (USER_ID)
    REFERENCES BANK_USER (USER_ID)
    ON DELETE CASCADE;

ALTER TABLE TRANSACTION_HISTORY ADD
    CONSTRAINT FK_TRANSACTION_HISTORY
    FOREIGN KEY (ACCOUNT_ID)
    REFERENCES BANK_ACCOUNT (BANK_ACCOUNT_ID)
    ON DELETE CASCADE;

CREATE OR REPLACE PROCEDURE SP_INSERT_USER( UN VARCHAR2 , PC VARCHAR2)
IS
BEGIN
  INSERT INTO BANK_USER(USER_NAME,PASSCODE)
    VALUES(UN,PC);
    COMMIT;
END;

CREATE OR REPLACE PROCEDURE SP_INSERT_ACCOUNT ( U_ID INTEGER)
IS 
BEGIN
    INSERT INTO BANK_ACCOUNT(USER_ID)
        VALUES (U_ID);
    COMMIT;
END;

BEGIN
    SP_INSERT_ACCOUNT(120);
  
END;

CREATE OR REPLACE PROCEDURE SP_TRANSACTION (A_ID INTEGER, AMOUNT NUMBER)
IS
TEMP NUMBER:=0;
BEGIN
    UPDATE BANK_ACCOUNT
        SET MONEY = MONEY + AMOUNT
    WHERE BANK_ACCOUNT_ID=A_ID;
    INSERT INTO TRANSACTION_HISTORY (ACCOUNT_ID,TRANSFER)
        VALUES(A_ID,AMOUNT);
        commit;
END;
    
BEGIN
    SP_TRANSACTION(31,120);
  
END;
DELETE FROM BANK_USER where USER_ID = 162;