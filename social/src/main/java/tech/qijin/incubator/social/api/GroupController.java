package tech.qijin.incubator.social.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.qijin.incubator.social.api.vo.GroupsVo;
import tech.qijin.util4j.web.pojo.ResultVo;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/incubator/social/group")
public class GroupController {
    /**
     * 群组列表
     *
     * @return
     */
    @GetMapping("/list")
    public GroupsVo groups() {
        return GroupsVo.builder().build();
    }

    /**
     * 个人所管理的群组类别
     *
     * @return
     */
    @GetMapping("/my/list")
    public GroupsVo myGroups() {
        return GroupsVo.builder().build();
    }

    /**
     * 加入群组
     * @return
     */
    @PostMapping("/join")
    public ResultVo joinGroup() {
        return ResultVo.instance();
    }

    /**
     * 主动离开群组
     * @return
     */
    @PostMapping("/join")
    public ResultVo leaveGroup() {
        return ResultVo.instance();
    }

    /**
     * 邀请进群
     * @return
     */
    @PostMapping("/invite")
    public ResultVo inviteIntoGroup() {
        return ResultVo.instance();
    }

    /**
     * 管理员权限 - 提出群组
     * @return
     */
    @PostMapping("/admin/kickOut")
    public ResultVo kickOutGroup() {
        return ResultVo.instance();
    }

    /**
     * 管理员权限 - 使某个用户从某个群组隐藏
     * @return
     */
    @PostMapping("/admin/hide")
    public ResultVo hideFromGroup() {
        return ResultVo.instance();
    }

    /**
     * {@link #hideFromGroup()} 的相反操作
     * @return
     */
    @PostMapping("/admin/hide/recall")
    public ResultVo recallHideFromGroup() {
        return ResultVo.instance();
    }
}
