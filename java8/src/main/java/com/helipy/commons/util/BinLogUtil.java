package com.helipy.commons.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Pack:       com.helipy.commons.util
 * File:       BinLogUtil
 * Desc:
 *
 * @author wangchuangfeng
 * CreateTime: 2023-09-01 12:58
 */
@Slf4j
public class BinLogUtil {
    private BinLogUtil() {
    }

    /**
     * 解析 binlog 操作字段. 无效 json, 则返回空
     *
     * @param binlogJson
     * @return
     */
    public static BinLogEventMeta parseBinLogEventMeta(JSONObject binlogJson) {
        if (binlogJson == null) {
            return null;
        }
        BinLogEventMeta.BinLogEventMetaBuilder builder = BinLogEventMeta.builder();
        // 只关心指定数据库和表
        String database = binlogJson.getString("ums_schema_dbname__");
        String table = binlogJson.getString("ums_schema_tbname__");

        // 解析字段
        String opTypeStr = binlogJson.getString("ums_op_");
        BinLogOpType opType = BinLogOpType.parseOpType(opTypeStr);

        builder.database(database);
        builder.table(table);
        builder.opType(opType);
        return builder.build();
    }

    /**
     * 解析 binlog
     *
     * @param binlogJson
     * @param clazz      要提取的消息字段 pojo 的 class
     * @param <T>        要提取的消息字段 pojo
     * @return
     */
    public static <T extends FocusMsgFields> BinLogEvent<T> parserBinLog(
            JSONObject binlogJson, Class<T> clazz) {
        BinLogEventMeta binLogEventMeta = parseBinLogEventMeta(binlogJson);
        return parserBinLog(binlogJson, binLogEventMeta, clazz);
    }

    public static <T extends FocusMsgFields> BinLogEvent<T> parserBinLog(
            JSONObject binlogJson, BinLogEventMeta binLogEventMeta, Class<T> clazz) {
        try {
            BinLogEvent.BinLogEventBuilder<T> builder = BinLogEvent.builder();
            builder.eventMeta(binLogEventMeta);
            // 所有类型的操作日志,记录当前行本身
            builder.curRecord(((T) clazz.newInstance().setByJsonObject(binlogJson)));
            builder.recordClass(clazz);
            // 对于更新操作,还需记录上一次行信息
            if (BinLogOpType.UPDATE == binLogEventMeta.getOpType()) {
                String beforeJson = binlogJson.getString("ums_before__");
                JSONObject before = JSON.parseObject(beforeJson);
                builder.lastRecord((T) clazz.newInstance().setByJsonObject(before));
            }
            return builder.build();
        } catch (Exception e) {
            log.error("unexpect msg format. msg={}. error=", binlogJson.toJSONString(), e);
        }
        return null;
    }


    public interface FocusMsgFields {
        /**
         * 从 jsonObject 解析自己需要的字段
         *
         * @param jsonObject
         * @return this
         */
        FocusMsgFields setByJsonObject(JSONObject jsonObject);
    }

    public enum BinLogOpType {
        // 插入
        INSERT("i"),
        // 更新
        UPDATE("u"),
        // 删除
        DELETE("d");

        /**
         * 消息中的标志字符串
         */
        private String code;

        BinLogOpType(String code) {
            this.code = code;
        }

        /**
         * 从字符串解析 binlog 操作类型
         *
         * @param op
         * @return
         */
        public static BinLogOpType parseOpType(String op) {
            for (BinLogOpType type : BinLogOpType.values()) {
                if (type.code.equals(op)) {
                    return type;
                }
            }
            return null;
        }
    }

    @Data
    @Builder
    public static class BinLogEventMeta {
        /**
         * binLog 操作类型
         */
        private BinLogOpType opType;
        /**
         * 库名
         */
        private String database;
        /**
         * 表名
         */
        private String table;
    }

    /**
     * File:       BinLogEvent
     * Desc: binlog 变更事件
     *
     * @param <T> 接收有效字段的类型
     * @author wangchuangfeng
     * CreateTime: 2023-09-01 11:47
     */
    @Data
    @Builder
    public static class BinLogEvent<T> {
        /**
         * binLog 元信息
         */
        private BinLogEventMeta eventMeta;
        /**
         * 当前行的值
         */
        private T curRecord;
        /**
         * 上一次记录的内容,只在更新操作里有
         */
        private T lastRecord;
        /**
         * 记录类型
         */
        private Class<T> recordClass;
    }
}
