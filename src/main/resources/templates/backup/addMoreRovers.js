$(document).ready(function () {
    const maxUnits = document.getElementById('plateauX') * document.getElementById('plateauY');
    const addButton = $('.add_button');
    const wrapper = $('.field_wrapper');

    let fieldHTML = '<div><input type="number" name="rover[x]" value="" placeholder="0"/><a href="javascript:void(0);" class="remove_button"></a></div>'
    let a = 1;

    //add button
    $(addButton).click(function() {
        if(a < maxUnits){
            a++;
            $(wrapper).append(fieldHTML);
        }
    });

    //remove button
    $(wrapper).on('click', '.remove_button', function(e) {
        e.preventDefault();
        $(this).parent('div').remove();
        a--;
    });
})

