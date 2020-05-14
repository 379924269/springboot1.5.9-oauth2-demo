package moe.cnkirito.security.oauth2.code.module.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import moe.cnkirito.security.oauth2.code.module.entity.Clientdetails;
import moe.cnkirito.security.oauth2.code.module.mapper.ClientdetailsMapper;
import moe.cnkirito.security.oauth2.code.module.service.IClientdetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author huazai
 * @since 2020-04-29
 */
@Service
public class ClientdetailsServiceImpl extends ServiceImpl<ClientdetailsMapper, Clientdetails> implements IClientdetailsService {

    @Autowired
    private ClientdetailsMapper clientdetailsMapper;
    @Override
    public Boolean hasDbTable(String dbName, String tableName) {
        String dealDbName = new StringBuilder().append("`").append(dbName).append("`").toString();
        List<Map<String, Object>> list = clientdetailsMapper.findDBTable(dealDbName);
        for (Map<String, Object> aList : list) {
            if (aList.get("Tables_in_" + dbName).toString().equals(tableName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addOauth2Table(String createOauth2TableSql) {
        clientdetailsMapper.addOauth2Table(createOauth2TableSql);
    }
}
