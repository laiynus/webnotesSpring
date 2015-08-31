$(document).ready(function () {

    $('#addNote').click(function addNote() {
        var note = $('#noteText').val();
        var json = {"note": note};
        $.ajax({
            url: "addNote",
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
                $("#noteText").val('');
                getLastNotes();
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
        url: "getLastNotes",
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
                table.append("<tr><td>" + elem.note + "</td><td>" + date + "</td></tr>");
            });
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert("error:" + textStatus + " - exception:" + errorThrown);
        }
    })
};

