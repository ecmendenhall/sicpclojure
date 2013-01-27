require(["http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML"]
);

require(["highlight.pack"], function(hljs) {
    hljs.initHighlighting();
});

require(["colorscheme"], function(colorscheme) {
    colorscheme.getColorPreference();
});
