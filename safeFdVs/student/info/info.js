window.addEventListener("load", () => {

    infoLoader();

    $('#updateBtn').on('click', () => {
        let sendData = JSON.parse(JSON.stringify(window.localStorage));
        sendData['phone'] = $('.phone').val();
        sendData['email'] = $('.email').val();
        sendData['dormitory'] = $('.dormitory').val();
        sendData['homeAddr'] = $('.address').val();
        if (hasEmpty(sendData)) {
            alert("更新信息不能为空");
            return;
        }

        $.ajax({
            url: serverIp +"/student/updateinfo",
            type: 'post',
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify(sendData),
            success: function (data) {
                if (data.code === "404") {
                    alert("更新信息失败");
                } else if (data.code === "200") {
                    //保存用户信息
                    console.log(data);
                    for (let i in sendData) {
                        window.localStorage[i] = sendData[i];
                    }
                    alert("更新信息成功")
                    location.reload();
                }
            },
            error: function () {
                alert('Internet Error');
            }
        })
    })


})

function infoLoader() {
    $('.stuName').text(window.localStorage.getItem("name"));
    $('.className').text(window.localStorage.getItem("className"));
    $('.deptName').text(window.localStorage.getItem("deptName"));
    $('.phone').val(window.localStorage.getItem("phone"));
    $('.email').val(window.localStorage.getItem("email"));
    $('.dormitory').val(window.localStorage.getItem("building"));
    $('.campus').text(window.localStorage.getItem("campName"));
    $('.address').val(window.localStorage.getItem("homeAddr"));

    $('.idCardType').text(window.localStorage.getItem("idCardType"));
    $('.idCardNum').text(window.localStorage.getItem("idCardNum"));

    //入校权限
    let level = parseInt(window.localStorage.getItem("admissionLevel"));
    let admissionString = "";
    if (level >= 16 || level <= -1) {
        admissionString = "进校权限位出错！"
    } else {
        console.log(window.localStorage);
        (level & 8) === 8 ? admissionString += '邯郸 ' : "";
        (level & 4) === 4 ? admissionString += '江湾 ' : "";
        (level & 2) === 2 ? admissionString += '枫林 ' : "";
        (level & 1) === 1 ? admissionString += '张江 ' : "";
    }
    $('.admissionLevel').text(admissionString);


    //状态
    let state = window.localStorage.getItem("inOutState");
    let inOutState = $('.inOutState');
    switch (parseInt(state)) {
        case(0):
            inOutState.text("校内");
            break;
        case(1):
            inOutState.text("校外");
            break;
        default:
            inOutState.text("未知状态");
    }

}

