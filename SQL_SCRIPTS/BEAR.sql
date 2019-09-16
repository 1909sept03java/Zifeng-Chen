--start
--DB CREATION SCRIPTS OFTER BEGIN WITH DROP STATEMENTS
--AVOIDS CLASSES WITH EXISTING TABLE

DROP TABLE BEAR;
/
DROP TABLE BEAR_TYPE;
/
DROP TABLE CAVE;
/
DROP TABLE BEEHIVE;
/
DROP TABLE BEAR_BEEHIVE;


--TABLE CREATION BEGINS HERE
CREATE TABLE BEAR_TYPE(
    BEAR_TYPE_ID INTEGER PRIMARY KEY,
    BEAR_TYPE_NAME varchar2(100)
    );
/
Create table CAVE
(
    CAVE_ID INTEGER PRIMARY KEY,
    CAVE_NAME varchar2(100),
    MAX_BEARS INTEGER DEFAULT 4
    );
/
create table BEAR
(
    BEAR_ID INTEGER PRIMARY KEY,
    BEAR_NAME VARCHAR2(100),
    BIRTHDATE DATE,
    WEIGHT  NUMBER(6,2) DEFAULT 200.00,
    BEAR_TYPE_ID INTEGER NOT NULL, --NOT NULL CONSTRAINT TO THIS COLUMN, FOREIGN KEY
    CAVE_ID INTEGER -- WILL BE A NULLABLE FOREIGN KEY
    );
/
create table BEEHIVE
(
    BEEHIVE_ID INTEGER PRIMARY KEY,
    HONEY_AMY NUMBER(5,2) DEFAULT 75.00
    );
/
create table BEAR_BEEHIVE
(
    BEAR_ID INTEGER,
    BEEHIVE_ID INTEGER,
    PRIMARY KEY(BEAR_ID, BEEHIVE_ID)
    );
/
--FOREIGN KEY CONSTRAINTS 

--CONSTRAINT: RULE PLACED ON THE CONTENTS OF A TABLE, LIMITING WHAT MAY BE INSERTED
--TYPES OF CONSTRAINTS: CHECK (INCLUDES NOT NULL), UNIQUE, PRIMARY KEY, FOREIGN KEY

ALTER TABLE BEAR
ADD CONSTRAINT FK_BEAR_BEAR_TYPE
FOREIGN KEY (BEAR_TYPE_ID) REFERENCES BEAR_TYPE(BEAR_TYPE_ID);
/

ALTER TABLE BEAR
ADD CONSTRAINT FK_BEAR_CAVE
FOREIGN KEY (CAVE_ID) REFERENCES CAVE(CAVE_ID);
/

ALTER TABLE BEAR_BEEHIVE
ADD CONSTRAINT FK_BEAR_BEEHIVE_BEAR
FOREIGN KEY (BEAR_ID) REFERENCES BEAR(BEAR_ID);
/

ALTER TABLE BEAR_BEEHIVE
ADD CONSTRAINT FK_BEAR_BEEHIVE_BEEHIVE
FOREIGN KEY (BEEHIVE_ID) REFERENCES BEEHIVE(BEEHIVE_ID);
/

--ADD SOME DATE - BE CAREFUL ABOUT INSERTION ORDER
--TWO WAYS TO ADD: BY FILIING ALL COLUMN OR SPECIFY WHICH COLUMNS TO FILL
INSERT INTO BEAR_TYPE VALUES(1,'GRIZZLY');
INSERT INTO BEAR_TYPE (BEAR_TYPE_ID,BEAR_TYPE_NAME) VALUES (2, 'SUN');

INSERT ALL
    INTO CAVE
        VALUES(1,'QUEENS COLLEGE',15)
    INTO CAVE (CAVE_ID,CAVE_NAME)
        VALUES(37,'AWESOME CAVE')
SELECT * FROM DUAL;

