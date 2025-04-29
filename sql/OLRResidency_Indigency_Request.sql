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

-- DROP TABLE IF EXISTS malabon_residents;

-- ALTER TABLE malabon_resident DROP INDEX UK9ompuvfpsji7q578or9gro4fg;

SHOW CREATE TABLE brgy_santulan_resident;
SHOW CREATE TABLE malabon_resident;
SHOW CREATE TABLE document_request;


ALTER TABLE malabon_resident DROP COLUMN complete_address;

ALTER TABLE malabon_resident
MODIFY subdivision VARCHAR(255) NULL;

-- ALTER TABLE document_request DROP FOREIGN KEY FKpcra3lyhu3ujl133a7ic3xp7e;

-- ALTER TABLE document_request
-- ADD CONSTRAINT FKpcra3lyhu3ujl133a7ic3xp7e
-- FOREIGN KEY (resident_id) REFERENCES malabon_resident(id)
-- ON DELETE CASCADE;

-- DELETE FROM document_request WHERE resident_id <= 104;
-- DELETE FROM malabon_resident WHERE id <= 104;




