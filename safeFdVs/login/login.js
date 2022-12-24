const stu_log = document.querySelector('#stu_log');
const cls_log = document.querySelector('#cls_log');
const dept_log = document.querySelector('#dept_log')
const sup_log = document.querySelector('#sup_log')

const stuButton = document.querySelector('#stuButton');
const clsButton = document.querySelector('#clsButton');
const deptButton = document.querySelector('#deptButton');
const supButton = document.querySelector('#supButton');


stuButton.addEventListener('click',()=>{
    let s_id = stu_log.value;
    if (!(s_id>=10000000000 && s_id<=99999999999)){
        alert("invalid s_id")
    }else{
        let sendData = {id:s_id};
        $.ajax({
            url: serverIp+"/student/login",
            type: 'post',
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify(sendData),
            success: function (data) {
                if (data.code === "404") {
                    alert("No such student");
                } else if (data.code === "200") {
                    //保存用户信息
                    for(let i in data.data){
                       window.localStorage.setItem(i,data.data[i]);
                    }
                    window.location.href ="../student/info/info.html";
                  }else{
                    alert(data.msg)
                }

            },
            error: function () {
                alert('Internet Error');
            }
        })
    }
})
clsButton.addEventListener('click',()=>{
    //verifyWid
    console.log(cls_log.value);
})
deptButton.addEventListener('click',()=>{
    console.log(dept_log.value);
})
supButton.addEventListener('click',()=>{
    console.log(sup_log.value);
})

function verifyWid(w_id){
    if(w_id >= 10000000 && w_id <= 99999999) return true;
    else return false;
}