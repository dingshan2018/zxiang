package com.zxiang.project.business.version.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.zxiang.common.exception.RRException;
import com.zxiang.common.support.Convert;
import com.zxiang.project.advertise.utils.AdHttpResult;
import com.zxiang.project.advertise.utils.Tools;
import com.zxiang.project.advertise.utils.constant.AdConstant;
import com.zxiang.project.business.server.service.IServerService;
import com.zxiang.project.business.terminal.domain.Terminal;
import com.zxiang.project.business.terminal.mapper.TerminalMapper;
import com.zxiang.project.business.version.domain.TerminalVersion;
import com.zxiang.project.business.version.domain.Version;
import com.zxiang.project.business.version.mapper.VersionMapper;

/**
 * 版本 服务层实现
 * 
 * @author ZXiang
 * @date 2018-12-06
 */
@Service
public class VersionServiceImpl implements IVersionService 
{
	Logger logger = Logger.getLogger(VersionServiceImpl.class);
	
	/** 版本更新包上传URL	 */
	public final static String 	UPLOAD_URL_PACKAGE = "http://mmedia.dingscm.com/mmedia/adpublish/api/uploadAttach.action";
	
	@Autowired
	private VersionMapper versionMapper;
	@Autowired
	private TerminalMapper terminalMapper;
	@Autowired 
	private IServerService serverService;

	/**
     * 查询版本信息
     * 
     * @param sysVerId 版本ID
     * @return 版本信息
     */
    @Override
	public Version selectVersionById(Integer sysVerId)
	{
	    return versionMapper.selectVersionById(sysVerId);
	}
	
	/**
     * 查询版本列表
     * 
     * @param version 版本信息
     * @return 版本集合
     */
	@Override
	public List<Version> selectVersionList(Version version)
	{
	    return versionMapper.selectVersionList(version);
	}
	
    /**
     * 新增版本
     * 
     * @param version 版本信息
     * @return 结果
     */
	@Override
	public int insertVersion(Version version)
	{
	    return versionMapper.insertVersion(version);
	}
	
	/**
     * 修改版本
     * 
     * @param version 版本信息
     * @return 结果
     */
	@Override
	public int updateVersion(Version version)
	{
	    return versionMapper.updateVersion(version);
	}

	/**
     * 删除版本对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteVersionByIds(String ids)
	{
		return versionMapper.deleteVersionByIds(Convert.toStrArray(ids));
	}

	@Override
	@Transactional
	public int uploadSave(HttpServletRequest request, List<MultipartFile> files, String operatorUser) throws Exception {
		int saveNum = 0;
		try {
			String sysVerCode = request.getParameter("sysVerCode").trim();
			String subject = request.getParameter("subject");
			String oldVersionCode = request.getParameter("oldVersionCode").trim();
			String sysVerSubject = request.getParameter("sysVerSubject");
			String sysVerInfo = request.getParameter("sysVerInfo");
			
			//1.上传素材文件
			if (!files.isEmpty()) {
		    	//调用上传文件的接口
				MultipartFile file = files.get(0);
				String fileName = file.getOriginalFilename();
		        long fileSize = file.getSize();
		        String md5Check = getMd5(file);
		        
				Version version = new Version();
				version.setSysVerCode(sysVerCode);
				version.setSubject(subject);
				version.setOldVersionCode(oldVersionCode);
				version.setSysVerSubject(sysVerSubject);
				version.setSysVerInfo(sysVerInfo);
				version.setFileName(fileName);
				version.setFilesize(fileSize);
				version.setMd5Check(md5Check);
				
				version.setCreateBy(operatorUser);
				version.setCreateDate(new Date());
				version.setDelFlag("0");
				
				
				//1.本地文件保存方式
				//String downloadPath = FileUploadUtils.upload(FileUploadUtils.getDefaultBaseDir(), file, fileName.substring(fileName.lastIndexOf(".")));
				//FTP接口,提供FTP保存
				String downloadPath = null;
				String result = packageUpload(file);
				//返回结果封装
				AdHttpResult adHttp = Tools.analysisResult(result);
				if(AdConstant.RESPONSE_CODE_SUCCESS.equals(adHttp.getCode())){
					JSONObject jsonResult =  (JSONObject) adHttp.get("data");
					downloadPath = jsonResult.getString("preview");
				}else{
					logger.error("调用FTP上传更新包接口失败!" + adHttp.toString());
					throw new RRException("调用FTP上传更新包接口失败!");
				}
				
				version.setDownloadPath(downloadPath);
				
				saveNum = insertVersion(version);

			} else {
		        logger.error("文件不能为空!");
		        throw new RRException("文件不能为空!");
		    }
			return saveNum;
			
		} catch (Exception e) {
			logger.error("error: " + e);
			throw e;
		}
	
	}
	
	/** 
     * 获取上传文件的md5 
     */  
	public String getMd5(MultipartFile file) {  
        try {  
            byte[] uploadBytes = file.getBytes();  
            MessageDigest md5 = MessageDigest.getInstance("MD5");  
            byte[] digest = md5.digest(uploadBytes);  
            String hashString = new BigInteger(1, digest).toString(16);  
            return hashString;  
        } catch (Exception e) {  
            e.printStackTrace();
        }  
        return null;  
    }

