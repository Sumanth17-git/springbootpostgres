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


## Generate the test data
you will have Hostname :<externalVM IP> username :postgres  password: admin@123
<img width="231" alt="image" src="https://github.com/user-attachments/assets/e703825c-1c3e-4c10-97a1-960be978224f" />



SELECT * FROM employees LIMIT 100;

kubectl apply -f deployment.yaml
http://<ipaddress/9090/api/employees/

docker run -d -p 9090:9090 sumanth17121988/creditcardservice:1

