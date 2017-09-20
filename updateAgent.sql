CREATE DEFINER=`root`@`localhost` PROCEDURE `updateAgent`(in agent_id int, IN name VARCHAR(40), IN address VARCHAR(100), in phone int)
BEGIN
	update agents set name=name, homeAddress = address, cellularPhone = phone where id = agent_id;
END