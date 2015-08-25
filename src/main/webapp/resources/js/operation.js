function addNote(){

    var note = $('#noteText').val();
    var json = {"note" : note};
    $.ajax({
        url: "addNote.web",
        type: 'POST',
        data: JSON.stringify(json),
        cache:false,
        beforeSend: function(xhr) {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $(document).ajaxSend(function(e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
        },
        success:function(response){

        },
        error:function(jqXhr, textStatus, errorThrown){
            alert(textStatus);
        }
    })

};
