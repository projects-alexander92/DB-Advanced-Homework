CREATE PROCEDURE usp_get_older(IN minion_id INT)
  BEGIN
    UPDATE minions SET minions.age = minions.age+1 WHERE minions.id = minion_id;
  END;

