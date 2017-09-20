CREATE DEFINER=`root`@`localhost` PROCEDURE `updateEmployee`(in employee_id int, IN name VARCHAR(40), in cName varchar(40), IN hAdrr VARCHAR(100), hNumber INT, IN bAdrr VARCHAR(100), bNumber INT, cNumber INT)
BEGIN
	update employees set name= name, companyName= cName, homeAddress= hAdrr, homePhoneNumber= hNumber,businessAddress= bAdrr ,businessPhoneNumber= bNumber, cellularPhone= cNumber where id= employee_id;
END