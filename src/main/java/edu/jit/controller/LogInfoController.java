package edu.jit.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.jit.model.LogInfo;
import edu.jit.service.ILogInfoService;
import javax.annotation.Resource;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;

import java.util.List;

/**
 * nullController接口定义
 * @author chendd
 * @date 2023/07/07 11:13
 */
//系统信息-日志信息
@Api(value = "null" , tags = "null")
@RequestMapping(value = "/log" , produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
public class LogInfoController {

    @Resource
    private ILogInfoService logInfoService;

    @RequestMapping("list")
    public String logList(Model model){
        List<LogInfo> list = logInfoService.list();
        Page<LogInfo> page = new Page<>(0,10);
        page.setRecords(list);
        model.addAttribute("page",page);
        LogInfo info=logInfoService.getDefaultHostname();
        if(null==info){
            info=new LogInfo();
        }
        info.setHostname("");
        model.addAttribute("logInfo",info);
        return "log/list";
    }

    @RequestMapping("view")
    public String viewContent(String id,Model model){
        LogInfo info = logInfoService.getById(id);
        model.addAttribute("logInfo",info);
        return "log/view";
    }
}
