# Функция первого порядка (first-class function)
https://developer.mozilla.org/en-US/docs/Glossary/First-class_Function
A programming language is said to have First-class functions when functions in that language are treated like any other variable. For example, in such a language, a function can be passed as an argument to other functions, can be returned by another function and can be assigned as a value to a variable.

# Функция высшего порядка (high-order function)
Моими словами - функция, чей аргумент (или возвращаемое значение) другая функция.

# Функция чистая (pure function)
* Детерменировання (возвращает на один и тот же набор аргументов один и тот же результат)
* Не порождает сайд эффетктов (не меняет состояния аргументов или ещё чего, не вываливает ничего в аутпут и т.д.)

# Иммутабельность
Не изменяемость состояния. Всё val и т.д., наследоваться нельзя, методы состояния не меняют

# Декларативная модель
Говорим что делать, а не как. В спрингах через аспекты

# Каррирование (currying)
Перегоняет функцию от N аргументов в N вложенных функций от одного аргумента.
Т.е. f(a, b) -> c превращается в f(a) -> f(b) -> c
(функция от двух аргументов и возвращаемым типом c превращается в функцию от одного аргумента, возвращающую ещё одну функцию уже от второго аргумента, которая уже возвращает c)

