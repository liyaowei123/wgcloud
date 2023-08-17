package edu.jit.service;

import edu.jit.model.DbInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * nullService接口定义
 * @author chendd
 * @date 2023/07/07 11:13
 */
public interface IDbInfoService extends IService<DbInfo> {


    boolean testConnection(DbInfo dbInfo);

    void modifyDbInfoById(DbInfo dbInfo);

    void removeDbInfoById(String id);

    List<DbInfo> listAliasAndIds();

    Integer getDbInfoCount();
}
