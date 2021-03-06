package com.djcps.wms.stocktaking.controller;

import com.baidu.unbiz.fluentvalidator.ComplexResult;
import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.ResultCollectors;
import com.baidu.unbiz.fluentvalidator.jsr303.HibernateSupportedValidator;
import com.djcps.log.DjcpsLogger;
import com.djcps.log.DjcpsLoggerFactory;
import com.djcps.wms.commons.aop.log.AddLog;
import com.djcps.wms.commons.base.BaseAddBO;
import com.djcps.wms.commons.enums.SysMsgEnum;
import com.djcps.wms.commons.model.PartnerInfoBO;
import com.djcps.wms.commons.msg.MsgTemplate;
import com.djcps.wms.commons.utils.GsonUtils;
import com.djcps.wms.stocktaking.model.*;
import com.djcps.wms.stocktaking.service.StocktakingTaskService;
import com.google.gson.Gson;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validation;
import java.util.Map;

/**
 * @title:盘点任务控制层
 * @description:
 * @author:wzy
 * @company:djwms
 * @create:2018/1/9
 **/
@RestController
@RequestMapping(value = "/stocktaking")
public class StocktakingTaskController {
    private static final DjcpsLogger LOGGER = DjcpsLoggerFactory.getLogger(StocktakingTaskController.class);

    @Autowired
    private StocktakingTaskService stocktakingTaskService;

    private Gson gson = GsonUtils.gson;

