package com.zz.util;

import com.zz.form.AvatarForm;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author cy
 */
public class CustomImgUtils {


    public static synchronized AvatarForm customFront(String targetUrl, String backGroundUrl, String templateId, String filepath) throws Exception {
        BufferedImage waterImage = ImageIO.read(new File(targetUrl));
        Thumbnails.of(backGroundUrl).scale(1.0f).watermark(Positions.CENTER, waterImage, 1.0f).outputQuality(1.0f)
                .toFile(filepath + "userfiles" + File.separator + "customsave" + File.separator + templateId + "-result-A" + ".jpg");
        return new AvatarForm().setTemplateId(templateId).setTemplateBlob("userfiles" + File.separator + "customsave" + File.separator + templateId + "-result-A" + ".jpg");
    }

    public static synchronized AvatarForm customBack(String targetUrl, String backGroundUrl, String templateId, String filepath) throws Exception {
        BufferedImage waterImage = ImageIO.read(new File(targetUrl));
        Thumbnails.of(backGroundUrl).rotate(90).scale(1.0f).watermark(Positions.CENTER, waterImage, 1.0f).outputQuality(1.0f)
                .toFile(filepath + "userfiles" + File.separator + "customsave" + File.separator + templateId + "-result-B" + ".jpg");
        return new AvatarForm().setTemplateId(templateId).setTemplateBlob("userfiles" + File.separator + "customsave" + File.separator + templateId + "-result-B" + ".jpg");
    }
}
