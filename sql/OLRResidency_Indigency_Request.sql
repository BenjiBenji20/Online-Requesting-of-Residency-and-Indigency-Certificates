CREATE SCHEMA olrresidency_indigency_request;

USE olrresidency_indigency_request;

SHOW TABLES;

SELECT * FROM malabon_resident;

SELECT * FROM brgy_santulan_resident;

SELECT * FROM document_request;

SHOW CREATE TABLE malabon_resident;
SELECT DISTINCT purpose FROM malabon_resident;
SELECT DISTINCT purpose
FROM malabon_resident
WHERE purpose IS NULL OR TRIM(purpose) NOT IN ('WORK', 'SCHOOL', 'MEDICAL', 'BUSINESS');

DELETE FROM malabon_resident WHERE id <= 32;

-- DROP TABLE IF EXISTS malabon_residents;

-- ALTER TABLE malabon_resident DROP INDEX UK9ompuvfpsji7q578or9gro4fg;

SHOW CREATE TABLE brgy_santulan_resident;
SHOW CREATE TABLE malabon_resident;


ALTER TABLE brgy_santulan_resident DROP CHECK brgy_santulan_resident_chk_1;


