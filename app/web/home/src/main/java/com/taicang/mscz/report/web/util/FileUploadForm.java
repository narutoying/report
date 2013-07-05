package com.taicang.mscz.report.web.util;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传表单
 * 
 * @author cheng.dai@alipay.com
 * @version $Id: FileUploadForm.java, v 0.1 2012-9-5 ����2:17:15 cheng.dai@alipay.com Exp $
 */
public class FileUploadForm {

    private MultipartFile file;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

}
