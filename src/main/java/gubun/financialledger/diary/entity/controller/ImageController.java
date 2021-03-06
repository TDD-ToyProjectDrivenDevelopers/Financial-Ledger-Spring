package gubun.financialledger.diary.entity.controller;

import gubun.financialledger.common.util.ImageUploader;
import gubun.financialledger.user.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 임시 테스트용 이미지 업로드 컨트롤러 입니다. (동작확인용)
 */
@Slf4j
@Controller
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {

    private final ImageUploader imageUploader;

    @GetMapping("/upload")
    public String newFile() {
        return "upload-form";
    }

    @ResponseBody
    @PostMapping("/upload")
    public String saveFile(@RequestParam MultipartFile file,@AuthenticationPrincipal PrincipalDetails user) throws IOException {

        log.info("request user = {}",user.toString());
        String url = imageUploader.saveImage(file);
        return url;
    }
}
