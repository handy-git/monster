package cn.handyplus.monster.util;

import cn.handyplus.monster.constant.MonsterConstants;

import java.util.regex.Matcher;

/**
 * @author handy
 */
public class MonsterUtil {

    /**
     * 获取[]内的数字
     * 例如：[123]测试123 获取到 123
     *
     * @param str 字符串
     * @return 数字
     * @since 1.1.6
     */
    public static int getSeparatorCustomNameNumber(String str) {
        Matcher matcher = MonsterConstants.BRACKET_NUMBER.matcher(str);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return -1;
    }

    /**
     * 根据分隔符获取自定义名称
     * 例如：[123]测试123 获取到 测试123
     *
     * @param str       字符串
     * @param separator 分隔符
     * @return 字符串
     * @since 1.1.6
     */
    public static String getSeparatorCustomName(String str, String separator) {
        return str.substring(str.indexOf(separator) + 1);
    }
}
