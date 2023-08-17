package edu.jit.controller;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.jit.model.AppInfo;
import edu.jit.model.AppState;
import edu.jit.model.SystemInfo;
import edu.jit.service.IAppInfoService;
import javax.annotation.Resource;

import edu.jit.service.IAppStateService;
import edu.jit.service.ISystemInfoService;
import edu.jit.util.DateUtil;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * nullController接口定义
 * @author chendd
 * @date 2023/07/07 11:13
 */
//资源管理-进程管理
@Api(value = "null" , tags = "null")
@RequestMapping(value = "/appInfo" , produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
public class AppInfoController {
    private Logger log= LoggerFactory.getLogger(AppInfoController.class);
    @Resource
    private IAppInfoService appInfoService;
    @Autowired
    private ISystemInfoService systemInfoService;
    @Autowired
    private IAppStateService appStateService;

    @RequestMapping("list")
    public String listAppInfo(String hostname,Integer dashView, Model model){
        if(hostname==null){
            hostname="";
        }
        if(dashView==null){
            dashView=1;
        }
        AppInfo appInfo=new AppInfo();
        appInfo.setHostname(hostname);
        model.addAttribute("appInfo",appInfo);
        Page<AppInfo> page=new Page<>((dashView-1)*10,10);
        List<AppInfo> list=appInfoService.getAppInfoListByName(hostname);
        if(CollectionUtils.isEmpty(list)){
            list=null;
        }
        page.setRecords(list);
        model.addAttribute("page",page);
        return "app/list";
    }
    //添加进程
    @RequestMapping("edit")
    public String addOrEditApp(String id,Model model){
        AppInfo info;
        List<SystemInfo> systemInfoList=systemInfoService.getHostnames();
        if(StringUtils.isEmpty(id)){//新增进程
            info=new AppInfo();
            info.setId("");
        }else{//修改进程,也就是编辑操作
            info=appInfoService.getById(id);
        }
        model.addAttribute("appInfo",info);
        model.addAttribute("systemInfoList",systemInfoList);
        return"app/add";
    }

    @RequestMapping("save")
    public String saveApp(AppInfo appInfo){
        if(null==appInfo || null==appInfo.getHostname()){
            log.error("进程信息不全,无法保存");

        }else{
            String id = appInfo.getId();
            if(StringUtils.isEmpty(id)){//新增的保存
                appInfo.setCreateTime(new Date());
                appInfo.setId(IdWorker.getIdStr());
                appInfoService.save(appInfo);
            }else{//修改的保存
                appInfoService.updateById(appInfo);
            }
        }
        return "redirect:/appInfo/list";
    }

    @RequestMapping("view")
    public String appInfoView(String id,Model model,String date){
        //需要APP_INFO
        AppInfo info = appInfoService.getById(id);
        //需要APP_STATE列表
        List<String> idList=new ArrayList<>();
        idList.add(id);
        List<AppState> appStateList = appStateService.listByIds(idList);
        model.addAttribute("datenow", date==null?DateUtil.dateFormat(new Date()):date);
        List<String> dateList=DateUtil.getLastWeek();
        appStateList.forEach(x->{
            JSONObject json=new JSONObject();
            json.append("dateStr",x.getDateStr());
            json.append("cpuPer",x.getCpuPer());
            x.setCpuStateJson(json.toString());
            json.append("memPer",x.getMemPer());
            x.setMemStateJson(json.toString());
        });
        model.addAttribute("dateList",dateList);
        model.addAttribute("appInfo",info);
        model.addAttribute("appStateList",appStateList);
        return "app/view";
    }

    @RequestMapping("del")
    public String delAppInfo(String id){
        appInfoService.removeAppById(id);
        return "redirect:/appInfo/list";
    }
}
