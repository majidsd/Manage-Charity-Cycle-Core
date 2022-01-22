INSERT IGNORE INTO   user   (id,status,enabled,created_at,last_update) VALUES ('1','1',1,'2020-06-06','2020-06-06')
INSERT IGNORE INTO wallet   (id,created_at,current_balance,last_update,created_by) VALUES (1,'2020-06-06',0.0,'2020-06-06',1)
UPDATE user SET wallet_id = 1 WHERE id = 1
INSERT IGNORE INTO authority(id,authority_name,authority_description) VALUES (1,'ANONYMOUS','The anonymous user authority')
INSERT IGNORE INTO authority(id,authority_name,authority_description) VALUES (2,'USER','The ordinary user authority')
INSERT IGNORE INTO authority(id,authority_name,authority_description) VALUES (3,'AGENT','The agent user authority')
INSERT IGNORE INTO authority(id,authority_name,authority_description) VALUES (4,'ADMIN','The admin user authority')
INSERT IGNORE INTO user_authority VALUES(1,1)