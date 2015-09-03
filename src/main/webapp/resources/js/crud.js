$(document).ready(function () {
    $('#addNote').click(function addNote() {
        var note = $('#noteText').val();
        var json = {"note": note};
        $("#errorBox").remove();
        $("#messageBox").remove();
        if(!note.trim()){
            $( "<div id='errorBox' class='alert alert-danger fade in'>" +
                "<button type='button' class='close' data-dismiss='alert'>&times;</button>Note can't be empty!</div>")
                .insertBefore( "#addNote");
            $("#noteText").val('');
        }else{
            $.ajax({
                url: "notes/addNote",
                type: 'POST',
                data: JSON.stringify(json),
                contentType: 'application/json; charset=utf-8',
                mimeType: 'application/json; charset=utf-8',
                beforeSend: function (xhr) {
                    var token = $("meta[name='_csrf']").attr("content");
                    var header = $("meta[name='_csrf_header']").attr("content");
                    $(document).ajaxSend(function (e, xhr, options) {
                        xhr.setRequestHeader(header, token);
                    });
                },
                success: function (response) {
                    if(response!="Success"){
                        $( "<div id='errorBox' class='alert alert-danger fade in'>" +
                            "<button type='button' class='close' data-dismiss='alert'>&times;</button>" + response + "</div>")
                            .insertBefore( "#addNote");
                        $("#noteText").val('');
                    }else{
                        $( "<div id='messageBox' class='alert alert-success fade in'>" +
                            "<button type='button' class='close' data-dismiss='alert'>&times;</button>Note successfully added</div>")
                            .insertBefore( "#addNote");
                        getLastNotes();
                        $("#editNote").prop('disabled', true);
                        $("#deleteNote").prop('disabled', true);
                        $("#noteText").val('');
                        $("#selectedNote").removeAttr("name");
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    alert("error:" + textStatus + " - exception:" + errorThrown);
                }
            })
        }
    })

    $('#editNote').click(function editNote(){
        var id = $("#selectedNote").attr('name');
        var note = $("#noteText").val();
        var json = {"id": id, "note": note};
        $("#errorBox").remove();
        $("#messageBox").remove();
        if(!note.trim()){
            $( "<div id='errorBox' class='alert alert-danger fade in'>" +
                "<button type='button' class='close' data-dismiss='alert'>&times;</button>Note can't be empty!</div>")
                .insertBefore( "#addNote");
            $("#noteText").val('');
        }else {
            $.ajax({
                url: "notes/editNote",
                type: 'PUT',
                data: JSON.stringify(json),
                contentType: 'application/json; charset=utf-8',
                mimeType: 'application/json; charset=utf-8',
                beforeSend: function (xhr) {
                    var token = $("meta[name='_csrf']").attr("content");
                    var header = $("meta[name='_csrf_header']").attr("content");
                    $(document).ajaxSend(function (e, xhr, options) {
                        xhr.setRequestHeader(header, token);
                    });
                },
                success: function (response) {
                    if(response!="Success"){
                        $( "<div id='errorBox' class='alert alert-danger fade in'>" +
                            "<button type='button' class='close' data-dismiss='alert'>&times;</button>" + response + "</div>")
                            .insertBefore( "#addNote");
                        $("#noteText").val('');
                        getLastNotes();
                        $("#editNote").prop('disabled', true);
                        $("#deleteNote").prop('disabled', true);
                        $("#selectedNote").removeAttr("name");
                    }else{
                        $( "<div id='messageBox' class='alert alert-success fade in'>" +
                            "<button type='button' class='close' data-dismiss='alert'>&times;</button>Note successfully updated</div>")
                            .insertBefore( "#addNote");
                        getLastNotes();
                        $("#editNote").prop('disabled', true);
                        $("#deleteNote").prop('disabled', true);
                        $("#noteText").val('');
                        $("#selectedNote").removeAttr("name");
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert("error:" + textStatus + " - exception:" + errorThrown);
                }
            })
        }
    })

    $('#deleteNote').click(function deleteNote(){
        var id = $("#selectedNote").attr('name');
        var json = {"id": id};
        $("#errorBox").remove();
        $("#messageBox").remove();
        $.ajax({
            url: "notes/deleteNote",
            type: 'DELETE',
            data: JSON.stringify(json),
            contentType: 'application/json; charset=utf-8',
            mimeType: 'application/json; charset=utf-8',
            beforeSend: function (xhr) {
                var token = $("meta[name='_csrf']").attr("content");
                var header = $("meta[name='_csrf_header']").attr("content");
                $(document).ajaxSend(function (e, xhr, options) {
                    xhr.setRequestHeader(header, token);
                });
            },
            success: function (response) {
                if(response!="Success"){
                    $( "<div id='errorBox' class='alert alert-danger fade in'>" +
                        "<button type='button' class='close' data-dismiss='alert'>&times;</button>" + response + "</div>")
                        .insertBefore( "#addNote");
                    $("#noteText").val('');
                    getLastNotes();
                    $("#editNote").prop('disabled', true);
                    $("#deleteNote").prop('disabled', true);
                    $("#selectedNote").removeAttr("name");
                }else{
                    $( "<div id='messageBox' class='alert alert-success fade in'>" +
                        "<button type='button' class='close' data-dismiss='alert'>&times;</button>Note successfully deleted</div>")
                        .insertBefore( "#addNote");
                    getLastNotes();
                    $("#editNote").prop('disabled', true);
                    $("#deleteNote").prop('disabled', true);
                    $("#noteText").val('');
                    $("#selectedNote").removeAttr("name");
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("error:" + textStatus + " - exception:" + errorThrown);
            }
        })
    })

    $("#switcherNote").click(function switcherNote(){
        $("#errorBox").remove();
        $("#messageBox").remove();
        if($("#switcher").val()==="last"){
            $("#switcher").val("all");
            getLastNotes();
            $("#switcherNote").html('Show last notes');
        }else{
            $("#switcher").val("last");
            getLastNotes();
            $("#switcherNote").html('Show all notes');
        }
    })

});

$(document).ready(getLastNotes);

function getLastNotes() {
    var url;
    if($("#switcher").val()==="last") {
         url =  "notes/getLastNotes";
    }else{
         url =  "notes/getAllNotes";
    }
    $.ajax({
        url: url,
        type: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        mimeType: 'application/json',
        beforeSend: function (xhr) {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $(document).ajaxSend(function (e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });
        },
        success: function (response) {
            $("#noteTableBody").empty();
            var table = $("#noteTable tbody");
            $.each(response, function (idx, elem) {
                var date = new Date(elem.dateTimeCreate);
                table.append("<tr><td style='display:none'>" + elem.id + "</td><td>" + elem.note + "</td><td>" + date + "<td><input type='button' value='Select note' onclick='selectNote(this)' class='btn btn-warning'/>" +  "</td></tr>");
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("error:" + textStatus + " - exception:" + errorThrown);
        }
    })
};

function selectNote(tmp){
    var id = +($(tmp).parents('tr:first').find('td:first').text());
    var note = {"id": id};
    $("#errorBox").remove();
    $("#messageBox").remove();
    $.ajax({
        url: "notes/getNote",
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(note),
        contentType: 'application/json; charset=utf-8',
        mimeType: 'application/json; charset=utf-8',
        beforeSend: function (xhr) {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $(document).ajaxSend(function (e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });
        },
        success: function (response) {
            if(response===null){
                $( "<div id='errorBox' class='alert alert-danger fade in'>" +
                    "<button type='button' class='close' data-dismiss='alert'>&times;</button>This note is not found!</div>")
                    .insertBefore( "#addNote");
                getLastNotes();
            }else{
                $("#editNote").prop('disabled', false);
                $("#deleteNote").prop('disabled', false);
                $("#selectedNote").attr("name",response.id);
                $("#noteText").val(response.note);
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("error:" + textStatus + " - exception:" + errorThrown);
        }
    })
};








