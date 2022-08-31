BEGIN
TRANSACTION;

INSERT INTO USER(NAME, EMAIL, TYPE)
VALUES ('Carl', 'carl@mail.com', 1),
    ('David', 'david@mail.com', 2),
    ('Ines', 'ines@mail.com', 2);

INSERT INTO SLOT(USER_ID, SLOT_DATE, SLOT_HOUR)
VALUES ((SELECT ID FROM USER WHERE NAME = 'David'), now(), 9),
    ((SELECT ID FROM USER WHERE NAME = 'David'), now(), 10),
    ((SELECT ID FROM USER WHERE NAME = 'David'), now(), 11),
    ((SELECT ID FROM USER WHERE NAME = 'David'), now(), 12),
    ((SELECT ID FROM USER WHERE NAME = 'Ines'), now()+1, 9),
    ((SELECT ID FROM USER WHERE NAME = 'Carl'), now(), 9),
    ((SELECT ID FROM USER WHERE NAME = 'Carl'), now()+1, 9);

COMMIT;
