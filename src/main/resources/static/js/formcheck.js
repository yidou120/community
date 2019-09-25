function check() {
    var ifSubmit = checkForm();
    if(ifSubmit){
        return true;
    }
    alert("标题或描述不能为空");
    return false;
}
function checkForm() {
        var flag = true;
        var titleFlag = true;
        var valueTitle = document.getElementById("title").value;
        if(valueTitle==null || valueTitle == undefined || valueTitle == ""){
            titleFlag = false;
        }
        var valueDesc = document.getElementById("description").value;
        var descFlag = true;
        if(valueDesc==null || valueDesc == undefined || valueDesc == ""){
            descFlag = false;
        }
        return flag = titleFlag && descFlag;
}