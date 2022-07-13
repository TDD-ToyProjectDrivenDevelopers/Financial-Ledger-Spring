package gubun.financialledger.common.util;


import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public interface ImageUploader {

    public String saveImage(MultipartFile file) throws IOException;
    public boolean deleteImage(String url);
}
