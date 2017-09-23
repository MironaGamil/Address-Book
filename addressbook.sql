CREATE DEFINER=`root`@`localhost` PROCEDURE `addressbook`()
BEGIN
	create table agents(
		id INT NOT NULL AUTO_INCREMENT,
		name VARCHAR(40) NOT NULL,
		homeAddress VARCHAR(100) NOT NULL,
		cellularPhone VARCHAR(20) NOT NULL,
		PRIMARY KEY (id)

    );
	CREATE TABLE employees(
		id INT NOT NULL AUTO_INCREMENT,
		name VARCHAR(40) NOT NULL,
        companyName varchar(40) not null,
		homeAddress VARCHAR(100) NOT NULL,
		homePhoneNumber VARCHAR(20),
		businessAddress VARCHAR(100) NOT NULL,
		businessPhoneNumber VARCHAR(20),
		cellularPhone VARCHAR(20) NOT NULL,
		PRIMARY KEY (id)
	);
    CREATE TABLE faxNumbers(
		employee_id INT,
        faxNumber VARCHAR(20),
        FOREIGN KEY(employee_id) REFERENCES employees(id) ON DELETE CASCADE,
        PRIMARY KEY (employee_id, faxNumber)
    );

END