package edu.jit.schedule;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import edu.jit.model.DbInfo;
import edu.jit.model.DeskState;
import edu.jit.service.IDbInfoService;
import edu.jit.service.impl.DbInfoService;
import edu.jit.util.DateUtil;
import edu.jit.util.JDBCUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.*;

import java.io.File;
import java.util.*;

//要去读系统配置
@Configuration
@EnableScheduling
@Async
public class SystemInfoSchedule {
    Logger logger= LoggerFactory.getLogger(SystemInfoSchedule.class);
    @Scheduled(cron = "0 22 11 */1 * ?")//定时任务
    public void readSystemInfo() {
        try{
            SystemInfo si = new SystemInfo();//只能读当前机器的
            HardwareAbstractionLayer hal = si.getHardware();//硬件信息
            OperatingSystem os = si.getOperatingSystem();//操作系统信息
            Sensors sensors = hal.getSensors();//CPU散热器组
            ComputerSystem cs = hal.getComputerSystem();//主板信息
            List<HWDiskStore> diskStores = hal.getDiskStores();//硬盘信息
            GlobalMemory memory = hal.getMemory();//内存信息
            CentralProcessor processor = hal.getProcessor();//cpu及线程信息
            List<NetworkIF> networkIFS = hal.getNetworkIFs();//网卡
            FileSystem fs = os.getFileSystem();//文件系统
            NetworkParams nps = os.getNetworkParams();//网络参数
            OSService[] service = os.getServices();//服务列表
            String hostname = getDefaultHostName(networkIFS, nps);
            String version = os.getManufacturer() + " " + os.getFamily();
            String version_Detail = version + " " + os.getVersionInfo().getVersion();
            double cpuPer = getCpuPer(processor);
            long used = memory.getTotal() - memory.getAvailable();
            double memPer = used * 1.0 / memory.getTotal();
            int coreNum = processor.getPhysicalProcessorCount();
            String cpuXh = processor.getProcessorIdentifier().getName();
            String state = "1";
            edu.jit.model.SystemInfo info = new edu.jit.model.SystemInfo();
            info.setId(IdWorker.getIdStr());
            info.setHostname(hostname);
            info.setVersion(version);
            info.setVersionDetail(version_Detail);
            info.setCpuPer(cpuPer * 100);
            info.setMemPer(memPer * 100);
            info.setCpuCoreNum(coreNum + "");
            info.setCreateTime(new Date());
            info.setCpuXh(cpuXh);
            info.setState(state);
            List<DeskState> deskStateList = new ArrayList<>();
            //List<HWPartition> partitions = diskStores.get(0).getPartitions();
            for (HWDiskStore store : diskStores) {
                List<HWPartition> partitions = store.getPartitions();
                for (HWPartition p : partitions) {
                    DeskState ds = new DeskState();
                    ds.setId(IdWorker.getIdStr());
                    ds.setHostname(hostname);
                    ds.setCreateTime(new Date());
                    ds.setDateStr(DateUtil.timeFormat(new Date()));
                    deskStateList.add(ds);
                }
            }

            File[] files = File.listRoots();
            for (int i = 0; i < deskStateList.size() && i < files.length; i++) {
                DeskState ds = deskStateList.get(i);
                ds.setAvail(String.format("%.2f", files[i].getUsableSpace() / 1024.0 / 1024 / 1024));
                long usedSpace = files[i].getTotalSpace() - files[i].getFreeSpace();
                ds.setUsed(String.format("%.2f", usedSpace / 1024.0 / 1024 / 1024));
                ds.setSize(String.format("%.2f", files[i].getTotalSpace() / 1024.0 / 1024 / 1024));
                double userPer = usedSpace * 100.0 / files[i].getTotalSpace();

                String format = String.format("%.2f", userPer);
                ds.setUsePer(format);
                ds.setFileSystem(files[i].getAbsolutePath());
            }
            JDBCUtil.initSystem(info);
            JDBCUtil.initDisk(deskStateList);
        }catch (Exception e){
            String msg = "初始化系统信息发生异常";
            logger.error(msg);
            try {
                JDBCUtil.saveErrLog(e,msg,"readSystemInfo","2");
            } catch (Exception e1) {
                logger.error("保存日志信息异常："+e.getMessage());
            }
        }
    }

