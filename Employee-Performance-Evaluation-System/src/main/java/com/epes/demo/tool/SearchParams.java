package com.epes.demo.tool;

import java.util.HashMap;
import java.util.Map;

/**
* @author 程龙
* @version 创建时间：2019年10月1日 下午3:01:07
* @ClassName 类名称：
* @Description 类描述：用于接收查询条件
*/
public class SearchParams {
    private Map<String, Object> searchMap = new HashMap();

    public SearchParams() {
    }

    public Map<String, Object> getSearchMap() {
        return this.searchMap;
    }

    public void setSearchMap(Map<String, Object> searchMap) {
        this.searchMap = searchMap;
    }

    public void addCondition(String key, Object value) {
        this.searchMap.put(key, value);
    }

    public Object removeCondition(String key) {
        return this.searchMap.remove(key);
    }
}
