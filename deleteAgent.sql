CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteAgent`(in agent_id int)
BEGIN
		delete from agents where id= agent_id;
END