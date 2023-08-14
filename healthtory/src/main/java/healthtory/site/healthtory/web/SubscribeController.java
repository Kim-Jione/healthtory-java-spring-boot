package healthtory.site.healthtory.web;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import healthtory.site.healthtory.domain.subscribe.Subscribe;
import healthtory.site.healthtory.service.SubscribeService;
import healthtory.site.healthtory.web.dto.CMRespDto;
import healthtory.site.healthtory.web.dto.response.SessionUserDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class SubscribeController {

    private final SubscribeService subscribeService;
    private final HttpSession session;

	@PostMapping("/subscribe/{creatorId}")
	public @ResponseBody CMRespDto<?> subscribe(@PathVariable Integer creatorId) {
        SessionUserDto principal = (SessionUserDto) session.getAttribute("principal");
        if (principal == null) {
            return new CMRespDto<>(-1, "로그인을 진행해주세요.", null);
        }
        Integer subscribeId = subscribeService.findById(principal.getUserId(), creatorId);
		Integer subscriberId = principal.getUserId();

        if (subscribeId == null) {
			Subscribe subscrbePS = subscribeService.subscrive(subscriberId, creatorId);
			return new CMRespDto<>(1, "구독 성공", subscrbePS);
		}

		Subscribe subscrbePS = subscribeService.unsubscribe(subscribeId);
		return new CMRespDto<>(1, "구독 취소 성공", subscrbePS);
	}
}
