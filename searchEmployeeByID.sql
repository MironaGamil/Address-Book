CREATE DEFINER=`root`@`localhost` PROCEDURE `searchEmployeeByID`(in employee_id int)
BEGIN
	select * from employees where id = employee_id;
END