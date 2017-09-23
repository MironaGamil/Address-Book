CREATE DEFINER=`root`@`localhost` PROCEDURE `updateFaxnumbers`(IN emp_id INT)
BEGIN
		delete from faxnumbers where employee_id= emp_id;
END