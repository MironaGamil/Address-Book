CREATE DEFINER=`root`@`localhost` PROCEDURE `updateEmployee`(in employee_id int, IN name VARCHAR(40), in cName varchar(40), IN hAdrr VARCHAR(100), hNumber varchar(20), IN bAdrr VARCHAR(100), bNumber varchar(20), cNumber varchar(20))
BEGIN
	update employees set name= name, companyName= cName, homeAddress= hAdrr, homePhoneNumber= hNumber,businessAddress= bAdrr ,businessPhoneNumber= bNumber, cellularPhone= cNumber where id= employee_id;
END