package top.serms.hgm.service;

import top.serms.hgm.pojo.MgApp;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * (MgApp)表服务接口
 *
 * @author SerMs
 * @since 2023-08-16 12:13:10
 */
public interface MgAppService extends IService<MgApp> {
    MgApp getByAppId(String appId);

    boolean removeByIdsSerMs(List<Long> id);
}

