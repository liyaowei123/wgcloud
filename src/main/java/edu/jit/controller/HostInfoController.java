package edu.jit.controller;


import edu.jit.model.SystemInfo;
import edu.jit.service.IHostInfoService;
import javax.annotation.Resource;


import edu.jit.service.ISystemInfoService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * nullController接口定义
 * @author chendd
 * @date 2023/07/07 11:13
 */
@Api(value = "null" , tags = "null")
@RequestMapping(value = "/host" , produces = MediaType.APPLICATION_JSON_VALUE)
@Controller
public class HostInfoController {

    @Resource
    private IHostInfoService hostInfoService;
    @Autowired
    private ISystemInfoService systemInfoService;
    private Logger log= LoggerFactory.getLogger(HostInfoController.class);
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String saveRemark(String remark,String id){
        if(StringUtils.isEmpty(remark) || StringUtils.isEmpty(id)){
            log.error("备注信息为空或者未匹配id");
            return "redirect:/dash/systemInfoList";
        }
        //根据id更新该条记录的备注信息
        SystemInfo si=new SystemInfo();
        si.setId(id);
        si.setRemark(remark);
        systemInfoService.updateById(si);
        return "redirect:/dash/systemInfoList";
    }
}
