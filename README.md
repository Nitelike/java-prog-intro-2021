# java-prog-intro-2021

[Условия домашних заданий](https://www.kgeorgiy.info/courses/prog-intro/homeworks.html)


## Домашнее задание 13. Обработка ошибок

Модификации
 * *Base*
    * Класс `ExpressionParser` должен реализовывать интерфейс
        [Parser](https://www.kgeorgiy.info/git/geo/prog-intro-2021/src/branch/master/java/expression/exceptions/Parser.java)
    * Классы `CheckedAdd`, `CheckedSubtract`, `CheckedMultiply`,
        `CheckedDivide` и `CheckedNegate` должны реализовывать интерфейс
        [TripleExpression](https://www.kgeorgiy.info/git/geo/prog-intro-2021/src/branch/master/java/expression/TripleExpression.java)
    * Нельзя использовать типы `long` и `double`
    * Нельзя использовать методы классов `Math` и `StrictMath`
    * [Исходный код тестов](https://www.kgeorgiy.info/git/geo/prog-intro-2021/src/branch/master/java/expression/exceptions/ExceptionsTest.java)
        * Первый аргумент: `easy` или `hard`
        * Последующие аргументы: модификации
 * *PowLog* (36-39)
    * Дополнительно реализуйте бинарные операции (максимальный приоритет):
        * `**` – возведение в степень, `2 ** 3` равно 8;
        * `//` – логарифм, `10 // 2` равно 3.
 * *MinMax* (31-37)
    * Дополнительно реализуйте бинарные операции (минимальный приоритет):
        * `min` – минимум, `2 min 3` равно 2;
        * `max` – максимум, `2 max 3` равно 3.
 * *Abs* (36-39)
    * Дополнительно реализуйте унарную операцию
        * `abs` – модуль числа, `abs -5` равно 5.


## Домашнее задание 12. Разбор выражений

Модификации
 * *Base*
    * Класс `expression.parser.ExpressionParser` должен реализовывать интерфейс
        [Parser](https://www.kgeorgiy.info/git/geo/prog-intro-2021/src/branch/master/java/expression/parser/Parser.java)
    * Результат разбора должен реализовывать интерфейс
        [TripleExpression](https://www.kgeorgiy.info/git/geo/prog-intro-2021/src/branch/master/java/expression/TripleExpression.java)
    * [Исходный код тестов](https://www.kgeorgiy.info/git/geo/prog-intro-2021/src/branch/master/java/expression/parser/ParserTest.java)
        * Первый аргумент: `easy` или `hard`
        * Последующие аргументы: модификации
 * *MinMax* (34-37)
    * Дополнительно реализуйте бинарные операции (минимальный приоритет):
        * `min` – минимум, `2 min 3` равно 2;
        * `max` – максимум, `2 max 3` равно 3.
 * *Zeroes* (31-33, 36-39)
    * Дополнительно реализуйте унарные операции
      * `l0` – число старших нулевых бит, `l0 123456` равно 15;
      * `t0` – число младших нулевых бит, `t0 123456` равно 6.


## Домашнее задание 11. Выражения

Модификации
 * *Base*
    * Реализуйте интерфейс [Expression](https://www.kgeorgiy.info/git/geo/prog-intro-2021/src/branch/master/java/expression/Expression.java)
    * [Исходный код тестов](https://www.kgeorgiy.info/git/geo/prog-intro-2021/src/branch/master/java/expression/ExpressionTest.java)
        * Первый аргумент: `easy` или `hard`
        * Последующие аргументы: модификации
 * *Triple* (31-39)
    * Дополнительно реализуйте поддержку выражений с тремя переменными: `x`, `y` и `z`.
    * Интерфейс [TripleExpression](https://www.kgeorgiy.info/git/geo/prog-intro-2021/src/branch/master/java/expression/TripleExpression.java).
 * *BigInteger* (36-37)
    * Дополнительно реализуйте вычисления в типе `BigInteger`.
    * Интерфейс [BigIntegerExpression](https://www.kgeorgiy.info/git/geo/prog-intro-2021/src/branch/master/java/expression/BigIntegerExpression.java).


## Домашнее задание 10. Игра m,n,k

Тесты не предусмотрены. Решение должно находиться в пакете `game`.

Модификации
 * *Турнир* (36-37)
    * Добавьте поддержку кругового турнира для нескольких участников.
    * В рамках кругового турнира каждый с каждым должен сыграть две партии,
      по одной каждым цветом.
    * Выведите таблицу очков по схеме:
        * 3 очка за победу;
        * 1 очко за ничью;
        * 0 очков за поражение.
 * *Гекс* (36-39)
    * Добавьте поддержку ромбической доски для 
      [игры Гекс](https://ru.wikipedia.org/wiki/Гекс)
      (с тремя направлениями линий).
    * В качестве примера, сделайте доску размером <em>11×11</em>.


## Домашнее задание 9. Markdown to HTML

Модификации
 * *Базовая*
    * [Исходный код тестов](https://www.kgeorgiy.info/git/geo/prog-intro-2021/src/branch/master/java/md2html/Md2HtmlTester.java)
    * [Откомпилированные тесты](https://www.kgeorgiy.info/git/geo/prog-intro-2021/src/branch/master/artifacts/Md2HtmlTest.jar)
        * Аргументы командной строки: модификации
 * *Pre* (36, 37)
    * Добавьте поддержку 
      \`\`\``кода __без__ форматирования`\`\`\`: 
      `<pre>кода __без__ форматирования</pre>`


## Домашнее задание 7. Разметка

Модификации
 * *Base*
    * Исходный код тестов:
        * [MarkupTester.java](https://www.kgeorgiy.info/git/geo/prog-intro-2021/src/branch/master/java/markup/MarkupTester.java)
        * [MarkupTest.java](https://www.kgeorgiy.info/git/geo/prog-intro-2021/src/branch/master/java/markup/MarkupTest.java)
        * Аргументы командной строки: модификации
 * *BBCodeList* (36, 37)
    * Сделайте модификацию *BBCode*
    * Добавьте поддержку:
      * Нумерованных списков (класс `OrderedList`, тег `list=1`): последовательность элементов
      * Ненумерованных списков (класс `UnorderedList`, тег `list`): последовательность элементов
      * Элементов списка (класс `ListItem`, открывающий тег `*`): последовательность абзацев и списков
    * Для новых классов поддержка Markdown не требуется
    * [Исходный код тестов](https://www.kgeorgiy.info/git/geo/prog-intro-2021/src/branch/master/java/markup/MarkupListTest.java)


## Домашнее задание 6. Подсчет слов++

Модификации
 * *Base*
    * Класс должен иметь имя `Wspp`
    * Исходный код тестов: 
        [WsppTest.java](https://www.kgeorgiy.info/git/geo/prog-intro-2021/src/branch/master/java/wspp/WsppTest.java), 
        [WsppTester.java](https://www.kgeorgiy.info/git/geo/prog-intro-2021/src/branch/master/java/wspp/WsppTester.java)
    * Откомпилированные тесты: [WsppTest.jar](https://www.kgeorgiy.info/git/geo/prog-intro-2021/src/branch/master/artifacts/WsppTest.jar)
        * Аргументы командной строки: модификации
 * *SecondG* (36-37)
    * Вместо номеров вхождений во всем файле надо указывать
      только четныe вхождения в каждой строке.
    * Класс должен иметь имя `WsppSecondG`


## Домашнее задание 5. Свой сканнер

Модификации
 * *Base*
    * Исходный код тестов: [FastReverseTest.java](https://www.kgeorgiy.info/git/geo/prog-intro-2021/src/branch/master/java/reverse/FastReverseTest.java)
    * Откомпилированные тесты: [FastReverseTest.jar](https://www.kgeorgiy.info/git/geo/prog-intro-2021/src/branch/master/artifacts/FastReverseTest.jar)
        * Аргументы командной строки: модификации
 * *HexDec2* (36-37)
    * На вход подаются десятичные и шестнадцатеричные числа
    * Шестнадцатеричные числа имеют префикс `0x`
    * Выведите все числа, используя формат шестнадцатеричных чисел
    * Класс должен иметь имя `ReverseHexDec2`


## Домашнее задание 4. Подсчет слов

Модификации
 * *Base*
    * Класс должен иметь имя `WordStatInput`
    * Исходный код тестов:
        [WordStatTest.java](https://www.kgeorgiy.info/git/geo/prog-intro-2021/src/branch/master/java/wordStat/WordStatTest.java),
        [WordStatTester.java](https://www.kgeorgiy.info/git/geo/prog-intro-2021/src/branch/master/java/wordStat/WordStatTester.java),
        [WordStatChecker.java](https://www.kgeorgiy.info/git/geo/prog-intro-2021/src/branch/master/java/wordStat/WordStatChecker.java)
    * Откомпилированные тесты: [WordStatTest.jar](https://www.kgeorgiy.info/git/geo/prog-intro-2021/src/branch/master/artifacts/WordStatTest.jar)
        * Аргументы командной строки: модификации
 * *Words* (31, 32, 33, 36, 37)
    * В выходном файле слова должны быть упорядочены в лексикографическом порядке
    * Класс должен иметь имя `WordStatWords`
 * *Sort* (36-39)
    * Пусть _n_ – число слов во входном файле,
      тогда программа должна работать за O(_n_ log _n_).


## Домашнее задание 3. Реверс

Модификации
 * *Base*
    * Исходный код тестов:
        [ReverseTest.java](https://www.kgeorgiy.info/git/geo/prog-intro-2021/src/branch/master/java/reverse/ReverseTest.java),
        [ReverseTester.java](https://www.kgeorgiy.info/git/geo/prog-intro-2021/src/branch/master/java/reverse/ReverseTester.java)
    * Откомпилированные тесты: [ReverseTest.jar](https://www.kgeorgiy.info/git/geo/prog-intro-2021/src/branch/master/artifacts/ReverseTest.jar)
        * Аргументы командной строки: модификации
 * *Min2* (36, 37)
    * Рассмотрим входные данные как (не полностью определенную) матрицу,
      вместо каждого числа выведите минимум из чисел 
      верхний левый угол матрицы — текущее число
    * Класс должен иметь имя `ReverseMin2`
 * *Linear* (36-39)
    * Пусть _n_ – сумма числа чисел и строк во входе,
      тогда программе разрешается потратить не более 6_n_+O(1) памяти


## Домашнее задание 2. Сумма чисел

Модификации
 * *LongHex* (36-37)
    * Входные данные являются 64-битными целыми числами
    * Шестнадцатеричные числа имеют префикс `0x`
    * Класс должен иметь имя `SumLongHex`
