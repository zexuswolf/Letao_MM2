insert into LETAO_MM_MATERIAL_TYPE (ID,THAI_NAME,ENGLISH_NAME) values (1,'�ͧ��ҹ','Dessert');
insert into LETAO_MM_MATERIAL_TYPE (ID,THAI_NAME,ENGLISH_NAME) values (2,'����ͧ����','Beverage');
insert into LETAO_MM_MATERIAL_TYPE (ID,THAI_NAME,ENGLISH_NAME) values (3,'�ͧ��ѭ','Gift');

insert into LETAO_MM_MATERIAL_TYPE (ID,THAI_NAME,ENGLISH_NAME,PARENT) values (4,'��','Cake',1);
insert into LETAO_MM_MATERIAL_TYPE (ID,THAI_NAME,ENGLISH_NAME,PARENT) values (5,'��ͤ��ŵ','Chocolate',1);
insert into LETAO_MM_MATERIAL_TYPE (ID,THAI_NAME,ENGLISH_NAME,PARENT) values (6,'��顡��','Cookie',1);

insert into LETAO_MM_MATERIAL (ID,THAI_NAME,ENGLISH_NAME,MATERIAL_TYPE_ID,CREATED_AT,UPDATED_AT) VALUES (1,'�����','Chesse Cake',4,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP);