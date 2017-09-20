CREATE DEFINER=`root`@`localhost` PROCEDURE `updateFaxnumbers`(IN employee_id INT)
BEGIN
		delete from faxnumbers where id= employee_id;
END