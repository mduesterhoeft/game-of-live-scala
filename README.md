# game of life

This project is a scala based implementation of the [game of life](http://codingdojo.org/cgi-bin/index.pl?KataGameOfLife) focussing on functional programming principles

I try to follow the [brutal coding constraints from @codecopkofler](http://blog.code-cop.org/2015/08/introducing-brutal-coding-constraints.html):

- No Conditionals, i.e. no if, unless or ?: operator. while can be used as conditional too and is not allowed as well.
- No Loops, i.e. no for, while, do or repeat until or whatever repetition constructs your language offers. Together No Conditionals and No Loops are sometimes called Cyclomatic Complexity One.
- TTDaiymi (TDD as if you Meant it), a very strict interpretation of the practice of TDD. This is optional, a "bonus" constraint for experienced developers. If you never heard about it, just ignore this one.
- No Naked Primitives, i.e. wrapping all "primitive" values, e.g. booleans, numbers or strings. Also general purpose containers like List, Map or Set are considered primitive. In extension all generic types of your language are primitive because they are not from your domain. A generic date (e.g. java.util.Date) is not from your domain even if you use dates, because it either does not define all methods you need or it defines other methods you do not need.
- No void, i.e. all functions must return something, methods with no return value are forbidden.
- Immutable, i.e. all data and data-structures must be immutable.

I did not follow the TTDaiymi and also the "No Naked Primitives" constraint is not followed currently.