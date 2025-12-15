CREATE ROLE User1 WITH
    LOGIN  PASSWORD 'strong' ;

    CREATE DATABASE football_db
        OWNER = User1
        ENCODING = 'UTF8'
        LC_COLLATE = 'fr_FR.UTF-8'
        LC_CTYPE = 'fr_FR.UTF-8'
        TABLESPACE = pg_default
        CONNECTION LIMIT = -1;

        \connect football_db;
        GRANT ALL PRIVILEGES ON DATABASE football_db TO football_db_manager;
        GRANT CREATE ON SCHEMA public TO football_db_manager;