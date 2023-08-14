package healthtory.site.healthtory.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import healthtory.site.healthtory.domain.love.Love;
import healthtory.site.healthtory.service.LoveService;
import healthtory.site.healthtory.web.dto.CMRespDto;
import healthtory.site.healthtory.web.dto.response.SessionUserDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class LoveController {

    private final HttpSession session;
    private final LoveService loveService;
    
    @PostMapping("/love/{postId}")
	public @ResponseBody CMRespDto<?> insertLove(@PathVariable Integer postId) {

        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        if (principal == null) {
            return new CMRespDto<>(-1, "로그인을 진행해주세요.", null);
        }
		Love love = new Love(principal.getUserId(), postId);

		Love lovePS = loveService.love(love);
		return new CMRespDto<>(1, "좋아요 성공", lovePS);
	}
}
