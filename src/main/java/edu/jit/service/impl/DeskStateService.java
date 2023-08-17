package edu.jit.service.impl;

import edu.jit.model.DeskState;
import edu.jit.mapper.DeskStateMapper;
import edu.jit.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * nullService接口实现
 * @author chendd
 * @date 2023/07/07 11:13
 */
@Service
public class DeskStateService extends ServiceImpl<DeskStateMapper , DeskState> implements IDeskStateService {

    @Autowired
    private IAppInfoService appInfoService;
    @Autowired
    private IDbInfoService dbInfoService;
    @Autowired
    private IDbTableService dbTableService;
    @Autowired
    private IDbTableCountService dbTableCountService;
    @Autowired
    private IHeathMonitorService heathMonitorService;
    @Autowired
    private ILogInfoService logInfoService;
    @Resource
    private DeskStateMapper deskStateMapper;

    @Override
    public List<DeskState> getDeskListByName(String hostname) {
        return deskStateMapper.queryDeskListByName(hostname);
    }
    public void initData(Model model){

        Integer appCount=appInfoService.getAppCount();
        Integer appMaxCount=appInfoService.getAppMaxCount();
        Integer dbInfoCount=dbInfoService.getDbInfoCount();
        Integer dbTableCount=dbTableService.getDbTableCount();
        Integer totalTableCount=dbTableService.getTotalTableCount();
        Integer serviceCount=heathMonitorService.getServiceCount();
        Integer errServiceCount=heathMonitorService.getErrServiceCount();
        Integer logCount=logInfoService.getLogCount();
        model.addAttribute("totalSizeApp",appCount);
        model.addAttribute("memPerSizeApp",appMaxCount);
        model.addAttribute("dbInfoSize",dbInfoCount);
        model.addAttribute("dbTableSize",dbTableCount);
        model.addAttribute("dbTableSum",totalTableCount);
        model.addAttribute("heathSize",serviceCount);
        model.addAttribute("heatherrSize",errServiceCount);
        model.addAttribute("logSize",logCount);
        List<Double> charInfoList=new ArrayList<>();
        charInfoList.add(0.785);
        charInfoList.add(0.365);
        model.addAttribute("chartInfoList,chartInfoList");
        Integer totalSystemInfoSize=10;
    }
}
