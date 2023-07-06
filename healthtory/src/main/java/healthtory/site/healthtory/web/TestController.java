package healthtory.site.healthtory.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import healthtory.site.healthtory.domain.post.PostDao;
import healthtory.site.healthtory.web.dto.CMRespDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class TestController {
    
    private final PostDao postDao;

    // Test
    @GetMapping("/")
    public @ResponseBody CMRespDto<?> test() {
        return new CMRespDto<>(1, "프로젝트 실행 성공", postDao.findById(2));
    }
}
