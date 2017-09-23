CREATE DEFINER=`root`@`localhost` PROCEDURE `addValuesToEmplyees`(IN name VARCHAR(40), IN cname VARCHAR(40), IN hAdrr VARCHAR(100), hNumber varchar(20), IN bAdrr VARCHAR(100), bNumber varchar(20), cNumber varchar(20) , INOUT inserted INT)
BEGIN

	INSERT INTO employees VALUES (default , name, cName, hAdrr, hNumber, bAdrr, bNumber, cNumber);
    SET inserted = LAST_INSERT_ID();

END