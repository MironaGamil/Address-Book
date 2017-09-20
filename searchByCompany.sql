CREATE DEFINER=`root`@`localhost` PROCEDURE `searchByCompany`(IN company_name varchar(40))
BEGIN
	select * from employees where companyName =company_name;
END