package com.zz.form;

import lombok.Data;

/**
 * @author cy
 */

public class AvatarForm {
    private String  templateBlob;
    private String  templateId;



    public String getTemplateBlob() {
        return templateBlob;
    }


    public AvatarForm setTemplateBlob(String templateBlob) {
        this.templateBlob = templateBlob;
        return this;
    }

    public String getTemplateId() {
        return templateId;
    }

    public AvatarForm setTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }
}