INSERT ALL
    INTO BEAR(BEAR_ID,BEAR_NAME,BIRTHDATE,BEAR_TYPE_ID,CAVE_ID)
        VALUES (72,'BARRY',TO_DATE('2000-10-08','YYYY-MM-DD'),1,37)
        
    INTO BEAR(BEAR_ID,BEAR_NAME,BIRTHDATE,BEAR_TYPE_ID,CAVE_ID)
        VALUES (2,'BOB',TO_DATE('2000-07-31','YYYY-MM-DD'),2,37)
        
    INTO BEAR(BEAR_ID,BEAR_NAME,BIRTHDATE,BEAR_TYPE_ID,CAVE_ID)
        VALUES (8,'BERNEICE',TO_DATE('1980-09-08','YYYY-MM-DD'),1,1)
        
    INTO BEAR(BEAR_ID,BEAR_NAME,BIRTHDATE,WEIGHT,BEAR_TYPE_ID,CAVE_ID)
        VALUES (45,'BERNEICE',TO_DATE('2005-03-08','YYYY-MM-DD'),600.00,1,37)
    
    INTO BEAR(BEAR_ID,BEAR_NAME,BIRTHDATE,BEAR_TYPE_ID,CAVE_ID)
        VALUES (89,'YOGI',TO_DATE('1958-08-18','YYYY-MM-DD'),2,1)

SELECT * FROM DUAL;    

INSERT INTO BEAR(BEAR_ID,BEAR_NAME,BIRTHDATE,BEAR_TYPE_ID)
    VALUES (56,'BILL', TO_DATE('2000-10-18','YYYY-MM-DD'),1);
    
CREATE SEQUENCE SQ_BEAR_PK
START WITH 1000
INCREMENT BY 1;
/
CREATE SEQUENCE SQ_BEAR_TYPE_PK
START WITH 1000
INCREMENT BY 1;
/
CREATE SEQUENCE SQ_CAVE_PK
START WITH 1000
INCREMENT BY 1;
/
CREATE SEQUENCE SQ_BEEHIVE_PK
START WITH 1000
INCREMENT BY 1;
/
--TRIGGERS: BLOCKS OF CODE THAT WILL EXECUTE IN REPONSE TO A DML STATEMENT 
--(INSERT, UPDATE, DELETE)
--CAN CREATE "BEFORE" OR "AFTER" TRIGGERS 
CREATE OR REPLACE TRIGGER TR_INSERT_BEAR
BEFORE INSERT ON BEAR --SPECIFY OPERATION, BEFORE/AFTER, AND TABLE 
FOR EACH ROW
BEGIN
    SELECT SQ_BEAR_PK.NEXTVAL INTO :NEW.BEAR_ID FROM DUAL;
END;
/
CREATE OR REPLACE TRIGGER TR_INSERT_BEAR_TYPE
BEFORE INSERT ON BEAR_TYPE --SPECIFY OPERATION, BEFORE/AFTER, AND TABLE 
FOR EACH ROW
BEGIN
    SELECT SQ_BEAR_TYPE_PK.NEXTVAL INTO :NEW.BEAR_TYPE_ID FROM DUAL;
END;
/
CREATE OR REPLACE TRIGGER TR_INSERT_CAVE
BEFORE INSERT ON CAVE --SPECIFY OPERATION, BEFORE/AFTER, AND TABLE 
FOR EACH ROW
BEGIN
    SELECT SQ_CAVE_PK.NEXTVAL INTO :NEW.CAVE_ID FROM DUAL;
END;
/
CREATE OR REPLACE TRIGGER TR_INSERT_BEEHIVE
BEFORE INSERT ON BEEHIVE --SPECIFY OPERATION, BEFORE/AFTER, AND TABLE 
FOR EACH ROW
BEGIN
    SELECT SQ_BEEHIVE_PK.NEXTVAL INTO :NEW.BEEHIVE_ID FROM DUAL;
END;
/
alter table beehive
rename column HONEY_AMY To HONEY_AMT;
INSERT INTO BEEHIVE (HONEY_AMT) VALUES (58.05);
INSERT INTO BEEHIVE (HONEY_AMT) VALUES (580.05);

--GIVE OUR HOMELESS BEAR SOMEWHERE TO LIVE
UPDATE BEAR SET CAVE_ID = 1 WHERE BEAR_NAME='Bill';









UPDATE BEAR
    SET CAVE_ID = 1
WHERE CAVE_ID IS NULL;

SELECT CAVE_ID,COUNT(CAVE_ID) from BEAR GROUP BY CAVE_ID;

SELECT CAVE_NAME  , COUNT(BEAR.CAVE_ID) 
FROM BEAR
    INNER JOIN CAVE ON BEAR.CAVE_ID = CAVE.CAVE_ID
GROUP BY CAVE_NAME, BEAR.CAVE_ID;
    
