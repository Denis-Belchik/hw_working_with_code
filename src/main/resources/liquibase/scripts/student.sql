-- liquibase formatted sql

-- changeset dbelchik:1
CREATE INDEX name_sdudent_index ON student (name);