    private double getCpuPer(CentralProcessor processor) throws InterruptedException {
        long[] beginTicks = processor.getSystemCpuLoadTicks();
        Thread.sleep(1000);//休眠1秒
        long[] endTicks = processor.getSystemCpuLoadTicks();
        int niceIndex = CentralProcessor.TickType.NICE.getIndex();
        int idleIndex = CentralProcessor.TickType.IDLE.getIndex();
        int sysIndex = CentralProcessor.TickType.SYSTEM.getIndex();
        int irqIndex = CentralProcessor.TickType.IRQ.getIndex();
        int userIndex = CentralProcessor.TickType.USER.getIndex();
        int stealIndex = CentralProcessor.TickType.STEAL.getIndex();
        int ioIndex = CentralProcessor.TickType.IOWAIT.getIndex();
        int softIndex = CentralProcessor.TickType.SOFTIRQ.getIndex();
        long nice = endTicks[niceIndex] - beginTicks[niceIndex];
        long idle = endTicks[idleIndex] - beginTicks[idleIndex];
        long irq = endTicks[irqIndex] - beginTicks[irqIndex];
        long sys = endTicks[sysIndex] - beginTicks[sysIndex];
        long user = endTicks[userIndex] - beginTicks[userIndex];
        long steal = endTicks[stealIndex] - beginTicks[stealIndex];
        long ioWait = endTicks[ioIndex] - beginTicks[ioIndex];
        long soft = endTicks[softIndex] - beginTicks[softIndex];
        long total=nice+idle+irq+sys+user+steal+ioWait+soft;
        double per=(irq+sys+soft+steal)*1.0/total;
        return per;
    }

    //使用IP地址作为主机名
    private String getDefaultHostName(List<NetworkIF> nws,NetworkParams nps){
        String hostname=nps.getHostName();
        String ipAddr="";
        String gateway = nps.getIpv4DefaultGateway();
        int point = gateway.lastIndexOf(".");
        String gateNet = gateway.substring(0,point+1);
        for (NetworkIF nw : nws){
            for (String ipv4 : nw.getIPv4addr()){
                if (ipv4.startsWith(gateNet)){//和网关在同一个网段的网络，即本机网络
                    ipAddr=ipv4;
                }
            }
        }
        return ipAddr;
    }

    //@Scheduled(cron = "0 0/2 * * * ?")
    public void loadChartInfo(){
        SystemInfo si=new SystemInfo();
        HardwareAbstractionLayer hardware = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();
        CentralProcessor processor = hardware.getProcessor();
        List<NetworkIF> nws = hardware.getNetworkIFs();
        NetworkParams nps = os.getNetworkParams();
        String hostName = getDefaultHostName(nws,nps);
        GlobalMemory memory = hardware.getMemory();
        double[] loadAverage = processor.getSystemLoadAverage(3);
        try {
            Map<String,Object> processList=getCpuPerListWithType(processor);
            JDBCUtil.readCpuState(hostName,processList);
            JDBCUtil.readMemState(hostName,memory);
            JDBCUtil.readSysLoadSate(hostName,loadAverage);
            JDBCUtil.readNetIoState(hostName,nws);
        } catch (Exception e) {
            String msg = "获取硬件信息异常";
            logger.error(msg);
            try {
                JDBCUtil.saveErrLog(e,msg,"loadChartInfo","6");
            } catch (Exception e1) {
                logger.error("保存日志信息异常："+e.getMessage());
            }
        }
    }

