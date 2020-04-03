package com.example.demo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.util.Random;

//http://blog.csdn.net/u011068702/article/details/51857557
//	System.out.println(ByteOrder.nativeOrder());
public class Toolkit {

	private static String TAG = Toolkit.class.getName();

	// 大字节序
	public static byte[] intToBigEndianByte(int i) {
		// byte[] bt = new byte[4];
		// bt[0] = (byte) (0xff & i);
		// bt[1] = (byte) ((0xff00 & i) >> 8);
		// bt[2] = (byte) ((0xff0000 & i) >> 16);
		// bt[3] = (byte) ((0xff000000 & i) >> 24);
		// return bt;

		byte[] bt = new byte[4];
		bt[3] = (byte) (0xff & i);
		bt[2] = (byte) ((0xff00 & i) >> 8);
		bt[1] = (byte) ((0xff0000 & i) >> 16);
		bt[0] = (byte) ((0xff000000 & i) >> 24);
		return bt;
	}

	// 小字节序
	public static byte[] intToLittleEndianByte(int i) {
		// byte[] bt = new byte[4];
		// bt[3] = (byte) (0xff & i);
		// bt[2] = (byte) ((0xff00 & i) >> 8);
		// bt[1] = (byte) ((0xff0000 & i) >> 16);
		// bt[0] = (byte) ((0xff000000 & i) >> 24);
		// return bt;

		byte[] bt = new byte[4];
		bt[0] = (byte) (0xff & i);
		bt[1] = (byte) ((0xff00 & i) >> 8);
		bt[2] = (byte) ((0xff0000 & i) >> 16);
		bt[3] = (byte) ((0xff000000 & i) >> 24);
		return bt;
	}

	// 大字节序
	public static int bigEndianByteToInt(byte[] bytes) {
		// int num = bytes[0] & 0xFF;
		// num |= ((bytes[1] << 8) & 0xFF00);
		// num |= ((bytes[2] << 16) & 0xFF0000);
		// num |= ((bytes[3] << 24) & 0xFF000000);
		// return num;

		int num = bytes[3] & 0xFF;
		num |= ((bytes[2] << 8) & 0xFF00);
		num |= ((bytes[1] << 16) & 0xFF0000);
		num |= ((bytes[0] << 24) & 0xFF000000);

		// Log.i(TAG, "***length byte :" + bytes[0] + "," + bytes[1] + ","
		// + bytes[2] + "," + bytes[3]);
		return num;
	}

	// （小字节序）
	public static int littleEndianByteToInt(byte[] bytes) {
		// int num = bytes[3] & 0xFF;
		// num |= ((bytes[2] << 8) & 0xFF00);
		// num |= ((bytes[1] << 16) & 0xFF0000);
		// num |= ((bytes[0] << 24) & 0xFF000000);
		//
		// Log.i(TAG, "***length byte :" + bytes[0] + "," + bytes[1] + ","
		// + bytes[2] + "," + bytes[3]);
		// return num;

		int num = bytes[0] & 0xFF;
		num |= ((bytes[1] << 8) & 0xFF00);
		num |= ((bytes[2] << 16) & 0xFF0000);
		num |= ((bytes[3] << 24) & 0xFF000000);
		return num;
	}

	/**
	 * 将short转为低字节在前，高字节在后的byte数组 (大字节序)
	 * 
	 * @param n
	 *            short
	 * @return byte[]
	 */
	public static byte[] shortToBigEndianByte(short n) {
		// byte[] b = new byte[2];
		// b[0] = (byte) (n & 0xff);
		// b[1] = (byte) (n >> 8 & 0xff);
		// return b;

		byte[] b = new byte[2];
		b[1] = (byte) (n & 0xff);
		b[0] = (byte) (n >> 8 & 0xff);
		return b;
	}

