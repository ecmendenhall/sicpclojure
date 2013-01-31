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
    
    contentLoaded(window, function () { 
        colorscheme.getColorPreference();
    });
}
