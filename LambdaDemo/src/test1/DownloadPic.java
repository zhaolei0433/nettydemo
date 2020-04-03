package test1;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;


public class DownloadPic {
	/**
	 * @param picUrl 图片访问路径
	 * @param savePath 本地保存地址
	 */
	public static boolean downloadPicture(String picUrl, String savePath) {
		URL url = null;
		try {
			url = new URL(picUrl);
			DataInputStream dataInputStream = new DataInputStream(
					url.openStream());
			FileOutputStream fileOutputStream = new FileOutputStream(new File(
					savePath));
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length;
			while ((length = dataInputStream.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
			fileOutputStream.write(output.toByteArray());
			dataInputStream.close();
			fileOutputStream.close();
			return true;
		} catch (MalformedURLException e) {

		} catch (IOException e) {

		}
		return false;
	}

	public static void main(String[] strings){
		downloadPicture("http://localhost/cms.mp4","E:\\cms\\cms.mp4");
	}

}
