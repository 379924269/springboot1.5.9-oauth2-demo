package moe.cnkirito.security.oauth2.code.module.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import moe.cnkirito.security.oauth2.code.module.entity.Clientdetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author huazai
 * @since 2020-04-29
 */
public interface ClientdetailsMapper extends BaseMapper<Clientdetails> {
    List<Map<String, Object>> findDBTable(@Param("dbName") String dbName);

    void addOauth2Table(@Param("createOauth2TableSql") String createOauth2TableSql);
}
