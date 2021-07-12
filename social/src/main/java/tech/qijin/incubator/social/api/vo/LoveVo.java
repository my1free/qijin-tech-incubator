package tech.qijin.incubator.social.api.vo;

import lombok.Data;
import tech.qijin.incubator.social.base.LoveKind;
import tech.qijin.incubator.social.db.model.SocialLove;

import java.util.List;

@Data
public class LoveVo {
    private String selfInfo;
    private String interest;
    private String family;
    private String expected;

    public static LoveVo from(List<SocialLove> loves) {
        LoveVo loveVo = new LoveVo();
        for (SocialLove love : loves) {
            if (LoveKind.SELF_INFO.equals(love.getKind())) {
                loveVo.setSelfInfo(love.getContent());
            } else if (LoveKind.INTEREST.equals(love.getKind())) {
                loveVo.setInterest(love.getContent());
            } else if (LoveKind.FAMILY.equals(love.getKind())) {
                loveVo.setFamily(love.getContent());
            } else if (LoveKind.EXPECTED.equals(love.getKind())) {
                loveVo.setExpected(love.getContent());
            }
        }
        return loveVo;
    }
}
