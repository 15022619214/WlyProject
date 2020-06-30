package com.jysc.ueditor.upload;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;

import com.jysc.ueditor.PathFormat;
import com.jysc.ueditor.define.AppInfo;
import com.jysc.ueditor.define.BaseState;
import com.jysc.ueditor.define.FileType;
import com.jysc.ueditor.define.State;

public final class Base64Uploader {

	public static State save(HttpServletRequest request, String content, Map<String, Object> conf) {

		byte[] data = decode(content);

		long maxSize = ((Long) conf.get("maxSize")).longValue();

		if (!validSize(data, maxSize)) {
			return new BaseState(false, AppInfo.MAX_SIZE);
		}

		String suffix = FileType.getSuffix("JPG");

		String savePath = PathFormat.parse((String) conf.get("savePath"),
				(String) conf.get("filename"));

		savePath = savePath + suffix;

        String prifixPath = (String) conf.get("rootPath");
        prifixPath = prifixPath.substring(0, prifixPath.length() - request.getContextPath().length());

		String physicalPath = prifixPath + savePath;

		State storageState = StorageManager.saveBinaryFile(data, physicalPath);

		if (storageState.isSuccess()) {
            String basePath = request.getScheme() + "://"
                    + request.getServerName() + ":" + request.getServerPort();
			storageState.putInfo("url", PathFormat.format(basePath + savePath));
			storageState.putInfo("type", suffix);
			storageState.putInfo("original", "");
		}

		return storageState;
	}

	private static byte[] decode(String content) {
		return Base64.decodeBase64(content);
	}

	private static boolean validSize(byte[] data, long length) {
		return data.length <= length;
	}

}