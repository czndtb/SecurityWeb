package com.wl.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.wl.web.config.jwt.JwtUser;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ParamTool {

	@Value("${qiniu.namespace}")
	public String QINIU_NAMESPACE;

	@Value("${qiniu.ak}")
	public String QINIU_AK;

	@Value("${qiniu.sk}")
	public String QINIU_SK;

	@Value("${qiniu.domain}")
	public String QINIU_DOMAIN;

	public String getToken() {
		return getRequest().getHeader("Authorization");
	}

	/**
	 * 获取当前request
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		return request;
	}

	public HttpServletResponse getResponse() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletResponse response = attributes.getResponse();
		return response;
	}

	/**
	 * 
	 * @Title: uploadSingleFile2Qiniu
	 * @Description: 上传文件到7牛 并返回访问链接
	 * @author: czn
	 * @date: 2018年10月24日 下午3:03:43
	 * @version: V1.0
	 * @param file
	 * @return String
	 *
	 */
	public String uploadSingleFile2Qiniu(MultipartFile file) {
		try {
			String ftpDomain = QINIU_DOMAIN;
			// 构造一个带指定Zone对象的配置类
			Configuration cfg = new Configuration(Zone.autoZone());
			// ...其他参数参考类注释
			UploadManager uploadManager = new UploadManager(cfg);
			// ...生成上传凭证，然后准备上传
			String accessKey = QINIU_AK;
			String secretKey = QINIU_SK;
			String bucket = QINIU_NAMESPACE;
			// 默认不指定key的情况下，以文件内容的hash值作为文件名
			String key = null;
			Auth auth = Auth.create(accessKey, secretKey);
			String upToken = auth.uploadToken(bucket);

			if (null != file) {
				Response rp = uploadManager.put(file.getInputStream(), key, upToken, null, null);
				// 解析上传成功的结果
				DefaultPutRet putRet = JSONObject.parseObject(rp.bodyString(), DefaultPutRet.class);

				return ftpDomain + putRet.key;
			}
		} catch (QiniuException ex) {
			log.error("qiniu", ex);
		} catch (Exception e) {
			log.error("qiniu上传出错", e);
		}
		return null;
	}

	/**
	 * 
	 * @Title: getJwtUser
	 * @Description: 获取auth 用户信息
	 * @author: czn
	 * @date: 2018年11月22日 上午10:56:28
	 * @version: V1.0
	 * @return JwtUser
	 *
	 */
	public JwtUser getJwtUser() {
		return (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
