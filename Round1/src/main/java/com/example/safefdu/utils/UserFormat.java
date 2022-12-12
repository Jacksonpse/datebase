package com.example.safefdu.utils;



import java.text.SimpleDateFormat;
import java.util.*;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserFormat {
    // role只会是teacher或student两个值
    private static String role;
    // 空可以过
    // Mailbox要求： * @ * .*

    private static boolean IsNull(String x)
    {
        return x == null;
    }

    private static boolean IsNotNull(String x)
    {
        return !IsNull(x);
    }

    /*public static boolean StatusCheck(String status){
        return Objects.equals(status, RoleConstant.NORMAL_STATUS) || Objects.equals(status, RoleConstant.INITIAL_STATUS) || Objects.equals(status, RoleConstant.INVALID_STATUS);
    }*/

    // 这是邮箱
    public  static  boolean IsMailBox(String email) {
        if (IsNotNull(email)) {
            return email.isEmpty()||Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", email);
        }
        return false;
    }

    // 空可以过
    // phonenum: 11位纯数字,1开头
    public static boolean IsPhoneNum(String x) {
        if(IsNotNull(x))
            if(x.length()==0 || (x.length()==11 && x.charAt(0)=='1' && IdCardVerification.isNumeric(x)))
                return true;
        return false;
    }


    public static boolean IsIdCardNum(String x)
    {
        if(IsNull(x))
            return false;
        IdCardVerification idvf=new IdCardVerification();
        String temp = "";
        try{
            temp=idvf.IDCardValidate(x);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        if(temp==idvf.VALIDITY)
            return true;
        else
            return false;
        // 身份证号
    }



    private static class IdCardVerification {
        /**身份证有效*/
        public static final String VALIDITY = "该身份证有效！";
        /**位数不足*/
        public static final String LACKDIGITS = "身份证号码长度应该为15位或18位。";
        /**最后一位应为数字*/
        public static final String LASTOFNUMBER = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
        /**出生日期无效*/
        public static final String INVALIDBIRTH = "身份证出生日期无效。";
        /**生日不在有效范围*/
        public static final String INVALIDSCOPE = "身份证生日不在有效范围。";
        /**月份无效*/
        public static final String INVALIDMONTH = "身份证月份无效";
        /**日期无效*/
        public static final String INVALIDDAY = "身份证日期无效";
        /**身份证地区编码错误*/
        public static final String CODINGERROR = "身份证地区编码错误。";
        /**身份证校验码无效*/
        public static final String INVALIDCALIBRATION = "身份证校验码无效，不是合法的身份证号码";

        /**
         * 检验身份证号码是否符合规范
         * @param IDStr 身份证号码
         * @return 错误信息或成功信息
         */
        public String IDCardValidate(String IDStr) throws ParseException {
            String tipInfo = VALIDITY;// 记录错误信息
            String Ai = "";
            // 判断号码的长度 15位或18位
            if (IDStr.length() != 15 && IDStr.length() != 18) {
                tipInfo = LACKDIGITS;
                return tipInfo;
            }

            // 18位身份证前17位位数字，如果是15位的身份证则所有号码都为数字
            if (IDStr.length() == 18) {
                Ai = IDStr.substring(0, 17);
            } else if (IDStr.length() == 15) {
                Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
            }
            if (!isNumeric(Ai)) {
                tipInfo = LASTOFNUMBER;
                return tipInfo;
            }

            // 判断出生年月是否有效
            String strYear = Ai.substring(6, 10);// 年份
            String strMonth = Ai.substring(10, 12);// 月份
            String strDay = Ai.substring(12, 14);// 日期
            if (!isDate(strYear + "-" + strMonth + "-" + strDay)) {
                tipInfo = INVALIDBIRTH;
                return tipInfo;
            }
            GregorianCalendar gc = new GregorianCalendar();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
            try {
                if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                        || (gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                    tipInfo = INVALIDSCOPE;
                    return tipInfo;
                }
            } catch (NumberFormatException | ParseException e) {
                e.printStackTrace();
            }
            if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
                tipInfo = INVALIDMONTH;
                return tipInfo;
            }
            if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
                tipInfo = INVALIDDAY;
                return tipInfo;
            }

            // 判断地区码是否有效
            Hashtable<String, String> areacode = GetAreaCode();
            // 如果身份证前两位的地区码不在Hashtable，则地区码有误
            if (areacode.get(Ai.substring(0, 2)) == null) {
                tipInfo = CODINGERROR;
                return tipInfo;
            }

            if (!isVarifyCode(Ai, IDStr)) {
                tipInfo = INVALIDCALIBRATION;
                return tipInfo;
            }

            return tipInfo;
        }

        /*
         * 判断第18位校验码是否正确 第18位校验码的计算方式：
         * 1. 对前17位数字本体码加权求和 公式为：S = Sum(Ai * Wi), i =
         * 0, ... , 16 其中Ai表示第i个位置上的身份证号码数字值，Wi表示第i位置上的加权因子，其各位对应的值依次为： 7 9 10 5 8 4
         * 2 1 6 3 7 9 10 5 8 4 2
         * 2. 用11对计算结果取模 Y = mod(S, 11)
         * 3. 根据模的值得到对应的校验码
         * 对应关系为： Y值： 0 1 2 3 4 5 6 7 8 9 10 校验码： 1 0 X 9 8 7 6 5 4 3 2
         */
        private static boolean isVarifyCode(String Ai, String IDStr) {
            String[] VarifyCode = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
            String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2" };
            int sum = 0;
            for (int i = 0; i < 17; i++) {
                sum = sum + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
            }
            int modValue = sum % 11;
            String strVerifyCode = VarifyCode[modValue];
            Ai = Ai + strVerifyCode;
            if (IDStr.length() == 18) {
                return Ai.equals(IDStr);
            }
            return true;
        }

        /**
         * 将所有地址编码保存在一个Hashtable中
         * @return Hashtable 对象
         */

        private static Hashtable<String, String> GetAreaCode() {
            Hashtable<String, String> hashtable = new Hashtable<String, String>();
            hashtable.put("11", "北京");
            hashtable.put("12", "天津");
            hashtable.put("13", "河北");
            hashtable.put("14", "山西");
            hashtable.put("15", "内蒙古");
            hashtable.put("21", "辽宁");
            hashtable.put("22", "吉林");
            hashtable.put("23", "黑龙江");
            hashtable.put("31", "上海");
            hashtable.put("32", "江苏");
            hashtable.put("33", "浙江");
            hashtable.put("34", "安徽");
            hashtable.put("35", "福建");
            hashtable.put("36", "江西");
            hashtable.put("37", "山东");
            hashtable.put("41", "河南");
            hashtable.put("42", "湖北");
            hashtable.put("43", "湖南");
            hashtable.put("44", "广东");
            hashtable.put("45", "广西");
            hashtable.put("46", "海南");
            hashtable.put("50", "重庆");
            hashtable.put("51", "四川");
            hashtable.put("52", "贵州");
            hashtable.put("53", "云南");
            hashtable.put("54", "西藏");
            hashtable.put("61", "陕西");
            hashtable.put("62", "甘肃");
            hashtable.put("63", "青海");
            hashtable.put("64", "宁夏");
            hashtable.put("65", "新疆");
            hashtable.put("71", "台湾");
            hashtable.put("81", "香港");
            hashtable.put("82", "澳门");
            hashtable.put("91", "国外");
            return hashtable;
        }

        /**
         * 判断字符串是否为数字,0-9重复0次或者多次
         * @param strnum
         * @return true, 符合; false, 不符合。
         */
        private static boolean isNumeric(String strnum) {
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(strnum);
            return isNum.matches();
        }

        /**
         * 功能：判断字符串出生日期是否符合正则表达式：包括年月日，闰年、平年和每月31天、30天和闰月的28天或者29天
         * @param strDate
         * @return true, 符合; false, 不符合。
         */
        public static boolean isDate(String strDate) {
            Pattern pattern = Pattern.compile(
                    "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))?$");
            Matcher m = pattern.matcher(strDate);
            return m.matches();
        }

    }



    // 注释高手: 输入/**在函数名字之前即可

    public static boolean IsPassWord(String x)
    {
        if(IsNull(x))
            return false;
        return x.length() >= 6 && x.length() <= 32 && PassWordSpecialRequirements(x);
    }

    // 要学Java正则表达式！！！！
    // 判断一个字符串是否含有AB之间的内容(A,B均包含)
    public static boolean HasSthBetweenAB(String content,char A,char B) {
        for(int i=0;i<content.length();i++)
        {
            if(content.charAt(i)<=B && content.charAt(i)>=A)
                return true;
        }
        return false;
    }

    private static boolean HasDigit(String content){
        return HasSthBetweenAB(content,'0','9');
    }

    private static boolean HasLetter(String content){
        return HasSthBetweenAB(content,'a','z') || HasSthBetweenAB(content,'A','Z');
    }

    private static boolean HasSpecial(String content){
        return HasSthBetweenAB(content,'-','_');
    }

    private static boolean PassWordSpecialRequirements(String x)
    {
        int LetterTrue= HasLetter(x) ? 1 : 0;
        int DigitTrue= HasDigit(x) ? 1 : 0;
        int SpecialTrue= HasSpecial(x) ? 1: 0;
        if(LetterTrue+DigitTrue+SpecialTrue>=2)
            return true;
        else
            return false;
    }

    /**
     *
     * @param x
     * @return
     */

    // 判断姓名,
    public static boolean IsName(String x)
    {
        if(IsNull(x))
            return false;
        // 全是中文加空格返回true,否则false
        String regex3 = "[\\u4e00-\\u9fa5\\u0020]+";
        boolean result5 = x.matches(regex3);
        //【全为英文】返回true  否则false
        boolean result1 = x.matches("[a-zA-Z\\u0020]+");
        return result5 || result1;
    }

    // 判断角色
    public static boolean IsRole(String x)
    {
        if(IsNull(x))
            return  false;
        switch (x) {
            case "教师":
            case "teacher":
                role = "teacher";
                return true;
            case "student":
            case "学生":
                role = "student";
                return true;
            case "管理员":
            case "admin":
                role = "admin";
                return true;
        }

        return false;
    }

    // 目前设置为学工号不同
    public static boolean IsUID(String x)
    {
        if(IsNull(x))
            return false;
        if(role.equals("teacher"))
            return  IsTeacherID(x);
        if(role.equals("student"))
            return IsStudentID(x);
        if(role.equals("admin"))
            return true;
        return false;
    }

    public static boolean IsTeacherID(String x)
    {
        if(x.length()==8 && IdCardVerification.isNumeric(x) && x.substring(0,2).equals(YearLastTwoNum()) )
            return  true;
        return false;
    }

    public static boolean IsStudentID(String x)
    {
        return x.length() == 6 && IdCardVerification.isNumeric(x) && x.substring(0, 2).equals(YearLastTwoNum());
    }


    // 作了下修改，应该没啥问题，还没测试自己的修改
    public static String YearLastTwoNum()
    {
        Date date=new Date();
        String year = new SimpleDateFormat("yy", Locale.CHINESE).format(date);
        return  year;
    }
}