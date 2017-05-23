package xin.imba.filter.biz;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 敏感词过滤
 *
 * @author : xiepengcheng 2017年2月23日 下午4:15:54
 */
public class KeywordInit {
    private String ENCODING = "UTF-8";    //字符编码

    /**
     * 根据文件路径获取关键词的DFA数据结构
     *
     * @param filePath
     * @return Map
     */
    @SuppressWarnings("rawtypes")
    public Map initKeyword(String filePath) {
        Map keywordMap = null;
        try {
            //读取敏感词库
            Set<String> keyWordSet = readKeywordFile(filePath);
            //将敏感词库加入到HashMap中
            keywordMap = addKeywordToHashMap(keyWordSet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return keywordMap;
    }

    /**
     * 读取敏感词库中的内容，将内容添加到set集合中
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    @SuppressWarnings("resource")
    private Set<String> readKeywordFile(String filePath) throws Exception {
        Set<String> set = null;
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, ENCODING));
        try {
            set = new HashSet<String>();
            String txt = null;
            while ((txt = reader.readLine()) != null) {
                set.add(txt);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return set;
    }

    /**
     * 读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：<br>
     * 中 = {
     * isEnd = 0
     * 国 = {<br>
     * isEnd = 1
     * 人 = {isEnd = 0
     * 民 = {isEnd = 1}
     * }
     * 男  = {
     * isEnd = 0
     * 人 = {
     * isEnd = 1
     * }
     * }
     * }
     * }
     * 五 = {
     * isEnd = 0
     * 星 = {
     * isEnd = 0
     * 红 = {
     * isEnd = 0
     * 旗 = {
     * isEnd = 1
     * }
     * }
     * }
     * }
     *
     * @param keyWordSet 敏感词库
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private Map addKeywordToHashMap(Set<String> keyWordSet) {
        Map keywordMap = new HashMap(keyWordSet.size());     //初始化敏感词容器，减少扩容操作
        String key = null;
        Map nowMap = null;
        Map<String, String> newWorMap = null;
        //迭代keyWordSet
        Iterator<String> iterator = keyWordSet.iterator();
        while (iterator.hasNext()) {
            key = iterator.next();    //关键字
            nowMap = keywordMap;
            for (int i = 0; i < key.length(); i++) {
                char keyChar = key.charAt(i);       //转换成char型
                Object wordMap = nowMap.get(keyChar);       //获取

                if (wordMap != null) {        //如果存在该key，直接赋值
                    nowMap = (Map) wordMap;
                } else {     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                    newWorMap = new HashMap<String, String>();
                    newWorMap.put("isEnd", "0");     //不是最后一个
                    nowMap.put(keyChar, newWorMap);
                    nowMap = newWorMap;
                }

                if (i == key.length() - 1) {
                    nowMap.put("isEnd", "1");    //最后一个
                }
            }
        }
        return keywordMap;
    }

}
