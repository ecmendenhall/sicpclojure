function ColorScheme () {

    var reverse_scheme = {'Dark': 'Light', 'Light': 'Dark'};

    this.setText = function (element, text) {
        if (document.all) {
            element.innerText = text;
        } else {
            element.textContent = text;
        }
    };

    this.getText = function (element) {
        if (document.all) {
            return element.innerText;
        } else {
            return element.textContent;
        }
    };

    this.getColorPreference = function () {
        var current_color = localStorage.sicp_colorscheme;

        if (!current_color) {
            localStorage.sicp_colorscheme = 'Light';
            this.createSchemeAnchor('Dark');
        } else {
            this.createSchemeAnchor(reverse_scheme[current_color]);
        }

    };

    this.createSchemeAnchor = function (color) {
        var color_p = document.getElementById('colorscheme');
        var color_a = document.createElement('a');
    
        this.setText(color_a, color);
        color_a.id = "currentcolor";
        color_a.addEventListener('click', this.changeScheme.bind(this));
        color_p.appendChild(color_a);
        this.setScheme(color_a, reverse_scheme[color]);
    };

    this.setScheme = function (a, color) {
        var html = document.getElementsByTagName('html')[0];

        if (color === 'Dark') {
            this.setText(a, 'Light');
            html.setAttribute('class', 'dark');
            localStorage.sicp_colorscheme = 'Dark';
        }
        else if (color === 'Light') {
            this.setText(a, 'Dark');
            html.setAttribute('class', '');
            localStorage.sicp_colorscheme = 'Light';            
        }
    }; 

    this.changeScheme = function (click) {
        var a = document.getElementById('currentcolor');
        
        if (this.getText(a) === 'Dark') {
            this.setScheme(a, 'Dark');
        }
        else if (this.getText(a) === 'Light') {
            this.setScheme(a, 'Light');
        }
    };
}

function SourceToggler () {

    this.source_visible = false;

    this.set_text = function (element, text) {
        console.log(element);
        if (document.all) {
            element.innerText = text;
        } else {
            element.textContent = text;
        }
    };

    this.show_source = function (el) {
        localStorage.show_source = true;
    this.source_link = undefined;

        var source_links = document.getElementsByClassName('view-source');
        for (var i in source_links) {
            source_links.item(i).setAttribute('class', 'view-source');
        }

        this.source_visible = true;
        this.set_text(el, "- Edit");

    };

    this.hide_source = function (el) {
        localStorage.show_source = false;

        var source_links = document.getElementsByClassName('view-source');
        for (var i in source_links) {
            source_links.item(i).setAttribute('class', 'view-source hidden');
        }

        this.source_visible = false;
        this.set_text(el, "+ Edit")

    };
}

if (Modernizr.localstorage) {
    /* contentloaded.js wrapper by Diego Perini (diego.perini at gmail.com) */
    function contentLoaded(win, fn) {

        var done = false, top = true,

        doc = win.document, root = doc.documentElement,

        add = doc.addEventListener ? 'addEventListener' : 'attachEvent',
        rem = doc.addEventListener ? 'removeEventListener' : 'detachEvent',
        pre = doc.addEventListener ? '' : 'on',

        init = function(e) {
            if (e.type == 'readystatechange' && doc.readyState != 'complete') return;
            (e.type == 'load' ? win : doc)[rem](pre + e.type, init, false);
            if (!done && (done = true)) fn.call(win, e.type || e);
        },

        poll = function() {
            try { root.doScroll('left'); } catch(e) { setTimeout(poll, 50); return; }
            init('poll');
        };

        if (doc.readyState == 'complete') fn.call(win, 'lazy');
        else {
            if (doc.createEventObject && root.doScroll) {
                try { top = !win.frameElement; } catch(e) { }
                if (top) poll();
            }
            doc[add](pre + 'DOMContentLoaded', init, false);
            doc[add](pre + 'readystatechange', init, false);
            win[add](pre + 'load', init, false);
        }

    }

    colorscheme = new ColorScheme();
    sourcetoggler = new SourceToggler();
    
    contentLoaded(window, function () { 
        colorscheme.getColorPreference();
        var source_link = document.getElementById('view-source-link');
        if (localStorage.show_source === "true") {
            sourcetoggler.show_source(source_link);
        }
        source_link.removeAttribute('href');
        source_link.addEventListener('click', function () {
            if (sourcetoggler.source_visible) {
                sourcetoggler.hide_source(source_link);
            } else {
                sourcetoggler.show_source(source_link);
            }});
    });
}
