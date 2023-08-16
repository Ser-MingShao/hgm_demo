package top.serms.hgm.service;

import top.serms.hgm.pojo.MgVideoNum;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.Serializable;

/**
 * (MgVideoNum)表服务接口
 *
 * @author SerMs
 * @since 2023-08-16 12:13:11
 */
public interface MgVideoNumService extends IService<MgVideoNum> {
    void delByVid(Serializable id);
}

