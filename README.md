#SICP in Clojure

##About
This is a version of the classic [_Structure and Interpretation of Computer Programs_](http://mitpress.mit.edu/sicp/)
by Hal Abelson and Jerry &amp; Julie Sussman, revised with code examples in [Clojure](http://clojure.org) instead
of the original [Scheme](http://en.wikipedia.org/wiki/Scheme_\(programming_language\)).

##Read
If you'd just like to read the book, [visit the site here](http://ecmendenhall.github.com/sicpclojure). 

##Contribute
The [code samples](https://github.com/ecmendenhall/sicpclojure/tree/master/resources/code) and 
[chapter text](https://github.com/ecmendenhall/sicpclojure/tree/master/resources/text) are available
in this repository, so you can contribute to the project yourself. If you see an example that could be improved, 
want to add a footnote or aside about Clojure, or want to jump in and edit one of the uncompleted chapters, 
please make a fix and submit a pull request.

###Editing guidelines
The source text is in the `resources/text` directory, with filenames corresponding to the pages of the original book's
[website](http://mitpress.mit.edu/sicp/full-text/book/book-Z-H-1.html). These files are all in standard 
[markdown](http://daringfireball.net/projects/markdown/), with three extra elements:

**Inline footnotes** are indicated by `[^fn-#]`, where `#` is the footnote number in the original text. The
corresponding anchor at the bottom of the page is indicated by `[fn-#]`:

```
We will use block structure extensively to help us break up large programs
into tractable pieces.[^fn-28]

[fn-28] Embedded definitions must come first in a procedure body. The 
management is not responsible for the consequences of running programs that 
intertwine definition and use.
```

**Code blocks** are represented by sequential filenames inside double curly braces. For example:

```
A second advantage of prefix notation is that it extends in a straightforward
way to allow combinations to be _nested_, that is, to have combinations whose
elements are themselves combinations:

{{ 1.1.1.5.clj }}

There is no limit (in principle) to the depth of such nesting and to the
overall complexity of the expressions that the Lisp interpreter can evaluate.
It is we humans who get confused by still relatively simple expressions such
as

{{ 1.1.1.6.clj }}

which the interpreter would readily evaluate to be 57.
```

These correspond to `.clj` files in `resources/code`. Code blocks are labeled sequentially according to their
location in the text: the third figure in section 1.1.7 is `1.1.7.3.clj`.

**Math** uses [MathJax](http://www.mathjax.org/) syntax, which includes most of your favorite 
[LaTex symbols](http://docs.mathjax.org/en/latest/tex.html#supported-latex-commands). Blocks of math are wrapped
in `\\[` and `\\]`:

```
In general, the Fibonacci numbers can be defined by the rule

\\[ \text{Fib}(n) = 
\begin{cases}
 0 & \text {if } n = 0 \\\\
 1 & \text{if }  n = 1 \\\\ 
 \text{Fib}(n-1) + \text{Fib}(n-2) & \text{otherwise} 
\end{cases} \\]
```

Inline expressions are wrapped in `\\(` and `\\)`:

```
**Exercise 1.13.**  Prove that _Fib_(_n_) is the closest integer to \\( \phi^{n} / \sqrt{5} \\),
where \\( \phi = (1 + \sqrt{5})/2 \\) Hint: Let \\( \psi= (1 - \sqrt{5}) / {2} \\).
```

Be careful! You'll need to escape any backslashes that don't precede a word, so separating rows in a matrix or
system of equations becomes `\\\\` instead of `\\`. I usually use this MathJax 
[test site](http://advisors-online.com/tex_field.html) for reference.

Finally, figures are stored in `resources/static/img/`, with their original labels from the source text. I've been
using `.png` images drawn up with Google Docs, but make your own however you'd like. Text is in 
[Ubuntu Mono](http://font.ubuntu.com/) and colors are from the [Solarized](http://ethanschoonover.com/solarized) 
color scheme. Transparent backgrounds work best.


###Previewing your edits
I haven't packaged this as a library, and probably won't, but if you want to check out your extra-escaped backslashes
or simply admire your handiwork, it's pretty easy to run it through the site generator from the REPL:

```
git clone git@github.com:ecmendenhall/sicpclojure.git
cd sicpclojure
lein deps
lein repl
```

Open `src/sicpclojure/config.clj` and add the pages you'd like to preview to the `:complete` vector:
```
(def build {:path-to-code "resources/code/" ; Path to code excerpts.
            :path-to-text "resources/text/" ; Path to chapter text.
            :deploy-directory "deploy/"     
            :ignore #{".scss"}              ; Ignore these filetypes when deploying.
            :complete [9 10 11 12]})        ; Vector of completed pages. <--- This one!
```

Slurp `src/sicpclojure/core.clj` into your REPL, then:
```
(deploy-and-serve)
```
This will generate the page and start a simple Jetty server. Your edits should now be available as beautiful 
full-color HTML via localhost:3000.

##License
The SICP text is licensed under a Creative Commons [BY-SA 3.0](http://creativecommons.org/licenses/by-sa/3.0/) license
(so the source text in this repository is too). My code is released under an MIT license. If you would like to read
one, see the file [LICENSE.md](https://github.com/ecmendenhall/sicpclojure/blob/master/LICENSE.md)!

This site also uses [Requirejs](http://requirejs.org/), [MathJax](http://www.mathjax.org/),
[Highlight.js](http://softwaremaniacs.org/soft/highlight/en/), [Google Web Fonts](http://www.google.com/webfonts),
and the [Solarized](http://ethanschoonover.com/solarized) [sass](http://sass-lang.com/) template, plus 
the [pegdown](https://github.com/sirthias/pegdown) markdown processor, [Hiccup](https://github.com/weavejester/hiccup)
templates, and the [Hickory](https://github.com/davidsantiago/hickory) HTML to Hiccup parser.
