$(document).ready(function () {
    //平安复旦数据填充
    $('.dailySid').text(window.localStorage.getItem('stuId'));
    $('.dailyName').text(window.localStorage.getItem('name'))
    let today = new Date();
    let dailyTime = `${today.getFullYear()}年${today.getMonth() + 1}月${today.getDate()}日`;
    $('.dailyTime').text(dailyTime);

    //离开日期的限制
    let myDate = new Date;
    let year = myDate.getFullYear(); //获取当前年
    let mon = myDate.getMonth() + 1 < 10 ? "0" + (myDate.getMonth() + 1) : (myDate.getMonth() + 1);//
    let dat = myDate.getDate() < 10 ? "0" + myDate.getDate() : myDate.getDate();
    $(".leaveDate").attr("min", year + "-" + mon + "-" + dat);
    $(".returnDate").attr("min", year + "-" + mon + "-" + dat);
    $(".entryDate").attr("min", year + "-" + mon + "-" + dat);

    //Module: 平安复旦的提交
    $('#dailyBtn').click(() => {
        let sendData = {
            stuId: window.localStorage.getItem('stuId'),
            location: $('.dailyLocation').val(),
            temperature: parseInt($('.dailyTemperature').val()),
            state: parseInt($("input[name='dailyState']:checked").val()),
        }
        if (hasEmpty(sendData)) {
            alert("表单项不能为空");
            return;
        }
        sendAjaxAlert("/healthreport/insert",'post',sendData, '成功提交平安复旦')

    })

    //Module: 离校申请的提交
    $('#leaveBtn').click(() => {

        let sendData = {
            stuId: window.localStorage.getItem('stuId'),
            reason: $('#leaveReason').val(),
            dest: $('.leaveDest').val(),
            leaveDate: $('.leaveDate').val(),
            returnDate: $('.returnDate').val()
        }

        if (hasEmpty(sendData)) {
            alert("表单项不能为空");
            return;
        }
        sendAjaxAlert("/exitapply/insert","post",sendData,'成功提交离校申请');

    })

    //Module:入校申请的提交
    $('#entryBtn').click(() => {

        let sendData = {
            stuId: window.localStorage.getItem('stuId'),
            reason: $('#entryReason').val(),
            arriveDate: $('#entryDate').val()
        }
        if (hasEmpty(sendData)) {
            alert("表单项不能为空");
            return;
        }
        sendAjaxAlert("/entryreply/insert","post",sendData,'成功提交入校申请');
    })


});


