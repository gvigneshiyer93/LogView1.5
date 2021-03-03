/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ers.logview.model;

import com.ers.logview.service.impl.LogServiceImpl;
import com.ers.logview.util.Entity;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author envtraining
 */
public class LogAction {

    LogServiceImpl service = new LogServiceImpl();

    public void logDisplayAction(HttpServletRequest request, HttpServletResponse response, String value) throws IOException, Exception {
        List<List<Entity>> logList = null;
        List<String> tableList = new ArrayList();
        Map<String, String> keyValueMap = new HashMap();
        StringTokenizer st = new StringTokenizer(value, ",");
        while (st.hasMoreElements()) {
            tableList.add(st.nextToken());
        }
        Gson gson = new Gson();

        keyValueMap = gson.fromJson(request.getReader(), Map.class);
        logList = service.logList(tableList, keyValueMap);
        String json = gson.toJson(logList);
        Writer out = response.getWriter();
        out.write(json);
        logList.clear();
        json=null;
        out.flush();
        out.close();
        //System.out.println(tableList);
//        System.out.println(keyValueMap);
//         Iterator<Map.Entry<String, String>> i = keyValueMap.entrySet().iterator(); 
//        while(i.hasNext()){
//        String key = i.next().getKey();
//        System.out.println("key "+key+", value "+keyValueMap.get(key));
//}
        //keyValueMap.put("userid","5");

        //System.out.println(json);
    }

}
