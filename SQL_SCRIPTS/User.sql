--2.1
SELECT * FROM EMPLOYEE;

SELECT * FROM EMPLOYEE 
    WHERE LASTNAME = 'King';

SELECT * FROM EMPLOYEE
    WHERE FIRSTNAME = 'Andrew' and REPORTSTO IS NULL;
    
--2.2
SELECT * FROM ALBUM
ORDER BY TITLE DESC;

SELECT * FROM CUSTOMER 
ORDER BY CITY ASC;

--2.3
INSERT INTO Genre (GenreId, Name) VALUES (26, 'Genre1');
INSERT INTO Genre (GenreId, Name) VALUES (27, 'Genre2');

INSERT INTO Employee (EmployeeId, LastName, FirstName, Title, ReportsTo, BirthDate, HireDate, Address, City, State, Country, PostalCode, Phone, Fax, Email) VALUES (8, 'Callahan', 'Laura', 'IT Staff', 6, TO_DATE('1968-1-9 00:00:00','yyyy-mm-dd hh24:mi:ss'), TO_DATE('2004-3-4 00:00:00','yyyy-mm-dd hh24:mi:ss'), '923 7 ST NW', 'Lethbridge', 'AB', 'Canada', 'T1H 1Y8', '+1 (403) 467-3351', '+1 (403) 467-8772', 'laura@chinookcorp.com');