define(function () {
    
    function ColorScheme () {

        var reverse_scheme = {'Dark': 'Light', 'Light': 'Dark'};
    
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
        
            color_a.innerText = color;
            color_a.id = "currentcolor";
            color_a.addEventListener('click', this.changeScheme.bind(this));
            color_p.appendChild(color_a);
            this.setScheme(color_a, reverse_scheme[color])
        };

        this.setScheme = function (a, color) {
            var html = document.getElementsByTagName('html')[0];
            var code_css = document.getElementsByTagName('link')[1];
            var css_name = code_css.href;
            var css_base = css_name.slice(0, css_name.search('_'));

            if (color === 'Dark') {
                a.innerText = 'Light';
                html.setAttribute('class', 'dark');
                code_css.href = css_base + '_dark.css';
                localStorage.sicp_colorscheme = 'Dark';
            }
            else if (color === 'Light') {
                a.innerText = 'Dark';
                html.setAttribute('class', '');
                code_css.href = css_base + '_light.css';
                localStorage.sicp_colorscheme = 'Light';            
            }
        }; 

        this.changeScheme = function (click) {
            var a = document.getElementById('currentcolor');
            
            if (a.innerText === 'Dark') {
                this.setScheme(a, 'Dark');
            }
            else if (a.innerText === 'Light') {
                this.setScheme(a, 'Light');
            }
        };
    }

return new ColorScheme();

});
