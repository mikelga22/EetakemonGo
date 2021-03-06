function getUrlParameter(name) {
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
    var results = regex.exec(location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
};



$(document).ready( function () {
    checkLoged();

    var urlParams = new URLSearchParams(window.location.search);
    var page = getUrlParameter('page');
    var idE = getUrlParameter('idE');
    console.log(page);
    if (page == "1") {
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: ctxPath + "Eetakemon/" + idE,
            headers: {"Authorization": "Bearer " + sessionStorage.getItem("Token")},
            statusCode: {
                200: function (result) {
                    $("#image-div").append(
                        "<img id=\"imagen\" src = \" /images/" + result.nombre.toLowerCase() + ".png\" style=\"width:20%;height:20%;\" '>"
                    );

                    $("#tabla-info").append("<tr class=\"eetakemon\">" +
                        "<td>" + result.nombre + "</td>" +
                        "<td>" + result.tipo + "</td>" +
                        "<td>" + result.nivel + "</td>" +
                        "</tr>");
                },
                204: function () {
                    alert("No se han encontrado eetakemons");
                },
                401: function () {
                    alert("No autorizado");
                    sessionStorage.clear();
                    window.location.replace("../index.html");
                }
            }
        });
    }
    else if (page=="2")
    {
        $.ajax({
            type: 'GET',
            contentType: 'application/json',
            url: ctxPath + "Relation/" + idE,
            headers: {"Authorization": "Bearer " + sessionStorage.getItem("Token")},
            statusCode: {
                200: function (result) {
                    $("#image-div").append(
                        "<img id=\"imagen\" src = \" /images/" + result.name.toLowerCase() + ".png\" style=\"width:20%;height:20%;\" '>"
                    );
                    $("#type").remove();
                    $("#tabla-info").append("<tr class=\"eetakemon\">" +
                        "<td>" + result.name + "</td>" +
                        "<td>" + result.level + "</td>" +
                        "</tr>");
                },
                204: function () {
                    alert("No se han encontrado eetakemons");
                },
                401: function () {
                    alert("No autorizado");
                    sessionStorage.clear();
                    window.location.replace("../index.html");
                }
            }
        });
    }
});