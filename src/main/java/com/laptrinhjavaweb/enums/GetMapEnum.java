package com.laptrinhjavaweb.enums;

import java.util.HashMap;
import java.util.Map;

public class GetMapEnum<T> {
    public Map districtMap(){
        Map<T,String> map = new HashMap<>();
        return map;
    }
}
