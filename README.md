# datebase
## 改动
    1）在student表中添加了dept_id字段
    2）HealthReport中的填报时间改为了年月日时分秒
    2）在StudentDto中添加了Timestamp outTime（最近一次的离校时间）
                          long entryReplyNum(总共的入校申请数量）
## 接口文档

### 查询学生过去n天的每日填报信息
    /healthreport/get/{id}/{count} GET
    id:学生id,
    count：过去count天
    返回值：
        List<HealthReport>

### 查询学生的入校权限
    /student/admission/{id}  GET
    id:学生id
    返回值：
        int

### 查询学生的入校申请、出校申请，支持按状态（待审核、已同意、已拒绝）进行筛选；
    入校申请：
        /entryreply/get/{id} GET   ：获取所有
        /entryreply/get/{id}/{count} GET   :获取前count天
        /getaccept/{id} GET     ：获取同意的
        /getrefuse/{id} GET    ：获取拒绝的
        /getundisposed/{id} GET ：获取未处理的

    出校申请：
        /exitapply/entryreply/get/{id} GET   ：获取所有
        /exitapply/entryreply/get/{id}/{count} GET   :获取前count天
        /exitapply/getaccept/{id} GET    ：获取同意的
        /exitapply/getrefuse/{id} GET   ：获取拒绝的
        /exitapply/getundisposed/{id} GET ：获取未处理的

###  前 n 个平均离校时间最长的学生，支持按多级范围（全校、院系、班级）进行筛选；
    /student/timeOfOutMaxInSchool/{count} GET
    /student/timeOfOutMaxInDept/{deptId}/{count} GET
    /student/timeOfOutMaxInDept/{classId}/{count} GET

    返回值：
        List<Student>
    如果需要显示离校时间，可以再进行修改

### 已出校但尚未返回校园（即离校状态）的学生数量、个人信息及各自的离校时间

    /student/out GET
    返回值：
        List<StudentDto>, 其中outTime就代表离校时间

### 未提交出校申请但离校状态超过 24h 的学生数量、个人信息
    /student/out_no_entryapply GET

    返回值：
        List<StudentDto> ,其中outTime为null

### 已提交出校申请但未离校的学生数量、个人信息
    /student/in_having_exitapply GET

    返回值：
        List<StudentDto> ,其中outTime为null

### 过去 n 天一直在校未曾出校的学生，支持按多级范围（全校、院系、班级）进行筛选
    /student/in_long/school/{num} GET   :全校
    /student/in_long/dept/{deptId}/{num} GET       ：院系
    /student/in_long/class/{classId}/{num} GET      ：班级

    返回值：
        List<StudentDto> 

### 连续 n 天填写“健康日报”时间（精确到分钟）完全一致的学生数量，个人信息
    /healthreport//same/{count} GET ：过去count天
    返回值：
        List<StudentDto>

### 过去 n 天每个院系学生产生最多出入校记录的校区
    /campus//max/{id}/{num}  GET
    id：院系id

    返回值：
        Pair<String,Long> :院系名称，出入记录数