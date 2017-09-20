CREATE DEFINER=`root`@`localhost` PROCEDURE `searchAgentByID`(in agent_id int)
BEGIN
		select * from agents where id = agent_id;
END