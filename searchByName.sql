CREATE DEFINER=`root`@`localhost` PROCEDURE `searchByName`(IN employee_name varchar(40))
BEGIN
	select * from employees where name=employee_name;

END