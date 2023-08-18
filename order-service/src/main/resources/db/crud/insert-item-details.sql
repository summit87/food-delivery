--liquibase formatted sql logicalFilePath: item_detials_insert.sql
--changeset sumit:item_detials_inser.sql
insert into item_details (item_name, item_id, item_price, item_type, availability_status)
values ('Orange', '1093b9d4-0de7-45ea-adb9-33e147aab4f1', 12.34, 'FRUITS', 'AVAILABLE'),
       ('Apple', '6c44e701-2608-482c-811a-89021258ac21', 12.67, 'FRUITS', 'AVAILABLE'),
       ('Banana', '1093b9d4-0de7-45ea-adb9-33e147aab4f2', 12.34, 'FRUITS', 'AVAILABLE'),
       ('Grapes', '6c44e701-2608-482c-811a-89021258ac23', 12.67, 'FRUITS', 'AVAILABLE'),
       ('Pineapple', '1093b9d4-0de7-45ea-adb9-33e147aab4f4', 12.34, 'FRUITS', 'AVAILABLE'),
       ('Mango', '6c44e701-2608-482c-811a-89021258ac25', 12.67, 'FRUITS', 'AVAILABLE'),
       ('Litchi', '1093b9d4-0de7-45ea-adb9-33e147aab4f6', 12.34, 'FRUITS', 'AVAILABLE'),
       ('Potato', '6c44e701-2608-482c-811a-89021258ac27', 12.67, 'Vegetables', 'AVAILABLE');