CREATE DEFINER=`root`@`localhost` PROCEDURE `addValuesToFaxnumbers`(IN id INT, in faxnumber varchar(20))
BEGIN
	INSERT INTO faxnumbers VALUES (id, faxnumber);
END