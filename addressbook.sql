CREATE DEFINER=`root`@`localhost` PROCEDURE `addressbook`()
BEGIN
	create table agents(
		id INT NOT NULL AUTO_INCREMENT,
		name VARCHAR(40) NOT NULL,
		homeAddress VARCHAR(100) NOT NULL,
		cellularPhone INT NOT NULL,
		PRIMARY KEY (id)

    );
	CREATE TABLE employees(
		id INT NOT NULL AUTO_INCREMENT,
		name VARCHAR(40) NOT NULL,
        companyName varchar(40) not null,
		homeAddress VARCHAR(100) NOT NULL,
		homePhoneNumber INT,
		businessAddress VARCHAR(100) NOT NULL,
		businessPhoneNumber INT,
		cellularPhone INT NOT NULL,
		PRIMARY KEY (id)
	);
    CREATE TABLE faxNumbers(
		id INT,
        faxNumber INT,
        FOREIGN KEY(id) REFERENCES employees(id),
        PRIMARY KEY (ID, faxNumber)
    );

END