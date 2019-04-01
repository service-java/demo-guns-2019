package cn.stylefeng.guns.modular.system.controller;

import cn.stylefeng.roses.core.base.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.system.model.BizOrder;
import cn.stylefeng.guns.modular.system.service.IBizOrderService;

/**
 * 控制器
 *
 * @author fengshuonan
 * @Date 2018-12-19 12:16:03
 */
@Api("订单管理")
@Controller
@RequestMapping("/bizOrder")
public class BizOrderController extends BaseController {

    private String PREFIX = "/system/bizOrder/";

    @Autowired
    private IBizOrderService bizOrderService;

    /**
     * 跳转到首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "bizOrder.html";
    }

    /**
     * 跳转到添加
     */
    @RequestMapping("/bizOrder_add")
    public String bizOrderAdd() {
        return PREFIX + "bizOrder_add.html";
    }

    /**
     * 跳转到修改
     */
    @RequestMapping("/bizOrder_update/{bizOrderId}")
    public String bizOrderUpdate(@PathVariable Integer bizOrderId, Model model) {
        BizOrder bizOrder = bizOrderService.selectById(bizOrderId);
        model.addAttribute("item", bizOrder);
        LogObjectHolder.me().set(bizOrder);
        return PREFIX + "bizOrder_edit.html";
    }

    /**
     * 获取列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return bizOrderService.selectList(null);
    }

    /**
     * 新增
     */
    @PostMapping(value = "/add")
    @ResponseBody
    @ApiOperation("新增订单")
    public Object add(BizOrder bizOrder) {
        bizOrderService.insert(bizOrder);
        return SUCCESS_TIP;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
//    @ApiOperation("删除订单")
    public Object delete(@RequestParam Integer bizOrderId) {
        bizOrderService.deleteById(bizOrderId);
        return SUCCESS_TIP;
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(BizOrder bizOrder) {
        bizOrderService.updateById(bizOrder);
        return SUCCESS_TIP;
    }

    /**
     * 详情
     */
    @RequestMapping(value = "/detail/{bizOrderId}")
    @ResponseBody
    public Object detail(@PathVariable("bizOrderId") Integer bizOrderId) {
        return bizOrderService.selectById(bizOrderId);
    }
}
