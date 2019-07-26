package apihome.modules.apitest.service;

import apihome.modules.apitest.entity.ApiTestGroupEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;

import java.util.Map;

/**
 * Created by zyanycall@gmail.com on 2019-07-26 15:39.
 */
public interface ApiTestGroupService extends IService<ApiTestGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存
     */
    void saveGroup(ApiTestGroupEntity apiTestGroupEntity);

    /**
     * 更新
     */
    void update(ApiTestGroupEntity apiTestGroupEntity);

//    /**
//     * 根据key，更新value
//     */
//    void updateValueByKey(String key, String value);

    /**
     * 删除
     */
    void deleteBatch(Long[] ids);


}
