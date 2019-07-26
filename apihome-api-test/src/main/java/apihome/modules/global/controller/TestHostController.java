package apihome.modules.global.controller;

import apihome.modules.global.entity.TestHostEntity;
import apihome.modules.global.service.TestHostService;
import io.renren.common.annotation.SysLog;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by zyanycall@gmail.com on 2019-07-25 20:00.
 */
@RestController
@RequestMapping("/apiTest/host")
public class TestHostController {

    @Autowired
    private TestHostService testHostService;

    /**
     * 所有列表
     */
    @GetMapping("/list")
    @RequiresPermissions("test:host:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = testHostService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("test:host:info")
    public R info(@PathVariable("id") Long id){
        TestHostEntity host = testHostService.getById(id);

        return R.ok().put("host", host);
    }

    /**
     * 保存
     */
    @SysLog("保存host信息")
    @PostMapping("/save")
    @RequiresPermissions("test:host:save")
    public R save(@RequestBody TestHostEntity host){
        ValidatorUtils.validateEntity(host);

        testHostService.saveHost(host);

        return R.ok();
    }

    /**
     * 修改
     */
    @SysLog("修改host信息")
    @PostMapping("/update")
    @RequiresPermissions("test:host:update")
    public R update(@RequestBody TestHostEntity host){
        ValidatorUtils.validateEntity(host);

        testHostService.update(host);

        return R.ok();
    }

    /**
     * 删除
     */
    @SysLog("删除host信息")
    @PostMapping("/delete")
    @RequiresPermissions("test:host:delete")
    public R delete(@RequestBody Long[] ids){
        testHostService.deleteBatch(ids);

        return R.ok();
    }


    /**
     * 修改status
     */
    @PostMapping("/changeStatus")
    @RequiresPermissions("test:host:changeStatus")
    public R changeStatus(@RequestBody TestHostEntity host){
        TestHostEntity testHostEntity = testHostService.getById(host.getHostId());
        testHostEntity.setStatus(host.getStatus());
        testHostService.update(testHostEntity);

        return R.ok();
    }

}

