package top.serms.hgm.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import top.serms.hgm.pojo.MgApp;
import top.serms.hgm.pojo.MgVideo;
import top.serms.hgm.pojo.MgVideoNum;
import top.serms.hgm.pojo.vo.MgVideoNumVo;
import top.serms.hgm.pojo.vo.MgVideoVo;
import top.serms.hgm.service.MgAppService;
import top.serms.hgm.service.MgVideoNumService;
import top.serms.hgm.service.MgVideoService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.serms.hgm.util.Result;
import top.serms.hgm.util.WechatApiUtil;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * (MgVideo)表控制层
 *
 * @author SerMs
 * @since 2023-08-16 12:13:10
 */
@RestController
@Api(tags = "视频接口")
@RequestMapping("mgVideo")
@Slf4j
public class MgVideoController {

    /**
     * 服务对象
     */
    @Resource
    private MgVideoService mgVideoService;

    @Resource
    private MgAppService mgAppService;

    @Resource
    private MgVideoNumService mgVideoNumService;

    /**
     * 分页查询所有数据
     */
    @GetMapping
    @ApiOperation("分页查询所有数据")
    public Result page(@RequestParam int current, @RequestParam int size) {
        Page<MgVideo> page = new Page<>(current, size);
        return Result.success(this.mgVideoService.page(page));
    }


    /**
     * 通过主键查询单条数据
     */
    @GetMapping("{id}")
    @ApiOperation("通过主键查询单条数据")
    public Result selectOne(@PathVariable Serializable id) {
        return Result.success(this.mgVideoService.getById(id));
    }

    /**
     * 新增数据
     */
    @PostMapping
    @ApiOperation("新增数据")
    public Result save(@RequestBody MgVideo mgVideo) {
        return Result.success(this.mgVideoService.save(mgVideo));
    }

    /**
     * 修改数据
     */
    @PutMapping
    @ApiOperation("修改drama_id")
    public Result updateById(@RequestBody MgVideoVo mgVideo) {
        MgVideo video = BeanUtil.copyProperties(mgVideo, MgVideo.class);
        return Result.success(this.mgVideoService.updateById(video));
    }

    /**
     * 单条/批量删除数据
     */
    @DeleteMapping
    @ApiOperation("单条/批量删除数据")
    public Result delete(@RequestParam List<Long> id) {
        return Result.success(this.mgVideoService.removeByIds(id));
    }

    @PutMapping("/syncDrama/{id}")
    @ApiOperation("同步数据到其他小程序")
    public Result delete(@PathVariable Integer id) {
        MgVideo video = this.mgVideoService.getById(id);
        String dramaId = video.getDramaId();
        MgApp app = this.mgAppService.getById(video.getPid());
        String accessToken = app.getAccessToken();
        List<MgVideoNumVo> mgVideoNumList = WechatApiUtil.requestMediaList(accessToken, dramaId);
        if (mgVideoNumList == null) {
            return Result.error("获取媒资列表数据异常，errcode:40001");
        }
        this.mgVideoNumService.delByVid(id);
        List<MgApp> mgApps = this.mgAppService.list();
        int count = 1;
        List<MgVideoNum> mgVideoNums;
        for (MgApp mgApp : mgApps) {
            count = mgVideoNumList.size();
            for (MgVideoNumVo mgVideoNum : mgVideoNumList) {
                mgVideoNum.setVideoId(id);
                mgVideoNum.setVideoNum(count--);
                mgVideoNum.setPid(mgApp.getPid());
                log.info("mgVideoNumSerMs:" + mgVideoNum.toString());
            }
            mgVideoNums = BeanUtil.copyToList(mgVideoNumList, MgVideoNum.class);
            this.mgVideoNumService.saveBatch(mgVideoNums);
        }
        return Result.success("同步成功");
    }
}

