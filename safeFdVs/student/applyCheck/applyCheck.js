$(document).ready(function () {
    exitApplyAjax('全部');
    dailyApplyAjax(9999);
    entryApplyAjax('全部');
    $('#exitType').change(()=>{
        exitApplyAjax($('#exitType').val());
    });
    $('#dailyType').change(()=>{
        dailyApplyAjax($('#dailyType').val());
    });
    $('#entryType').change(()=>{
        entryApplyAjax($('#entryType').val());
    });
})

//离校申请
function exitApplyAjax(type) {
    let stuId = window.localStorage.getItem("stuId");
    let url="";
    switch (type) {
        case'全部':url=`/exitapply/get/${stuId}`;break;
        case'已通过':url=`/exitapply/getaccept/${stuId}`;break;
        case'未通过':url=`/exitapply/getrefuse/${stuId}`;break;
        case'待审批':url = `/exitapply/getundisposed/${stuId}`;break;
        default:alert('错误的exitApplyAjax type');
    }
    sendAjax(url, 'get', null, loadLeave);
}

function loadLeave(data) {
    $('.insideUnit').remove(".exitUnit");
    data.forEach((record, index) => {
        //具体内容
        let applyTime = getYMD(record.applyTime);
        let stage = switchCheckStage(record.checkStage);
        let ele = `<li>
            <ul class="insideUnit exitUnit">
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
function dailyApplyAjax(num) {
    let stuId = window.localStorage.getItem("stuId");
    let url = `/healthreport/get/${stuId}/${num}`;
    sendAjax(url, 'get', null, loadDaily);
}

function loadDaily(data) {
    $('.insideUnit').remove(".dailyUnit");
    data.forEach((record, index) => {
        //具体内容
        let applyTime = getYMD(record.applyTime);
        let stage = switchCheckStage(record.checkStage);
        let ele = `<li>
            <ul class="insideUnit dailyUnit">
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
function entryApplyAjax(type) {
    let stuId = window.localStorage.getItem("stuId");
    let url="";
    switch (type) {
        case'全部':url=`/entryreply/get/${stuId}`;break;
        case'已通过':url=`/entryreply/getaccept/${stuId}`;break;
        case'未通过':url=`/entryreply/getrefuse/${stuId}`;break;
        case'待审批':url = `/entryreply/getundisposed/${stuId}`;break;
        default:alert('错误的entryApplyAjax type');
    }
    sendAjax(url, 'get', null, loadEntry);
}
function loadEntry(data){
    $('.insideUnit').remove(".entryUnit");
    data.forEach((record, index) => {
        //具体内容
        let applyTime = getYMD(record.applyTime);
        let stage = switchCheckStage(record.checkStage);
        let ele = `<li>
            <ul class="insideUnit entryUnit">
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
                <span class="insideUnitContentLabel">返校时间：</span>
                <span class="insideUnitContentTrue">${record.arriveDate}</span>
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
        $('#entryApplyUnit').append(ele);
    })
}
