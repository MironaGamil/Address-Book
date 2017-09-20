CREATE DEFINER=`root`@`localhost` PROCEDURE `searchFaxbyID`(in employee_id int)
BEGIN
		select * from faxnumbers where id = employee_id;
END