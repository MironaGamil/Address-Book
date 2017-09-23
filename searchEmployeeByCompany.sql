CREATE DEFINER=`root`@`localhost` PROCEDURE `searchEmployeeByCompany`(IN company_name varchar(40))
BEGIN
	select * from employees where companyName like CONCAT('%', company_name, '%');
END