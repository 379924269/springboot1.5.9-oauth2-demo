package moe.cnkirito.security.oauth2.code.module.service;

import com.baomidou.mybatisplus.extension.service.IService;
import moe.cnkirito.security.oauth2.code.module.entity.Clientdetails;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author huazai
 * @since 2020-04-29
 */
public interface IClientdetailsService extends IService<Clientdetails> {
    Boolean hasDbTable(String dbName, String tableName);

    /**
     * 添加oauth2数据库  注意一次只能插入一个table，插入多个要报错
     *
     * @param createOauth2TableSql
     */
    void addOauth2Table(String createOauth2TableSql);
}
