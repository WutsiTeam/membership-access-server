ALTER TABLE T_ACCOUNT ADD COLUMN deactivated DATETIME;
UPDATE T_ACCOUNT SET deactivated=suspended;

ALTER TABLE T_ACCOUNT DROP COLUMN suspended;