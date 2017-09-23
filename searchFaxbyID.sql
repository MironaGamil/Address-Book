CREATE DEFINER=`root`@`localhost` PROCEDURE `searchFaxbyID`(in emp_id int)
BEGIN
		select * from faxnumbers where employee_id = emp_id;
END