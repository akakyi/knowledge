## В тупую: транзакция - атомарная операция над базой.
## Ниже спизжено с симбира:

### Что такое _«транзакция»_?
__Транзакция__ - это воздействие на базу данных, переводящее её из одного целостного состояния в другое и выражаемое в изменении данных, хранящихся в базе данных.


### Назовите основные свойства транзакции (ACID).
__Атомарность (atomicity)__ гарантирует, что никакая транзакция не будет зафиксирована в системе частично. Будут либо выполнены все её подоперации, либо не выполнено ни одной.

__Согласованность (consistency)__. Транзакция, достигающая своего нормального завершения и, тем самым, фиксирующая свои результаты, сохраняет согласованность базы данных.

__Изолированность (isolation)__. Во время выполнения транзакции параллельные транзакции не должны оказывать влияние на её результат.

__Долговечность (durability)__. Независимо от проблем на нижних уровнях (к примеру, обесточивание системы или сбои в оборудовании) изменения, сделанные успешно завершённой транзакцией, должны остаться сохранёнными после возвращения системы в работу.


### Какие проблемы могут возникать при параллельном доступе с использованием транзакций?
При параллельном выполнении транзакций возможны следующие проблемы:

+ __Потерянное обновление (lost update)__ — при одновременном изменении одного блока данных разными транзакциями одно из изменений теряется;
+ __«Грязное» чтение (dirty read)__ — чтение данных, добавленных или изменённых транзакцией, которая впоследствии не подтвердится (откатится);
+ __Неповторяющееся чтение (non-repeatable read)__ — при повторном чтении в рамках одной транзакции ранее прочитанные данные оказываются изменёнными;
+ __Фантомное чтение (phantom reads)__ — одна транзакция в ходе своего выполнения несколько раз выбирает множество записей по одним и тем же критериям. Другая транзакция в интервалах между этими выборками добавляет или удаляет записи или изменяет столбцы некоторых записей, используемых в критериях выборки первой транзакции, и успешно заканчивается. В результате получится, что одни и те же выборки в первой транзакции дают разные множества записей.


### Какие существуют уровни изолированности транзакций?
В порядке увеличения изолированности транзакций и, соответственно, надёжности работы с данными:

+ __Чтение неподтверждённых данных (грязное чтение) (read uncommitted, dirty read)__ — чтение незафиксированных изменений как своей транзакции, так и параллельных транзакций. Нет гарантии, что данные, изменённые другими транзакциями, не будут в любой момент изменены в результате их отката, поэтому такое чтение является потенциальным источником ошибок. Невозможны потерянные изменения, возможны неповторяемое чтение и фантомы.
+ __Чтение подтверждённых данных (read committed)__ — чтение всех изменений своей транзакции и зафиксированных изменений параллельных транзакций. Грязное чтение не допускается, возможны неповторяемое чтение и фантомы.
+ __Повторяемость чтения (repeatable read, snapshot)__ — чтение всех изменений своей транзакции, любые изменения, внесённые параллельными транзакциями после начала своей, недоступны. Потерянные изменения, грязное и неповторяемое чтение невозможны, возможны фантомы.
+ __Упорядочиваемость (serializable)__ — результат параллельного выполнения сериализуемой транзакции с другими транзакциями должен быть логически эквивалентен результату их какого-либо последовательного выполнения. Проблемы синхронизации не возникают.


### Заметка от моего кремниевого повелителя (ChatGPT)

#### what the difference between non-repeatable read and phantom read?

Both non-repeatable read and phantom read are phenomena that can occur in database systems when multiple transactions access the same data simultaneously. However, they are caused by different types of conflicts and result in different types of inconsistencies.

Non-repeatable read occurs when a transaction reads the same data multiple times within the same transaction, but gets different values each time. This happens when another transaction modifies the data that the first transaction has read between its read operations. For example, consider the following sequence of events:

    Transaction A reads a row from a table.
    Transaction B updates the same row.
    Transaction A reads the same row again and gets a different value than the first read.

This inconsistency is called a non-repeatable read because the same read operation cannot be repeated within the same transaction without getting a different result.

On the other hand, a phantom read occurs when a transaction reads a set of rows based on a certain condition, and then, during the same transaction, another transaction inserts or deletes rows that match the same condition, causing the first transaction to see a different set of rows if it re-executes the same query. For example, consider the following sequence of events:

    Transaction A reads all rows from a table where the "status" column is set to "active".
    Transaction B inserts a new row with the "status" column set to "active".
    Transaction A re-executes the same query and sees the new row, even though it did not exist when the query was first executed.

This inconsistency is called a phantom read because the first transaction sees "phantom" rows that did not exist in the original query result.

In summary, non-repeatable read occurs when the same data is read twice and gets different values due to a modification by another transaction, while phantom read occurs when the same query is executed twice and gets different results due to the insertion or deletion of rows by another transaction that match the same condition.

## АЛАРМА! ЧатГПТ пиздит и противоречит сам себе пероиодически
Потому вот статейка
https://mkdev.me/posts/transaction-isolation-levels-with-postgresql-as-an-example
и вот цитата Коли про рипитабл рид и защиту от потерянных обновлений:

#### транзакция А (Репитабле  рид):
читает строку (сделав снапшот этой строки)
#### транзакция Б (любая):
пишед данные в ту же строку
#### А:
пишет данные, видит, что текущая строка несовпадают со снапшетом и падает нахер.
