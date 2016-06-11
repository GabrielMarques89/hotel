/**
 * Created by Luiz Guilherme on 11/06/2016.
 */

var Hotel = {};

Hotel.DropdownlistUsuario = function(){
    var $dropdown = $('#menu-usuario.dropdown');
    var $dropdown_menu = $dropdown.find('ul.dropdown-menu');
    $dropdown_menu.css({'width' : $dropdown.width() });
};
