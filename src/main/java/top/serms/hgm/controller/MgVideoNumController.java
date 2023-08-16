package top.serms.hgm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.serms.hgm.pojo.MgVideoNum;
import top.serms.hgm.service.MgVideoNumService;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import top.serms.hgm.util.Result;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (MgVideoNum)表控制层
 *
 * @author SerMs
 * @since 2023-08-16 12:13:11
 */
@RestController
@Api(tags = "视频剧集内容")
@RequestMapping("mgVideoNum")
public class MgVideoNumController {

    /**
     * 服务对象
     */
    @Resource
    private MgVideoNumService mgVideoNumService;

    /**
     * 分页查询所有数据
     */
    @GetMapping
    @ApiOperation("分页查询所有数据")
    public Result page(@RequestParam int current, @RequestParam int size) {
        Page<MgVideoNum> page = new Page<>(current, size);
        return Result.success(this.mgVideoNumService.page(page));
    }


    /**
     * 通过主键查询单条数据
     */
    @GetMapping("{id}")
    @ApiOperation("通过主键查询单条数据")
    public Result selectOne(@PathVariable Serializable id) {
        return Result.success(this.mgVideoNumService.getById(id));
    }

    /**
     * 新增数据
     */
    @PostMapping
    @ApiOperation("新增数据")
    public Result save(@RequestBody MgVideoNum mgVideoNum) {
        return Result.success(this.mgVideoNumService.save(mgVideoNum));
    }

    /**
     * 修改数据
     */
    @PutMapping
    @ApiOperation("修改数据")
    public Result updateById(@RequestBody MgVideoNum mgVideoNum) {
        return Result.success(this.mgVideoNumService.updateById(mgVideoNum));
    }

    /**
     * 单条/批量删除数据
     */
    @DeleteMapping
    @ApiOperation("单条/批量删除数据")
    public Result delete(@RequestParam List<Long> id) {
        return Result.success(this.mgVideoNumService.removeByIds(id));
    }
}

