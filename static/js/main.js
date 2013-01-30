require(["mathjax"], function(mjsettings) {
    mjsettings.injectConfig(mjsettings.mathJaxConfig);
    mjsettings.mathJaxDefaults();
});

require(["highlight.pack"], function(hljs) {
    hljs.initHighlighting();
});

require(["analytics"]);
