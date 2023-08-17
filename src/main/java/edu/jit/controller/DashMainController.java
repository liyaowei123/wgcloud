package edu.jit.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.jit.model.*;
import edu.jit.service.*;
import edu.jit.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
//资源管理-监控概要，主机管理
@Controller
@RequestMapping("/dash")
public class DashMainController {
    private Logger log = LoggerFactory.getLogger(DashMainController.class);
    @Autowired
    private ISystemInfoService systemInfoService;
    @Autowired
    private IDeskStateService deskStateService;
    @Autowired
    private ICpuStateService cpuStateService;
    @Autowired
    private IMemStateService memStateService;
    @Autowired
    private ISysLoadStateService sysLoadStateService;
    @Autowired
    private INetioStateService netioStateService;
    @RequestMapping("main")
    public  String dashMain(Model model){
        deskStateService.initData(model);
        return "index";
    }
    @RequestMapping("systemInfoList")
    public String getAllSystems(Model model,Integer dashView){
        if(dashView==null){
            dashView=1;
        }
        Page<SystemInfo> page=new Page<>((dashView-1)*10,10);
        List<SystemInfo> list=systemInfoService.list();
        page.setRecords(list);
        model.addAttribute("page",page);
        return "host/list";
    }
    @RequestMapping("detail")
    public String getSystemInfoDetail(String id,Model model){
        if(StringUtils.isEmpty(id)){
            log.error("id不能为空!");
            return"redirect:/dash/systemInfoList";
        }
        SystemInfo systemInfo = systemInfoService.getById(id);
        model.addAttribute("systemInfo",systemInfo);
        List<DeskState> deskStateList = deskStateService.getDeskListByName(systemInfo.getHostname());
        model.addAttribute("deskStateList",deskStateList);
        return "dashView/view";
    }

    @RequestMapping("chart")
    public String getChart(String id,Integer dashView,String date,Model model){
        if(null==dashView){
            dashView=1;
        }
        String dateNow=date;
        if(null==date){
            dateNow=DateUtil.dateFormat(new Date());
        }
        SystemInfo systemInfo = systemInfoService.getById(id);
        model.addAttribute("systemInfo",systemInfo);
        model.addAttribute("dateList", DateUtil.getLastWeek());
        model.addAttribute("datenow",dateNow);
        List<CpuState> cpuStateList=cpuStateService.getCpuStateListByName(systemInfo.getHostname(),dateNow);
        List<MemState> memStateList=memStateService.getMemStateListByName(systemInfo.getHostname(),dateNow);
        List<SysLoadState> sysLoadStateList=sysLoadStateService.getSysStateListByName(systemInfo.getHostname(),dateNow);
        List<NetioState> netIoStateList=netioStateService.getNetIoStateListByName(systemInfo.getHostname(),dateNow);
        model.addAttribute("cpuStateList",cpuStateList);
        model.addAttribute("memStateList",memStateList);
        model.addAttribute("sysLoadStateList",sysLoadStateList);
        model.addAttribute("netIoStateList",netIoStateList);
        return "dashView/viewChart";
    }

    @RequestMapping("del")
    public String delHost(String id){
        systemInfoService.removeHostById(id);
        return "redirect:/dash/systemInfoList";
    }
}
