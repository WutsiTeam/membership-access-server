INSERT INTO T_PLACE(id, name, name_french, country, timezone_id, longitude, latitude)
    VALUES
        (100, 'Yaounde', 'Yaoudé', 'CM', 'Africa/Douala', 1.1, 2.2);

INSERT INTO T_PHONE(id, number, country)
    VALUES
        (100, '+237221234100', 'CM'),
        (101, '+237221234101', 'CM'),
        (200, '+237221234200', 'CM'),
        (201, '+237221234201', 'CM'),
        (300, '+237221234300', 'CM')
;

INSERT INTO T_ACCOUNT(id, phone_fk, status, display_name)
    VALUES
        (101, 101, 1, 'Yo man'),
        (200, 200, 1, 'John Doe'),
        (300, 300, 2, 'Roger Milla - Suspended')
;
