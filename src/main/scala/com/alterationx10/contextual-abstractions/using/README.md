The code examples here go over a comparison of passing implicits to methods in Scala 2, and what that would
look like in Scala 3 with the using/given syntax.

> Abstracting over contextual information. Using clauses allow programmers to abstract over information that is available in the calling context and should be passed implicitly. As an improvement over Scala 2 implicits, using clauses can be specified by type, freeing function signatures from term variable names that are never explicitly referred to.

https://docs.scala-lang.org/scala3/reference/contextual/using-clauses.html