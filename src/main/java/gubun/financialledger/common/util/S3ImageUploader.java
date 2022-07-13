package gubun.financialledger.common.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Profile("dev")
@Component
@RequiredArgsConstructor
public class S3ImageUploader implements ImageUploader{

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3Client amazonS3Client;


//    @Value("${file.path}") // application.yml 에 설정된 외부경로, final 사용하면 안됨
//    private String uploadFolder;

    public String S3Uploader(MultipartFile file) {

        String imgFileName = createFileName(file); // path : images/username/{uuid}_파일이름

        log.info("imageFileName = {}", imgFileName);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());
        try {
            amazonS3Client.putObject(
                    new PutObjectRequest(bucket, imgFileName, file.getInputStream(), objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead));  // S3 업로드
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "File upload is failed.");
        }
        return amazonS3Client.getUrl(bucket, imgFileName).toString(); // 저장 경로 반환
    }


    private String createFileName(MultipartFile file) {
        return "images/"+UUID.randomUUID() + "_" + file.getOriginalFilename();
    }



    @Override
    public String saveImage(MultipartFile file) throws IOException {
        return S3Uploader(file);
    }

    @Override
    public boolean deleteImage(String url) {
        return false;
    }
}
