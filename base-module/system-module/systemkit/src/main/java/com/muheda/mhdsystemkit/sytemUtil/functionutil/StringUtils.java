package com.muheda.mhdsystemkit.sytemUtil.functionutil;

import android.graphics.Paint;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by WF on 2017/9/23.
 */
public final class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isBlank(final String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isNotBlank(final String s) {
        return !StringUtils.isBlank(s);
    }

    /**
     * 判断一个字符串是不是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumericString(final String str) {
        return str.matches("[0-9]+");
    }

    /**
     * 判断一个字符串是不是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    /**
     * 判断两字符串是否相等
     *
     * @param s1 The first string.
     * @param s2 The second string.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean equals(final CharSequence s1, final CharSequence s2) {
        if (s1 == s2) return true;
        int length;
        if (s1 != null && s2 != null && (length = s1.length()) == s2.length()) {
            if (s1 instanceof String && s2 instanceof String) {
                return s1.equals(s2);
            } else {
                for (int i = 0; i < length; i++) {
                    if (s1.charAt(i) != s2.charAt(i)) return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 判断两字符串忽略大小写是否相等
     *
     * @param s1 The first string.
     * @param s2 The second string.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean equalsIgnoreCase(final String s1, final String s2) {
        return s1 == null ? s2 == null : s1.equalsIgnoreCase(s2);
    }


    /**
     * 判断一个字符串是不是电话号码
     *
     * @param mobileNo
     * @return
     */
    public static boolean isPhoneNumber(final String mobileNo) {
        String regex = "^[1][0-9]{10}$";
        return Pattern.matches(regex, mobileNo);
    }

    /**
     * 判断是不是邮箱
     *
     * @param eMail
     * @return
     */
    public static boolean isValidEMail(final String eMail) {
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return Pattern.matches(regex, eMail);
    }

    /**
     * 判断是不是合适的密码格式
     *
     * @param pwd
     * @return
     */
    public static boolean isValidPwd(final String pwd) {
        String regex =
                "^(?=.*?[a-zA-Z`~!@#$%^&*()_\\-+={}\\[\\]\\\\|:;\\\"'<>,.?/])[a-zA-Z\\d`~!@#$%^&*" +
                        "()_\\-+={}\\[\\]\\\\|:;\\\"'<>,.?/]{6,20}$";
        return Pattern.matches(regex, pwd);
    }

    /**
     * 判断是不是六位数字验证码
     *
     * @param code
     * @return
     */
    public static boolean isValidVerifyCode(final String code) {
        String regex = "[0-9]{6}";
        return Pattern.matches(regex, code);
    }

    /**
     * 根据身份证号提取生日
     *
     * @param cardID 37078319931209379X
     * @return **-**-**
     */
    public static String getBirthday(final String cardID) {
        String returnDate = null;
        StringBuffer tempStr = null;
        if (cardID != null && cardID.trim().length() > 0) {
            tempStr = new StringBuffer(cardID.substring(6, 14));
            tempStr.insert(6, '-');
            tempStr.insert(4, '-');
        }
        if (tempStr != null && tempStr.toString().trim().length() > 0) {
            returnDate = tempStr.toString();
        }
        return returnDate;
    }

    /**
     * 根据身份证号判断性别
     *
     * @param cardID
     * @return
     */
    public static String getGender(final String cardID) {
        String returnGender = null;
        if (cardID != null && cardID.trim().length() > 0) {
            returnGender = cardID.substring(16, 17);
            if (Integer.parseInt(returnGender) % 2 == 0) {
                returnGender = "2";
            } else {
                returnGender = "1";
            }
        }
        return returnGender;
    }

    /**
     * 手机号中间四位隐藏
     *
     * @param mobile 手机号
     * @return
     */
    public static String mobileFormat(final String mobile) {
        if (isBlank(mobile)) {
            return mobile;
        }
        String startString = mobile.substring(0, 3);
        String endString = mobile.substring(7);
        return startString + "****" + endString;
    }

    /**
     * 校验输入的是否是字母、数字、汉字
     *
     * @param s
     * @return
     */
    public static boolean isValidEditText(final String s) {
        String regex = "^[a-zA-Z0-9\\u4e00-\\u9fa5]+$";
        return Pattern.matches(regex, s);
    }

    /**
     * 判断是否是Emoji表情
     *
     * @param codePoint
     * @return
     */
    public static boolean isEmojiCharacter(final char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) ||
                (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000)
                && (codePoint <= 0x10FFFF));
    }

    /**
     * 检测是否有emoji表情
     *
     * @param source
     * @return 表情返回true  否则返回false
     */
    public static boolean isEmojiCharacter(final String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { //如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }

    /**
     * 限制字符串长度
     *
     * @param source 字符串
     * @param limit  限制的长度
     * @param suffix 截取字符串后添加后缀，如"..."，如果不需要，设为null
     * @return
     */
    public static String limitStringLength(final String source, final int limit, final String suffix) {
        int length = source.length();
        StringBuffer stringBuffer = new StringBuffer();
        if (length > limit) {
            stringBuffer.append(source, 0, limit)
                    .append(suffix);
        }
        return stringBuffer.toString();
    }

    /**
     * 含有特殊字符时，截取特殊字符下标之前的字符串(处理图片url时)
     *
     * @param originalStr 字符串
     * @param specialStr  特殊字符
     * @return
     */
    public static String isContainSpecialSign(final String originalStr, final String specialStr) {
        String newStr = originalStr;
        if (originalStr.indexOf(specialStr) > -1) {
            newStr = originalStr.substring(0, originalStr.indexOf(specialStr));
        }
        return newStr;
    }

    /**
     * 生成价格字符串
     */
    public static String formatPriceString(final String price) {
        if (isBlank(price)) {
            return price;
        }
        return "￥" + price;
    }

    /**
     * 带有下划线的Textview
     */
    public static void formatUnderlinePrice(final TextView view, final String price) {
        view.setVisibility(View.GONE);
        if (view != null && StringUtils.isNotBlank(price)) {
            view.setText(price);
            view.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            view.setVisibility(View.GONE);
        }
    }


    /**
     * 清除电话号码中的空格
     *
     * @return
     */
    private static String clearBlank(final String number) {
        if (StringUtils.isNotBlank(number)) {
            return number.replaceAll(" ", "");
        }
        return number;
    }

    /**
     * 过滤掉电话号码中个的"+86"
     */
    public static String filterPlus86AndBlank(final String number) {
        if (StringUtils.isNotBlank(number)) {
            if (number.contains("+86")) {
                return clearBlank(number.replace("+86", ""));
            } else {
                return clearBlank(number);
            }
        }
        return number;
    }


    /**
     * 转换成8DA43EE0格式16进制
     */
    public static final String hexString = "0123456789ABCDEF";

    public static String decode(final String bytes, final boolean isUpperCase) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(
                bytes.length() / 2);
        // 将每2位16进制整数组装成一个字节
        for (int i = 0; i < bytes.length(); i += 2)
            baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString
                    .indexOf(bytes.charAt(i + 1))));
        if (isUpperCase) {
            return new String(baos.toByteArray());
        }
        return new String(baos.toByteArray()).toLowerCase();
    }

    /**
     * 将字节数组中每个字节拆解成2位16进制整数
     *
     * @param str
     * @return
     */
    public static String encode(String str) {
        str = str.toUpperCase();

        // 根据默认编码获取字节数组

        byte[] bytes = str.getBytes();

        StringBuilder sb = new StringBuilder(bytes.length * 2);

        // 将字节数组中每个字节拆解成2位16进制整数

        for (byte aByte : bytes) {

            sb.append(hexString.charAt((aByte & 0xf0) >> 4));

            sb.append(hexString.charAt((aByte & 0x0f)));

        }

        return sb.toString();

    }

    /**
     * 转换银行卡卡号
     *
     * @param cardNum
     * @return
     */
    public static String getHideCardNum(final String cardNum) {
        int length = cardNum.length();
        if (length >= 8) {
            String firstFour = cardNum.substring(0, 4);
            String lastFour = cardNum.substring(length - 4, length);
            int hideLength = length - firstFour.length() - lastFour.length();

            String hideTag = "";

            for (int i = 0; i < hideLength; i++) {
                hideTag += "*";
            }
            return firstFour + hideTag + lastFour;
        }

        return cardNum;

    }

    /**
     * 格式化两位小数 （金钱）
     *
     * @param money
     * @return
     */

    public static String formatMoney(final String money) {
        if (StringUtils.isBlank(money)) {
            return money;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        double d = Double.valueOf(money);
        return decimalFormat.format(d);
    }

    /**
     * 使用java正则表达式去掉多余的0
     *
     * @param s
     * @return
     */
    public static String subAllZero(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    /**
     * spannableStr
     *
     * @param str
     * @param colorId
     * @param start
     * @param end
     * @return
     */
    public static SpannableString getSpannableString(final String str, final int colorId, final int start, final int end) {
        SpannableString ss = new SpannableString(str);
        ss.setSpan(new ForegroundColorSpan(colorId), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ss;
    }

    /**
     * 车牌号正则表达式匹配规则
     *
     * @param number
     * @return
     */
    public static boolean isCarNumber(String number) {
        String carNumRegex = "[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5,6}";
        if (TextUtils.isEmpty(number)) return false;
        else return number.matches(carNumRegex);
    }


    public static String substring(String str, int start, int end) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return str.substring(start, end);
        } catch (Exception e) {
            return "";
        }
    }

}

