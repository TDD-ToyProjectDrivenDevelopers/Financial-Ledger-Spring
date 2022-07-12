package gubun.financialledger.common.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Slf4j
@Profile("test")
@Component
public class LocalImageUploader implements ImageUploader{

    private static final String LocalPath = System.getProperty("user.home");

    @Override
    public String saveImage(MultipartFile file,String username) throws IOException {

        log.info("local path = {}",LocalPath);

        String dirPath = LocalPath +File.separator+ username;


        File folder = new File(dirPath);
        if (!folder.exists())
            folder.mkdirs();

        String fullPath = dirPath + File.separator + file.getOriginalFilename();
        File saveImage = new File(fullPath);
        file.transferTo(saveImage);

        return fullPath;
    }

    @Override
    public boolean deleteImage(String url) {

        File file = new File(url);
        log.info("image removed path = {}",url);
        return file.delete();
    }
}
