package com.lc.sofa.core.framework.util;

import java.math.BigDecimal;

/**
 * 
 * 浮点数计算工具
 * @author     admin
 * @version 1.0, 2013-10-25
 * @since 1.0, 2013-10-25
 */
public class ArthUtil {

	// 默认除法运算精度
		private static final int DEF_DIV_SCALE = 15; // 使用double默认精度和vb相同，应该能满足要求
		private static final String DECMSG = "小数位数不得小于0！";

		// 这个类不能实例化
		private ArthUtil() {
		}

		/**
		 * 提供精确的加法运算。
		 * 
		 * @param v1
		 *            被加数
		 * @param v2
		 *            加数
		 * @return 两个参数的和
		 */
		public static final double add(double v1, double v2) {
			return add(v1, v2, 0, 0, 0, 0, 0, 0, 0, 2);
		}

		public static final double add(double v1, double v2, double v3) {
			return add(v1, v2, v3, 0, 0, 0, 0, 0, 0, 3);
		}

		public static final double add(double v1, double v2, double v3, double v4) {
			return add(v1, v2, v3, v4, 0, 0, 0, 0, 0, 4);
		}

		public static final double add(double v1, double v2, double v3, double v4,
				double v5) {
			return add(v1, v2, v3, v4, v5, 0, 0, 0, 0, 5);
		}

		public static final double add(double v1, double v2, double v3, double v4,
				double v5, double v6) {
			return add(v1, v2, v3, v4, v5, v6, 0, 0, 0, 6);
		}

		public static final double add(double v1, double v2, double v3, double v4,
				double v5, double v6, double v7) {
			return add(v1, v2, v3, v4, v5, v6, v7, 0, 0, 7);
		}

		public static final double add(double v1, double v2, double v3, double v4,
				double v5, double v6, double v7, double v8) {
			return add(v1, v2, v3, v4, v5, v6, v7, v8, 0, 8);
		}

		public static final double add(double v1, double v2, double v3, double v4,
				double v5, double v6, double v7, double v8, double v9) {
			return add(v1, v2, v3, v4, v5, v6, v7, v8, v9, 9);
		}

		private static final double add(double v1, double v2, double v3, double v4,
				double v5, double v6, double v7, double v8, double v9, int n) {
			BigDecimal b1 = new BigDecimal(Double.toString(v1));
			BigDecimal b2 = new BigDecimal(Double.toString(v2));
			BigDecimal b3 = null;
			BigDecimal b4 = null;
			BigDecimal b5 = null;
			BigDecimal b6 = null;
			BigDecimal b7 = null;
			BigDecimal b8 = null;
			BigDecimal b9 = null;

			switch (n) {
			case 2:
				return b1.add(b2).doubleValue();
			case 6:
				b6 = new BigDecimal(Double.toString(v6));
			case 5:
				b5 = new BigDecimal(Double.toString(v5));
			case 4:
				b4 = new BigDecimal(Double.toString(v4));
			case 3:
				b3 = new BigDecimal(Double.toString(v3));
			case 7:
				b7 = new BigDecimal(Double.toString(v7));
			case 8:
				b8 = new BigDecimal(Double.toString(v8));
			case 9:
				b9 = new BigDecimal(Double.toString(v9));
			}

			switch (n) {
			case 3:
				return b1.add(b2).add(b3).doubleValue();
			case 4:
				return b1.add(b2).add(b3).add(b4).doubleValue();
			case 5:
				return b1.add(b2).add(b3).add(b4).add(b5).doubleValue();
			case 6:
				return b1.add(b2).add(b3).add(b4).add(b5).add(b6).doubleValue();
			case 7:
				return b1.add(b2).add(b3).add(b4).add(b5).add(b6).add(b7)
						.doubleValue();
			case 8:
				return b1.add(b2).add(b3).add(b4).add(b5).add(b6).add(b7).add(b8)
						.doubleValue();
			case 9:
				return b1.add(b2).add(b3).add(b4).add(b5).add(b6).add(b7).add(b8)
						.add(b9).doubleValue();
			default:
				return 0;
			}
		}

