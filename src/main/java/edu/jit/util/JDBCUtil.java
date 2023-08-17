package edu.jit.util;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import edu.jit.model.*;
import edu.jit.schedule.SystemInfoSchedule;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import oshi.hardware.GlobalMemory;
import oshi.hardware.NetworkIF;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//用来在定时任务中，执行SQL脚本
    public class JDBCUtil {
    private static final String DRIVER="com.mysql.jdbc.Driver";
    private static final String URL="jdbc:mysql://120.26.201.165/wgcloud?characterEncoding=utf8";
    private static Logger logger= LoggerFactory.getLogger(SystemInfoSchedule.class);
    public static void initSystem(SystemInfo info) throws Exception{
        Class.forName(DRIVER);
        Connection conn = DriverManager.getConnection(URL,"root","980622mzh");
        String initSql="truncate table SYSTEM_INFO";
        String sql="insert into SYSTEM_INFO(ID,HOST_NAME,VERSION,VERSION_DETAIL,CPU_PER," +
                "MEM_PER,CPU_CORE_NUM,CREATE_TIME,CPU_XH,STATE) values(?,?,?,?,?,?,?,?,?,?) ";
        PreparedStatement init = conn.prepareStatement(initSql);
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setObject(1,info.getId());
        pst.setObject(2,info.getHostname());
        pst.setObject(3,info.getVersion());
        pst.setObject(4,info.getVersionDetail());
        pst.setObject(5,info.getCpuPer());
        pst.setObject(6,info.getMemPer());
        pst.setObject(7,info.getCpuCoreNum());
        pst.setObject(8,info.getCreateTime());
        pst.setObject(9,info.getCpuXh());
        pst.setObject(10,info.getState());
        init.execute();
        pst.execute();
        pst.close();
        conn.close();
    }
    public static void initDisk(List<DeskState> deskStateList) throws Exception {
        Class.forName(DRIVER);
        Connection conn = DriverManager.getConnection(URL,"root","980622mzh");
        String initSql="truncate table DESK_STATE";
        String sql="insert into DESK_STATE(ID,HOST_NAME,FILE_SYSTEM,SIZE,USED,AVAIL,USE_PER,DATE_STR,CREATE_TIME) " +
                "values(?,?,?,?,?,?,?,?,?)";
        for(int i = 1; i < deskStateList.size();i++){
            sql+=",(?,?,?,?,?,?,?,?,?)";
        }
        PreparedStatement init = conn.prepareStatement(initSql);
        PreparedStatement pst = conn.prepareStatement(sql);
            int num=1;
            for(int i=0;i < deskStateList.size(); i++){
                DeskState ds = deskStateList.get(i);
                pst.setObject(num++,ds.getId());
                pst.setObject(num++,ds.getHostname());
                pst.setObject(num++,ds.getFileSystem());
                pst.setObject(num++,ds.getSize());
                pst.setObject(num++,ds.getUsed());
                pst.setObject(num++,ds.getAvail());
                pst.setObject(num++,ds.getUsePer());
                pst.setObject(num++,ds.getDateStr());
                pst.setObject(num++,ds.getCreateTime());
            }
        init.execute();
        pst.execute();
        pst.close();
        conn.close();
        }

    public static void readCpuState(String hostName, Map<String, Object> map) throws Exception {
        Class.forName(DRIVER);
        Connection conn = DriverManager.getConnection(URL,"root","980622mzh");
        String sql="insert into CPU_STATE values(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setObject(1,IdWorker.getIdStr());
        pst.setObject(2,hostName);
        pst.setObject(3,map.get("user"));
        pst.setObject(4,map.get("sys"));
        pst.setObject(5,map.get("idle"));
        pst.setObject(6,map.get("ioWait"));
        pst.setObject(7,map.get("irq"));
        pst.setObject(8,map.get("soft"));
        pst.setObject(9,DateUtil.timeFormat(new Date()));
        pst.setObject(10,new Date());
        pst.execute();
        pst.close();
        conn.close();
    }

    public static void readMemState(String hostName, GlobalMemory memory) throws Exception{
        Class.forName(DRIVER);
        Connection conn = DriverManager.getConnection(URL,"root","980622mzh");
        String sql="insert into MEM_STATE values(?,?,?,?,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setObject(1,IdWorker.getIdStr());
        pst.setObject(2,hostName);
        double total = memory.getTotal() / 1024.0 / 1024 / 1024;
        double free = memory.getAvailable() / 1024.0 / 1024 / 1024;
        double used = (memory.getTotal()-memory.getAvailable()) / 1024.0 / 1024 / 1024;
        double usePer=(memory.getTotal()-memory.getAvailable())*1.0/memory.getTotal();
        pst.setObject(3,String.format("%.2f",total));
        pst.setObject(4,String.format("%.2f",used));
        pst.setObject(5,String.format("%.2f",free));
        pst.setObject(6,usePer);
        pst.setObject(7,DateUtil.timeFormat(new Date()));
        pst.setObject(8,new Date());
        pst.execute();
        pst.close();
        conn.close();
    }

    public static void readSysLoadSate(String hostName, double[] loadAverage) throws Exception {
        Class.forName(DRIVER);
        Connection conn = DriverManager.getConnection(URL,"root","980622mzh");
        String sql="insert into SYS_LOAD_STATE values(?,?,?,?,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setObject(1,IdWorker.getIdStr());
        pst.setObject(2,hostName);
        pst.setObject(3,loadAverage[0]);
        pst.setObject(4,loadAverage[1]);
        pst.setObject(5,loadAverage[2]);
        pst.setObject(6,"admin");
        pst.setObject(7,DateUtil.timeFormat(new Date()));
        pst.setObject(8,new Date());
        pst.execute();
        pst.close();
        conn.close();
    }

    public static void readNetIoState(String hostName, List<NetworkIF> nws) throws Exception{
        Class.forName(DRIVER);
        Connection conn = DriverManager.getConnection(URL,"root","980622mzh");
        String sql="insert into NETIO_STATE values(?,?,?,?,?,?,?,?,?,?,?)";
        int num=1;
        long packRec=0;
        long packSent=0;
        long byteRec=0;
        long byteSent=0;
        for (NetworkIF nw : nws) {
            packRec += nw.getPacketsRecv();
            packSent += nw.getPacketsSent();
            byteRec += nw.getBytesRecv();
            byteSent += nw.getBytesSent();
        }
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setObject(num++,IdWorker.getIdStr());
        pst.setObject(num++,hostName);
        pst.setObject(num++,packRec);
        pst.setObject(num++,packSent);
        pst.setObject(num++,byteRec);
        pst.setObject(num++,byteSent);
        pst.setObject(num++,"0");
        pst.setObject(num++,"0");
        pst.setObject(num++,"0");
        pst.setObject(num++,DateUtil.timeFormat(new Date()));
        pst.setObject(num++,new Date());
        pst.execute();
        pst.close();
        conn.close();
    }

        public static List<AppInfo> getAllAppInfoList() throws Exception{
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL,"root","980622mzh");
            String sql= "select ID id,APP_PID appPid from APP_INFO ";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            List<AppInfo> idList=new ArrayList<>();
            while (rs.next()){
                String id = rs.getString("id");
                String appPid=rs.getString("appPid");
                AppInfo appInfo=new AppInfo();
                appInfo.setId(id);
                appInfo.setAppPid(appPid);
                idList.add(appInfo);
            }
            rs.close();
            pst.close();
            conn.close();
            return idList;
        }

        public static void saveAppState(OperatingSystem os, long total) throws Exception{
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL,"root","980622mzh");
            String sql="insert into APP_STATE values(?,?,?,?,?)";
            List<AppInfo> appInfoList = JDBCUtil.getAllAppInfoList();
            List<Integer> pidList = appInfoList.stream().map(x -> Integer.valueOf(x.getAppPid()))
                    .collect(Collectors.toList());
            List<OSProcess> processList = os.getProcesses(pidList);
            for (int i = 1; i <processList.size(); i++) {
                sql+=",(?,?,?,?,?)";
            }
            PreparedStatement pst = conn.prepareStatement(sql);
            int num=1;
            //通过使用时长来计算占用比
            if(CollectionUtils.isEmpty(processList)){
                String msg = "获取进程信息异常";
                logger.error(msg);
                Exception e=new Exception("进程已下线异常");
                JDBCUtil.saveErrLog(e,msg,"runAppState","5");
                return;
            }
            for (int j = 0; j < processList.size(); j++){
                OSProcess process = processList.get(j);
                pst.setObject(num++,IdWorker.getIdStr());
                int i = 0;
                String infoId=null;
                for (; i < appInfoList.size(); i++) {
                    AppInfo appInfo = appInfoList.get(i);
                    if (appInfo.getAppPid().equals(process.getProcessID()+"")){
                        infoId=appInfo.getId();
                        pst.setObject(num++,infoId);
                        appInfoList.remove(i);
                        //内核时间+使用时间=占用时间，除以已活动时间，即可得到占用比
                        double cpuPer = (process.getKernelTime() + process.getUserTime()) * 100.0 / process.getUpTime();
                        pst.setObject(num++,cpuPer);
                        //进程的数据集大小，被看作内存大小
                        double memPer = process.getResidentSetSize() * 100.0 / total;
                        pst.setObject(num++,memPer);
                        pst.setObject(num++,new Date());
                        appInfo.setCpuPer(cpuPer);
                        appInfo.setMemPer(memPer);
                        appInfo.setState("1");
                        updateAppInfo(appInfo,conn);
                        break;
                    }
                }
            }
            pst.execute();
            pst.close();
            conn.close();
        }

        private static void updateAppInfo(AppInfo appInfo,Connection connection) throws Exception{
            String sql="update APP_INFO set CPU_PER=?,MEM_PER=?,STATE=? where ID=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setObject(1,appInfo.getCpuPer());
            pst.setObject(2,appInfo.getMemPer());
            pst.setObject(3,appInfo.getState());
            pst.setObject(4,appInfo.getId());
            pst.execute();
            pst.close();
        }

    public static List<DbInfo> queryAllDbINfos() throws Exception{
        Class.forName(DRIVER);
        Connection conn = DriverManager.getConnection(URL,"root","980622mzh");
        String sql="select * from DB_INFO";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        List<DbInfo> dbInfoList=new ArrayList<>();
        while (rs.next()){
            DbInfo info=new DbInfo();
            info.setId(rs.getString("ID"));
            info.setDbType(rs.getString("DBTYPE"));
            info.setUser(rs.getString("USER"));
            info.setPasswd(rs.getString("PASSWD"));
            info.setIp(rs.getString("IP"));
            info.setPort(rs.getString("PORT"));
            info.setDbName(rs.getString("DBNAME"));
            dbInfoList.add(info);
        }
        rs.close();
        pst.close();
        conn.close();
        return dbInfoList;
    }

    public static void updateDbInfoState(String id, String state) throws Exception{
        Class.forName(DRIVER);
        Connection conn = DriverManager.getConnection(URL,"root","980622mzh");
        String sql = "update DB_INFO set DB_STATE=? where ID=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setObject(1,state);
        pst.setObject(2,id);
        pst.execute();
        pst.close();
        conn.close();
    }

    public static void updateTableCount() throws Exception  {
        Class.forName(DRIVER);
        Connection conn = DriverManager.getConnection(URL,"root","980622mzh");
        String sql="select * from DB_TABLE";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        //List<DbTable> dbTableList=new ArrayList<>();
        int size=0;
        while (rs.next()){
        //    DbTable table=new DbTable();
            String id=rs.getString("ID");
            String tbName=rs.getString("TABLE_NAME");
            String whereVal=rs.getString("WHERE_VAL");
            String dbInfoId=rs.getString("DBINFO_ID");
            //根据数据源id查询出数据源配置信息
            DbInfo info=getDbInfoById(dbInfoId,conn);
            String cntSql="select count(1) cnt from "+tbName+
                    (StringUtils.isEmpty(whereVal)?"":" where "+whereVal);

            //用数据源配置信息，再去查询对应的数据表的数据条数
            Integer cnt=getTbCount(cntSql,info);
            updateTbCount(conn,cnt,id);
            size++;
        }
        if(size==0){
            String msg="数据表没有信息异常";
            Exception e=new Exception(msg);
            saveErrLog(e,msg,"updateTableCount","5");
        }
        rs.close();
        pst.close();
        conn.close();
    }

    private static Integer getTbCount(String cntSql, DbInfo info) throws Exception {
        if(info.getDbType()!=null && info.getDbType().equals("mysql")){
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + info.getIp() + ":" + info.getPort()
                            + "/" + info.getDbName(), info.getUser(), info.getPasswd());
            PreparedStatement pst = conn.prepareStatement(cntSql);
            ResultSet rs = pst.executeQuery();
            int cnt =0;
            if (rs.next()){
                cnt = rs.getInt("cnt");
                rs.close();
                pst.close();
                conn.close();
            }
            return cnt;
        }
        return 0;
    }

    private static DbInfo getDbInfoById(String dbInfoId,Connection conn) throws Exception {
        String sql="select * from DB_INFO where ID=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setObject(1,dbInfoId);
        ResultSet rs = pst.executeQuery();
        DbInfo info=new DbInfo();
        if(rs.next()){
            info.setId(dbInfoId);
            info.setDbName(rs.getString("DBNAME"));
            info.setPort(rs.getString("PORT"));
            info.setIp(rs.getString("IP"));
            info.setPasswd(rs.getString("PASSWD"));
            info.setUser(rs.getString("USER"));
            info.setDbType(rs.getString("DBTYPE"));
        }
        rs.close();
        pst.close();
        return info;
    }

    private static void updateTbCount(Connection conn,Integer tableCount,String id) throws Exception{
        String sql = "update DB_TABLE set TABLE_COUNT=? where ID=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setObject(1,tableCount);
        pst.setObject(2,id);
        String sql2="insert into DB_TABLE_COUNT values(?,?,?,?,?)";
        PreparedStatement pst2 = conn.prepareStatement(sql2);
        pst2.setObject(1,IdWorker.getIdStr());
        pst2.setObject(2,id);
        pst2.setObject(3,tableCount);
        pst2.setObject(4,DateUtil.timeFormat(new Date()));
        pst2.setObject(5,new Date());
        pst.execute();
        pst2.execute();
        pst.close();
        pst2.close();
    }

    public static void updateServiceStatus() throws Exception {
        Class.forName(DRIVER);
        Connection conn = DriverManager.getConnection(URL,"root","980622mzh");
        String sql="select * from HEATH_MONITOR";
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String id=rs.getString("ID");
            String heathUrl=rs.getString("HEATH_URL");
            String statusCode=testUrl(heathUrl);
            saveServiceStatus(id,statusCode,conn);
        }
        rs.close();
        pst.close();
        conn.close();
    }

    private static void saveServiceStatus(String id, String statusCode, Connection conn) throws Exception{
        String sql="update HEATH_MONITOR set HEATH_STATUS=? where ID=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setObject(1,statusCode);
        pst.setObject(2,id);
        pst.execute();
        pst.close();
    }

    private static String testUrl(String heathUrl) {
        HttpGet get=new HttpGet(heathUrl);
        get.setHeader("Content-Type","application/json");
        RequestConfig config = RequestConfig.custom().setConnectTimeout(1000)
                .setConnectionRequestTimeout(1000)
                .setSocketTimeout(1000).build();
        get.setConfig(config);
        CloseableHttpClient httpClient=null;
        CloseableHttpResponse httpResponse=null;
        int statusCode=404;
        httpClient= HttpClients.createDefault();
        try {
            httpResponse=httpClient.execute(get);
            statusCode=httpResponse.getStatusLine().getStatusCode();
        } catch (IOException e) {
            return "404";
        }
        if(statusCode!=200){
            String msg="服务接口状态不正确异常";
            Exception e=new Exception(msg);
            try {
                saveErrLog(e,msg,"testUrl","3");
            } catch (Exception e1) {
                logger.error("保存日志异常");
            }
        }
        return String.valueOf(statusCode);
    }

    public static void saveErrLog(Exception e, String msg, String methodName,String state) throws Exception {
        Class.forName(DRIVER);
        Connection conn = DriverManager.getConnection(URL,"root","980622mzh");
        String sql="insert into LOG_INFO values(?,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setObject(1,IdWorker.getIdStr());
        pst.setObject(2,msg);
        String content="在方法："+methodName+"()中，发生了错误：\n\t"+msg
                +"：\n\n\t具体异常信息："+e.getMessage();
        pst.setObject(3,content);
        pst.setObject(4,state);//0正常，1系统异常，2主机异常，3服务异常，4数据库异常,5进程异常,6硬件异常,7日志异常,9网络异常
        pst.setObject(5,new Date());
        pst.execute();
        pst.close();
        conn.close();
    }
}


