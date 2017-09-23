CREATE DEFINER=`root`@`localhost` PROCEDURE `dropTables`()
BEGIN
	drop table if exists faxnumbers;
	drop table if exists employees;
	drop table if exists agents;

END