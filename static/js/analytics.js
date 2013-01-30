define(["http://www.google-analytics.com/ga.js"], function () {

  function inject(fn) {
            var script = document.createElement('script');
            script.setAttribute("type", "text/javascript");
            script.textContent = '(' + fn + ')();';
            document.body.appendChild(script);
        };

  function analyticsScript () {  
      var _gaq = _gaq || [];
      _gaq.push(['_setAccount', 'UA-38081658-1']);
      _gaq.push(['_trackPageview']);

      (function() {
        var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
        ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
        var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
      })();
  }

  inject(analyticsScript);

});
