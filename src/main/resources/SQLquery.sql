/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  solid
 * Created: Oct 6, 2020
 */

INSERT INTO `user_entity` VALUES (1,'cus@gmail.com',_binary '','123qwerty','ROLE_USER','customer1'),(2,'cus@gmail.com',_binary '','123qwerty','ROLE_USER','customer2'),(3,'cus@gmail.com',_binary '','1234qwerty','ROLE_TELLER','teller'),(4,'cus@gmail.com',_binary '','1234qwerty','ROLE_ADMIN','admin');
INSERT INTO `customer_entity` VALUES (2,'123','Da nang','2012-02-23','Da nang','Viet nam','2017-02-23','Thanh khue','thaingoctam11cdt2@gmail.com','HOANG DUY NHAT','police Da nang','iet nam','43534','male',_binary '',1),(3,'123','Da nang','2012-02-23','Da nang','Viet Nam','2013-03-02','Thah khue','solidwork2013@gmail.com','THAI NGOC TAM','Da nang','Viet Nam','5435','Female',_binary '',2);
INSERT INTO `bank_entity` VALUES (1,'Da nang','BIDV@gmail.com','BIDV','87439837');
INSERT INTO `account_entity` VALUES (1736686184803,'Saving',10065000,_binary '',1,3),(6683693928425,'Recurring Deposit Account',11060000,_binary '',1,3),(6866066111373,'Saving',299600000,_binary '',1,2),(7014713880244,'Recurring Deposit Account',15045000,_binary '',1,2);
INSERT INTO `teller_entity` VALUES (1,'Da nang','teller1@gmail.com',1,3),(2,'Da nang','Admin@gmail.com',1,4);