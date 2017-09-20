CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteOnlyFromEmployee`(in employee_id int)
BEGIN
		delete from employees where id=employee_id;
END