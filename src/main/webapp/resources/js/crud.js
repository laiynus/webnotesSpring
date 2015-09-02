$(document).ready(function () {
    $('#addNote').click(function addNote() {
        var note = $('#noteText').val();
        var json = {"note": note};
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
                getLastNotes();
                $("#editNote").prop('disabled', true);
                $("#deleteNote").prop('disabled', true);
                $("#noteText").val('');
                $("#selectedNote").removeAttr("name");
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("error:" + textStatus + " - exception:" + errorThrown);
            }
        })
    })

    $('#editNote').click(function editNote(){
        var id = $("#selectedNote").attr('name');
        var note = $("#noteText").val();
        var json = {"id": id, "note": note};
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
                getLastNotes();
                $("#editNote").prop('disabled', true);
                $("#deleteNote").prop('disabled', true);
                $("#noteText").val('');
                $("#selectedNote").removeAttr("name");
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("error:" + textStatus + " - exception:" + errorThrown);
            }
        })
    })

    $('#deleteNote').click(function deleteNote(){
        var id = $("#selectedNote").attr('name');
        var json = {"id": id};
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
                getLastNotes();
                $("#editNote").prop('disabled', true);
                $("#deleteNote").prop('disabled', true);
                $("#noteText").val('');
                $("#selectedNote").removeAttr("name");
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert("error:" + textStatus + " - exception:" + errorThrown);
            }
        })
    })

});

$(document).ready(getLastNotes);

function getLastNotes() {
    $.ajax({
        url: "notes/getLastNotes",
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
            $("#editNote").prop('disabled', false);
            $("#deleteNote").prop('disabled', false);
            $("#selectedNote").attr("name",response.id);
            $("#noteText").val(response.note);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("error:" + textStatus + " - exception:" + errorThrown);
        }
    })
};






