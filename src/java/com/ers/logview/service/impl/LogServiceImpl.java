/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ers.logview.service.impl;

import com.ers.logview.dao.impl.LogDAOImpl;
import com.ers.logview.dao.inf.LogDAO;
import com.ers.logview.service.inf.LogService;
import com.ers.logview.util.Entity;
import java.util.List;
import java.util.Map;

/**
 *
 * @author envtraining
 */
public class LogServiceImpl implements LogService {

    private LogDAO dao = new LogDAOImpl();

    @Override
    public List<List<Entity>> logList(List<String> tableList, Map<String, String> keyValueMap) {
        List<List<Entity>> logList = null;
        try {
            logList = dao.log(tableList, keyValueMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return logList;
    }
//        public static void main(String[] args) {
//        LogServiceImpl service=new LogServiceImpl();
//         List<String> list=new ArrayList();
//       Map<String,String> map=new HashMap();
//        
//        list.add("loginhistory");
//        map.put("userid","5");
//            System.out.println(service.logList(list, map));
//    }

}
