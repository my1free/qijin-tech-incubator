package tech.qijin.incubator.social.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.qijin.incubator.social.api.vo.MeVo;

/**
 * @author michealyang
 * @date 2019-12-06
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Slf4j
@RestController
@RequestMapping("/incubator/social/me")
public class MeController {

    @GetMapping("/detail")
    public MeVo me() {
        return new MeVo();
    }
}
