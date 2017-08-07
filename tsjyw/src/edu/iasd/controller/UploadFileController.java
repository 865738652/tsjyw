package edu.iasd.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;
import com.baidu.ueditor.upload.StorageManager;

import edu.iasd.form.AttachVO;
import edu.iasd.service.StudentService;

@Controller
public class UploadFileController 
{
	
	@Autowired
	private StudentService studentService;
	
	
	
	
	@RequestMapping(value = "/uploadFile")
	@ResponseBody
	public String uploadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		FileItemStream fileStream = null;
		boolean isAjaxUpload = request.getHeader( "X_Requested_With" ) != null;

		if (!ServletFileUpload.isMultipartContent(request)) {
			return new JsonResult("fail", 0, null, "未选择上传的文件").toJsonString();
		}

		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());

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
				return new JsonResult("fail", 0, null, "未选择上传的文件").toJsonString();
			}

			String savePath = "WEB-INF/upload/{yyyy}{mm}{dd}/{time}{rand:6}";
			String originFileName1 = fileStream.getName();
			String originFileName = new String(originFileName1.getBytes("gbk"), "utf-8"); 
			String suffix = FileType.getSuffixByFilename(originFileName);

			originFileName = originFileName.substring(0,
					originFileName.length() - suffix.length());
			savePath = savePath + suffix;

			savePath = PathFormat.parse(savePath, originFileName);

			String realPath = request.getSession().getServletContext().getRealPath("/");
			String physicalPath = realPath + savePath;

			InputStream is = fileStream.openStream();
			State storageState = StorageManager.saveFileByInputStream(is, physicalPath);
			is.close();

			if (storageState.isSuccess()) {
				String u = PathFormat.format(savePath);
				if (u.startsWith("WEB-INF/upload/"))
					u = u.replaceFirst("WEB-INF/upload/", "../upload/");
				File f = new File(physicalPath);
				AttachVO attach = new AttachVO();
	        	attach.setAttachName(originFileName + suffix);
	        	attach.setAttachSize(Float.parseFloat(Long.toString(f.length())));
	        	attach.setAttachUrl(u);
	        	return new JsonResult("succ", 0, null, attach).toJsonString();
			}
			else
				return new JsonResult("fail", 0, null, "保存文件失败").toJsonString();
			
		} catch (FileUploadException e) {
			return new JsonResult("fail", 0, null, "数据传输失败" + e.getMessage()).toJsonString();
		} catch (IOException e) {
			return new JsonResult("fail", 0, null, "保存文件失败" + e.getMessage()).toJsonString();
		} catch (Exception e) {
			return new JsonResult("fail", 0, null, e.getMessage()).toJsonString();
		}		
	}
	/*
	@RequestMapping(value = "/uploadFile")
	@ResponseBody
	public String uploadFile(
			@RequestParam(value = "file") MultipartFile file, 
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception{
		JsonResult result = new JsonResult("succ", 0, null, null);
	    if (file == null) {
	    	result = new JsonResult("fail", 0, null, "未选择上传的文件");
	    }
	    else if (file.isEmpty()) {
	    	result = new JsonResult("fail", 0, null, "上传的文件内容为空");
	    }
	    else {
	    	String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
	        String originFileName = file.getOriginalFilename();
	        try {
	        	File dest = new File(realPath, originFileName);
	        	FileOutputStream os = new FileOutputStream(dest);
	        	FileCopyUtils.copy(file.getInputStream(), os);
	        	AttachVO attach = new AttachVO();
	        	attach.setAttachName(file.getOriginalFilename());
	        	attach.setAttachSize(Float.parseFloat(Long.toString(file.getSize())));
	        	attach.setAttachUrl("/upload/" + originFileName);
	        	result = new JsonResult("succ", 0, null, attach);
	        } 
	        catch (IOException e) {
	        	result = new JsonResult("fail", 0, null, e.getMessage());
	        }
	    }
	    return result.toJsonString();
	}
	*/
	
	//excel专用的上传方法
	@ResponseBody
	@RequestMapping(value="/uploadExcelFile")
	public String uploadExcelFile(HttpServletRequest request,@RequestParam Integer excelType,@RequestParam Integer objectId) throws Exception
	{
		try
		{
			FileItemStream fileStream = null;
			boolean isAjaxUpload = request.getHeader( "X_Requested_With" ) != null;
			if (!ServletFileUpload.isMultipartContent(request)) {
				return new JsonResult("fail", 0, null, "未选择上传的文件").toJsonString();
			}
			ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
	        if ( isAjaxUpload ) {
	            upload.setHeaderEncoding( "UTF-8" );
	        }
			FileItemIterator iterator = upload.getItemIterator(request);
			while (iterator.hasNext()) {
				fileStream = iterator.next();
				if (!fileStream.isFormField())
					break;
				fileStream = null;
			}
			if (fileStream == null) {
				return new JsonResult("fail", 0, null, "未选择上传的文件").toJsonString();
			}	
			InputStream is = fileStream.openStream();
			//代表导入学生
			if(excelType == 1)
			{
				List<Integer> userIds = studentService.getStudentforExcel(objectId,is);
				return new JsonResult("succ",userIds.size(), null, userIds).toJsonString();
			}
			//代表全校范围的导入学生
			if(excelType == 2)
			{
				List<Integer> userIds = studentService.getSchoolStudentforExcel(objectId,is);
				return new JsonResult("succ",userIds.size(),null,userIds).toJsonString();
			}
			
			return new JsonResult("fail",0, null, "添加类型未知").toJsonString();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return new JsonResult("fail", 0, null, e.getMessage()).toJsonString();
		}
	}
	
	@RequestMapping(value = "WeChat/uploadFile")
	@ResponseBody
	public String uploadWeChatFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		FileItemStream fileStream = null;
		boolean isAjaxUpload = request.getHeader( "X_Requested_With" ) != null;

		if (!ServletFileUpload.isMultipartContent(request)) {
			return new JsonResult("fail", 0, null, "未选择上传的文件").toJsonString();
		}

		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());

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
				return new JsonResult("fail", 0, null, "未选择上传的文件").toJsonString();
			}

			String savePath = "WEB-INF/upload/{yyyy}{mm}{dd}/{time}{rand:6}";
			String originFileName = fileStream.getName();
			String suffix = FileType.getSuffixByFilename(originFileName);

			originFileName = originFileName.substring(0,
					originFileName.length() - suffix.length());
			savePath = savePath + suffix;

			savePath = PathFormat.parse(savePath, originFileName);

			String realPath = request.getSession().getServletContext().getRealPath("/");
			String physicalPath = realPath + savePath;

			InputStream is = fileStream.openStream();
			State storageState = StorageManager.saveFileByInputStream(is, physicalPath);
			is.close();

			if (storageState.isSuccess()) {
				String u = PathFormat.format(savePath);
				if (u.startsWith("WEB-INF/upload/"))
					u = u.replaceFirst("WEB-INF/upload/", "../upload/");
				File f = new File(physicalPath);
				AttachVO attach = new AttachVO();
	        	attach.setAttachName(originFileName + suffix);
	        	attach.setAttachSize(Float.parseFloat(Long.toString(f.length())));
	        	attach.setAttachUrl(u);
	        	return new JsonResult("succ", 0, null, attach).toJsonString();
			}
			else
				return new JsonResult("fail", 0, null, "保存文件失败").toJsonString();
			
		} catch (FileUploadException e) {
			return new JsonResult("fail", 0, null, "数据传输失败" + e.getMessage()).toJsonString();
		} catch (IOException e) {
			return new JsonResult("fail", 0, null, "保存文件失败" + e.getMessage()).toJsonString();
		} catch (Exception e) {
			return new JsonResult("fail", 0, null, e.getMessage()).toJsonString();
		}		
	}
	
	
	
	
}
