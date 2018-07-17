INSERT INTO Organization (id, version, name, full_name, inn, kpp, address)
VALUES (1, 0, 'Евросеть', 'ООО Евросеть-Ритейл', '3664069397', '773301001', 'ул. Пушкина, 49');
INSERT INTO Organization (id, version, name, full_name, inn, kpp, address)
VALUES (2, 0, 'DNS', 'ООО ДНС', '566401571', '644901001', 'ул. Достоевского, 42');
INSERT INTO Organization (id, version, name, full_name, inn, kpp, address)
VALUES (3, 0, 'МТС', 'ПАО МТС', '7869697158', '561501154', 'ул. Проспект Ленина, 121');

INSERT INTO Office (id, version, name, address, phone, is_active, org_id)
VALUES (1, 0, 'Центральный', 'ул. Пушкина, 49', '89275588796', true, 1);
INSERT INTO Office (id, version, name, address, phone, is_active, org_id)
VALUES (2, 0, 'Западный', 'ул. Зорге, 12', '89275588745', true, 1);
INSERT INTO Office (id, version, name, address, phone, is_active, org_id)
VALUES (3, 0, 'Восточный', 'ул. Строителей, 1', '89275588712', true, 1);
INSERT INTO Office (id, version, name, address, phone, is_active, org_id)
VALUES (4, 0, 'Рынок', 'ул. Кувыкина, 52', '89179179171', true, 2);
INSERT INTO Office (id, version, name, address, phone, is_active, org_id)
VALUES (5, 0, 'ДНС-1', 'ул. Достоевского, 5', '89179179172', true, 2);
INSERT INTO Office (id, version, name, address, phone, is_active, org_id)
VALUES (6, 0, 'ДНС-2', 'ул. Шохолова, 17', '89179179173', true, 2);
INSERT INTO Office (id, version, name, address, phone, is_active, org_id)
VALUES (7, 0, 'Торговый центр', 'ул. Строителей, 1', '89275588712', true, 3);

INSERT INTO Doc (id, version, name, code) VALUES (1, 0, 'Свидетельство о рождении', '03');
INSERT INTO Doc (id, version, name, code) VALUES (2, 0, 'Военный билет', '07');
INSERT INTO Doc (id, version, name, code) VALUES (3, 0, 'Паспорт иностранного гражданина', '10');
INSERT INTO Doc (id, version, name, code) VALUES (4, 0, 'Вид на жительство в Российской Федерации', '12');
INSERT INTO Doc (id, version, name, code) VALUES (5, 0, 'Удостоверение беженца', '13');
INSERT INTO Doc (id, version, name, code) VALUES (6, 0, 'Паспорт гражданина Российской Федерации', '21');

INSERT INTO Country (id, version, name, code) VALUES (1, 0, 'Российская Федерация', '643');
INSERT INTO Country (id, version, name, code) VALUES (2, 0, 'Украина', '804');
INSERT INTO Country (id, version, name, code) VALUES (3, 0, 'Республика Казахстан', '398');
INSERT INTO Country (id, version, name, code) VALUES (4, 0, 'Грузия', '268');
INSERT INTO Country (id, version, name, code) VALUES (5, 0, 'Чешская республика', '203');

INSERT INTO User (id, version, first_name, second_name, middle_name, position, phone, doc_name, doc_number, doc_date, citizenship_code, is_identified, office_id)
VALUES (1, 0, 'Михаил', 'Уточкин', 'Петрович', 'Директор', '89275588712', '21', '8345654879', '21.05.05', '643', true, 1);
INSERT INTO User (id, version, first_name, second_name, middle_name, position, phone, doc_name, doc_number, doc_date, citizenship_code, is_identified, office_id)
VALUES
  (2, 0, 'Евгений', 'Князев', 'Петрович', 'Старший менеджер', '89176564123', '21', '12353453', '20.04.00', '643', true, 1);
INSERT INTO User (id, version, first_name, second_name, middle_name, position, phone, doc_name, doc_number, doc_date, citizenship_code, is_identified, office_id)
VALUES (3, 0, 'Андрей', 'Большев', 'Андреевич', 'Продавец', '89175585121', '13', '53673775', '14.12.05', '804', true, 2);
INSERT INTO User (id, version, first_name, second_name, middle_name, position, phone, doc_name, doc_number, doc_date, citizenship_code, is_identified, office_id)
VALUES (4, 0, 'Михаил', 'Белый', 'Алексеевич', 'Продавец', '89295583569', '07', '4567876687', '04.05.05', '643', true, 3);
INSERT INTO User (id, version, first_name, second_name, middle_name, position, phone, doc_name, doc_number, doc_date, citizenship_code, is_identified, office_id)
VALUES (5, 0, 'Вячеслав', 'Андреев', 'Романович', 'Продавец', '89285585484', '21', '12345378', '11.01.01', '643', true, 4);
INSERT INTO User (id, version, first_name, second_name, middle_name, position, phone, doc_name, doc_number, doc_date, citizenship_code, is_identified, office_id)
VALUES
  (6, 0, 'Петр', 'Волков', 'Андреевич', 'Администратор', '89255586512', '21', '8768672131', '21.06.12', '643', true, 4);
INSERT INTO User (id, version, first_name, second_name, middle_name, position, phone, doc_name, doc_number, doc_date, citizenship_code, is_identified, office_id)
VALUES (7, 0, 'Роман', 'Паромов', 'Леонидович', 'Кассир', '89075582123', '07', '253456345', '12.05.99', '804', true, 4);
INSERT INTO User (id, version, first_name, second_name, middle_name, position, phone, doc_name, doc_number, doc_date, citizenship_code, is_identified, office_id)
VALUES (8, 0, 'Эдуард', 'Сычев', 'Эдуардович', 'Охранник', '89075581248', '21', '546456432', '30.08.14', '643', true, 5);
INSERT INTO User (id, version, first_name, second_name, middle_name, position, phone, doc_name, doc_number, doc_date, citizenship_code, is_identified, office_id)
VALUES (9, 0, 'Василий', 'Гусев', 'Александрович', 'Уборщик', '89275584512', '21', '45645645', '01.01.17', '643', true, 6);
INSERT INTO User (id, version, first_name, second_name, middle_name, position, phone, doc_name, doc_number, doc_date, citizenship_code, is_identified, office_id)
VALUES
  (10, 0, 'Александр', 'Жарков', 'Данилович', 'Заместитель директора', '8927558123', '21', '546456456', '21.05.07', '268',
   true, 7);
INSERT INTO User (id, version, first_name, second_name, middle_name, position, phone, doc_name, doc_number, doc_date, citizenship_code, is_identified, office_id)
VALUES
  (11, 0, 'Артур', 'Леоньтев', 'Артурович', 'Менеджер по персоналу', '89275582141', '21', '45456212', '13.07.07', '643',
   true, 7);

