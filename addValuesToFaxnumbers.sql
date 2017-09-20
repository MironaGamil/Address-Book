CREATE DEFINER=`root`@`localhost` PROCEDURE `addValuesToFaxnumbers`(IN id INT, faxnumber INT)
BEGIN
	INSERT INTO faxnumbers VALUES (id, faxnumber);
END