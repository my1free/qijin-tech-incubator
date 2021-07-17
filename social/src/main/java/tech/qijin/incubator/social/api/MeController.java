package tech.qijin.incubator.social.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.qijin.incubator.social.api.vo.*;
import tech.qijin.incubator.social.db.model.SocialHobby;
import tech.qijin.incubator.social.service.MeService;
import tech.qijin.incubator.social.service.bo.HobbiesBo;
import tech.qijin.incubator.social.service.bo.MeBo;
import tech.qijin.util4j.web.pojo.ResultVo;

/**
 * @author michealyang
 * @date 2019-12-06
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Slf4j
@RestController
@RequestMapping("/incubator/social/me")
public class MeController {
    @Autowired
    private MeService meService;

    @GetMapping("/detail")
    public MeVo me() {
        MeBo meBo = meService.getMeInfo();
        return MeVo.from(meBo);
    }

    @GetMapping("/gallery")
    public GalleryVo gallery() {
        MeBo meBo = meService.getGallery();
        return GalleryVo.from(meBo);
    }

    @GetMapping("/hobbies")
    public HobbiesVo hobbies() {
        HobbiesBo hobbiesBo = meService.listHobbies();
        return HobbiesVo.from(hobbiesBo);
    }

    @PostMapping("/hobby/add")
    public HobbyAddRespVo addHobby(@RequestBody HobbyAddReqVo hobbyAddReqVo) {
        SocialHobby socialHobby = meService.addHobby(hobbyAddReqVo.getContent());
        return HobbyAddRespVo.builder()
                .id(socialHobby.getId())
                .build();
    }

    @PostMapping("/hobby/del")
    public ResultVo delHobby(@RequestBody HobbyDelReqVo hobbyDelReqVo) {
        meService.delHobby(hobbyDelReqVo.getContent());
        return ResultVo.instance().success();
    }

    @PostMapping("/love/update")
    public ResultVo updateLove(@RequestBody LoveReqVo loveReqVo) {
        meService.upsertLove(loveReqVo.getLoveKind(), loveReqVo.getContent());
        return ResultVo.instance().success();
    }
}
