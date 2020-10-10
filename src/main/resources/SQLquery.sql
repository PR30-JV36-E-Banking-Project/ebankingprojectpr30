/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  solid
 * Created: Oct 6, 2020
 */

INSERT INTO `user_entity` VALUES (1,'cus@gmail.com','123','ROLE_USER','customer1'),(2,'cus@gmail.com','123','ROLE_USER','customer2'),(3,'cus@gmail.com','1234','ROLE_TELLER','teller'),(4,'cus@gmail.com','1234','ROLE_ADMIN','admin');
INSERT INTO `bank_entity` VALUES (1,'Da nang','BIDV@gmail.com','BIDV','87439837');
INSERT INTO `customer_entity` VALUES (2,'123','Da nang','2012-02-23','Da nang','Viet nam','2017-02-23','Thanh khue','cus@gmail.com','Tam','police Da nang','iet nam','43534','male',_binary '',1),(3,'123','Da nang','2012-02-23','Da nang','Viet Nam','2013-03-02','Thah khue','cus@gmail.com','TamThai','Da nang','Viet Nam','5435','Female',_binary '',2);
INSERT INTO `teller_entity` VALUES (1,'Da nang','teller1@gmail.com',1,3),(2,'Da nang','Admin@gmail.com',1,4);
INSERT INTO `account_entity` VALUES (3,'Saving',10000000,_binary '',1,2),(4,'Recurring Deposit Account',20000000,_binary '',1,2),(6,'saving',30000000,_binary '',1,3),(7,'Recurring Deposit Account',34000000,_binary '',1,3);
INSERT INTO `transaction_entity` VALUES (1,1000000,'Chuyentien',_binary '','2012-02-02','Internal Tranfer',3,6);