--VIEW
CREATE VIEW VW_BEARS_PER_CAVE(CAVE,NUMBEAR)
AS 
    SELECT CAVE_NAME, COUNT (BEAR.CAVE_ID)
    FROM BEAR
    INNER JOIN CAVE ON BEAR.CAVE_ID = CAVE.CAVE_ID
    GROUP BY CAVE_NAME,BEAR.CAVE_ID;
    
SELECT * FROM VW_BEARS_PER_CAVE;

CREATE OR REPLACE FUNCTION FIND_MAX_NUMBER (X IN NUMBER, Y IN NUMBER)
RETURN NUMBER
IS Z NUMBER;
BEGIN 
    IF X>Y THEN 
    Z:= X;
    ELSE
    Z :=Y ;
    END IF;
    RETURN Z;
END;

--CALLING FUNCTION
DECLARE 
    FIRST_NUM NUMBER;
    SECOND_NUM NUMBER;
    MAX_NUM NUMBER;
BEGIN
    FIRST_NUM:= 22;
    SECOND_NUM := 42;
    MAX_NUM := FIND_MAX_NUMBER(FIRST_NUM,SECOND_NUM);
    DBMS_OUTPUT.PUT_LINE('MAX: ' || MAX_NUM);
END;


SELECT bear_name, weight
FROM bear br
WHERE 2=(SELECT COUNT(DISTINCT weight) FROM bear br2
WHERE br.weight<=br2.weight);

    
-- 2 functions 
-- returning the 2nd heaviest bear
-- returning bears who are lighter than average in their cave


CREATE OR REPLACE FUNCTION SECOND_HEAVY_BEAR ()
RETURN  TABLE 
AS 
RETURN
SELECT BEAR_NAME,WEIGHT
FROM BEAR
BEGIN
RETURN  
  
END;



--subqueries : correlated vs noncorrelated

--First, require that bears and beehive have >= 0 as a weights
ALTER TABLE BEEHIVE ADD CONSTRAINT CK_BEEHIVE_WEIGHT
CHECK (HONEY_AMT >= 0);
/
ALTER TABLE BEAR ADD CONSTRAINT CK_BEAR_WEIGHT
CHECK (WEIGHT >= 0);
/
INSERT ALL
    INTO BEAR_BEEHIVE
    VALUES (72,1000)

    INTO BEAR_BEEHIVE
    VALUES (2,1000)

    INTO BEAR_BEEHIVE
    VALUES (89,1000)

    INTO BEAR_BEEHIVE
    VALUES (89,1001)

    INTO BEAR_BEEHIVE
    VALUES (56,1000)

    INTO BEAR_BEEHIVE
    VALUES (45,1001)
SELECT * From DUAL;

create or replace procedure SP_FEED_BEAR(
    B_ID IN NUMBER, H_ID  IN NUMBER, AMT_EATTEN IN NUMBER, AMT_FED OUT NUMBER)
IS 

BB_EXISTS INTEGER ;
BEGIN
    --CHECH THAT BEAR AND BEEHIVE ARE CORRECTLY MATCHED
    --IS THE AMOUNT OF HONEY > 0 AND LESS THAN HIVE'S WEIGHT?
        -- REDUCE HIVE WEIGHT
        -- INCREASE BEAR WEIGHT
        -- SET AMOUNT TO RETURN
        SELECT COUNT(BB.BEAR_ID) INTO BB_EXISTS
            FROM BEAR_BEEHIVE BB
        WHERE BB.BEAR_ID = B_ID
            AND BB.BEEHIVE_ID = H_ID;
        
        IF BB_EXISTS > 0 AND  AMT_EATTEN > 0 THEN 
            UPDATE BEEHIVE SET HONEY_AMT =HONEY_AMT - AMT_EATTEN
            WHERE BEEHIVE_ID = H_ID;
        
            UPDATE BEAR SET WEIGHT = WEIGHT + AMT_EATTEN
            WHERE BEAR_ID = B_ID;
            
            AMT_FED := AMT_EATTEN;
        ELSE
            AMT_FED:= 0;
            
        END IF;
        COMMIT;
    EXCEPTION 
        WHEN OTHERS THEN
        AMT_FED := 0;
        ROLLBACK;
       
END;

DECLARE 
    AMT_FED NUMBER;
BEGIN
    SP_FEED_BEAR(56,1000,10,AMT_FED);
    DBMS_OUTPUT.PUT_LINE('FED BEAR: '||AMT_FED||' HONEY');
END;

