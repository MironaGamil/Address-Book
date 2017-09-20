CREATE DEFINER=`root`@`localhost` PROCEDURE `addValuesToEmplyees`(IN name VARCHAR(40), IN hAdrr VARCHAR(100), hNumber INT, IN bAdrr VARCHAR(100), bNumber INT, cNumber INT , INOUT inserted INT)
BEGIN

	INSERT INTO employees VALUES (default , name, hAdrr, hNumber, bAdrr, bNumber, cNumber);
    SET inserted = LAST_INSERT_ID();

END