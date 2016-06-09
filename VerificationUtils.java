/*
 *   Copyright (C)  2016 android@19code.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.mooc.test;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/**
 * 摘抄自：
 * https://github.com/h4de5ing/AndroidCommon
 */
public class VerificationUtils {
    /**
     * 真实姓名可以是汉字,也可以是字母,但是不能两者都有,也不能包含任何符号和数字
     1.如果是英文名,可以允许英文名字中出现空格
     2.英文名的空格可以是多个,但是不能连续出现多个
     3.汉字不能出现空格
     */
    public static boolean matcherRealName(String value) {
        String regex = "^([\\u4e00-\\u9fa5]+|([a-zA-Z]+\\s?)+)$";
        return testRegex(regex, value);
    }
//    判断手机号格式 (匹配11数字,并且13-19开头)
    public static boolean matcherPhoneNum(String value) {
        String regex = "^(\\+?\\d{2}-?)?(1[0-9])\\d{9}$";
        return testRegex(regex, value);
    }
//    判断账号格式 (4-20位字符)
    public static boolean matcherAccount(String value) {
        String regex = "[\\u4e00-\\u9fa5a-zA-Z0-9\\-]{4,20}";
        return testRegex(regex, value);
    }
//    判断密码格式 (6-12位字母或数字)
    public static boolean matcherPassword(String value) {
        String regex = "^[a-zA-Z0-9]{6,12}$";
        return testRegex(regex, value);
    }
//    判断密码格式 (6-12位字母或数字,必须同时包含字母和数字)
    public static boolean matcherPassword2(String value) {
        String regex = "(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}";
        return testRegex(regex, value);
    }

//    判断邮箱格式
    public static boolean matcherEmail(String value) {
//      String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)" +
//                "+[a-zA-Z]{2,}$";
        String regex = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+" +
                "(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+" +
                "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
        return testRegex(regex, value);
    }

//    判断中国民用车辆号牌
    public static boolean matcherVehicleNumber(String value) {
        String regex = "^[京津晋冀蒙辽吉黑沪苏浙皖闽赣鲁豫鄂湘粤桂琼川贵云藏陕甘青宁新渝]?[A-Z][A-HJ-NP-Z0-9学挂港澳练]{5}$";
        return testRegex(regex, value.toLowerCase());
    }

    /**
     *  判断身份证号码格式
      */

    public static boolean matcherIdentityCard(String value) {

        if (TextUtils.isEmpty(value)) {
            return false;
        }
        final int length = value.length();
        if (15 == length) {
            try {
                return isOldCNIDCard(value);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return false;
            }
        } else if (18 == length) {
            return isNewCNIDCard(value);
        } else {
            return false;
        }
    }



    public static boolean isNewCNIDCard(String numbers) {

        final int[] WEIGHT = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

        final char[] VALID = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
        numbers = numbers.toUpperCase();
        int sum = 0;
        for (int i = 0; i < WEIGHT.length; i++) {
            final int cell = Character.getNumericValue(numbers.charAt(i));
            sum += WEIGHT[i] * cell;
        }
        int index = sum % 11;
        return VALID[index] == numbers.charAt(17);
    }

    public static boolean isOldCNIDCard(String numbers) {
        String yymmdd = numbers.substring(6, 11);
        boolean aPass = numbers.equals(String.valueOf(Long.parseLong(numbers)));
        boolean yPass = true;
        try {
            new SimpleDateFormat("yyMMdd").parse(yymmdd);
        } catch (Exception e) {
            e.printStackTrace();
            yPass = false;
        }
        return aPass && yPass;
    }


    public static boolean testRegex(String regex, String inputValue) {
        return Pattern.compile(regex).matcher(inputValue).matches();
    }
    /**
     * 身份证校验
     * <p>
     * 根据〖中华人民共和国国家标准 GB 11643-1999〗中有关公民身份号码的规定,公民身份号码是特征组合码,由十七位数字本体码和一位数字校验码组成。
     * 排列顺序从左至右依次为：六位数字地址码,八位数字出生日期码,三位数字顺序码和一位数字校验码。
     * 地址码表示编码对象常住户口所在县(市、旗、区)的行政区划代码。
     * 出生日期码表示编码对象出生的年、月、日,其中年份用四位数字表示,年、月、日之间不用分隔符。
     * 顺序码表示同一地址码所标识的区域范围内,对同年、月、日出生的人员编定的顺序号。顺序码的奇数分给男性,偶数分给女性。
     * 校验码是根据前面十七位数字码,按照ISO 7064:1983.MOD 11-2校验码计算出来的检验码。
     * 出生日期计算方法。
     * 15位的身份证编码首先把出生年扩展为4位,简单的就是增加一个19或18,这样就包含了所有1800-1999年出生的人;
     * 2000年后出生的肯定都是18位的了没有这个烦恼,至于1800年前出生的,那啥那时应该还没身份证号这个东东,⊙﹏⊙b汗...
     * 下面是正则表达式:
     * 出生日期1800-2099  /(18|19|20)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])/
     * 身份证正则表达式 /^[1-9]\d{5}((1[89]|20)\d{2})(0[1-9]|1[0-2])(0[1-9]|[12]\d|3[01])\d{3}[\dx]$/i
     * 15位校验规则 6位地址编码+6位出生日期+3位顺序号
     * 18位校验规则 6位地址编码+8位出生日期+3位顺序号+1位校验位
     * 校验位规则     公式:∑(ai×Wi)(mod 11)……………………………………(1)
     * 公式(1)中：
     * i----表示号码字符从由至左包括校验码在内的位置序号;
     * ai----表示第i位置上的号码字符值；
     * Wi----示第i位置上的加权因子,其数值依据公式Wi=2^(n-1）(mod 11)计算得出。
     * i 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1
     * Wi 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2 1
     * </P>
     *
     * @author Yoojia.Chen (yoojia.chen@gmail.com)
     * @version version 2015-05-21
     * @since 2.0
     */
}
