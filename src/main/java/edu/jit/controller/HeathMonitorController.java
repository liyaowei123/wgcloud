package edu.jit.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.jit.model.HeathMonitor;
import edu.jit.service.IHeathMonitorService;
import javax.annotation.Resource;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;

import java.util.Date;
import java.util.List;

/**
 * nullController接口定义
 * @author chendd
 * @date 2023/07/07 11:13
 */
//服务接口检测-服务接口管理
@Api(value = "null" , tags = "null")
@RequestMapping(value = "heathMonitor" , produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
public class HeathMonitorController {
    private Logger logger= LoggerFactory.getLogger(HeathMonitorController.class);

    @Resource
    private IHeathMonitorService heathMonitorService;

    @RequestMapping("list")
    public String healthList(Model model){
        Page<HeathMonitor> page=new Page<>(0,10);
        List<HeathMonitor> list=heathMonitorService.list();
        page.setRecords(list);
        model.addAttribute("page",page);
        return "heath/list";
    }

    @RequestMapping("edit")
    public String editOrAddHeath(String id,Model model){
        HeathMonitor hm;
        if(StringUtils.isEmpty(id)){//新增
            hm=new HeathMonitor();
            hm.setId("");
        }else{//修改
            hm=heathMonitorService.getById(id);
        }
        model.addAttribute("heathMonitor",hm);
        return "heath/add";
    }

    @RequestMapping("save")
    public String saveEditHeath(HeathMonitor hm){
        if(null==hm){
            logger.error("HeathMonitor对象为空，传参不合法");
            return "redirect:/heathMonitor/list";
        }
        if(StringUtils.isEmpty(hm.getId())){//新增，插入
            hm.setId(IdWorker.getIdStr());
            hm.setCreateTime(new Date());
            heathMonitorService.save(hm);
        }else{//修改
            heathMonitorService.updateById(hm);
        }
        return "redirect:/heathMonitor/list";
    }

    @RequestMapping("del")
    public String delHeathById(String id){
        heathMonitorService.removeHeathById(id);
        return "redirect:/heathMonitor/list";
    }

    @RequestMapping("view")
    public String viewHeath(String id,Model model){
        HeathMonitor hm=heathMonitorService.getById(id);
        model.addAttribute("heathMonitor",hm);
        return "heath/view";
    }

}
