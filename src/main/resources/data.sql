insert into LETAO_MM_MATERIAL_TYPE (ID,THAI_NAME,ENGLISH_NAME) values (1,'ของหวาน','Dessert');
insert into LETAO_MM_MATERIAL_TYPE (ID,THAI_NAME,ENGLISH_NAME) values (2,'เครื่องดื่ม','Beverage');
insert into LETAO_MM_MATERIAL_TYPE (ID,THAI_NAME,ENGLISH_NAME) values (3,'ของขวัญ','Gift');

insert into LETAO_MM_MATERIAL_TYPE (ID,THAI_NAME,ENGLISH_NAME,PARENT) values (4,'เค้ก','Cake',1);
insert into LETAO_MM_MATERIAL_TYPE (ID,THAI_NAME,ENGLISH_NAME,PARENT) values (5,'ช็อคโกแลต','Chocolate',1);
insert into LETAO_MM_MATERIAL_TYPE (ID,THAI_NAME,ENGLISH_NAME,PARENT) values (6,'คุ้กกี้','Cookie',1);

insert into LETAO_MM_MATERIAL (ID,THAI_NAME,ENGLISH_NAME,MATERIAL_TYPE_ID,CREATED_AT,UPDATED_AT) VALUES (1,'ชีสเค้ก','Chesse Cake',4,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);