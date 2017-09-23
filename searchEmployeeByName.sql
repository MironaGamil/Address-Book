CREATE DEFINER=`root`@`localhost` PROCEDURE `searchEmployeeByName`(IN employee_name varchar(40))
BEGIN
	select * from employees where name like CONCAT('%', employee_name, '%');

END