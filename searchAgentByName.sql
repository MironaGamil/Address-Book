CREATE DEFINER=`root`@`localhost` PROCEDURE `searchAgentByName`(IN agent_name varchar(40))
BEGIN
	select * from agents where name=agent_name;
END