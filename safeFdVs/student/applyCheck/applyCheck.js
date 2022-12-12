$(document).ready(function () {
    exitApplyAjax();
    dailyApplyAjax();
})

//离校申请
function exitApplyAjax() {
    let stuId = window.localStorage.getItem("stuId");
    let url = "/exitapply/get/" + stuId;
    sendAjax(url, 'get', null, loadLeave);
}

function loadLeave(data) {
    setLeaveTitle();
    data.forEach((record, index) => {
        //具体内容
        let applyTime = getYMD(record.applyTime);
        let stage = switchCheckStage(record.checkStage);
        let ele = `<li>
            <ul class="insideUnit ">
                <li><h2 class="headLineContent">--【申请${index + 1}】--</h2></li>
                <li class="insideUnitItem">
                    <span class="insideUnitContentLabel">申请时间：</span>
                    <span class="insideUnitContentTrue">${applyTime}</span>
                </li>
                <li class="insideUnitItem">
                    <span class="insideUnitContentLabel">申请理由：</span>
                    <span class="insideUnitContentTrue">${record.reason}</span>
                </li>
                <li class="insideUnitItem">
                <span class="insideUnitContentLabel">目标地点：</span>
                <span class="insideUnitContentTrue">${record.dest}</span>
            </li>
                <li class="insideUnitItem">
                <span class="insideUnitContentLabel">离开时间：</span>
                <span class="insideUnitContentTrue">${record.leaveDate}</span>
            </li>
                <li class="insideUnitItem">
                <span class="insideUnitContentLabel">返回时间：</span>
                <span class="insideUnitContentTrue">${record.returnDate}</span>
            </li>
                <li class="insideUnitItem">
                    <span class="insideUnitContentLabel">审批阶段：</span>
                    <span class="insideUnitContentTrue">${stage}</span>
                </li>
                <li class="insideUnitItem">
                    <span class="insideUnitContentLabel">拒绝理由：</span>
                    <span class="insideUnitContentTrue">${record.denyComment !== 'null' ? '无' : record.denyComment}</span>
                </li>
            </ul>
        </li>`
        $('#exitApplyUnit').append(ele);
    })
}

function setLeaveTitle() {
    //设置标题
    let exitTitle =
        `<li class="insideTitle">
               <h1 class="insideTitleContent">--所有离校申请</h1>
               <select class"insideContentNumSelector">
                    <option value ="全部">Volvo</option>
                    <option value ="过去七天">Saab</option>
                    <option value="过去一月">Opel</option>
                    <option value="过去一年">Audi</option>
                </select>
                <select class"insideContentStateSelector">
                    <option value ="全部">Volvo</option>
                    <option value ="未通过">Saab</option>
                    <option value="待审批">Opel</option>
                    <option value="已通过">Audi</option>
                </select>
         </li>`;
    $('#exitApplyUnit').html(exitTitle);

}

function switchCheckStage(checkStage) {
    //认为checkStage是string
    let stage = parseInt(checkStage);
    switch (checkStage) {
        case(-2):
            return `被院系管理员拒绝`;
        case(-1):
            return `被班级管理员拒绝`;
        case(0):
            return `等待班级管理员审批`;
        case(1):
            return `等待院系管理员审批`;
        case(2):
            return `审批通过`;
    }
}

//健康日报
function dailyApplyAjax() {
    // /get/id/count
    let stuId = window.localStorage.getItem("stuId");
    let count = 5;
    let url = `/healthreport/get/${stuId}/${count}`;
    sendAjax(url, 'get', null, loadDaily);
}

function setDailyTile() {
    //设置标题
    let exitTitle = ` <li class="insideTitle"><h1 class="insideTitleContent">--所有健康日报</h1></li>`;
    $('#dailyReportUnit').html(exitTitle);
}

function loadDaily(data) {
    setDailyTile();
    data.forEach((record, index) => {
        //具体内容
        let applyTime = getYMD(record.applyTime);
        let stage = switchCheckStage(record.checkStage);
        let ele = `<li>
            <ul class="insideUnit ">
                <li><h2 class="headLineContent">--【填报${index + 1}】--</h2></li>
                <li class="insideUnitItem">
                    <span class="insideUnitContentLabel">填报时间：</span>
                    <span class="insideUnitContentTrue">${record.time}</span>
                </li>
                <li class="insideUnitItem">
                    <span class="insideUnitContentLabel">填报地点：</span>
                    <span class="insideUnitContentTrue">${record.location}</span>
                </li>
                <li class="insideUnitItem">
                <span class="insideUnitContentLabel">填报状态：</span>
                <span class="insideUnitContentTrue">${record.state}</span>
            </li>
                <li class="insideUnitItem">
                <span class="insideUnitContentLabel">身体温度：</span>
                <span class="insideUnitContentTrue">${record.temperature}</span>
            </li>
            </ul>
        </li>`
        $('#dailyReportUnit').append(ele);
    })
}

//进校申请
function entryApplyAjax() {
    // /get/id/count
    let stuId = window.localStorage.getItem("stuId");
    let count = 5;
    //全部 通过 未通过
    let url = `/healthreport/get/${stuId}/${count}`;
}