package top.danny.tools.collection;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huyuyang@lxfintech.com
 * @Title: MapUtilTest
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2018-03-13 15:58:12
 */
public class MapUtilTest {

    @Test
    public void isEmptyTest(){
        Map map1=new HashMap<>(-1);
        map1.put("1","fdfdfd");
        map1.put("2","fdfdfd");
        map1.put("3","fdfdfd");
        Map map2=new HashMap<>();
        map2.put(null,null);
        Map map3=new HashMap<>();
        System.out.println(MapUtil.isEmpty(map1));
        System.out.println(MapUtil.isEmpty(map2));
        System.out.println(MapUtil.isEmpty(map3));
    }
}