		/**
		 * 提供精确的减法运算。
		 * 
		 * @param v1
		 *            被减数
		 * @param v2
		 *            减数
		 * @return 两个参数的差
		 */
		public static final double sub(double v1, double v2) {
			return add(v1, -v2, 0, 0, 0, 0, 0, 0, 0, 2);
		}

		public static final double sub(double v1, double v2, double v3) {
			return add(v1, -v2, -v3, 0, 0, 0, 0, 0, 0, 3);
		}

		public static final double sub(double v1, double v2, double v3, double v4) {
			return add(v1, -v2, -v3, -v4, 0, 0, 0, 0, 0, 4);
		}

		public static final double sub(double v1, double v2, double v3, double v4,
				double v5) {
			return add(v1, -v2, -v3, -v4, -v5, 0, 0, 0, 0, 5);
		}

		public static final double sub(double v1, double v2, double v3, double v4,
				double v5, double v6) {
			return add(v1, -v2, -v3, -v4, -v5, -v6, 0, 0, 0, 6);
		}

		/**
		 * 提供精确的乘法运算。
		 * 
		 * @param v1
		 *            被乘数
		 * @param v2
		 *            乘数
		 * @return 两个参数的积
		 */
		public static final double mul(double v1, double v2) {
			return mul(v1, v2, 1, 1, 1, 1, 1, 1, 1, 2);
		}

		public static final double mul(double v1, double v2, double v3) {
			return mul(v1, v2, v3, 1, 1, 1, 1, 1, 1, 3);
		}

		public static final double mul(double v1, double v2, double v3, double v4) {
			return mul(v1, v2, v3, v4, 1, 1, 1, 1, 1, 4);
		}

		public static final double mul(double v1, double v2, double v3, double v4,
				double v5) {
			return mul(v1, v2, v3, v4, v5, 1, 1, 1, 1, 5);
		}

		public static final double mul(double v1, double v2, double v3, double v4,
				double v5, double v6) {
			return mul(v1, v2, v3, v4, v5, v6, 1, 1, 1, 6);
		}

		private static final double mul(double v1, double v2, double v3, double v4,
				double v5, double v6, double v7, double v8, double v9, int n) {
			BigDecimal b1 = new BigDecimal(Double.toString(v1));
			BigDecimal b2 = new BigDecimal(Double.toString(v2));
			BigDecimal b3 = null;
			BigDecimal b4 = null;
			BigDecimal b5 = null;
			BigDecimal b6 = null;
			BigDecimal b7 = null;
			BigDecimal b8 = null;
			BigDecimal b9 = null;
			double result = 0;

			switch (n) {
			case 2:
				result = b1.multiply(b2).doubleValue();
			case 6:
				b6 = new BigDecimal(Double.toString(v6));
			case 5:
				b5 = new BigDecimal(Double.toString(v5));
			case 4:
				b4 = new BigDecimal(Double.toString(v4));
			case 3:
				b3 = new BigDecimal(Double.toString(v3));
			case 7:
				b7 = new BigDecimal(Double.toString(v7));
			case 8:
				b8 = new BigDecimal(Double.toString(v8));
			case 9:
				b9 = new BigDecimal(Double.toString(v9));
			}

			switch (n) {
			case 9:
				result = b1.multiply(b2).multiply(b3).multiply(b4).multiply(b5)
						.multiply(b6).multiply(b7).multiply(b8).multiply(b9)
						.doubleValue();
				break; // 后面添加上break结束 by leeyu 2009-06-02 QDV4招商证券2009年04月09日01_A
						// MS00371
			case 8:
				result = b1.multiply(b2).multiply(b3).multiply(b4).multiply(b5)
						.multiply(b6).multiply(b7).multiply(b8).doubleValue();
				break; // 后面添加上break结束 by leeyu 2009-06-02 QDV4招商证券2009年04月09日01_A
						// MS00371
			case 7:
				result = b1.multiply(b2).multiply(b3).multiply(b4).multiply(b5)
						.multiply(b6).multiply(b7).doubleValue();
				break; // 后面添加上break结束 by leeyu 2009-06-02 QDV4招商证券2009年04月09日01_A
						// MS00371
			case 6:
				result = b1.multiply(b2).multiply(b3).multiply(b4).multiply(b5)
						.multiply(b6).doubleValue();
				break; // 后面添加上break结束 by leeyu 2009-06-02 QDV4招商证券2009年04月09日01_A
						// MS00371
			case 5:
				result = b1.multiply(b2).multiply(b3).multiply(b4).multiply(b5)
						.doubleValue();
				break; // 后面添加上break结束 by leeyu 2009-06-02 QDV4招商证券2009年04月09日01_A
						// MS00371
			case 4:
				result = b1.multiply(b2).multiply(b3).multiply(b4).doubleValue();
				break; // 后面添加上break结束 by leeyu 2009-06-02 QDV4招商证券2009年04月09日01_A
						// MS00371
			case 3:
				result = b1.multiply(b2).multiply(b3).doubleValue();
				break; // 后面添加上break结束 by leeyu 2009-06-02 QDV4招商证券2009年04月09日01_A
						// MS00371
			default:
				result = b1.multiply(b2).doubleValue(); // 默认 返回 b1*b2 by leeyu
														// 2009-06-02
														// QDV4招商证券2009年04月09日01_A
														// MS00371
			}
			return adjustDouble(result);
		}

