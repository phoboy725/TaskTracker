INSERT INTO tasks (user_id, title, description, task_status, created_at, completed_at)
VALUES
    (1, 'Купить весло', null, 'ACTIVE',now(), null),
    (1, 'Побриться', 'Нужно чаще бриться', 'ACTIVE', now(), null ),
    (1, 'Сделать уроки', 'Не хочется, но надо!', 'ACTIVE',now(), null),
    (1, 'Спортзал', 'движение это жизнь', 'COMPLETED',now(), now()),
    (1, 'Выпить воды', 'Это полезно', 'COMPLETED', now(), now()),
    (1, 'Созвон', 'Не хочется, но надо!', 'COMPLETED',now(), now());