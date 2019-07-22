/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
data = '';
$(document).ready(function () {
    load(1);
});
load = function (page) {
    var url2 = "listEmp?page=" + page;
    $.ajax({
//        url: 'listEmp',
        url: url2,
        type: 'GET',
        success: function (response) {
            data = response.list;
            //            alert(data);
            $('.tr').remove();
            for (i = 0; i < response.list.length; i++) {
                $("#tbl").append("<tr class='tr'><td>" + response.list[i].name + "</td><td>" + response.list[i].email + "</td><td>" + response.list[i].address + "</td><td>" + response.list[i].telephone + "</td><td>" + response.list[i].category + "</td><td><a href='editEmployeeAjax?id=" + response.list[i].id + "' >Edit</a>&nbsp&nbsp<a href='#' onclick='deleteEmp(" + response.list[i].id + ",`" + response.list[i].email + "`);'>Delete</a></td>");
            }
//            console.log(response.pno);
            pagenumber = "";
            for (i = 0; i < response.pno; i++) {
                j = i + 1;
//                pagenumber += "&nbsp" + j;
                pagenumber += "<a href='#' onclick=load(" + j + ");>" + j + "</a>" + "&nbsp";
            }
            $("#n").html(pagenumber);
        }
    }
    );
};
deleteEmp = function (id, email) {
    var con = confirm("Do you want to delete this Employee");
    if (con === true) {
        $.ajax({
            url: 'deleteEmployeeAjax',
            type: 'POST',
            data: JSON.stringify({id: id, email: email}),
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function (data) {
                alert(data.msg);
                load(1);
            }
        });
    } else {
        return false;
    }
};
function chk() {
    var x = document.forms["skillform"]["skillSearch"].value;
    if (x == "" || x.length < 3) {
        alert("Skill is empty or Atleast require 3 character to find the result ");
        return false;
    }
}

$(document).ready(function () {
    $('cc').click(function () {

        console.log("Hello world! anil");
        /* alert("Skill is empty or Atleast require 3 character to find the result ");  */
        $.ajax({
            url: 'success1',
            success: function (result) {
                console.log("SUCCESS: ", result);
                console.log("Hello world! anil");
            }
        });
    });
});
function f1(mail) {
    var returned = true;
    var r = confirm("Are you sure want to delete");
    if (r == true) {
        var url = "success1?mail=" + mail;
        $.ajax({
            url: url,
            async: false,
            success: function (result) {

                console.log("SUCCESS: ", result);
                if (result > 0) {

                    alert("You can't delete employee");
                    returned = false;
                    return false;
                    f3(returned)

                } else
                {
                    alert("Employee deleted successfully");
                }

            }
        });
    } else
    {
        return false;
    }

    if (returned)
    {

    } else
    {

        return false;
    }

}
function f3(flag)
{
    if (flag)
    {

    } else
    {
        console.log("anil flase");
        alert('flag false');
        return false;
    }

}

