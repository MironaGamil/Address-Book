CREATE DEFINER=`root`@`localhost` PROCEDURE `addValuesToAgents`(in name varchar(40) , in address varchar(100), in phone int )
BEGIN
	insert into agents values (default, name, address, phone);
END