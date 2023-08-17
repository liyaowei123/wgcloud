package edu.jit.controller;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.jit.model.DbInfo;
import edu.jit.model.DbTable;
import edu.jit.model.DbTableCount;
import edu.jit.service.IDbInfoService;
import edu.jit.service.IDbTableCountService;
import edu.jit.service.IDbTableService;
import javax.annotation.Resource;

import edu.jit.util.DateUtil;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
//数据监控-数据表管理
@Api(value = "null" , tags = "null")
@RequestMapping(value = "dbTable" , produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
public class DbTableController {
    private Logger logger= LoggerFactory.getLogger(DbTableController.class);

    @Resource
    private IDbTableService dbTableService;

    @Autowired
    private IDbInfoService dbInfoService;

    @Autowired
    private IDbTableCountService dbTableCountService;

    @RequestMapping("list")
    public String listDbTables(Model model,Integer dashView){
        if(dashView==null){
            dashView=1;
        }
        Page<DbTable> page=new Page<>((dashView-1)*10,10);
        List<DbTable> list=dbTableService.list();
        page.setRecords(list);
        model.addAttribute("page",page);
        return "mysql/list";
    }

    @RequestMapping("edit")
    public String editOrAddDbTable(String id,Model model){
        DbTable dbTable;
        if(StringUtils.isEmpty(id)){//新增请求
            dbTable=new DbTable();
            dbTable.setId("");
        }else{//修改的请求
            dbTable = dbTableService.getById(id);

        }
        List<DbInfo> dbInfoList=dbInfoService.listAliasAndIds();
        model.addAttribute("dbInfoList",dbInfoList);
        model.addAttribute("dbTable",dbTable);
        return "mysql/add";
    }

    @RequestMapping("save")
    public String savaDbTable(DbTable dbTable){
        if(dbTable==null){
            logger.error("页面传入的数据有问题，DBTable对象为空");
            return "redirect:/dbTable/list";
        }
        if(StringUtils.isEmpty(dbTable.getId())){//新增的保存，也就是插入
            dbTable.setId(IdWorker.getIdStr());
            dbTable.setCreateTime(new Date());
            dbTable.setDateStr(DateUtil.dateFormat(new Date()));
            dbTableService.save(dbTable);
        }else {//修改的保存
            dbTableService.updateById(dbTable);
        }
        return "redirect:/dbTable/list";
    }

    @RequestMapping("del")
    public String delDbTable(String id){
        if(StringUtils.isEmpty(id)){
            logger.error("id不存在");
            return "redirect:/dbTable/list";
        }
        dbTableService.removeDbTableById(id);
        return "redirect:/dbTable/list";
    }
    @RequestMapping("viewChart")
    public String viewTableChart(String id,Model model){
        DbTable dbTable = dbTableService.getById(id);
        dbTable.setRemark(dbTable.getTableName());
        model.addAttribute("dbTable",dbTable);
        String cntSql="select count(1) cnt from "+dbTable.getTableName()+
                (StringUtils.isEmpty(dbTable.getWhereVal())?"":" where "+dbTable.getWhereVal());
        model.addAttribute("sqlCount",cntSql);
        List<DbTableCount> countList=dbTableCountService.getCountListByTableId(id);
        model.addAttribute("dbTableCounts",countList);
        return "mysql/view";
    }

}
