package edu.jit.service.impl;

import edu.jit.model.DbInfo;
import edu.jit.mapper.DbInfoMapper;
import edu.jit.schedule.SystemInfoSchedule;
import edu.jit.service.IDbInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.jit.util.JDBCUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * nullService接口实现
 * @author chendd
 * @date 2023/07/07 11:13
 */
@Service
public class DbInfoService extends ServiceImpl<DbInfoMapper , DbInfo> implements IDbInfoService {
    private Logger logger= LoggerFactory.getLogger(SystemInfoSchedule.class);
    @Resource
    private DbInfoMapper dbInfoMapper;

    @Override
    public boolean testConnection(DbInfo dbInfo) {
        if(null==dbInfo || StringUtils.isEmpty(dbInfo.getDbType()) ||
        StringUtils.isEmpty(dbInfo.getIp()) || StringUtils.isEmpty(dbInfo.getUser()) ||
        StringUtils.isEmpty(dbInfo.getPasswd()) || StringUtils.isEmpty(dbInfo.getPort())){
            return false;
        }
        String dbType = dbInfo.getDbType();
        String driverClassName=null;
        String url="jdbc:";
        String username=dbInfo.getUser();
        String password=dbInfo.getPasswd();
        switch (dbType){
            case "mysql":driverClassName="com.mysql.jdbc.Driver";
            url+="mysql://"+dbInfo.getIp()+":"+dbInfo.getPort()+"/"+dbInfo.getDbName();
                break;
            case "oracle":driverClassName="oracle.jdbc.driver.OracleDriver";
            url+="oracle:thin:@"+dbInfo.getIp()+":"+dbInfo.getPort()+":"+dbInfo.getDbName();
                break;
            case "sqlServer":driverClassName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
            url+="microsoft:sqlserver://"+dbInfo.getIp()+":"+dbInfo.getPort()+";DatabaseName="+dbInfo.getDbName();
                break;
            case "db2":driverClassName="com.ibm.db2.jcc.DB2Driver";
            url+="db2://"+dbInfo.getIp()+":"+dbInfo.getPort()+"/"+dbInfo.getDbName();
                break;
            case"postgresql":driverClassName="org.postgresql.Driver";
            url+="postgresql://"+dbInfo.getIp()+":"+dbInfo.getPort()+"/"+dbInfo.getDbName();
                break;
        }
        Connection conn = null;
        try {
            Class.forName(driverClassName);
            conn = DriverManager.getConnection(url, username, password);
            if(null!=conn){
                return true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            String msg = "数据库连接测试异常";
            log.error(msg);
            try{
                JDBCUtil.saveErrLog(e,msg,"testConnection","5");
            }catch(Exception e1){
                    logger.error("保存日志信息异常："+e.getMessage());
            }
        }finally {
            try{
                if(conn!=null){
                    conn.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void modifyDbInfoById(DbInfo dbInfo) {
        dbInfoMapper.updateDbInfoById(dbInfo);
    }

    @Override
    public void removeDbInfoById(String id) {
        dbInfoMapper.deleteDbInfoById(id);
    }

    @Override
    public List<DbInfo> listAliasAndIds() {
        return dbInfoMapper.queryAliasAndIdList();
    }

    @Override
    public Integer getDbInfoCount() {
        return dbInfoMapper.queryDbInfoCount();
    }
}
