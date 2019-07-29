package apihome.modules.apitest.service;

import apihome.modules.apitest.entity.ApiTestRequestEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;

import java.util.Map;

/**
 * Created by zyanycall@gmail.com on 2019-07-29 10:29.
 */
public interface ApiTestRequestService  extends IService<ApiTestRequestEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 保存信息
     */
    void saveRequest(ApiTestRequestEntity apiTestRequestEntity);

    /**
     * 更新信息
     */
    void update(ApiTestRequestEntity apiTestRequestEntity);

//    /**
//     * 根据key，更新value
//     */
//    void updateValueByKey(String key, String value);

    /**
     * 删除信息
     */
    void deleteBatch(Long[] ids);

}
