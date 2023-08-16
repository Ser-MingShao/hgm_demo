package top.serms.hgm.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import top.serms.hgm.pojo.AccessTokenResponse;
import top.serms.hgm.pojo.MgApp;
import top.serms.hgm.pojo.vo.MgAppVo;
import top.serms.hgm.service.MgAppService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.serms.hgm.util.Result;
import top.serms.hgm.util.WechatApiUtil;

import javax.annotation.Resource;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * (MgApp)表控制层
 *
 * @author SerMs
 * @since 2023-08-16 12:13:10
 */
@RestController
@Api(tags = "小程序接口")
@RequestMapping("mgApp")
@Slf4j
public class MgAppController {

    /**
     * 服务对象
     */
    @Resource
    private MgAppService mgAppService;

    /**
     * 分页查询所有数据
     */
    @GetMapping
    @ApiOperation("分页查询所有小程序")
    public Result page(@RequestParam int current, @RequestParam int size) {
        Page<MgApp> page = new Page<>(current, size);
        return Result.success(this.mgAppService.page(page));
    }


    /**
     * 通过主键查询单条数据
     */
    @GetMapping("{id}")
    @ApiOperation("通过主键查询单条数据")
    public Result selectOne(@PathVariable Serializable id) {
        return Result.success(this.mgAppService.getById(id));
    }

    /**
     * 新增数据
     */
    @PostMapping
    @ApiOperation("登录获取token或刷新token")
    public Result save(@RequestBody MgAppVo mgAppVo) {
        MgApp mgApp = this.mgAppService.getByAppId(mgAppVo.getAppId());
//        log.info("mgApp:" + mgApp);
        if (ObjectUtil.isEmpty(mgApp)) {
            AccessTokenResponse tokenResponse = WechatApiUtil.requestAccessToken(mgAppVo.getAppId(), mgAppVo.getAppSecret());
            MgApp app = new MgApp();
            app.setAppSecret(mgAppVo.getAppSecret());
            app.setAppId(mgAppVo.getAppId());
            app.setAccessToken(tokenResponse.getAccess_token());
            long exTime = tokenResponse.getExpires_in() * 1000 + System.currentTimeMillis();
            Date date = new Date(exTime);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = sdf.format(date);
            System.out.println("Formatted Date: " + formattedDate);
            app.setExpiresTime(exTime);
            this.mgAppService.save(app);
            log.info("登录信息为：" + app + "过期时间为：" + formattedDate);
            return Result.success("AccessToke:" + app.getAccessToken());
        } else {
            AccessTokenResponse tokenResponse = WechatApiUtil.requestAccessToken(mgApp.getAppId(), mgApp.getAppSecret());
            mgApp.setAccessToken(tokenResponse.getAccess_token());
            long exTime = tokenResponse.getExpires_in() * 1000 + System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = sdf.format(new Date(exTime));
            mgApp.setExpiresTime(exTime);
            this.mgAppService.updateById(mgApp);
            log.info("更新成功" + formattedDate);
            return Result.success("新的AccessToke：" + mgApp.getAccessToken());
        }

    }

    /**
     * 修改数据
     */
    @PutMapping
    @ApiOperation("修改数据")
    public Result updateById(@RequestBody MgAppVo mgApp) {
        MgApp app = BeanUtil.copyProperties(mgApp, MgApp.class);
        AccessTokenResponse tokenResponse = WechatApiUtil.requestAccessToken(mgApp.getAppId(), mgApp.getAppSecret());
        app.setAccessToken(tokenResponse.getAccess_token());
        app.setExpiresTime(tokenResponse.getExpires_in() * 1000 + System.currentTimeMillis());
        return Result.success(this.mgAppService.updateById(app));
    }

    /**
     * 单条/批量删除数据
     */
    @DeleteMapping
    @ApiOperation("单条/批量删除数据")
    public Result delete(@RequestParam List<Long> id) {
        return Result.success(this.mgAppService.removeByIdsSerMs(id));
    }
}

