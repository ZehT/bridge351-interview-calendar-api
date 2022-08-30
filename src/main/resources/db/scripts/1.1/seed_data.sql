BEGIN
TRANSACTION;

INSERT INTO PERSON(NAME, EMAIL, TYPE)
VALUES ('David', 'david@mail.com', 2),
    ('Carl', 'carl@mail.com', 1),
    ('Ines', 'ines@mail.com', 2);

INSERT INTO SLOT(PERSON_ID, SLOT_DATE, SLOT_HOUR)
VALUES ((SELECT ID FROM PERSON WHERE NAME = 'David'), now(), 9),
    ((SELECT ID FROM PERSON WHERE NAME = 'David'), now(), 10),
    ((SELECT ID FROM PERSON WHERE NAME = 'David'), now(), 11),
    ((SELECT ID FROM PERSON WHERE NAME = 'David'), now(), 12),
    ((SELECT ID FROM PERSON WHERE NAME = 'Ines'), now()+1, 9),
    ((SELECT ID FROM PERSON WHERE NAME = 'Carl'), now(), 9),
    ((SELECT ID FROM PERSON WHERE NAME = 'Carl'), now()+1, 9);

COMMIT;
