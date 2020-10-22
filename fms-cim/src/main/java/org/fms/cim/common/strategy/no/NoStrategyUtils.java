/**
 * Author : chizf
 * Date : 2020年10月22日 上午8:32:19
 * Title : org.fms.cim.common.strategy.no.NoStrategyUtils.java
 *
**/
package org.fms.cim.common.strategy.no;

public class NoStrategyUtils {
	/**
	 *
	 * @param number    需要格式化的字符
	 * @param digit     格式化多少位
	 * @param character 替代符
	 * @param direction true前面填充替代符 false右面填充替代符
	 * @return
	 */
	public static String generateFormatNo(String number, int digit, String character, boolean direction) {
		int strLen = number.length();
		if (strLen >= digit) {
			return number;
		}
		StringBuffer sb = null;
		while (strLen < digit) {
			sb = new StringBuffer();
			if (direction) {
				sb.append(character).append(number);// 左补0
			} else {
				sb.append(number).append(character);// 右补0

			}
			number = sb.toString();
			strLen = number.length();
		}
		return number;
	}

}
