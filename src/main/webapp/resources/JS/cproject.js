/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
data = "";
$(document).ready(function () {

    $('#btn').click(function () {

        var data = {};
        data["project_id"] = $("#project_id").val();
        data["project_Name"] = $('#project_Name').val();
        data["project_desc"] = $("#project_desc").val();
        data["pstart_Date"] = $('#pstart_Date').val();
        data["pEnd_Date"] = $("#pEnd_Date").val();
//        alert(JSON.stringify(data));
        $.ajax({
            url: "cProjectAjax",
            data: JSON.stringify(data),
            type: "POST",
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function (response) {
                alert(response.msg);
                refresh();
                list3();
            },

        });

    });
});
refresh = function () {
    $("#project_id").val("");
    $('#project_Name').val("");
    $('#project_desc').val("");
    $('#pstart_Date').val("");
    $('#pEnd_Date').val("");
};

edit = function (index) {
    $("#project_id").val(data[index].project_id);
    $('#project_Name').val(data[index].project_Name);
    $('#project_desc').val(data[index].project_desc);
    $('#pstart_Date').val(formatDate(data[index].pstart_Date));
    $('#pEnd_Date').val(formatDate(data[index].pEnd_Date));
};

function formatDate(inputStr) {
    var currentTime = new Date(parseInt(inputStr));
    var month = currentTime.getMonth() + 1;

    if (month < 10)
    {
        month = "0" + month;
    }
    var day = currentTime.getDate();
    if (day < 10)
    {
        day = "0" + day;
    }
    var year = currentTime.getFullYear();
    var date2 = year + "-" + month + "-" + day;
    return date2;

}


list3 = function () {
    $.ajax({
        url: "listProjectAjax",
        type: 'GET',
        success: function (response) {
            data = response.data;
//            console.log(response.data[i].pstart_Date);
            $('.tr').remove();
            for (i = 0; i < response.data.length; i++) {
                formatDate(data[i].pstart_Date);
//                $("#table1").append("<tr class='tr'> <td> " + response.data[i].project_Name + " </td>  <td> <a href='#' onclick= edit(" + i + ");> Edit </a>  </td> </td> <td> <a href='#' onclick='delete_(" + response.data[i].project_id + ");'> Delete </a>  </td> </tr>");
                $("#table1").append("<tr class='tr'> <td><a href='showtask?project_id=" + response.data[i].project_id + "'>" + response.data[i].project_Name + "</a> </td>  <td> <a href='#' onclick= edit(" + i + ");> Edit </a>  </td> </td> <td> <a href='#' onclick='delete_(" + response.data[i].project_id + ");'> Delete </a>  </td> </tr>");

            }

        }
    });
};
delete_ = function (id) {
    var con = confirm("Do you want to delete this project");

    if (con === true) {
        $.ajax({
            url: 'delete2Project',
            type: 'POST',
            data: {project_id: id},
            success: function (response) {
                alert(response.msg);
                list3();
            }
        });
    } else {
        return false;
    }
};
function f1(pid) {
    var returned = true;

    var r = confirm("Are you sure want to delete");
    if (r === true) {
        var url = "getProjecttask?pid=" + pid;


        $.ajax({
            url: url,
            async: false,
            success: function (result) {

                console.log("SUCCESS: ", result);

                if (result > 0) {

                    alert("You can't delete Project");
                    returned = false;
                    return false;
                    f3(returned)

                } else
                {
                    alert("Project deleted successfully");

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



