#!/bin/bash

# create database
sudo mysql -u root -e "CREATE DATABASE pp221 CHARSET utf8mb4 COLLATE utf8mb4_general_ci"

# create user
sudo mysql -u root -e 'CREATE USER pp221@localhost IDENTIFIED BY "Pp221Apple1976@@@###Fuck" '

# grart privileges
sudo mysql -u root -e 'GRANT ALL PRIVILEGES ON pp221.* TO pp221@localhost '

# check connection
mysql -u pp221 -pPp221Apple1976@@@###Fuck pp221


