CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteEmployee`(in employee_id int)
BEGIN
	delete from employees where id=employee_id;
END