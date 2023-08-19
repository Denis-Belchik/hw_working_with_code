-- liquibase formatted sql

-- changeset dbelchik:1
CREATE INDEX name_color_faculty_index ON faculty (name, color);