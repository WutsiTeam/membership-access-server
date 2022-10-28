INSERT INTO T_PLACE(id, name, name_french, country, timezone_id, longitude, latitude)
    VALUES
        (100, 'Yaounde', 'Yaoude_e_', 'CM', 'Africa/Douala', 1.1, 2.2),
        (200, 'Douala', 'Douala', 'CM', 'Africa/Douala', null, null)
    ;

INSERT INTO T_PHONE(id, number, country)
    VALUES
        (100, '+237221234100', 'CM'),
        (101, '+237221234101', 'CM'),
        (199, '+237221234199', 'CM')
;

INSERT INTO T_ACCOUNT(id, phone_fk, city_fk, category_fk, status, display_name, picture_url)
    VALUES
        (100, 100, 100, 1000, 1, 'Ray Sponsible', 'https://me.com/12343/picture.png'),
        (101, 101, 100, null, 1, 'Thomas Nkono', 'https://me.com/101/picture.png'),
        (199, 199, 100, null, 2, 'Suspended', null)
    ;
