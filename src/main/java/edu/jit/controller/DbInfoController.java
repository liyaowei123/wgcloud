package edu.jit.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.jit.model.DbInfo;
import edu.jit.model.MessageVo;
import edu.jit.service.IDbInfoService;
import javax.annotation.Resource;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * nullController接口定义
 * @author chendd
 * @date 2023/07/07 11:13
 */
//数据监控-数据源管理
@Api(value = "dbInfo" , tags = "null")
@RequestMapping(value = "dbInfo" , produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
public class DbInfoController {
    private Logger logger= LoggerFactory.getLogger(DbInfoController.class);
    @Resource
    private IDbInfoService dbInfoService;

    @RequestMapping("list")
    public String listDb(Model model,Integer dashView){
        List<DbInfo> list = dbInfoService.list();
       // if(CollectionUtils.isEmpty(list)){
           // list=null;
       // }
        if(null==dashView){
            dashView=1;
        }
        Page<DbInfo> page=new Page<>((dashView-1)*10,10);
        page.setRecords(list);
        model.addAttribute("page",page);
        return "mysql/dblist";
    }

    @RequestMapping("edit")
    public String editOrAddDbInfo(String id,Model model){
        DbInfo dbInfo;
        if(StringUtils.isEmpty(id)){//新增数据源信息
            logger.info("新增数据源页面");
            dbInfo=new DbInfo();
            dbInfo.setId("");
        }else{//修改数据源信息
            logger.info("修改数据源页面");
            dbInfo = dbInfoService.getById(id);
        }
        model.addAttribute("dbInfo",dbInfo);
        return "mysql/init";
    }

    @RequestMapping(value = "validate",method = RequestMethod.POST,produces = {"application/json"})
    @ResponseBody
    public String validateDb(DbInfo dbInfo){
        boolean isConnect=dbInfoService.testConnection(dbInfo);
        MessageVo vo=new MessageVo();
        vo.setMsg(isConnect?"连接成功":"连接失败");
        vo.setCode(isConnect?"0":"1");
        logger.info(JSONUtil.toJsonStr(vo));
        return JSONUtil.toJsonStr(vo);
    }
    @RequestMapping("save")
    public String saveDbInfo(DbInfo dbInfo){
        if(StringUtils.isEmpty(dbInfo.getId())){//新增页面的保存
            dbInfo.setId(IdWorker.getIdStr());
            dbInfo.setCreateTime(new Date());
            dbInfoService.save(dbInfo);
        }else {//修改页面的保存
            dbInfoService.modifyDbInfoById(dbInfo);
        }
        return "redirect:/dbInfo/list";
    }

    @RequestMapping("del")
    public String delDbInfo(String id){
        dbInfoService.removeDbInfoById(id);
        return "redirect:/dbInfo/list";
    }
}