	/**
	 * 文件上传FTP接口    Multipart接口
	 * @param multipartFile
	 * @return
	 */
	public String packageUpload(MultipartFile multipartFile) throws Exception{
		
		PostMethod postMethod = new PostMethod(UPLOAD_URL_PACKAGE);
    	HttpClient client = new HttpClient();
    	File file = null;
        try {

        	InputStream ins = multipartFile.getInputStream();
        	file=new File(multipartFile.getOriginalFilename());
		    Tools.inputStreamToFile(ins, file);
            
            //FilePart：用来上传文件的类
        	FilePart myUpload = new FilePart("myUpload", file);
        	//若有普通文本参数使用StringPart:普通文本参数
        	//StringPart eid = new StringPart("eid", eId);
        	Part[] parts = {(Part) myUpload};
        	
        	MultipartRequestEntity mre = new MultipartRequestEntity((org.apache.commons.httpclient.methods.multipart.Part[]) 
        			parts, postMethod.getParams());
        	postMethod.setRequestEntity(mre);
        	
            client.getHttpConnectionManager().getParams().setConnectionTimeout(50000);// 设置连接时间
            int status = client.executeMethod(postMethod);
            if (status == HttpStatus.SC_OK) {
                String result = postMethod.getResponseBodyAsString();
        		return result;
            }
        } catch (Exception e) {
        	logger.error("uploadAttach error:" + e);
        	throw e;
        } finally {
        	if(file.exists()){
        		file.delete();
        	}
            //释放连接
            postMethod.releaseConnection();
        }
		return null;
	}
	
	
	/**
	 * 校验版本编号唯一
	 */
	@Override
	public String checkCodeUnique(String sysVerCode) {
		int count = versionMapper.checkCodeUnique(sysVerCode);
        if (count > 0)
        {
            return "1";
        }
        return "0";
	}

	@Override
	public int versionIssued(String sysVerId, String terminals) throws NumberFormatException, IOException {
		int sendNum = 0;
		Version version = selectVersionById(Integer.parseInt(sysVerId));
		if(version != null ){
			String[] terminalArray = Convert.toStrArray(terminals);
			if(terminalArray.length > 0){
				for (String terminalId : terminalArray) {
					sendNum += callSendMethod(Integer.parseInt(terminalId), version.getSysVerCode(), version.getDownloadPath());
				}
			}
		}
		
		return sendNum;
	}
	
	/**
	 * 下发版本升级命令封装方法
	 * @param terminalId
	 * @param version
	 * @param downloadUrl
	 * @return
	 * @throws IOException
	 */
	public int callSendMethod(Integer terminalId,String sysVerCode,String downloadUrl) throws IOException{
		int issuedNum = 0;
		//下发命令操作
		Terminal terminal = terminalMapper.selectTerminalById(terminalId);
		if(terminal != null){
			TerminalVersion terminalVersion = new TerminalVersion();
			terminalVersion.setTermCode(terminal.getTerminalCode());
			terminalVersion.setVersion(sysVerCode);
			terminalVersion.setDownloadUrl(downloadUrl);
			
			JSONObject reqJson = new JSONObject();
			reqJson.put("termCode",terminal.getTerminalCode());
			reqJson.put("terminalVersion",terminalVersion);
			reqJson.put("command","22");//参数下发命令0x16,转十进制为22
			
			serverService.issuedCommand(terminal,reqJson);
			issuedNum++;
		}
		
		return issuedNum;
	}
	
}
