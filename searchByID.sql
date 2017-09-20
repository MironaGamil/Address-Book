CREATE DEFINER=`root`@`localhost` PROCEDURE `searchByID`(in employee_id int)
BEGIN
	select * from employees where id = employee_id;
END