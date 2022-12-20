//global variable
// const serverIp = "192.168.50.252:8080/";
const serverIp = "http://127.0.0.1:8080";

//检查发送的数据是否含有空
function hasEmpty(sendData){
    for(let i in sendData){
        if(sendData[i] ==="") return true;
    }
    return false;
}
//将java时间转换
function getYMD(javaDate){
    let date = new Date(javaDate);
    let ymd = `${date.getFullYear()}\-${date.getMonth()+1}\-${date.getDate()}`;
    return ymd;
}

//精简版ajax
function sendAjax(url,type,sendData,sucFunction){
    $.ajax({
        url: serverIp+url,
        type: type,
        dataType: 'json',
        contentType: 'application/json; charset=UTF-8',
        data: JSON.stringify(sendData),
        success: function (data) {
            if (data.code === '200') {
                sucFunction(data.data);
            }
            else if(data.code === '600'){
                alert(data.msg);
            }else{
                alert("其它错误");
            }
        },
        error: function () {
            alert('Internet Error');
        }
    })
}
//精简版ajax，但是成功函数sucFunction固定为Alert
function sendAjaxAlert(url,type,sendData,alertMsg){
    $.ajax({
        url: serverIp+url,
        type: type,
        dataType: 'json',
        contentType: 'application/json; charset=UTF-8',
        data: JSON.stringify(sendData),
        success: function (data) {
            if (data.code === '200') {
                alert(alertMsg);
            }
            else if(data.code === '600'){
                alert(data.msg);
            }else{
                alert("其它错误");
            }
        },
        error: function () {
            alert('Internet Error');
        }
    })
}
