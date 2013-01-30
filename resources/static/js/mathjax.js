define( function () {
    
    function customMathJaxSettings () {
        
        this.injectConfig = function (fn, type) {
            var script = document.createElement('script');
            script.setAttribute("type", "text/x-mathjax-config");
            script.textContent = '(' + fn + ')();';
            document.body.appendChild(script);
        };

        this.mathJaxConfig = function () {
            MathJax.Hub.Config({ 
                messageStyle: "none",
                config: ["MMLorHTML.js"],
                jax: ["input/TeX",
                    "input/MathML",
                    "output/HTML-CSS",
                    "output/NativeMML"],
                extensions: ["tex2jax.js",
                            "mml2jax.js",
                            "MathMenu.js",
                            "MathZoom.js"],
                TeX: { 
                    extensions: ["AMSmath.js",
                                "AMSsymbols.js",
                                "noErrors.js",
                                "noUndefined.js"]
                }
            });
        };

        this.mathJaxDefaults = function () {
            var script = document.createElement('script');
            script.setAttribute("type", "text/javascript");
            script.src = "http://cdn.mathjax.org/mathjax/latest/MathJax.js";
            document.body.appendChild(script);
        };
    }

    return new customMathJaxSettings();
});