		/**
		 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到15位有效数字，和VB的兼容
		 * 
		 * @param v1
		 *            被除数
		 * @param v2
		 *            除数
		 * @return 两个参数的商
		 */
		public static final double div(double v1, double v2) {

			if (v2 == 0) { // 保证不会发生除数为0的错误
				v2 = 1;
			}

			BigDecimal b1 = new BigDecimal(Double.toString(v1));
			BigDecimal b2 = new BigDecimal(Double.toString(v2));

			return b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP)
					.doubleValue();

			// 如果b3的有效数字
		}

		/**
		 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
		 * 
		 * @param v1
		 *            被除数
		 * @param v2
		 *            除数
		 * @param scale
		 *            表示表示需要精确到小数点以后几位。
		 * @return 两个参数的商
		 */
		public static final double div(double v1, double v2, int scale) {

			if (scale < 0) {
				throw new IllegalArgumentException(DECMSG);
			}

			if (v2 == 0) { // 保证不会发生除数为0的错误
				v2 = 1;
			}

			BigDecimal b1 = new BigDecimal(Double.toString(v1));
			BigDecimal b2 = new BigDecimal(Double.toString(v2));

			return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		}

		/**
		 * Math.pow的结果最多可以有17位有效数字，本函数控制最多15位，和VB的double兼容
		 * 
		 * @param v1
		 *            double
		 * @param v2
		 *            double
		 * @return double
		 */
		@SuppressWarnings("unused")
		public static final double pow(double v1, double v2) {
			BigDecimal b1 = new BigDecimal(Double.toString(v1));
			BigDecimal b2 = new BigDecimal(Double.toString(v2));
			return adjustDouble(Math.pow(v1, v2));
		}

		/**
		 * 把java产生的double值转换成15位最多有效数字的值，多于小数四舍五入，整数不处理
		 * 
		 * @param v1
		 *            double
		 * @return double
		 */
		public static final double adjustDouble(double v1) { // todo 考虑
																// E多少的问题还有div也可能存在问题

			String strRes = String.valueOf(v1).toUpperCase();
			int i, len = strRes.length();
			int e = strRes.lastIndexOf('E'); // 考虑科学记数法!

			if (len <= DEF_DIV_SCALE || strRes.indexOf('.') < 0) {
				return v1;
			}

			for (i = 0; i < len; i++) {
				if (strRes.charAt(i) != '0' && strRes.charAt(i) != '.') {
					break;
				}
			}
			i = strRes.length() - (strRes.indexOf('.') > i ? i + 1 : i)
					- (e >= 0 ? len - e : 0); // 得出有效位数
			if (i > DEF_DIV_SCALE) {
				if (e < 0) {
					v1 = round(v1, len - strRes.indexOf('.') - 1 - i
							+ DEF_DIV_SCALE);
				} else { // 带科学记数法的处理: 先处理E前面的部分，再附加E部分，再转换成double
					v1 = Double.parseDouble(strRes.substring(0,
							e - i + DEF_DIV_SCALE).concat(strRes.substring(e)));
				}
			}

			return v1;
		}

		/**
		 * 比较两个double数值的大小
		 * 
		 * @param v1
		 *            double
		 * @param v2
		 *            double
		 * @return int：1,0或者-1，分别代表>,=和<
		 */
		public static final int compare(double v1, double v2) {
			return new BigDecimal(Double.toString(v1)).compareTo(new BigDecimal(
					Double.toString(v2)));
		}

		/**
		 * 提供精确的小数位四舍五入处理。
		 * 
		 * @param v
		 *            需要四舍五入的数字
		 * @param scale
		 *            小数点后保留几位
		 * @return 四舍五入后的结果
		 */
		public static final double round(double v, int scale, boolean bTrunc) {
			if (scale < 0) {
				throw new IllegalArgumentException(DECMSG);
			}

			BigDecimal b = new BigDecimal(Double.toString(v));
			BigDecimal one = new BigDecimal("1");

			return b.divide(one, scale,
					bTrunc ? BigDecimal.ROUND_DOWN : BigDecimal.ROUND_HALF_UP)
					.doubleValue();
		}

		public static final double round(double v, int scale) {
			return round(v, scale, false);
		}

		public static final double round(double v, int scale, int iModel) {

			if (scale < 0) {

				throw new IllegalArgumentException(

				DECMSG);

			}

			BigDecimal b = new BigDecimal(Double.toString(v));

			BigDecimal one = new BigDecimal("1");

			return b.divide(one, scale, iModel).doubleValue();

		}

		/**
		 * 提供精确的小数位四舍五入处理。
		 * 
		 * @param v
		 *            需要四舍五入的数字
		 * @param scale
		 *            小数点后保留几位
		 * @return 四舍五入后的结果
		 */
		public static final double round(BigDecimal v, int scale, boolean bTrunc) {
			if (scale < 0) {
				throw new IllegalArgumentException(DECMSG);
			}

			BigDecimal b = v;
			BigDecimal one = new BigDecimal("1");

			return b.divide(one, scale,
					bTrunc ? BigDecimal.ROUND_DOWN : BigDecimal.ROUND_HALF_UP)
					.doubleValue();
		}

		/**
		 * 2009-07-22 蒋锦 添加 MS00022 国内债券业务 QDV4.1赢时胜（上海）2009年4月20日22_A 返回高精度值的舍入方法
		 * 
		 * @param v
		 *            BigDecimal
		 * @param scale
		 *            int
		 * @param bTrunc
		 *            boolean
		 * @return BigDecimal
		 */
		public static final BigDecimal roundD(BigDecimal v, int scale,
				boolean bTrunc) {
			if (scale < 0) {
				throw new IllegalArgumentException(DECMSG);
			}

			BigDecimal b = v;
			BigDecimal one = new BigDecimal("1");

			return b.divide(one, scale, bTrunc ? BigDecimal.ROUND_DOWN
					: BigDecimal.ROUND_HALF_UP);
		}

		public static final double round(BigDecimal v, int scale) {
			return round(v, scale, false);
		}

		/**
		 * 2009-07-22 蒋锦 添加 MS00022 国内债券业务 QDV4.1赢时胜（上海）2009年4月20日22_A 返回高精度值的舍入方法
		 * 
		 * @param v
		 *            BigDecimal
		 * @param scale
		 *            int
		 * @return BigDecimal
		 */
		public static final BigDecimal roundD(BigDecimal v, int scale) {
			return roundD(v, scale, false);
		}

		/**
		 * 提供精确的加法运算。
		 * 
		 * @param v1
		 *            被加数
		 * @param v2
		 *            加数
		 * @return 两个参数的和
		 */

		public static final double add(BigDecimal v1, BigDecimal v2) {
			return adjustDouble(addD(v1, v2, new BigDecimal("0"),
					new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"),
					2).doubleValue());
		}

		public static final double add(BigDecimal v1, BigDecimal v2, BigDecimal v3) {
			return adjustDouble(addD(v1, v2, v3, new BigDecimal("0"),
					new BigDecimal("0"), new BigDecimal("0"), 3).doubleValue());
		}

		public static final double add(BigDecimal v1, BigDecimal v2, BigDecimal v3,
				BigDecimal v4) {
			return adjustDouble(addD(v1, v2, v3, v4, new BigDecimal("0"),
					new BigDecimal("0"), 4).doubleValue());
		}

		public static final double add(BigDecimal v1, BigDecimal v2, BigDecimal v3,
				BigDecimal v4, BigDecimal v5) {
			return adjustDouble(addD(v1, v2, v3, v4, v5, new BigDecimal("0"), 5)
					.doubleValue());
		}

		public static final double add(BigDecimal v1, BigDecimal v2, BigDecimal v3,
				BigDecimal v4, BigDecimal v5, BigDecimal v6) {
			return adjustDouble(addD(v1, v2, v3, v4, v5, v6, 6).doubleValue());
		}

		public static final BigDecimal addD(double v1, double v2) {
			return addD(v1, v2, 0, 0, 0, 0, 2);
		}

		public static final BigDecimal addD(double v1, double v2, double v3) {
			return addD(v1, v2, v3, 0, 0, 0, 3);
		}

		public static final BigDecimal addD(double v1, double v2, double v3,
				double v4) {
			return addD(v1, v2, v3, v4, 0, 0, 4);
		}

		public static final BigDecimal addD(double v1, double v2, double v3,
				double v4, double v5) {
			return addD(v1, v2, v3, v4, v5, 0, 5);
		}

		private static final BigDecimal addD(double v1, double v2, double v3,
				double v4, double v5, double v6, int n) {
			return addD(new BigDecimal(Double.toString(v1)), new BigDecimal(Double
					.toString(v2)), new BigDecimal(Double.toString(v3)),
					new BigDecimal(Double.toString(v4)), new BigDecimal(Double
							.toString(v5)), new BigDecimal(Double.toString(v6)), n);
		}

		public static final BigDecimal addD(BigDecimal v1, BigDecimal v2) {
			return addD(v1, v2, new BigDecimal("0"), new BigDecimal("0"),
					new BigDecimal("0"), new BigDecimal("0"), 2);
		}

		public static final BigDecimal addD(BigDecimal v1, BigDecimal v2,
				BigDecimal v3) {
			return addD(v1, v2, v3, new BigDecimal("0"), new BigDecimal("0"),
					new BigDecimal("0"), 3);
		}

		public static final BigDecimal addD(BigDecimal v1, BigDecimal v2,
				BigDecimal v3, BigDecimal v4) {
			return addD(v1, v2, v3, v4, new BigDecimal("0"), new BigDecimal("0"), 4);
		}

		public static final BigDecimal addD(BigDecimal v1, BigDecimal v2,
				BigDecimal v3, BigDecimal v4, BigDecimal v5) {
			return addD(v1, v2, v3, v4, v5, new BigDecimal("0"), 5);
		}

		public static final BigDecimal addD(BigDecimal v1, BigDecimal v2,
				BigDecimal v3, BigDecimal v4, BigDecimal v5, BigDecimal v6) {
			return addD(v1, v2, v3, v4, v5, v6, 6);
		}

		private static final BigDecimal addD(BigDecimal v1, BigDecimal v2,
				BigDecimal v3, BigDecimal v4, BigDecimal v5, BigDecimal v6, int n) {
			BigDecimal b1 = v1;
			BigDecimal b2 = v2;
			BigDecimal b3 = v3;
			BigDecimal b4 = v4;
			BigDecimal b5 = v5;
			BigDecimal b6 = v6;

			switch (n) {
			case 2:
				return b1.add(b2);
			case 3:
				return b1.add(b2).add(b3);
			case 4:
				return b1.add(b2).add(b3).add(b4);
			case 5:
				return b1.add(b2).add(b3).add(b4).add(b5);
			case 6:
				return b1.add(b2).add(b3).add(b4).add(b5).add(b6);
			default:
				return new BigDecimal("0");
			}
		}

		/**
		 * 提供精确的减法运算。
		 * 
		 * @param v1
		 *            被减数
		 * @param v2
		 *            减数
		 * @return 两个参数的差
		 */

		public static final double sub(BigDecimal v1, BigDecimal v2) {
			return adjustDouble(subD(v1, v2, new BigDecimal("0"),
					new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"),
					2).doubleValue());
		}

		public static final double sub(BigDecimal v1, BigDecimal v2, BigDecimal v3) {
			return adjustDouble(subD(v1, v2, v3, new BigDecimal("0"),
					new BigDecimal("0"), new BigDecimal("0"), 3).doubleValue());
		}

		public static final double sub(BigDecimal v1, BigDecimal v2, BigDecimal v3,
				BigDecimal v4) {
			return adjustDouble(subD(v1, v2, v3, v4, new BigDecimal("0"),
					new BigDecimal("0"), 4).doubleValue());
		}

		public static final double sub(BigDecimal v1, BigDecimal v2, BigDecimal v3,
				BigDecimal v4, BigDecimal v5) {
			return adjustDouble(subD(v1, v2, v3, v4, v5, new BigDecimal("0"), 5)
					.doubleValue());
		}

		public static final double sub(BigDecimal v1, BigDecimal v2, BigDecimal v3,
				BigDecimal v4, BigDecimal v5, BigDecimal v6) {
			return adjustDouble(subD(v1, v2, v3, v4, v5, v6, 6).doubleValue());
		}

		public static final BigDecimal subD(double v1, double v2) {
			return subD(v1, v2, 0, 0, 0, 0, 2);
		}

		public static final BigDecimal subD(double v1, double v2, double v3) {
			return subD(v1, v2, v3, 0, 0, 0, 3);
		}

		public static final BigDecimal subD(double v1, double v2, double v3,
				double v4) {
			return subD(v1, v2, v3, v4, 0, 0, 4);
		}

		public static final BigDecimal subD(double v1, double v2, double v3,
				double v4, double v5) {
			return subD(v1, v2, v3, v4, v5, 0, 5);
		}

		private static final BigDecimal subD(double v1, double v2, double v3,
				double v4, double v5, double v6, int n) {
			return subD(new BigDecimal(Double.toString(v1)), new BigDecimal(Double
					.toString(v2)), new BigDecimal(Double.toString(v3)),
					new BigDecimal(Double.toString(v4)), new BigDecimal(Double
							.toString(v5)), new BigDecimal(Double.toString(v6)), n);
		}

		public static final BigDecimal addD(double v1, double v2, double v3,
				double v4, double v5, double v6) {
			return addD(v1, v2, v3, v4, v5, v6, 6);
		}

		public static final BigDecimal subD(BigDecimal v1, BigDecimal v2) {
			return subD(v1, v2, new BigDecimal("0"), new BigDecimal("0"),
					new BigDecimal("0"), new BigDecimal("0"), 2);
		}

		public static final BigDecimal subD(BigDecimal v1, BigDecimal v2,
				BigDecimal v3) {
			return subD(v1, v2, v3, new BigDecimal("0"), new BigDecimal("0"),
					new BigDecimal("0"), 3);
		}

		public static final BigDecimal subD(BigDecimal v1, BigDecimal v2,
				BigDecimal v3, BigDecimal v4) {
			return subD(v1, v2, v3, v4, new BigDecimal("0"), new BigDecimal("0"), 4);
		}

		public static final BigDecimal subD(BigDecimal v1, BigDecimal v2,
				BigDecimal v3, BigDecimal v4, BigDecimal v5) {
			return subD(v1, v2, v3, v4, v5, new BigDecimal("0"), 5);
		}

		public static final BigDecimal subD(BigDecimal v1, BigDecimal v2,
				BigDecimal v3, BigDecimal v4, BigDecimal v5, BigDecimal v6) {
			return subD(v1, v2, v3, v4, v5, v6, 6);
		}

		private static final BigDecimal subD(BigDecimal v1, BigDecimal v2,
				BigDecimal v3, BigDecimal v4, BigDecimal v5, BigDecimal v6, int n) {
			BigDecimal b1 = v1;
			BigDecimal b2 = v2;
			BigDecimal b3 = v3;
			BigDecimal b4 = v4;
			BigDecimal b5 = v5;
			BigDecimal b6 = v6;

			switch (n) {
			case 2:
				return b1.subtract(b2);
			case 3:
				return b1.subtract(b2).subtract(b3);
			case 4:
				return b1.subtract(b2).subtract(b3).subtract(b4);
			case 5:
				return b1.subtract(b2).subtract(b3).subtract(b4).subtract(b5);
			case 6:
				return b1.subtract(b2).subtract(b3).subtract(b4).subtract(b5)
						.subtract(b6);
			default:
				return new BigDecimal("0");
			}
		}

		/**
		 * 提供精确的乘法运算。
		 * 
		 * @param v1
		 *            被乘数
		 * @param v2
		 *            乘数
		 * @return 两个参数的积
		 */

		public static final double mul(BigDecimal v1, BigDecimal v2) {
			return adjustDouble(mulD(v1, v2, new BigDecimal("0"),
					new BigDecimal("0"), new BigDecimal("0"), new BigDecimal("0"),
					2).doubleValue());
		}

		public static final double mul(BigDecimal v1, BigDecimal v2, BigDecimal v3) {
			return adjustDouble(mulD(v1, v2, v3, new BigDecimal("0"),
					new BigDecimal("0"), new BigDecimal("0"), 3).doubleValue());
		}

		public static final double mul(BigDecimal v1, BigDecimal v2, BigDecimal v3,
				BigDecimal v4) {
			return adjustDouble(mulD(v1, v2, v3, v4, new BigDecimal("0"),
					new BigDecimal("0"), 4).doubleValue());
		}

		public static final double mul(BigDecimal v1, BigDecimal v2, BigDecimal v3,
				BigDecimal v4, BigDecimal v5) {
			return adjustDouble(mulD(v1, v2, v3, v4, v5, new BigDecimal("0"), 5)
					.doubleValue());
		}

		public static final double mul(BigDecimal v1, BigDecimal v2, BigDecimal v3,
				BigDecimal v4, BigDecimal v5, BigDecimal v6) {
			return adjustDouble(mulD(v1, v2, v3, v4, v5, v6, 6).doubleValue());
		}

		public static final BigDecimal mulD(double v1, double v2) {
			return mulD(v1, v2, 0, 0, 0, 0, 2);
		}

		public static final BigDecimal mulD(double v1, double v2, double v3) {
			return mulD(v1, v2, v3, 0, 0, 0, 3);
		}

		public static final BigDecimal mulD(double v1, double v2, double v3,
				double v4) {
			return mulD(v1, v2, v3, v4, 0, 0, 4);
		}

		public static final BigDecimal mulD(double v1, double v2, double v3,
				double v4, double v5) {
			return mulD(v1, v2, v3, v4, v5, 0, 5);
		}

		private static final BigDecimal mulD(double v1, double v2, double v3,
				double v4, double v5, double v6, int n) {
			return mulD(new BigDecimal(Double.toString(v1)), new BigDecimal(Double
					.toString(v2)), new BigDecimal(Double.toString(v3)),
					new BigDecimal(Double.toString(v4)), new BigDecimal(Double
							.toString(v5)), new BigDecimal(Double.toString(v6)), n);
		}

		public static final BigDecimal mulD(double v1, double v2, double v3,
				double v4, double v5, double v6) {
			return mulD(v1, v2, v3, v4, v5, v6, 6);
		}

		public static final BigDecimal mulD(BigDecimal v1, BigDecimal v2) {
			return mulD(v1, v2, new BigDecimal(1), new BigDecimal(1),
					new BigDecimal(1), new BigDecimal(1), 2);
		}

		public static final BigDecimal mulD(BigDecimal v1, BigDecimal v2,
				BigDecimal v3) {
			return mulD(v1, v2, v3, new BigDecimal(1), new BigDecimal(1),
					new BigDecimal(1), 3);
		}

		public static final BigDecimal mulD(BigDecimal v1, BigDecimal v2,
				BigDecimal v3, BigDecimal v4) {
			return mulD(v1, v2, v3, v4, new BigDecimal(1), new BigDecimal(1), 4);
		}

		public static final BigDecimal mulD(BigDecimal v1, BigDecimal v2,
				BigDecimal v3, BigDecimal v4, BigDecimal v5) {
			return mulD(v1, v2, v3, v4, v5, new BigDecimal(1), 5);
		}

		public static final BigDecimal mulD(BigDecimal v1, BigDecimal v2,
				BigDecimal v3, BigDecimal v4, BigDecimal v5, BigDecimal v6) {
			return mulD(v1, v2, v3, v4, v5, v6, 6);
		}

		private static final BigDecimal mulD(BigDecimal v1, BigDecimal v2,
				BigDecimal v3, BigDecimal v4, BigDecimal v5, BigDecimal v6, int n) {
			BigDecimal b1 = v1;
			BigDecimal b2 = v2;
			BigDecimal b3 = null;
			BigDecimal b4 = null;
			BigDecimal b5 = null;
			BigDecimal b6 = null;

			switch (n) {
			case 6:
				return b1.multiply(b2).multiply(b3).multiply(b4).multiply(b5)
						.multiply(b6);
			case 5:
				return b1.multiply(b2).multiply(b3).multiply(b4).multiply(b5);
			case 4:
				return b1.multiply(b2).multiply(b3).multiply(b4);
			case 3:
				return b1.multiply(b2).multiply(b3);
			case 2:
				return b1.multiply(b2);
			default:
				return new BigDecimal("0");
			}
		}

		/**
		 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到15位有效数字，和VB的兼容
		 * 
		 * @param v1
		 *            被除数
		 * @param v2
		 *            除数
		 * @return 两个参数的商
		 */

		public static final double div(BigDecimal v1, BigDecimal v2) {
			return adjustDouble(divD(v1, v2).doubleValue());
		}

		public static final BigDecimal divD(double v1, double v2) {
			return divD(new BigDecimal(Double.toString(v1)), new BigDecimal(Double
					.toString(v2)));
		}

		public static final BigDecimal divD(BigDecimal v1, BigDecimal v2) {

			BigDecimal b1 = v1;
			BigDecimal b2 = v2;

			return b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);

			// 如果b3的有效数字
		}

		/**
		 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
		 * 
		 * @param v1
		 *            被除数
		 * @param v2
		 *            除数
		 * @param scale
		 *            表示表示需要精确到小数点以后几位。
		 * @return 两个参数的商
		 */

		public static final double div(BigDecimal v1, BigDecimal v2, int scale) {
			return adjustDouble(divD(v1, v2, scale).doubleValue());
		}

		public static final BigDecimal divD(double v1, double v2, int scale) {
			return divD(new BigDecimal(Double.toString(v1)), new BigDecimal(Double
					.toString(v2)), scale);
		}

		public static final BigDecimal divD(BigDecimal v1, BigDecimal v2, int scale) {

			if (scale < 0) {
				throw new IllegalArgumentException(DECMSG);
			}

			BigDecimal b1 = v1;
			BigDecimal b2 = v2;

			return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
		}

		/**
		 * Math.pow的结果最多可以有17位有效数字，本函数控制最多15位，和VB的double兼容
		 * 
		 * @param v1
		 *            double
		 * @param v2
		 *            double
		 * @return double
		 */
		public static final double powD(BigDecimal v1, BigDecimal v2) {
			return Math.pow(v1.doubleValue(), v2.doubleValue());
		}
}
