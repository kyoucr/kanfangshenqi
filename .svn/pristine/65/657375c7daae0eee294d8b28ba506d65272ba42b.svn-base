package com.xinwei.kanfangshenqi.view;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import com.google.gson.Gson;
import com.xinwei.kanfangshenqi.response.UpHeadPortraitResponse;

import android.util.Log;

/**
 * * * 实现文件上传的工具类
 * 
 * @Title:
 */
public class FileImageUpload {
	private static final String TAG = "uploadFile";
	private static final int TIME_OUT = 10 * 10000000; // 超时时间
	private static final String CHARSET = "utf-8"; // 设置编码
	public static final String SUCCESS = "1";
	public static final String FAILURE = "0";
	private static Gson mGson = new Gson();

	/**
	 * * android上传文件到服务器
	 * 
	 * @param file
	 *            需要上传的文件
	 * @param RequestURL
	 *            请求的rul
	 * @return 返回响应的内容
	 */
	public static UpHeadPortraitResponse uploadFile(File file,
			String RequestURL, String transId, String appAgent, String OSVer,
			String token) {
		String BOUNDARY = UUID.randomUUID().toString();
		// 边界标识 随机生成
		String PREFIX = "--", LINE_END = "\r\n";
		String CONTENT_TYPE = "multipart/form-data"; // 内容类型
		try {
			URL url = new URL(RequestURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(TIME_OUT);
			conn.setConnectTimeout(TIME_OUT);
			conn.setDoInput(true); // 允许输入流
			conn.setDoOutput(true); // 允许输出流
			conn.setUseCaches(false); // 不允许使用缓存
			conn.setRequestMethod("POST"); // 请求方式
			conn.setRequestProperty("transId", transId);
			conn.setRequestProperty("appAgent", appAgent);
			conn.setRequestProperty("OSVer", OSVer);
			conn.setRequestProperty("token", token);
			conn.setRequestProperty("Charset", CHARSET);
			// 设置编码
			conn.setRequestProperty("connection", "keep-alive");
			conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary="
					+ BOUNDARY);
			if (file != null) {
				/** * 当文件不为空，把文件包装并且上传 */
				OutputStream outputSteam = conn.getOutputStream();
				DataOutputStream dos = new DataOutputStream(outputSteam);
				StringBuffer sb = new StringBuffer();
				sb.append(PREFIX);
				sb.append(BOUNDARY);
				sb.append(LINE_END);
				/**
				 * 这里重点注意： name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
				 * filename是文件的名字，包含后缀名的 比如:abc.png
				 */
				sb.append("Content-Disposition: form-data; name=\"file\"; filename=\""
						+ file.getName() + "\"" + LINE_END);
				sb.append("Content-Type: application/octet-stream; charset="
						+ CHARSET + LINE_END);
				sb.append(LINE_END);
				dos.write(sb.toString().getBytes());
				InputStream is = new FileInputStream(file);
				byte[] bytes = new byte[1024];
				int len = 0;
				while ((len = is.read(bytes)) != -1) {
					dos.write(bytes, 0, len);
				}
				is.close();
				dos.write(LINE_END.getBytes());
				byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END)
						.getBytes();
				dos.write(end_data);
				dos.flush();
				/**
				 * 获取响应码 200=成功 当响应成功，获取响应的流
				 */
				int res = conn.getResponseCode();
				Log.e(TAG, "response code:" + res);
				if (res == 200) { // 获服务器取数据
					String result = "";
					InputStream inputStream = conn.getInputStream();
					InputStreamReader inputStreamReader = new InputStreamReader(
							inputStream);
					BufferedReader reader = new BufferedReader(
							inputStreamReader);
					String inputLine = null;
					// 使用循环来读取获得的数据，把数据都村到result中了
					while (((inputLine = reader.readLine()) != null)) {
						// 我们在每一行后面加上一个"\n"来换行
						result += inputLine + "\n";
					}
					reader.close();// 关闭输入流
					// 关闭http连接
					conn.disconnect();
					// 设置显示取得的内容
					if (result != null) {
						UpHeadPortraitResponse response = mGson.fromJson(
								result, getResponseClass());
						return response;
					}
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		}
		return null;
	}

	private static Class<UpHeadPortraitResponse> getResponseClass() {
		// TODO Auto-generated method stub
		return UpHeadPortraitResponse.class;
	}
}
