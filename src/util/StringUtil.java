package util;

/*
 * 字符串工具类
 */

/*
 * 判断字符串是否为空
 */
public class StringUtil {
	public static boolean isEmpty(String str){
		if("".equals(str) || str == null){
			return true;
		}
		return false;
	}
}
