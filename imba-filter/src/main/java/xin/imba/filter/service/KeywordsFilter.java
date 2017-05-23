package xin.imba.filter.service;

import java.util.Map;
import java.util.Set;

/**
 * 关键词过滤 接口
 * Created by xiepengcheng on 2017/5/3.
 */
public interface KeywordsFilter {

    /**
     * 索引生成-根据文件路径获取关键词的DFA数据结构
     *
     * @param filePath
     * @return
     */
    public Map getKeywordMap(String filePath);

    /**
     * 关键词判断-判断文字是否包含关键字符
     *
     * @param txt       文字
     * @param matchType 匹配规则&nbsp;1：最小匹配规则，2：最大匹配规则
     * @return 若包含返回true，否则返回false
     */
    public boolean isContaintKeyword(String txt, int matchType, Map map);

    /**
     * 关键词获取-获取文字中的关键词
     *
     * @param txt       文字
     * @param matchType 匹配规则&nbsp;1：最小匹配规则，2：最大匹配规则
     * @return
     */
    public Set<String> getKeyword(String txt, int matchType, Map map);

    /**
     * 关键词替换-替换关键词为指定字符
     *
     * @param txt
     * @param matchType
     * @param replaceChar 替换字符，默认*
     */
    public String replaceKeyword(String txt, int matchType, String replaceChar, Map map);

    /**
     * 检查文字中是否包含敏感字符，检查规则如下：<br>
     *
     * @param txt
     * @param beginIndex
     * @param matchType
     * @return，如果存在，则返回敏感词字符的长度，不存在返回0
     */
    public int CheckKeyword(String txt, int beginIndex, int matchType, Map map);

}
