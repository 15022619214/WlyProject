package com.jysc.ueditor.upload;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jysc.ueditor.PathFormat;
import com.jysc.ueditor.define.AppInfo;
import com.jysc.ueditor.define.BaseState;
import com.jysc.ueditor.define.FileType;
import com.jysc.ueditor.define.State;

public class BinaryUploader {

	public static final State save(HttpServletRequest request,Map<String, Object> conf) {
        final Logger logger = LoggerFactory.getLogger(BinaryUploader.class);
		FileItemStream fileStream = null;
		boolean isAjaxUpload = request.getHeader( "X_Requested_With" ) != null;

		if (!ServletFileUpload.isMultipartContent(request)) {
			return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
		}

		ServletFileUpload upload = new ServletFileUpload(
				new DiskFileItemFactory());

        if ( isAjaxUpload ) {
            upload.setHeaderEncoding( "UTF-8" );
        }

		try {
			FileItemIterator iterator = upload.getItemIterator(request);

			while (iterator.hasNext()) {
				fileStream = iterator.next();

				if (!fileStream.isFormField())
					break;
				fileStream = null;
			}

			if (fileStream == null) {
				return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
			}

			String savePath = (String) conf.get("savePath");
            logger.info("savePath:"+savePath);
			String originFileName = fileStream.getName();
            logger.info("originFileName:"+originFileName);
			String suffix = FileType.getSuffixByFilename(originFileName);
            logger.info("suffix:"+suffix);
			originFileName = originFileName.substring(0,originFileName.length() - suffix.length());
			savePath = savePath + suffix;
            logger.info("savePath2:"+savePath);
			long maxSize = ((Long) conf.get("maxSize")).longValue();

			if (!validType(suffix, (String[]) conf.get("allowFiles"))) {
				return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}

			savePath = PathFormat.parse(savePath, originFileName);

            String prifixPath = (String) conf.get("rootPath");
            logger.info("prifixPath:"+prifixPath);
            prifixPath = prifixPath.substring(0, prifixPath.length() - request.getContextPath().length());
            logger.info("prifixPath2:"+prifixPath);
            String physicalPath = prifixPath + savePath;
            logger.info("physicalPath:"+physicalPath);
			InputStream is = fileStream.openStream();
			State storageState = StorageManager.saveFileByInputStream(is,physicalPath, maxSize);
			is.close();
			if (storageState.isSuccess()) {
                String basePath = request.getScheme() + "://"
                        + request.getServerName() + ":" + request.getServerPort();

				storageState.putInfo("url", PathFormat.format("/wly/img"+savePath));
				storageState.putInfo("type", suffix);
				storageState.putInfo("original", originFileName + suffix);
			}

			return storageState;
		} catch (FileUploadException e) {
			return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
		} catch (IOException e) {
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}

	private static boolean validType(String type, String[] allowTypes) {
		List<String> list = Arrays.asList(allowTypes);

		return list.contains(type);
	}
}
