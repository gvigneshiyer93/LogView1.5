/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ers.logview.service.impl;

import com.ers.logview.dao.impl.MasterDAOImpl;
import com.ers.logview.dao.inf.MasterDAO;
import com.ers.logview.service.inf.MasterService;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author envtraining
 */
public class MasterServiceImpl implements MasterService {

    private String[] split() throws Exception {

        MasterDAO dao = new MasterDAOImpl();
        String masterString = dao.masterTable();
        String entries[] = masterString.split("\n");
        return entries;
    }

    @Override
    public Map<String, String> masterMap() throws Exception {
        Map<String, String> masterMap = new HashMap();

        String entries[] = split();
        for (String entry : entries) {
            String parts[] = entry.split("#");
            for (String p : parts) {
                masterMap.put(parts[0], parts[1]);
            }
        }
        return masterMap;
    }

//    public static void main(String[] args) throws Exception {
//        MasterService service=new MasterServiceImpl();
//        Map<String,String>map=null;
//        map=service.masterMap();
//        Iterator<Map.Entry<String, String>> i = map.entrySet().iterator(); 
//        while(i.hasNext()){
//        String key = i.next().getKey();
//        System.out.println("key "+key+", value "+map.get(key));
//}
//        
//    }
}
