package com.example.demo.client.codec;

import com.example.demo.Toolkit;
import com.example.demo.client.MessageReceivedListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhaolei
 * Create: 2019/2/14 14:59
 * Modified By:
 * Description:
 */
public class DefaultFactoryListener implements MessageReceivedListener {

    private String TAG = DefaultFactoryListener.class.getName();

    public String status;

    public List<String[]> diskLoadInfoList;

    public int wRate;

    public int rRate;

    public byte[] responseByteData;

    public byte[] bodyByte;

    @Override
    public void setResponseData(byte[] responseByteData) {
        this.responseByteData = responseByteData;
        parseRespData();
    }

    public byte[] getBodyByte() {
        return bodyByte;
    }

    public String getStatus() {
        return status;
    }

    public List<String[]> getDiskLoadInfoList() {
        return diskLoadInfoList;
    }

    public int getwRate() {
        return wRate;
    }

    public void setwRate(int wRate) {
        this.wRate = wRate;
    }

    public int getrRate() {
        return rRate;
    }

    public void setrRate(int rRate) {
        this.rRate = rRate;
    }

    private void parseRespData() {
        byte[] packLengthByte = new byte[4];
        System.arraycopy(responseByteData, 2, packLengthByte, 0, 4);
        int length = Toolkit.bigEndianByteToInt(packLengthByte);

        // cmdId及subCmdId
        byte cmdId = responseByteData[8];
        byte subCmdId = responseByteData[9];

        // body
        bodyByte = new byte[length - 39];
        System.arraycopy(responseByteData, 39, bodyByte, 0, bodyByte.length);

        // status
        byte[] statusByte = new byte[2];
        System.arraycopy(bodyByte, 0, statusByte, 0, 2);
        // short s = Toolkit.bigEndianByteToShort(statusByte);
        short s = Toolkit.littleEndianByteToShort(statusByte);

        // for (int i = 0; i < statusByte.length; i++) {
        // System.out.println(statusByte[i]);
        // }

        status = String.valueOf(s);

        // 负载数据单独处理
        if (subCmdId == 0x10) {
            byte[] didByte = new byte[2];
            System.arraycopy(bodyByte, 2, didByte, 0, 2);
            short did = Toolkit.bigEndianByteToShort(didByte);

            // startTime
            byte[] startDateByte = new byte[4];
            System.arraycopy(bodyByte, 4, startDateByte, 0, 4);
            int startDate = Toolkit.bigEndianByteToInt(startDateByte);
            long statStartDate = startDate * 1000l;

            // 2016-12-01 00:00:00

            // 时间点的个数
            byte[] countByte = new byte[4];
            System.arraycopy(bodyByte, 8, countByte, 0, 4);
            int count = Toolkit.bigEndianByteToInt(countByte);



            diskLoadInfoList = new ArrayList<String[]>();
            for (int i = 0; i < count; i++) {
                String[] statItem = new String[3];
                // 时间点
                long statStartDateNew = statStartDate + i * 3600000l;// 间隔1小时1个时间点

                Date statDate = new Date();
                statDate.setTime(statStartDateNew);
                String statDateStr = "1";
                statItem[0] = statDateStr;

                byte[] wmBytes = new byte[4];
                byte[] rmBytes = new byte[4];
                System.arraycopy(bodyByte, 12 + i * 8, wmBytes, 0, 4);
                System.arraycopy(bodyByte, 12 + i * 8 + 4, rmBytes, 0, 4);

                int wm = Toolkit.bigEndianByteToInt(wmBytes);
                int rm = Toolkit.bigEndianByteToInt(rmBytes);
                statItem[1] = String.valueOf(wm);
                statItem[2] = String.valueOf(rm);

                // if (i < 100)
                // Log.i(TAG, "***statDateStr:" + statDateStr + " wm:"
                // + wm + " rm:" + rm);
                diskLoadInfoList.add(statItem);
            }
        } else if (subCmdId == 0x16) {
            // 写的速度
            byte[] wRateByte = new byte[4];
            System.arraycopy(bodyByte, 3, wRateByte, 0, 4);
            wRate = Toolkit.bigEndianByteToInt(wRateByte);

            // 读的速度
            byte[] rRateByte = new byte[4];
            System.arraycopy(bodyByte, 7, rRateByte, 0, 4);
            rRate = Toolkit.bigEndianByteToInt(rRateByte);

        } else if (subCmdId == 0x18) {// 磁盘当前的读写速率

            byte[] wRateByte = new byte[4];
            System.arraycopy(bodyByte, 4, wRateByte, 0, 4);
            wRate = Toolkit.bigEndianByteToInt(wRateByte);

            // 读的速度
            byte[] rRateByte = new byte[4];
            System.arraycopy(bodyByte, 8, rRateByte, 0, 4);
            rRate = Toolkit.bigEndianByteToInt(rRateByte);

        } else if (subCmdId == 0x1c) {// 获取大小分组磁盘数量

        }
    }
}