    /**
     * 优化版新增全盘
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/25 9:10
     **/
    @AddLog(value ="新增全盘任务",module = "盘点")
    @RequestMapping(name="新增全盘任务",value = "/addAllTask", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> addTaskByAll(@RequestBody(required = false) String json, HttpServletRequest request){
        try {
            PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
            AddTaskBO addStocktakingBO=gson.fromJson(json,AddTaskBO.class);
            BeanUtils.copyProperties(partnerInfoBo,addStocktakingBO);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(addStocktakingBO,
                            new HibernateSupportedValidator<AddTaskBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return stocktakingTaskService.addTaskByAll(addStocktakingBO);
        }
        catch (Exception e){
            LOGGER.error("新增全盘异常：{} ",e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 新增部分盘点任务
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/11 9:34
     **/
    @AddLog(value = "新增部分盘点任务",module = "盘点")
    @RequestMapping(name="新增部分盘点任务",value = "/addPartTask", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> addPartTask(@RequestBody(required = false) String json, HttpServletRequest request){
        try {
            PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
            AddStocktakingBO addStocktakingBO=gson.fromJson(json,AddStocktakingBO.class);
            BeanUtils.copyProperties(partnerInfoBo,addStocktakingBO);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(addStocktakingBO,
                            new HibernateSupportedValidator<AddStocktakingBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return stocktakingTaskService.addTaskByPart(addStocktakingBO);
        }
        catch (Exception e){
            LOGGER.error("新增部分任务异常：{} ",e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     *更新盘点状态
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/11 9:30
     **/
    @RequestMapping(name="更新盘点状态",value = "/updateTask", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> updateTaskState(@RequestBody(required = false) String json, HttpServletRequest request){
        try{
        UpdateStocktakingTaskBO updateStocktakingTaskBO=gson.fromJson(json,UpdateStocktakingTaskBO.class);
        PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
        BeanUtils.copyProperties(partnerInfoBo,updateStocktakingTaskBO);
        ComplexResult ret = FluentValidator.checkAll().failFast()
                .on(updateStocktakingTaskBO,
                        new HibernateSupportedValidator<UpdateStocktakingTaskBO>()
                                .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                .doValidate().result(ResultCollectors.toComplex());
        if (!ret.isSuccess()) {
            return MsgTemplate.failureMsg(ret);
        }
        return stocktakingTaskService.updateTaskState(updateStocktakingTaskBO);
        }
        catch (Exception e){
            LOGGER.error("更新盘点状态异常：{} ",e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 下发任务，更新pda状态
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/26 10:16
     **/
    @RequestMapping(name="下发任务更新pda状态",value = "/updatePdaState", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> updatePdaState(@RequestBody(required = false) String json, HttpServletRequest request){
        try{
        UpdateStocktakingTaskBO updateStocktakingTaskBO=gson.fromJson(json,UpdateStocktakingTaskBO.class);
        PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
        BeanUtils.copyProperties(partnerInfoBo,updateStocktakingTaskBO);
        ComplexResult ret = FluentValidator.checkAll().failFast()
                .on(updateStocktakingTaskBO,
                        new HibernateSupportedValidator<UpdateStocktakingTaskBO>()
                                .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                .doValidate().result(ResultCollectors.toComplex());
        if (!ret.isSuccess()) {
            return MsgTemplate.failureMsg(ret);
        }
        return stocktakingTaskService.updatePdaStatus(updateStocktakingTaskBO);
        }
        catch (Exception e){
            LOGGER.error("更新pda状态异常：{} ",e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 获取可用盘点人员列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/11 15:00
     **/
    @RequestMapping(name="获取可用盘点人员集合",value = "/getInventoryclerk", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> getInventoryclerk(@RequestBody(required = false) String json, HttpServletRequest request){
        BaseAddBO param = new BaseAddBO();
        PartnerInfoBO partnerInfoBean = (PartnerInfoBO) request.getAttribute("partnerInfo");
        BeanUtils.copyProperties(partnerInfoBean,param);
        return stocktakingTaskService.getInventoryclerk(param);
    }

    /**
     * 获取新增盘点任务作业单号
     * @author  wzy
     * @param json
     * @return
     * @create  2018/1/11 15:17
     **/
    @RequestMapping(name="获取新增盘点任务作业单号",value = "/getStocktakingNumber", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> getStocktakingNumber(@RequestBody(required = false) String json, HttpServletRequest request){
        try {
            return stocktakingTaskService.getNumber();
        } catch (Exception e) {
            LOGGER.error("获取新增盘点任务作业单号异常：{} ",e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 保存新增盘点任务
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/11 15:17
     **/
    @RequestMapping(name="保存新增的盘点任务",value = "/saveTask", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> saveTask(@RequestBody(required = false) String json, HttpServletRequest request){
        try{
        PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
        SaveStocktakingTaskBO saveStocktakingTaskBO=gson.fromJson(json,SaveStocktakingTaskBO.class) ;
        BeanUtils.copyProperties(partnerInfoBo,saveStocktakingTaskBO);
        saveStocktakingTaskBO.setCreatorId(partnerInfoBo.getOperatorId());
        saveStocktakingTaskBO.setCreator(partnerInfoBo.getOperator());
        ComplexResult ret = FluentValidator.checkAll().failFast()
                .on(saveStocktakingTaskBO,
                        new HibernateSupportedValidator<SaveStocktakingTaskBO>()
                                .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                .doValidate().result(ResultCollectors.toComplex());
        if (!ret.isSuccess()) {
            return MsgTemplate.failureMsg(ret);
        }
        return stocktakingTaskService.saveSoctakingTask(saveStocktakingTaskBO);
        }
        catch (Exception e){
            LOGGER.error("保存盘点任务异常：{} ",e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 录入盘点结果时请求盘点任务信息
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/12 14:29
     **/
    @RequestMapping(name="请求盘点任务信息",value = "/stocktakingOrderInfoList", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> stocktakingOrderInfoList(@RequestBody(required = false) String json, HttpServletRequest request){
        try{
        PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
        PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO=gson.fromJson(json,PdaGetStocktakingOrderBO.class);
        BeanUtils.copyProperties(partnerInfoBo,pdaGetStocktakingOrderBO);
        ComplexResult ret = FluentValidator.checkAll().failFast()
                .on(pdaGetStocktakingOrderBO,
                        new HibernateSupportedValidator<PdaGetStocktakingOrderBO>()
                                .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                .doValidate().result(ResultCollectors.toComplex());
        if (!ret.isSuccess()) {
            return MsgTemplate.failureMsg(ret);
        }
        return stocktakingTaskService.stocktakingOrderInfoList(pdaGetStocktakingOrderBO);
        }
        catch (Exception e){
            LOGGER.error("获取盘点任务信息异常：{} ",e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * web发起盘盈
     * @author  wzy
     * @param
     * @return
     * @date  2018/2/3 12:17
     **/
    @RequestMapping(name="发起盘盈",value = "/inventorySurplus", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> inventorySurplus(@RequestBody(required = false) String json, HttpServletRequest request){
        try{
        StocktakingTaskfBO stocktakingTaskBO=gson.fromJson(json,StocktakingTaskfBO.class);
        PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
        BeanUtils.copyProperties(partnerInfoBo,stocktakingTaskBO);
        ComplexResult ret = FluentValidator.checkAll().failFast()
                .on(stocktakingTaskBO,
                        new HibernateSupportedValidator<StocktakingTaskfBO>()
                                .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                .doValidate().result(ResultCollectors.toComplex());
        if (!ret.isSuccess()) {
            return MsgTemplate.failureMsg(ret);
        }
        return stocktakingTaskService.inventorySurplus2(stocktakingTaskBO);
        }
        catch (Exception e){
            LOGGER.error("发起盘盈异常：{} ",e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * @title:保存盘盈录入信息/保存单条PDA盘点结果
     * @description:
     * @author:wzy
     * @company:djwms
     * @create:2018/1/14
     **/
    @RequestMapping(name="PDA保存盘盈录入信息/保存单条PDA盘点结果",value = "/saveInventoryProfitInfo", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> saveInventoryProfitInfo(@RequestBody(required = false) String json, HttpServletRequest request){
        try{
        SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO=gson.fromJson(json,SaveStocktakingOrderInfoBO.class);
        PartnerInfoBO partnerInfoBO=(PartnerInfoBO)request.getAttribute("partnerInfo");
        BeanUtils.copyProperties(partnerInfoBO,saveStocktakingOrderInfoBO);
        ComplexResult ret = FluentValidator.checkAll().failFast()
                .on(saveStocktakingOrderInfoBO,
                        new HibernateSupportedValidator<SaveStocktakingOrderInfoBO>()
                                .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                .doValidate().result(ResultCollectors.toComplex());
        if (!ret.isSuccess()) {
            return MsgTemplate.failureMsg(ret);
        }
        return stocktakingTaskService.saveInventoryProfitInfo(saveStocktakingOrderInfoBO);
    }
        catch (Exception e){
            LOGGER.error("PDA保存单挑盘点结果异常：{} ",e.getMessage());
            e.printStackTrace();
        return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
     }
    }

    /**
     * PDA盘盈请求
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/12 10:31
     **/
    @RequestMapping(name="PDA发起盘盈",value = "/pdaInventorySurplus", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> pdaInventorySurplus(@RequestBody(required = false) String json, HttpServletRequest request){
        try{
        StocktakingTaskBO stocktakingTaskBO=gson.fromJson(json,StocktakingTaskBO.class);
        PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
        BeanUtils.copyProperties(partnerInfoBo,stocktakingTaskBO);
        ComplexResult ret = FluentValidator.checkAll().failFast()
                .on(stocktakingTaskBO,
                        new HibernateSupportedValidator<StocktakingTaskBO>()
                                .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                .doValidate().result(ResultCollectors.toComplex());
        if (!ret.isSuccess()) {
            return MsgTemplate.failureMsg(ret);
        }
        return stocktakingTaskService.pdaInventorySurplus(stocktakingTaskBO);
        }
        catch (Exception e){
            LOGGER.error("PDA发起盘盈异常：{} ",e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 优化版
     * @author  wzy
     * @param
     * @return
     * @date  2018/2/4 9:07
     **/
    @RequestMapping(name="PDA发起盘盈",value = "/pdaInventorySurplus2", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> pdaInventorySurplus2(@RequestBody(required = false) String json, HttpServletRequest request){
        try{
        StocktakingTaskfBO stocktakingTaskBO=gson.fromJson(json,StocktakingTaskfBO.class);
        PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
        BeanUtils.copyProperties(partnerInfoBo,stocktakingTaskBO);
        ComplexResult ret = FluentValidator.checkAll().failFast()
                .on(stocktakingTaskBO,
                        new HibernateSupportedValidator<StocktakingTaskfBO>()
                                .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                .doValidate().result(ResultCollectors.toComplex());
        if (!ret.isSuccess()) {
            return MsgTemplate.failureMsg(ret);
        }
        return stocktakingTaskService.pdaInventorySurplus2(stocktakingTaskBO);
        }
        catch (Exception e){
            LOGGER.error("PDA发起盘盈异常：{} ",e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 暂存盘点结果请求
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/12 14:38
     **/
    @RequestMapping(name="Web暂存盘点结果请求",value = "/saveStocktaking", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> saveStocktaking(@RequestBody(required = false) String json, HttpServletRequest request){
        try{
        SaveStocktakingOrderInfoList saveStocktakingOrderInfoBOList=gson.fromJson(json,SaveStocktakingOrderInfoList.class);
        PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
        BeanUtils.copyProperties(partnerInfoBo,saveStocktakingOrderInfoBOList);
        return stocktakingTaskService.saveStocktakingResult(saveStocktakingOrderInfoBOList,partnerInfoBo);
        }
        catch (Exception e){
            LOGGER.error("Web暂存盘点结果请求：{} ",e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 保存盘点请求
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/12 16:05
     **/
    @RequestMapping(name="Web保存盘点结果请求",value = "/completeStocktaking", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> completeStocktaking(@RequestBody(required = false) String json, HttpServletRequest request){
       try{
        SaveStocktakingOrderInfoList saveStocktakingOrderInfoList=gson.fromJson(json,SaveStocktakingOrderInfoList.class);
        PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
        BeanUtils.copyProperties(partnerInfoBo,saveStocktakingOrderInfoList);
        ComplexResult ret = FluentValidator.checkAll().failFast()
                .on(saveStocktakingOrderInfoList,
                        new HibernateSupportedValidator<SaveStocktakingOrderInfoList>()
                                .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                .doValidate().result(ResultCollectors.toComplex());
        if (!ret.isSuccess()) {
            return MsgTemplate.failureMsg(ret);
        }
        return stocktakingTaskService.completeStocktakingTask(saveStocktakingOrderInfoList,partnerInfoBo);
       }
       catch (Exception e){
           LOGGER.error("Web保存盘点结果请求：{} ",e.getMessage());
           e.printStackTrace();
           return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
       }
    }

    /**
     * PDA获取盘点任务列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 13:18
     **/
    @RequestMapping(name="PDA获取盘点任务列表",value = "/pdaGetStocktakingTaskList", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> pdaGetStocktakingTaskList(@RequestBody(required = false) String json, HttpServletRequest request){
        try{
        PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
        PdaStocktakingTaskBO pdaStocktakingTaskBO=gson.fromJson(json,PdaStocktakingTaskBO.class);
        BeanUtils.copyProperties(partnerInfoBo,pdaStocktakingTaskBO);
        ComplexResult ret = FluentValidator.checkAll().failFast()
                .on(pdaStocktakingTaskBO,
                        new HibernateSupportedValidator<PdaStocktakingTaskBO>()
                                .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                .doValidate().result(ResultCollectors.toComplex());
        if (!ret.isSuccess()) {
            return MsgTemplate.failureMsg(ret);
        }
        return stocktakingTaskService.pdaStocktakingTaskList(pdaStocktakingTaskBO);
    }
        catch (Exception e){
            LOGGER.error("PDA获取盘点任务列表失败：{} ",e.getMessage());
            e.printStackTrace();
        return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * PDA获取盘点任务订单列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 13:18
     **/
    @RequestMapping(name="PDA获取盘点任务订单列表",value = "/pdaGetStocktakingOrderList", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> pdaGetStocktakingOrderList(@RequestBody(required = false) String json, HttpServletRequest request){
        try{
            PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
            PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO=gson.fromJson(json,PdaGetStocktakingOrderBO.class);
            BeanUtils.copyProperties(partnerInfoBo,pdaGetStocktakingOrderBO);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(pdaGetStocktakingOrderBO,
                            new HibernateSupportedValidator<PdaGetStocktakingOrderBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return stocktakingTaskService.pdaStocktakingOrderList(pdaGetStocktakingOrderBO);
        }
        catch (Exception e){
            LOGGER.error("PDA获取盘点任务订单列表失败：{} ",e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * PDA获取盘点任务订单详情
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 15:53
     **/
    @RequestMapping(name="PDA获取盘点任务订单详情",value = "/pdaStocktakingOrderInfo", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> pdaStocktakingOrderInfo(@RequestBody(required = false) String json, HttpServletRequest request){
        try{
            PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
            PdaStocktakingOrderInfo pdaStocktakingOrderInfo=gson.fromJson(json,PdaStocktakingOrderInfo.class);
            BeanUtils.copyProperties(partnerInfoBo,pdaStocktakingOrderInfo);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(pdaStocktakingOrderInfo,
                            new HibernateSupportedValidator<PdaStocktakingOrderInfo>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return stocktakingTaskService.pdaStocktakingOrderInfo(pdaStocktakingOrderInfo);
        }
        catch (Exception e){
            e.printStackTrace();
            LOGGER.error("PDA获取盘点任务订单详情异常:{}",e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * PDA保存盘点结果
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 13:18
     **/
    @RequestMapping(name="PDA保存盘点结果",value = "/pdaSaveStocktakingResult", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> pdaSaveStocktakingResult(@RequestBody(required = false) String json, HttpServletRequest request){
        try{
            PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
            SaveStocktakingOrderInfoBO saveStocktakingOrderInfoBO=gson.fromJson(json,SaveStocktakingOrderInfoBO.class);
            BeanUtils.copyProperties(partnerInfoBo,saveStocktakingOrderInfoBO);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(saveStocktakingOrderInfoBO,
                            new HibernateSupportedValidator<SaveStocktakingOrderInfoBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return stocktakingTaskService.savePdaStocktakingResult(saveStocktakingOrderInfoBO);
        }
        catch (Exception e){
            e.printStackTrace();
            LOGGER.error("PDA保存盘点结果异常：{}",e);
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * PDA完成盘点请求
     * @author  wzy
     * @param
     * @return 
     * @create  2018/1/13 17:17
     **/
    @RequestMapping(name="PDA获取盘点订单各个状态数量",value = "/getOrderAmount", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> getOrderAmount(@RequestBody(required = false) String json, HttpServletRequest request){
        try{
            PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
            PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO=gson.fromJson(json,PdaGetStocktakingOrderBO.class);
            BeanUtils.copyProperties(partnerInfoBo,pdaGetStocktakingOrderBO);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(pdaGetStocktakingOrderBO,
                            new HibernateSupportedValidator<PdaGetStocktakingOrderBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return stocktakingTaskService.getOrderAmount(pdaGetStocktakingOrderBO);
        }
        catch (Exception e){
            LOGGER.error("PDA获取盘点订单各个状态数量异常：{}",e.getMessage());
            e.printStackTrace();
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * PDA完成盘点更新状态
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/13 15:25
     **/
    @RequestMapping(name="PDA完成盘点更新状态",value = "/pdaCompleteStocktaking", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> pdaCompleteStocktaking(@RequestBody(required = false) String json, HttpServletRequest request){
       try{
        PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
        PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO=gson.fromJson(json,PdaGetStocktakingOrderBO.class);
        BeanUtils.copyProperties(partnerInfoBo,pdaGetStocktakingOrderBO);
        ComplexResult ret = FluentValidator.checkAll().failFast()
                .on(pdaGetStocktakingOrderBO,
                        new HibernateSupportedValidator<PdaGetStocktakingOrderBO>()
                                .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                .doValidate().result(ResultCollectors.toComplex());
        if (!ret.isSuccess()) {
            return MsgTemplate.failureMsg(ret);
        }
        return stocktakingTaskService.pdaCompleteStocktaking(pdaGetStocktakingOrderBO);
    }
        catch (Exception e){
            e.printStackTrace();
            LOGGER.error("PDA完成盘点更新状态异常:{}",e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 获取全部盘点任务列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/12 16:39
     **/
    @RequestMapping(name=" 获取全部盘点任务列表",value = "/stocktakingTaskList", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> stocktakingTaskList(@RequestBody(required = false) String json, HttpServletRequest request){
        try{
        GetStocktakingTaskBO getStocktakingTaskBO=gson.fromJson(json,GetStocktakingTaskBO.class);
        PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
        BeanUtils.copyProperties(partnerInfoBo,getStocktakingTaskBO);
        ComplexResult ret = FluentValidator.checkAll().failFast()
                .on(getStocktakingTaskBO,
                        new HibernateSupportedValidator<GetStocktakingTaskBO>()
                                .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                .doValidate().result(ResultCollectors.toComplex());
        if (!ret.isSuccess()) {
            return MsgTemplate.failureMsg(ret);
        }
        return stocktakingTaskService.stocktakingTaskList(getStocktakingTaskBO);
        }
        catch (Exception e){
            e.printStackTrace();
            LOGGER.error("获取全部盘点任务列表异常:{}",e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 条件获取盘点任务列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/12 16:39
     **/
    @RequestMapping(name=" 条件获取盘点任务列表",value = "/searchTaskList", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> searchTaskList(@RequestBody(required = false) String json, HttpServletRequest request){
        try{
        PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
        GetStocktakingTaskBO getStocktakingTaskBO=gson.fromJson(json,GetStocktakingTaskBO.class);
        BeanUtils.copyProperties(partnerInfoBo,getStocktakingTaskBO);
        ComplexResult ret = FluentValidator.checkAll().failFast()
                .on(getStocktakingTaskBO,
                        new HibernateSupportedValidator<GetStocktakingTaskBO>()
                                .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                .doValidate().result(ResultCollectors.toComplex());
        if (!ret.isSuccess()) {
            return MsgTemplate.failureMsg(ret);
        }
        return stocktakingTaskService.searchTaskList(getStocktakingTaskBO);
        }
        catch (Exception e){
            e.printStackTrace();
            LOGGER.error("条件获取盘点任务列表异常:{}",e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 查看盘点结果列表
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/14 13:45
     **/
    @RequestMapping(name=" 查看盘点结果列表",value = "/stocktakingResultList", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> stocktakingResultList(@RequestBody(required = false) String json, HttpServletRequest request){
        try{
        PdaGetStocktakingOrderBO pdaGetStocktakingOrderBO=gson.fromJson(json,PdaGetStocktakingOrderBO.class);
        PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
        BeanUtils.copyProperties(partnerInfoBo,pdaGetStocktakingOrderBO);
        ComplexResult ret = FluentValidator.checkAll().failFast()
                .on(pdaGetStocktakingOrderBO,
                        new HibernateSupportedValidator<PdaGetStocktakingOrderBO>()
                                .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                .doValidate().result(ResultCollectors.toComplex());
        if (!ret.isSuccess()) {
            return MsgTemplate.failureMsg(ret);
        }
        return stocktakingTaskService.stocktakingResultList(pdaGetStocktakingOrderBO);
        }
        catch (Exception e){
            e.printStackTrace();
            LOGGER.error("查看盘点结果列表异常:{}",e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 获取操作记录
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/17 10:43
     **/
    @RequestMapping(name=" 获取操作记录",value = "/operationRecordList", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> operationRecordList(@RequestBody(required = false) String json, HttpServletRequest request){
        try{
        PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
        GetStocktakingTaskBO getStocktakingTaskBO=gson.fromJson(json,GetStocktakingTaskBO.class);
        BeanUtils.copyProperties(partnerInfoBo,getStocktakingTaskBO);
        return stocktakingTaskService.operationRecordList(getStocktakingTaskBO);
        }
        catch (Exception e){
            e.printStackTrace();
            LOGGER.error("获取操作记录异常:{}",e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 查看盘点任务进行情况
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/17 10:54
     **/
    @RequestMapping(name=" 查看盘点任务进行情况",value = "/stocktakingCompleteStatus", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> stocktakingCompleteStatus(@RequestBody(required = false) String json, HttpServletRequest request){
        try{
        PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
        GetStocktakingTaskBO getStocktakingTaskBO=gson.fromJson(json,GetStocktakingTaskBO.class);
        BeanUtils.copyProperties(partnerInfoBo,getStocktakingTaskBO);
        return stocktakingTaskService.stocktakingCompleteStatus(getStocktakingTaskBO);
        }
        catch (Exception e){
            e.printStackTrace();
            LOGGER.error("查看盘点任务进行情况异常:{}",e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    @RequestMapping(name=" 获取盘点任务的库位信息",value = "/areaAndLocInfo", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> areaAndLocInfo(@RequestBody(required = false) String json, HttpServletRequest request){
       try{
        PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
        JobAndWarehouseBO jobAndWarehouseBO=gson.fromJson(json,JobAndWarehouseBO.class);
        BeanUtils.copyProperties(partnerInfoBo,jobAndWarehouseBO);
        ComplexResult ret = FluentValidator.checkAll().failFast()
                .on(jobAndWarehouseBO,
                        new HibernateSupportedValidator<JobAndWarehouseBO>()
                                .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                .doValidate().result(ResultCollectors.toComplex());
        if (!ret.isSuccess()) {
            return MsgTemplate.failureMsg(ret);
        }
        return stocktakingTaskService.areaAndLocInfo(jobAndWarehouseBO);
       }
       catch (Exception e){
           e.printStackTrace();
           LOGGER.error("获取盘点任务的库位信息异常:{}",e.getMessage());
           return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
       }
    }

    /**
     * 更新打印次数接口
     * @author  wzy
     * @param
     * @return
     * @create  2018/1/25 9:37
     **/
    @RequestMapping(name=" 更新打印次数接口",value = "/printCount", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> printCount(@RequestBody(required = false) String json, HttpServletRequest request){
        try{
        PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
       PrintCountBO printCountBO=gson.fromJson(json,PrintCountBO.class);
        BeanUtils.copyProperties(partnerInfoBo,printCountBO);
        ComplexResult ret = FluentValidator.checkAll().failFast()
                .on(printCountBO,
                        new HibernateSupportedValidator<PrintCountBO>()
                                .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                .doValidate().result(ResultCollectors.toComplex());
        if (!ret.isSuccess()) {
            return MsgTemplate.failureMsg(ret);
        }
        return stocktakingTaskService.printCount(printCountBO);
        }
        catch (Exception e){
            e.printStackTrace();
            LOGGER.error("更新打印次数接口异常:{}",e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.SYS_EXCEPTION);
        }
    }

    /**
     * 获取未下发作业订单信息接口
     * @author  wzy
     * @param
     * @return 
     * @create  2018/1/25 15:35
     **/
    @RequestMapping(name=" 获取未下发作业订单信息",value = "/noSendOrderInfo", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> noSendOrderInfo(@RequestBody(required = false) String json, HttpServletRequest request){
        try{
            PartnerInfoBO partnerInfoBo=(PartnerInfoBO) request.getAttribute("partnerInfo");
            PrintCountBO printCountBO=gson.fromJson(json,PrintCountBO.class);
            BeanUtils.copyProperties(partnerInfoBo,printCountBO);
            ComplexResult ret = FluentValidator.checkAll().failFast()
                    .on(printCountBO,
                            new HibernateSupportedValidator<PrintCountBO>()
                                    .setHiberanteValidator(Validation.buildDefaultValidatorFactory().getValidator()))
                    .doValidate().result(ResultCollectors.toComplex());
            if (!ret.isSuccess()) {
                return MsgTemplate.failureMsg(ret);
            }
            return stocktakingTaskService.noSendOrderInfo(printCountBO);
        }
        catch (Exception e){
            e.printStackTrace();
            LOGGER.error("获取未下发作业订单信息异常:{}",e.getMessage());
            return MsgTemplate.failureMsg(SysMsgEnum.OPS_FAILURE);
        }

    }
}
