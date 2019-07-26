package apihome.modules.global.service;

import apihome.modules.global.entity.TestHostEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;

import java.util.Map;

/**
 * Created by zyanycall@gmail.com on 2019-07-25 20:11.
 */
public interface TestHostService extends IService<TestHostEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存信息
     */
    void saveHost(TestHostEntity testHostEntity);

    /**
     * 更新信息
     */
    void update(TestHostEntity testHostEntity);

//    /**
//     * 根据key，更新value
//     */
//    void updateValueByKey(String key, String value);

    /**
     * 批量删除信息
     */
    void deleteBatch(Long[] ids);
}
