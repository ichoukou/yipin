INSERT INTO help_category(name,parentId,rank) VALUES ('退款政策', '100003', '3');
INSERT INTO help(helpCategoryId) 
SELECT helpCategoryId FROM `help_category` WHERE parentId='100003' AND rank='3';