    private Map<String, Object> getCpuPerListWithType(CentralProcessor processor) throws InterruptedException {
        Map<String,Object> map=new HashMap<>();
        long[] beginTicks = processor.getSystemCpuLoadTicks();
        Thread.sleep(1000);//休眠1秒
        long[] endTicks = processor.getSystemCpuLoadTicks();
        int niceIndex = CentralProcessor.TickType.NICE.getIndex();
        int idleIndex = CentralProcessor.TickType.IDLE.getIndex();
        int sysIndex = CentralProcessor.TickType.SYSTEM.getIndex();
        int irqIndex = CentralProcessor.TickType.IRQ.getIndex();
        int userIndex = CentralProcessor.TickType.USER.getIndex();
        int stealIndex = CentralProcessor.TickType.STEAL.getIndex();
        int ioIndex = CentralProcessor.TickType.IOWAIT.getIndex();
        int softIndex = CentralProcessor.TickType.SOFTIRQ.getIndex();
        long nice = endTicks[niceIndex] - beginTicks[niceIndex];
        long idle = endTicks[idleIndex] - beginTicks[idleIndex];
        long irq = endTicks[irqIndex] - beginTicks[irqIndex];
        long sys = endTicks[sysIndex] - beginTicks[sysIndex];
        long user = endTicks[userIndex] - beginTicks[userIndex];
        long steal = endTicks[stealIndex] - beginTicks[stealIndex];
        long ioWait = endTicks[ioIndex] - beginTicks[ioIndex];
        long soft = endTicks[softIndex] - beginTicks[softIndex];
        long total=nice+idle+irq+sys+user+steal+ioWait+soft;
        double userPer=user*100.0/total;
        double sysPer=sys*100.0/total;
        double idlePer=idle*100.0/total;
        double ioWaitPer=ioWait*100.0/total;
        double irqPer=irq*100.0/total;
        double softPer=soft*100.0/total;
        map.put("user",String.format("%.2f",userPer));
        map.put("sys",sysPer);
        map.put("idle",idlePer);
        map.put("ioWait",ioWaitPer);
        map.put("irq",String.format("%.2f",irqPer));
        map.put("soft",String.format("%.2f",softPer));
        return map;
    }

    //@Scheduled(cron = "0 */10 * * * ?")
    public void runAppState(){
        SystemInfo si=new SystemInfo();
        HardwareAbstractionLayer hardware = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();

        try {
            JDBCUtil.saveAppState(os,hardware.getMemory().getTotal());
        } catch (Exception e) {
            String msg = "获取进程信息异常";
            logger.error(msg);
            try {
                JDBCUtil.saveErrLog(e,msg,"runAppState","5");
            } catch (Exception e1) {
                logger.error("保存日志信息异常："+e.getMessage());
            }
        }
    }

    //@Scheduled(cron = "0 */30 * * * ?")
    public void updateDbState() {
        try{
            List<DbInfo> dbInfoList=JDBCUtil.queryAllDbINfos();
            IDbInfoService service=new DbInfoService();
            for (DbInfo dbInfo : dbInfoList) {
                boolean conRst = service.testConnection(dbInfo);
                String state=conRst?"1":"2";
                JDBCUtil.updateDbInfoState(dbInfo.getId(),state);
            }
        }catch (Exception e) {
            String msg = "更新数据源信息异常";
            logger.error(msg);
            try {
                JDBCUtil.saveErrLog(e,msg,"updateDbState","4");
            } catch (Exception e1) {
                logger.error("保存日志信息异常："+e.getMessage());
            }
        }
    }
    //@Scheduled(cron = "30 */30 * * * ?")
    public void updateTableCount() {
        try {
            JDBCUtil.updateTableCount();
        }catch (Exception e) {
            String msg = "更新数据表信息异常";
            logger.error(msg);
            try {
                JDBCUtil.saveErrLog(e,msg,"updateTableCount","4");
            } catch (Exception e1) {
                logger.error("保存日志信息异常："+e.getMessage());
            }
        }
    }
    @Scheduled(cron = "0 */30 * * * ?")
    public void updateServiceStatus(){
        try {
            JDBCUtil.updateServiceStatus();
        }catch (Exception e) {
            String msg = "更新服务状态异常";
            logger.error(msg);
            try {
                JDBCUtil.saveErrLog(e,msg,"updateServiceStatus","3");
            } catch (Exception e1) {
                logger.error("保存日志信息异常："+e.getMessage());
            }
        }
    }
}
