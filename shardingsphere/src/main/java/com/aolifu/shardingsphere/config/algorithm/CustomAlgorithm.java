package com.aolifu.shardingsphere.config.algorithm;

import com.aolifu.shardingsphere.entity.CreateTableSql;
import com.aolifu.shardingsphere.mapper.UserMapper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.sharding.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.RangeShardingValue;
import org.apache.shardingsphere.sharding.api.sharding.standard.StandardShardingAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class CustomAlgorithm implements StandardShardingAlgorithm<LocalDateTime> {

    private static UserMapper userMapper;

    @Getter
    @Setter
    private Properties props = new Properties();

    private final Set<String> resultTable = new HashSet<>();

    public static void setUserMapper(UserMapper userMapper) {
        CustomAlgorithm.userMapper = userMapper;
    }

    @Override public String doSharding(Collection collection, PreciseShardingValue preciseShardingValue) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("_dd");
        String format = dtf.format((TemporalAccessor) preciseShardingValue.getValue());
        if (format.startsWith("_0")) {
            format = format.replace("0", "");
        }
        return shardingTablesCheckAndCreatAndReturn(preciseShardingValue.getLogicTableName(),
            preciseShardingValue.getLogicTableName() + format);
    }

    @Override public Collection<String> doSharding(Collection collection, RangeShardingValue rangeShardingValue) {
        return null;
    }

    @Override public Properties getProps() {
        return props;
    }

    @Override
    public void init(Properties properties) {

    }

    @Transactional
    public String shardingTablesCheckAndCreatAndReturn(String logicTableName, String resultTableName) {
        try {
            synchronized (logicTableName.intern()) {
                // 缓存中有此表 返回
                if (shardingTablesExistsCheck(resultTableName)) {
                    return resultTableName;
                }
                // 缓存中无此表 建表 并添加缓存
                CreateTableSql createTableSql = userMapper.SelectTableCreateSql(logicTableName);
                String sql = createTableSql.getCreateTable();
                sql = sql.replace("CREATE TABLE", "CREATE TABLE IF NOT EXISTS");
                // 替换第一个表名，后面字段里面包含表名的不替换
                sql = sql.replaceFirst(logicTableName, resultTableName);
                userMapper.executeSql(sql);
                resultTable.add(resultTableName);
            }
        } catch (Exception e) {
            log.error("获取分表信息 异常: ", e);
            return null;
        }

        return resultTableName;
    }

    /**
     * 判断表是否存在于缓存中
     *
     * @param resultTableName 表名
     * @return 是否存在于缓存中
     */
    public boolean shardingTablesExistsCheck(String resultTableName) {
        return resultTable.contains(resultTableName);
    }

    @Override
    public String getType() {
        return "CUSTOM";
    }


}