	/**
	 * 将short转为高字节在前，低字节在后的byte数组(小字节序)
	 * 
	 * @param n
	 *            short
	 * @return byte[]
	 */
	public static byte[] shortToLittleEndianByte(short n) {
		// byte[] b = new byte[2];
		// b[1] = (byte) (n & 0xff);
		// b[0] = (byte) (n >> 8 & 0xff);
		// return b;

		byte[] b = new byte[2];
		b[0] = (byte) (n & 0xff);
		b[1] = (byte) (n >> 8 & 0xff);
		return b;
	}

	/**
	 * 高字节数组到short的转换 (大字节序)
	 * 
	 * @param b
	 *            byte[]
	 * @return short
	 */
	public static short bigEndianByteToShort(byte[] b) {
		// int s = 0;
		// if (b[0] >= 0) {
		// s = s + b[0];
		// } else {
		// s = s + 256 + b[0];
		// }
		// s = s * 256;
		// if (b[1] >= 0) {
		// s = s + b[1];
		// } else {
		// s = s + 256 + b[1];
		// }
		// short result = (short) s;
		// return result;

		int s = 0;
		if (b[1] >= 0) {
			s = s + b[1];
		} else {
			s = s + 256 + b[1];
		}
		s = s * 256;
		if (b[0] >= 0) {
			s = s + b[0];
		} else {
			s = s + 256 + b[0];
		}
		short result = (short) s;
		return result;
	}

	/**
	 * 低字节数组到short的转换 (小字节序)
	 * 
	 * @param b
	 *            byte[]
	 * @return short
	 */
	public static short littleEndianByteToShort(byte[] b) {
		// int s = 0;
		// if (b[1] >= 0) {
		// s = s + b[1];
		// } else {
		// s = s + 256 + b[1];
		// }
		// s = s * 256;
		// if (b[0] >= 0) {
		// s = s + b[0];
		// } else {
		// s = s + 256 + b[0];
		// }
		// short result = (short) s;
		// return result;

		int s = 0;
		if (b[0] >= 0) {
			s = s + b[0];
		} else {
			s = s + 256 + b[0];
		}
		s = s * 256;
		if (b[1] >= 0) {
			s = s + b[1];
		} else {
			s = s + 256 + b[1];
		}
		short result = (short) s;
		return result;
	}

	// 01:00:00
	public static int toSecond(String time) {
		String[] timeArray = time.split(":");
		int hour = Integer.parseInt(timeArray[0]);
		int minute = Integer.parseInt(timeArray[1]);
		int second = 0;
		if (timeArray.length > 2)
			second = Integer.parseInt(timeArray[2]);
		second += hour * 3600 + minute * 60;
		return second;
	}

	public static String toTimeStr(int second) {
		// 小时
		int hour = second / 3600;
		int minute = 0;
		second = second - hour * 3600;
		if (second > 0) {
			minute = second / 60;
			second = second - minute * 60;
		}
		String mStr, hStr, sStr;
		if (hour < 10)
			hStr = "0" + hour;
		else
			hStr = String.valueOf(hour);

		if (minute < 10)
			mStr = "0" + minute;
		else
			mStr = String.valueOf(minute);

		if (second < 10)
			sStr = "0" + second;
		else
			sStr = String.valueOf(second);
		return hStr + ":" + mStr + ":" + sStr;
	}

	/**
	 * 4个字节数组转成long型
	 * 
	 * @param crcBytes
	 * @return
	 */
	public static long bytesToLongValue(byte[] crcBytes) {
		ByteBuffer reverse = ByteBuffer.allocate(8);
		reverse.put(new byte[] { 0, 0, 0, 0 });
		reverse.put(crcBytes);
		reverse.rewind();
		long crcVal = reverse.getLong();
		return crcVal;
	}

	public static final long getLongForByte(byte[] b) {
		if (b == null)
			return -1;
		long i = 0;
		int j = (b.length - 1) * 8;
		for (int k = 0; j >= 0; k++) {
			i += (b[k] & 0xff) << j;
			j -= 8;
		}
		return i;
	}

