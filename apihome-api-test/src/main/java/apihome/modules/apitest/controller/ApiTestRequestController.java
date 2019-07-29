package apihome.modules.apitest.controller;

import apihome.modules.apitest.entity.ApiTestRequestEntity;
import apihome.modules.apitest.service.ApiTestRequestService;
import io.renren.common.annotation.SysLog;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by zyanycall@gmail.com on 2019-07-29 10:36.
 */
@RestController
@RequestMapping("/apiTest/request")
public class ApiTestRequestController {

    @Autowired
    private ApiTestRequestService apiTestRequestService;

    /**
     * 所有请求列表
     */
    @GetMapping("/list")
    @RequiresPermissions("apiTest:request:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = apiTestRequestService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 请求信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("apiTest:request:info")
    public R info(@PathVariable("id") Long id){
        ApiTestRequestEntity request = apiTestRequestService.getById(id);

        return R.ok().put("request", request);
    }

    /**
     * 保存请求
     */
    @SysLog("保存接口请求信息")
    @PostMapping("/save")
    @RequiresPermissions("apiTest:request:save")
    public R save(@RequestBody ApiTestRequestEntity request){
        ValidatorUtils.validateEntity(request);

        apiTestRequestService.saveRequest(request);

        return R.ok();
    }

    /**
     * 修改请求
     */
    @SysLog("修改接口请求信息")
    @PostMapping("/update")
    @RequiresPermissions("apiTest:request:update")
    public R update(@RequestBody ApiTestRequestEntity request){
        ValidatorUtils.validateEntity(request);

        apiTestRequestService.update(request);

        return R.ok();
    }

    /**
     * 删除请求
     */
    @SysLog("删除接口请求信息")
    @PostMapping("/delete")
    @RequiresPermissions("apiTest:request:delete")
    public R delete(@RequestBody Long[] ids){
        apiTestRequestService.deleteBatch(ids);

        return R.ok();
    }

    /**
     * 快速请求
     */
    @PostMapping("/fastTest")
    @RequiresPermissions("apiTest:request:fastTest")
    public R fastTest(@RequestBody Map<String,Object> params){

//            @RequestParam(value = "protocol") String protocol,
//                      @RequestParam(value = "method") String method,
//                      @RequestParam(value = "hostIp") String hostIp,
//                      @RequestParam(value = "url") String url,
//                      @RequestParam(value = "headers") List<String> headers,
//                      @RequestParam(value = "bodyMode") String bodyMode,
//                      @RequestParam(value = "bodyDataForm") List<String> bodyDataForm,
//                      @RequestParam(value = "bodyDataRaw") String bodyDataRaw

        System.out.println();

        return R.ok();
    }


}
