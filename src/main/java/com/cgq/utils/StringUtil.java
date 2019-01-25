package com.cgq.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用函数类（字符串构件）
 * 
 * @version 1.0
 * @author chenjinlong
 */
public class StringUtil {

	private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);

	/**
	 * 字符串左补齐
	 * 
	 * @param strSource 原始字符串
	 * @param chrAdd    需要补充的字符
	 * @param intLength 固定字符串长度(多余的截掉)
	 * @return 返回GBK字符串
	 */
	public static String msAddLeft(String strSource, String chrAdd, int intLength) {
		if ((intLength <= 0))
			return null;
		if (strSource.length() > intLength) {
			return strSource;
		}

		int i = 0;
		StringBuffer strbufTemp = new StringBuffer("");

		while (i < intLength - strSource.length()) {
			strbufTemp = strbufTemp.append(chrAdd);
			i++;
		}

		return strbufTemp.toString() + strSource;
	}

	/**
	 * 两个字符串数组合并成一个字符串数组
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String[] concat(String[] a, String[] b) {
		String[] c = new String[a.length + b.length];
		System.arraycopy(a, 0, c, 0, a.length);
		System.arraycopy(b, 0, c, a.length, b.length);
		return c;
	}

	/**
	 * 字符串左补齐。如果超过长度，则截取前intLength位
	 * 
	 * @param strSource 原始字符串
	 * @param chrAdd    需要补充的字符
	 * @param intLength 固定字符串长度
	 * @return 返回GBK字符串
	 */
	public static String msFillCharLeft(String strSource, char chrAdd, int intLength) {
		if ((intLength <= 0))
			return null;
		if (strSource.length() > intLength) {
			return strSource.substring(0, intLength);
		}

		int i = 0;
		StringBuffer strbufTemp = new StringBuffer("");

		while (i < intLength - strSource.length()) {
			strbufTemp = strbufTemp.append(chrAdd);
			i++;
		}

		return strbufTemp.toString() + strSource;
	}

	/**
	 * 字符串右补齐
	 * 
	 * @param strSource 原始字符串
	 * @param chrAdd    需要补充的字符
	 * @param intLength 固定字符串长度(多余的截掉)
	 * @return 返回GBK字符串
	 */
	public static String msAddRight(String strSource, char chrAdd, int intLength) {
		if ((intLength <= 0))
			return null;
		if (strSource.length() > intLength) {
			return strSource;
		}

		int i = 0;
		StringBuffer strbufTemp = new StringBuffer(strSource);

		while (i < intLength - strSource.length()) {
			strbufTemp = strbufTemp.append(chrAdd);
			i++;
		}

		return strbufTemp.toString();
	}

	/**
	 * 取消字符串左部空格或制表符（\t,\n,\r）截断然后返回
	 * 
	 * @param strSource 需要转换的字符串
	 * @return 返回左部截断的字符串（string）
	 */
	public static String msLeftTrimStr(String strSource) {
		char chrLet;
		int i = 0;

		if (strSource == null) {
			return null;
		}

		for (i = 0; i < strSource.length(); i++) {
			chrLet = strSource.charAt(i);
			if ((chrLet != ' ') && (chrLet != '\t') && (chrLet != '\n') && (chrLet != '\r')) {
				break;
			}
		}

		return strSource.substring(i, strSource.length());
	}

	/**
	 * 取消字符串右部空格或制表符（\t,\n,\r）截断然后返回
	 * 
	 * @param strSource 需要转换的字符串
	 * @return 返回已截取右侧空格或制表符的字符串（String）
	 */
	public static String msRightTrimStr(String strSource) {
		char chrLet;
		int i = 0;

		if (strSource == null) {
			return "";
		}

		for (i = strSource.length() - 1; i >= 0; i--) {
			chrLet = strSource.charAt(i);
			if ((chrLet != ' ') && (chrLet != '\t') && (chrLet != '\n') && (chrLet != '\r')) {
				break;
			}
		}

		return strSource.substring(0, i + 1);
	}

	/**
	 * 取消字符串右部指定字符截断然后返回
	 * 
	 * @param strSource 需要转换的字符串
	 * @param chr       去掉的字符
	 * @return 返回已截取右侧空格或制表符的字符串（String）
	 */
	public static String msRightStr(String strSource, char chr) {
		char chrLet;
		int i = 0;

		if (strSource == null) {
			return "";
		}

		for (i = strSource.length() - 1; i >= 0; i--) {
			chrLet = strSource.charAt(i);
			if (chrLet != chr) {
				break;
			}
		}

		return strSource.substring(0, i + 1);
	}

	/**
	 * 在主字符串中查找匹配的字符串(第N次)
	 * 
	 * @param strSource 原始字符串
	 * @param strPart   查找的子串
	 * @param intTimes  第几次出现
	 * @return 返回字符串所在位置
	 */
	public static int msIndexOfNTime(String strSource, String strPart, int intTimes) {
		if ((strPart.length() > strSource.length()) | (intTimes == 0) | (strPart.length() == 0))
			return -1;

		int times = 1;
		int position = strSource.indexOf(strPart);
		int temp = position;
		StringBuffer strnew = new StringBuffer(strSource);

		while ((times < intTimes)) {
			if (temp == -1)
				return -1;
			strnew = strnew.delete(0, temp + strPart.length());
			temp = strnew.indexOf(strPart);
			position = position + strPart.length() + temp;
			times++;
		}

		return position;
	}

	/**
	 * 在主串中指定位置插入子串
	 * 
	 * @param strSource 原始字符串
	 * @param strPart   需要插入的子串
	 * @param intPos    插入的位置
	 * @return 返回字符串所在位置
	 */
	public static String msInsert(String strSource, String strPart, int intPos) {
		if (strSource.equals(""))
			return strSource;
		StringBuffer strbufTemp = new StringBuffer(strSource);
		strbufTemp = strbufTemp.insert(intPos, strPart);

		return strbufTemp.toString();

	}

	/**
	 * 判断字符串是否全为数字
	 * 
	 * @param strSource 原始字符串
	 * @return 是返回true，否则返回false
	 */
	public static boolean msIsNum(String strSource) {
		if ((StringUtils.isEmpty(strSource)))
			return false;

		for (int i = 0; i < strSource.length(); i++) {
			if (!Character.isDigit(strSource.charAt(i)))
				return false;
		}

		return true;
	}

	/**
	 * 判断字符串是否全为数字(包括点)
	 * 
	 * @param strSource 原始字符串
	 * @return 是返回true，否则返回false
	 */
	public static boolean msIsNumeric(String strSource) {
		if ((StringUtils.isEmpty(strSource)))
			return false;

		for (int i = 0; i < strSource.length(); i++) {
			if (strSource.charAt(i) == ('.') || strSource.charAt(i) == (',')) {
				continue;
			} else if (!Character.isDigit(strSource.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 检查字符串是否为整数
	 * 
	 * @param strSource 原始字符串
	 * @return 为整数返回true，否则返回false
	 */
	public static boolean msIsInteger(String strSource) {
		String s = strSource;
		if (s == null || s.trim().equalsIgnoreCase("")) {
			return false;
		} else {
			s = s.trim();
			char[] c = s.toCharArray();
			for (int i = 0; i < c.length; i++) {
				if (Character.isDigit(c[i])) {
				} else if (i == 0) {
					if (c[i] != '+' && c[i] != '-') {
						return false;
					}
				} else
					return false;
			}
		}
		return true;
	}

	/**
	 * 判断字符串是否全为同一个数值
	 * 
	 * @param strSource 原始字符串
	 * @return 返回布尔值
	 */
	public static boolean msIsOneChar(String strSource) {
		if ((StringUtils.isEmpty(strSource)))
			return false;

		for (int i = 0; i < strSource.length(); i++) {
			if (!(strSource.charAt(i) == strSource.charAt(0)))
				return false;
		}

		return true;
	}

	/**
	 * 判断一个字符串是否为空字符串
	 * 
	 * @param strSource 需要转换的字符串
	 * @return 如果为空返回true，否则返回false
	 */
	public static boolean msIsEmpty(String strSource) {
		char chrS;
		int intStrLength = 0;
		int i = 0;

		// 如果为空对象，则返回true
		if (strSource == null) {
			return true;
		}

		intStrLength = strSource.length();
		for (i = 0; i < intStrLength; i++) {
			chrS = strSource.charAt(i);
			if ((chrS != ' ') && (chrS != '\t') && (chrS != '\n')) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 截取字符串前后的空格
	 * 
	 * @param strSource 欲拆分的字符串
	 * @return 去空格后字符串(若为空，返回"")
	 */
	public static String msTrim(String strSource) {
		if (strSource == null) {
			return "";
		} else {
			return strSource.trim();
		}
	}

	/**
	 * 字符串替换
	 * 
	 * @param strSource 原始字符串
	 * @param strOldStr 被替换的串
	 * @param strNewStr 替换为的串
	 * @return 替换后的字符串
	 */
	public static String msReplaceAll(String strSource, String strOldStr, String strNewStr) {
		String strTempString = ""; // 临时字符串
		int intPoint = 0; // 被替换字符串的位置
		int intOldStrLength = 0; // 被替换串长度

		while (strSource.indexOf(strOldStr) != -1) {
			intPoint = strSource.indexOf(strOldStr);

			strTempString = "";
			strTempString += strSource.substring(0, intPoint);
			strTempString += strNewStr;
			intOldStrLength = strOldStr.length();
			strTempString += strSource.substring(intPoint + intOldStrLength);
			strSource = strTempString;
		}

		return strSource;
	}

	/**
	 * 根据分隔符拆分字符串
	 * 
	 * @param strSource 原始字符串
	 * @param strSep    分隔符
	 * @return 拆分后的字符串数组
	 */
	public static String[] msSplit(String strSource, String strSep) {
		if (strSource == null)
			strSource = "";
		StringBuffer strbufTemp = new StringBuffer(strSource);
		String strNew = new String();
		String[] arrRetuen = new String[strSource.length() + 1];
		int intAddr = strSource.indexOf(strSep);
		int i = 0;
		int j = 0;
		if (strSource.equals("")) {
			String[] arrNullRetuen = new String[1];
			arrNullRetuen[0] = "";
			return arrNullRetuen;
		}
		for (i = 0; i < strSource.length(); i++) {
			String strTemp = new String(strbufTemp);
			intAddr = strTemp.indexOf(strSep);
			if (intAddr == -1)
				break;
			strNew = strbufTemp.substring(0, intAddr);
			strbufTemp = strbufTemp.delete(0, intAddr + strSep.length());
			arrRetuen[i] = strNew;
		}
		strNew = strbufTemp.substring(0);
		arrRetuen[i] = strNew;
		String[] arrlastRetuen = new String[i + 1];
		for (j = 0; j <= i; j++)
			arrlastRetuen[j] = arrRetuen[j];
		return arrlastRetuen;
	}

	/**
	 * 将一个字符串变成一个整数，如果为空或不能转换，则返回0
	 * 
	 * @param strSource 需要转换的字符串
	 * @return 转换后的整数
	 */
	public static int msReturnInt(String strSource) {
		if (StringUtil.msIsEmpty(strSource)) {
			return 0;
		} else {
			try {
				return Integer.parseInt(strSource);
			} catch (NumberFormatException ex) {
				return 0;
			}

		}
	}

	public static long msReturnLong(String strSource) {
		if (StringUtil.msIsEmpty(strSource)) {
			return 0;
		} else {
			try {
				return Long.valueOf(strSource);
			} catch (NumberFormatException ex) {
				return 0;
			}

		}
	}

	/**
	 * 将一个字符串变成一个整数，如果为空或不能转换，则返回参数
	 * 
	 * @param strSource 需要转换的字符串
	 * @param intReturn 出错返回
	 * @return 转换后的整数或intReturn
	 */
	public static int msReturnInt(String strSource, int intReturn) {
		if (StringUtil.msIsEmpty(strSource)) {
			return intReturn;
		} else {
			try {
				return Integer.parseInt(strSource);
			} catch (NumberFormatException ex) {
				return intReturn;
			}

		}
	}

	/**
	 * 返回第一次出现的指定子字符串在此字符串中的索引（不区分大小写）
	 * 
	 * @param strSource 原始字符串
	 * @param strPart   子串IgnoreCase
	 * @return 在主串中的位置
	 */
	public static int msIndexOfIgnoreCase(String strSource, String strPart) {
		if (StringUtil.msIsEmpty(strSource)) {
			return -1;
		}

		String strUpper = strSource.toUpperCase();

		return strUpper.indexOf(strPart.toUpperCase());

	}

	/**
	 * 返回在此字符串中最右边出现的指定子字符串的索引（不区分大小写）
	 * 
	 * @param strSource 原始字符串
	 * @param strPart   子串
	 * @return 在主串中最右边的位置
	 */
	public static int msLastIndexOfIgnoreCase(String strSource, String strPart) {
		if (StringUtil.msIsEmpty(strSource)) {
			return -1;
		}

		String strUpper = strSource.toUpperCase();

		return strUpper.lastIndexOf(strPart.toUpperCase());

	}

	/**
	 * 处理null为''
	 * 
	 * @param objInput 用户信息
	 * @return 处理后的字符串
	 */
	public static String msNvl(Object objInput) {
		if (objInput == null)
			return "";
		else
			return objInput.toString();
	}

	/**
	 * 去除数组中重复的元素
	 * 
	 * @param strSource 数组
	 * @return 处理后的数组
	 */
	public static String[] msUnrepeat(String[] strSource) {
		Vector<String> veReturn = new Vector<String>();
		veReturn.add(strSource[0]);
		String[] strReturn = new String[1];
		boolean flag = false;
		for (int i = 1; i < strSource.length; i++) {
			String strTemp = strSource[i];
			flag = false;
			for (int j = 0; j < i; j++) {
				if (strTemp.equals(strSource[j])) {
					flag = true;
					break;
				}
			}

			if (flag == false)
				veReturn.add(strSource[i]);

		}

		return veReturn.toArray(strReturn);
	}

	/**
	 * 去除数组中指定值的元素
	 * 
	 * @param strSource 数组
	 * @param strDelete 要被删除的值
	 * @return 处理后的数组
	 */
	public static String[] msClear(String[] strSource, String strDelete) {
		Vector<String> veReturn = new Vector<String>();
		for (int i = 1; i < strSource.length; i++) {
			if (!strSource[i].equals(strDelete) && strSource[i] != null) {
				veReturn.add(strSource[i]);
			}
		}
		String[] strReturn = new String[veReturn.size()];
		return veReturn.toArray(strReturn);
	}

	/**
	 * 将数组元素初始化为一个值
	 * 
	 * @param strSource 数组
	 * @param strFill   初始化为的值
	 * @return 处理后的数组
	 */
	public static String[][] msInitString(String[][] strSource, String strFill) {
		if (strSource != null) {
			for (int i = 0; i < strSource.length; i++) {
				for (int j = 0; j < strSource[i].length; j++) {
					strSource[i][j] = strFill;
				}
			}
		}
		return strSource;
	}

	/**
	 * 将数组元素初始化为一个值
	 * 
	 * @param strSource 数组
	 * @param strFill   初始化为的值
	 * @return 处理后的数组
	 */
	public static String[] msInitString(String[] strSource, String strFill) {
		if (strSource != null) {
			for (int i = 0; i < strSource.length; i++) {
				strSource[i] = strFill;
			}
		}
		return strSource;
	}

	/**
	 * 取二维数组里面的一列，并将其转化为一个一维数组
	 * 
	 * @param strSource   数组
	 * @param columnIndex 获取第几列
	 * @return 处理后的数组
	 */
	public static String[] msGetColumn(String[][] strSource, int columnIndex) {
		String[] strRtn = null;
		if (strSource != null) {
			if (columnIndex < 0)
				columnIndex = 0;
			if (columnIndex > strSource[0].length)
				columnIndex = strSource[0].length;
			strRtn = new String[strSource.length];
			for (int i = 0; i < strSource.length; i++) {
				strRtn[i] = strSource[i][columnIndex];
			}
		}
		return strRtn;
	}

	/**
	 * 取二维数组里面的一列,对其排序
	 * 
	 * @param strSource   数组
	 * @param columnIndex 获取第几列
	 * @return 处理后的数组
	 */
	public static String[][] msTaxis(String[][] strSource, int columnIndex) {
		if (strSource != null) {
			for (int i = 0; i < strSource.length; i++) {
				for (int j = i + 1; j < strSource.length; j++) {
					if (strSource[j][columnIndex].compareToIgnoreCase(strSource[i][columnIndex]) < 0) // 忽略大小写
					{
						String[] t = strSource[i];
						strSource[i] = strSource[j];
						strSource[j] = t;
					}
				}
			}
		}
		return strSource;
	}

	/**
	 * 仿oracle的decode函数
	 * 
	 * @param strA,……
	 * @return 如果A=B,则返回C,否则返回D
	 */
	public static String msDecode(String strA, String strB, String strC, String strD) {
		if (strA.equals(strB)) {
			return strC;
		} else {
			return strD;
		}
	}

	/**
	 * 仿oracle的左like函数
	 * 
	 * @param strSource,strLeftLike
	 * @return 如果strSource左相似strLeftLike,则返回ture,否则返回false
	 */
	public static boolean msLeftLike(String strSource, String strLeftLike) {
		boolean blReturn = false;
		int ssLength = strLeftLike.length();
		if (strSource.equals(strLeftLike)) {
			blReturn = true;
		} else if (strSource.length() > ssLength) {
			if (strSource.substring(0, ssLength).equals(strLeftLike)) {
				blReturn = true;
			}
		}

		return blReturn;
	}

	public static String msMerge(String... strings) {
		if (strings == null || strings.length == 0) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (String s : strings) {
			sb.append(s);
		}
		return sb.toString();
	}

	/**
	 * 将字符串数组中的字符用单引号包含起来并用逗号隔开。
	 * <p>
	 * 方法详述:略。
	 * </p>
	 * 
	 * @param obj
	 * @return
	 */
	public static String getFormItemValue(Object obj) {
		String ret = "";
		if (obj == null) {
			ret = "";
		} else if (obj instanceof String) {
			ret = obj.toString();
		} else if (obj instanceof String[]) {
			String arr[] = (String[]) obj;
			for (int i = 0; i < arr.length; i++) {
				ret += arr[i] + ",";
			}
			if (ret.endsWith(",")) {
				ret = ret.substring(0, ret.length() - 1);
			}
		}
		return ret;
	}

	/**
	 * 判断对象为空
	 * 
	 * @param object
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNull(Object object) {
		if (object == null || "".equals(object) || "null".equalsIgnoreCase((String) object))
			return true;
		if (object instanceof String)
			return "".equals(((String) object).trim());
		if (object instanceof Collection)
			return ((Collection) object).isEmpty();
		if (object instanceof Array)
			return Array.getLength(object) == 0 ? true : false;
		if (object instanceof Map)
			return ((Map) object).isEmpty();
		return false;
	}

	/**
	 * 产生随机字符串
	 * 
	 * @param length 生成的字符串的的长度
	 * @return 随机的字符串
	 */
	public static final String randomString(int length) {
		if (length < 1) {
			return null;
		}

		Random randGen = new Random();
		char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	/**
	 * 对象属性转换成map存储
	 * 
	 * @param obj
	 * @param classPath
	 * @return
	 */
	public static Map<String, String> objParseMap(Object obj, String classPath) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			Field[] fds = Class.forName(classPath).getDeclaredFields();
			for (int i = 0; i < fds.length; i++) {
				Field field = fds[i];
				field.setAccessible(true);
				String v = (String) field.get(obj);
				if (!StringUtil.msIsEmpty(v)) {
					String name = field.getName();
					result.put(name, v);
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 分转换为元
	 * 
	 * @param fen
	 * @return
	 * 		public static String fromFenToYuan(final String fen) { String yuan =
	 *         "0"; final int MULTIPLIER = 100; Pattern pattern =
	 *         Pattern.compile("^[1-9][0-9]*{1}"); Matcher matcher =
	 *         pattern.matcher(fen); if (matcher.matches()) { yuan = new
	 *         BigDecimal(fen).divide(new
	 *         BigDecimal(MULTIPLIER)).setScale(2).toString(); } return yuan; }
	 */

	/**
	 * 元转换为分
	 * 
	 * @param yuan
	 * @return
	 * 		public static String fromYuanToFen(final String yuan) { String fen =
	 *         "0"; Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]{2})?{1}");
	 *         Matcher matcher = pattern.matcher(yuan); if (matcher.matches()) { try
	 *         { NumberFormat format = NumberFormat.getInstance(); Number number =
	 *         format.parse(yuan); double temp = number.doubleValue() * 100.0; //
	 *         默认情况下GroupingUsed属性为true 不设置为false时,输出结果为2,012
	 *         format.setGroupingUsed(false); // 设置返回数的小数部分所允许的最大位数
	 *         format.setMaximumFractionDigits(0); fen = format.format(temp); }
	 *         catch (Exception e) { e.printStackTrace(); } } return fen; }
	 */

	/**
	 * 数组转换为List
	 * 
	 * @param objArray 数组
	 * @return List
	 */
	public static List<String> arryToList(String[] objArray) {
		List<String> objList = new ArrayList<String>();
		if (null == objArray || 0 == objArray.length) {
			return objList;
		}
		for (String obj : objArray) {
			objList.add(obj);
		}
		return objList;
	}

	/**
	 * 根据传入参数data，多个参数之间以\t分隔，返回顺序匹配message中的\t字符后的字符串
	 * 
	 * @param message
	 * @param data
	 * @return 消息模板匹配data后的内容
	 */
	public static String reMessge(String message, String data) {
		if (message == null || message.equals(""))
			return null;
		if (data == null || data.equals(""))
			return null;

		String[] datas = data.split("\t");

		for (int i = 0; i < datas.length; i++) {
			message = message.replaceFirst("\t", datas[i]);
		}
		return message;
	}

	/**
	 * @description 字符串转BigDecimal类型，并保留两位
	 * @date 2015年6月17日
	 * @author 柯光辉
	 * @param strSource 可转换为数字类型对象
	 * @return 返回BigDecimal
	 */
	public static BigDecimal str2BigDecimal(String strSource) {
		if (StringUtil.msIsEmpty(strSource)) {
			return new BigDecimal(0.00);
		} else {
			try {
				BigDecimal bd = new BigDecimal(strSource);
				bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
				return bd;
			} catch (Exception e) {
				return new BigDecimal(0.00);
			}
		}
	}

	/**
	 * String转BigDecimal 四舍五入 保留2位小数
	 */
	public static Float str2Float(String strSource) {
		if (StringUtil.msIsEmpty(strSource)) {
			return new Float(0.00);
		} else {
			try {
				return Float.valueOf(strSource);
			} catch (Exception e) {
				return new Float(0.00);
			}
		}
	}

	/**
	 * @description 对象转BigDecimal类型 String Integer等类型 非数字转换时，抛出异常
	 * @date 2015年6月17日
	 * @author 李威
	 * @param obj 可转换为数字类型对象
	 * @return 返回BigDecimal
	 */
	public static BigDecimal toBigDecimal(Object obj) {
		BigDecimal zero = new BigDecimal(0.00);
		if (obj == null) {
			return zero;
		}
		if (obj instanceof BigDecimal) {
			return (BigDecimal) obj;
		} else {
			String strValue = obj.toString().trim();
			if ("".equals(strValue)) {
				return zero;
			}
			try {
				return new BigDecimal(strValue);
			} catch (Exception e) {
				throw new RuntimeException("数字格式不正确，无法转换成BigDecimal类型：" + strValue);
			}
		}
	}

	/**
	 * 截取字符串后几位数据
	 * 
	 * @param str 截取字符串
	 * @param num 截取长度
	 * @return
	 */
	public static String getLastString(String str, int num) {
		if (StringUtils.isNotBlank(str)) {
			return str.substring(str.length() - num, str.length());
		}
		return str;
	}

	/**
	 * 将指定字符串首字母转换成大写字母
	 *
	 * @param str 指定字符串
	 * @return 返回首字母大写的字符串
	 */
	public static String firstCharUpperCase(String str) {
		StringBuffer buffer = new StringBuffer(str);
		if (buffer.length() > 0) {
			char c = buffer.charAt(0);
			buffer.setCharAt(0, Character.toUpperCase(c));
		}
		return buffer.toString();
	}

	/**
	 * 组装成json集合
	 * 
	 * @param map
	 * @return
	 */
	public static JSONArray getJsonArray(Map<String, String> map) {
		JSONArray array = new JSONArray();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			JSONObject json = new JSONObject();
			json.put("key", entry.getKey());
			json.put("value", entry.getValue());
			array.add(json);
		}
		return array;
	}

	/**
	 * 提取字符串中的变量字符串</br>
	 * 
	 * @param document 例如:尊敬的#custlname#先生（女士）,您欠款金额#account.acctupamt#元
	 * @return list:"custlname","account.acctupamt"集合
	 */
	public static List<String> getTemplateParams(String document) {
		// ContentTemplate contentTemplate = (ContentTemplate)
		// GenericDao.getInstance().get(ContentTemplate.class,contentTemplateId);
		int index = document.indexOf("#");
		int nextIndex = -1;
		List<String> params = new ArrayList<String>();
		int i = 0;
		while ((nextIndex = document.indexOf("#", index + 1)) != -1) {
			if (i % 2 == 0) {
				params.add(document.substring(index + 1, nextIndex));
			}
			index = nextIndex;
			i++;
		}
		return params;
	}

	/**
	 * str转Map
	 * 
	 * @param str
	 * @return
	 */
	public static Map<String, String> StringToMap(String str, String replaceChar) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		Map<String, Integer> sizeMap = new HashMap<String, Integer>();
		if (StringUtils.isNotBlank(str)) {
			String strArray[] = str.split(replaceChar);

			for (int i = 0; i < strArray.length; i++) {
				String array = strArray[i].trim();
				String key = array.substring(0, array.indexOf(" "));
				String value = array.substring(array.indexOf(" ") + 1, array.length());
				if (map.containsKey(key)) {
					int size = Integer.parseInt(sizeMap.get(key + "SIZE").toString()) + 1;
					map.put(key + (size - 1), value);
					map.put(key + "SIZE", size + "");
					sizeMap.put(key + "SIZE", size);
				} else {
					map.put(key, value);
					sizeMap.put(key + "SIZE", 1);
				}
			}
		}
		return map;
	}

	public static Map<String, String> StringToMap2(String str, String replaceChar) {
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtils.isNotBlank(str)) {
			String strArray[] = str.split(replaceChar);
			for (int i = 0; i < strArray.length; i++) {
				String array = strArray[i].trim();
				String key = array.substring(0, array.indexOf(" "));
				String value = array.substring(array.indexOf(" ") + 1, array.length());
				map.put(key, value);
			}
		}
		return map;
	}

	/**
	 * 判断数组中重复元素的数量
	 * 
	 * @param str
	 * @return
	 */
	public static Map<String, Integer> ArrayRepeatCount(String str[]) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < str.length; i++) {
			String temp = str[i];
			int count = 0;
			for (int j = 0; j < str.length; j++) {
				String temp2 = str[j];
				if (temp.equals(temp2)) {
					count++;
				}
				map.put(str[i], count);
			}
		}
		return map;
	}

	/**
	 * 打印参数值
	 * 
	 * @param objects
	 * @return
	 */
	public static String getObjParamValue(Object[] objects) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < objects.length; i++) {
			buffer.append(" (入参值" + (i + 1) + ")= " + objects[i]);
		}
		return buffer.toString();
	}

	/**
	 * 获取异常字符串
	 * 
	 * @param e
	 * @return
	 */
	public static String getExceptionMsg(Throwable e, int length) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		e.printStackTrace(pw);
		String errstr = sw.toString();
		String errmsg = errstr.substring(0, (errstr.length() < length ? errstr.length() : length)) + " ......";
		return errmsg;
	}

	/**
	 * 毫秒转化时分秒毫秒
	 * 
	 * @param ms
	 * @return
	 */
	public static String formatTime(Long ms) {
		Integer ss = 1000;
		Integer mi = ss * 60;
		Integer hh = mi * 60;
		Integer dd = hh * 24;

		Long day = ms / dd;
		Long hour = (ms - day * dd) / hh;
		Long minute = (ms - day * dd - hour * hh) / mi;
		Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
		// Long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

		StringBuffer sb = new StringBuffer();
		if (day > 0) {
			sb.append(day + "天");
		}
		if (hour > 0) {
			sb.append(hour + "小时");
		}
		if (minute > 0) {
			sb.append(minute + "分");
		}
		if (second > 0) {
			sb.append(second + "秒");
		} else if (ms < 1000 && ms > 0) {
			sb.append("1秒");
		} else if (ms == 0L) {
			sb.append("0秒");
		}
		// if(milliSecond > 0) {
		// sb.append(milliSecond+"毫秒");
		// }
		return sb.toString();
	}

	/**
	 * 毫秒转化时分秒毫秒
	 * 
	 * @param ms
	 * @return
	 */
	public static String formatTime(Long ms, String date) {
		Integer ss = 1000;
		Integer mi = ss * 60;
		Integer hh = mi * 60;
		Integer dd = hh * 24;

		Long day = ms / dd;
		Long hour = (ms - day * dd) / hh;
		Long minute = (ms - day * dd - hour * hh) / mi;
		Long second = (ms - day * dd - hour * hh - minute * mi) / ss;
		String sb = "";

		if (day > 0) {
			sb = date;
			return sb;
		}
		if (hour > 0) {
			sb = hour + "小时前";
			return sb;
		}
		if (minute > 0) {
			sb = minute + "分钟前";
			return sb;
		}

		if (second >= 0L) {
			sb = "刚刚";
			return sb;
		}

		return sb;
	}

	public static final char UNDERLINE = '_';

	private static String camelToUnderline(String str) {
		if (str == null || str.trim().isEmpty()) {
			return "";
		}

		int len = str.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(UNDERLINE);
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	// 驼峰命名转下划线命名
	public static String jsonKeyEdit(String jsonStr) {

		// 转换后的字符串
		String str = jsonStr;

		// 满足json字符串key的正则
		String regx = "\"\\w+\":";

		// 1.将正在表达式封装成对象Patten 类来实现
		Pattern pattern = Pattern.compile(regx);

		// 2.将字符串和正则表达式相关联
		Matcher matcher = pattern.matcher(jsonStr);

		// 查找符合规则的子串
		while (matcher.find()) {
			// 取代驼峰命名法的key为下划线的key
			str = str.replaceFirst(matcher.group(), camelToUnderline(matcher.group()));
		}
		return str;
	}

	public static String formatAMT(BigDecimal amt) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(amt);
	}

	/**
	 * URLEncoder
	 */
	public static String encodeUrl(String url) {
		try {
			return URLEncoder.encode(url, "UTF-8");
		} catch (Exception e) {
			logger.error("", e);
			throw new RuntimeException(e);
		}
	}

	public static byte[] getBytes(String o) throws Exception{
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
		objectOutputStream.writeObject(o);
		return byteArrayOutputStream.toByteArray();
	}

}