	public static final Float floatRound(float f) {
		BigDecimal b = new BigDecimal(f);
		float f1 = b.setScale(2, RoundingMode.HALF_UP).floatValue();
		return f1;
	}

	public static final int floatRoundToInt(float f) {
		BigDecimal b = new BigDecimal(f);
		float f1 = b.setScale(0, RoundingMode.HALF_UP).floatValue();
		return (int) f1;
	}
	/** 
     * @功能: 10进制串转为BCD码 
     * @参数: 10进制串 
     * @结果: BCD码 
     */  
    public static byte[] str2Bcd(String asc) {  
        int len = asc.length();  
        int mod = len % 2;  
        if (mod != 0) {  
            asc = "0" + asc;  
            len = asc.length();  
        }  
        byte abt[] = new byte[len];  
        if (len >= 2) {  
            len = len / 2;  
        }  
        byte bbt[] = new byte[len];  
        abt = asc.getBytes();  
        int j, k;  
        for (int p = 0; p < asc.length() / 2; p++) {  
            if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {  
                j = abt[2 * p] - '0';  
            } else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {  
                j = abt[2 * p] - 'a' + 0x0a;  
            } else {  
                j = abt[2 * p] - 'A' + 0x0a;  
            }  
            if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {  
                k = abt[2 * p + 1] - '0';  
            } else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {  
                k = abt[2 * p + 1] - 'a' + 0x0a;  
            } else {  
                k = abt[2 * p + 1] - 'A' + 0x0a;  
            }  
            int a = (j << 4) + k;  
            byte b = (byte) a;  
            
            int unsignedByte = b >= 0 ? b : 256 + b;
            System.out.println("unsigen:"+ unsignedByte);
            
            bbt[p] = b;
        }
        /*
        byte[] newByte = new byte[bbt.length];
        for(int i=0;i<bbt.length;i++){
        	newByte[i] = bbt[bbt.length-1-i];
        }*/
        
        return bbt;  
    }  
    
    /** 
     * @功能: BCD码转为10进制串(阿拉伯数据) 
     * @参数: BCD码 
     * @结果: 10进制串 
     */  
    public static String bcd2Str(byte[] bytes) {  
        StringBuffer temp = new StringBuffer(bytes.length * 2);  
        for (int i = 0; i < bytes.length; i++) {  
            temp.append((byte) ((bytes[i] & 0xf0) >>> 4));  
            temp.append((byte) (bytes[i] & 0x0f));  
        }  
        return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp  
                .toString().substring(1) : temp.toString();  
    }
	public static String checkNull(String data) {
		return data == null ? "" : data;
	}

	/**
	 * 返回指定范围内的随机数
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandom(int min, int max) {
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		return s;
	}

	public static String getTimeStr(int second) {
		int hour = second / 3600;
		int minute = (second - hour * 3600) / 60;
		String hourStr = String.valueOf(hour);
		String minuteStr = String.valueOf(minute);
		if (hourStr.length() < 2)
			hourStr = "0" + hourStr;
		if (minuteStr.length() < 2)
			minuteStr = "0" + minuteStr;
		return hourStr + minuteStr;
	}
	// -68,-127,89,113
	// 1d f1 3a be
	public static void main(String[] args) {
		// byte[] bytes = new byte[] { -68, -127, 89, 113 };
		// long length = Toolkit.bytesToIntFromC(bytes);
		// System.out.println("****length:" + length);

		//int data = Toolkit.floatRoundToInt(2.7f);
		//System.out.println(data);

//		byte[] data = Toolkit.str2Bcd("597992fe910b476c646ad25c");
//		System.out.println(data.length);
//		
//		String str = Toolkit.bcd2Str(data);
//		System.out.println(str);
		
		int offlineConfirmTimeval = 720000;
		long millSecondTimevale = offlineConfirmTimeval * 3600000l;// ms
		System.out.println(millSecondTimevale);
		
		
	}
}
