# PostgreSQL Setup on VM

This guide provides step-by-step instructions to set up PostgreSQL on a virtual machine (VM).

## Steps to Install and Manage PostgreSQL
```bash
sudo su
apt update
apt install postgresql
sudo apt install postgresql-client
sudo systemctl status postgresql.service
sudo systemctl restart postgresql.service
sudo -su postgres
psql
alter user postgres with password 'admin@123';
show hba_file;
\q
vi /etc/postgresql/15/main/postgresql.conf
listen_addresses = '*'
#### Now edit the PostgreSQL access policy configuration file.
\q
vi /etc/postgresql/15/main/pg_hba.conf
host  all             all             0.0.0.0/0            md5

#### Restart the services
```bash
sudo systemctl restart postgresql.service
sudo systemctl status postgresql.service

### Once connected, please create a Database

sudo -u postgres psql
CREATE DATABASE springbootdb;
\conninfo
Check if the database was created:
\l
Exit psql:
\q

```
```bash
## Generate the test data
you will have Hostname :<externalVM IP> username :postgres  password: admin@123
```bash
CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100)
);
-- Ingest the Data into Postgresql using stored procedure
-- Create a function to insert employees
CREATE OR REPLACE FUNCTION insert_employees()
RETURNS VOID AS $$
DECLARE
    i INT := 1;
BEGIN
    WHILE i <= 4000000 LOOP
        INSERT INTO employees (first_name, last_name, email) 
        VALUES (
            CONCAT('sumanth', i), 
            CONCAT('krishna', i), 
            CONCAT('email', i, '@example.com')
        );
        i := i + 1;
    END LOOP;
END;
$$ LANGUAGE plpgsql;

-- Execute the function
SELECT insert_employees();

-- Verify the number of records inserted
SELECT COUNT(*) FROM employees;
SELECT * FROM employees LIMIT 100;
```
Once data is generated  , run the build and run the application using java-jar or docker or kubernetes
kubectl apply -f deployment.yaml
http://<ipaddress/9090/api/employees/

docker run -d -p 9090:9090 sumanth17121988/creditcardservice:1

**Setup Dynatrace Postgres Extension**
Click  Install
<img width="794" alt="image" src="https://github.com/user-attachments/assets/53929d94-8f70-4a2e-a44b-90164ec4f5c3" />
CREATE USER dynatrace WITH PASSWORD 'password' INHERIT;  
GRANT pg_monitor TO dynatrace;

Click COnfigure
<img width="812" alt="image" src="https://github.com/user-attachments/assets/833f86a6-2a00-478d-9e9f-af1a1ce56071" />


<img width="785" alt="image" src="https://github.com/user-attachments/assets/d9907389-e7b2-4e62-9a31-4633f4094ac1" />

<img width="786" alt="image" src="https://github.com/user-attachments/assets/b780237b-9c42-4452-8e53-727c485647ce" />

<img width="746" alt="image" src="https://github.com/user-attachments/assets/3a3d2968-64a5-4c29-be36-1d31ef65f695" />

<img width="869" alt="image" src="https://github.com/user-attachments/assets/4d70ecf7-4e90-4080-93e8-5abcbc7c559a" />

<img width="884" alt="image" src="https://github.com/user-attachments/assets/ac427a36-6136-4118-a431-b039d2300581" />


If you are using configmap and secrets , you can check what values are passed
kubectl exec -it postgres-0 -- printenv | grep POSTGRES

POSTGRES_PASSWORD=admin@123
POSTGRES_DB=springbootdb
POSTGRES_USER=postgres

kubectl get secret postgres-secret -o jsonpath='{.data.username}' | base64 -d
kubectl get secret postgres-secret -o jsonpath='{.data.password}' | base64 -d

psql -h 34.118.225.45 -U postgres -d springbootdb -p 5432
