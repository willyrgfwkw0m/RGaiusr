package xhu.wncg.common.xss;

import org.apache.commons.lang.StringUtils;
import xhu.wncg.common.exception.FireException;

/**
 * SQL过滤
 * @author BZhao
 * @email 15528330581@163.com
 * @version  2017-04-01 16:16
 */
public class SQLFilter {

    /**
     * SQL注入过滤
     * @param str  待验证的字符串
     */
    public static String sqlInject(String str){
        if(StringUtils.isBlank(str)){
            return null;
        }
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        str = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop"};

        //判断是否包含非法字符
        for(String keyword : keywords){
            if(str.contains(keyword)){
                throw new FireException("包含非法字符");
            }
        }

        return str;
    }
}
