/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ers.logview.model;

import com.ers.logview.service.impl.MasterServiceImpl;
import com.ers.logview.service.inf.MasterService;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author envtraining
 */
public class MasterAction {

    private static MasterAction action = null;
    MasterService service = new MasterServiceImpl();
    Map<String, String> masterMap;

    public MasterAction() {
    }

    public static MasterAction getInstance() {
        if (action == null) {
            action = new MasterAction();
        }
        return action;
    }

    public Map<String, String> masterMapAction() throws Exception {
        masterMap = service.masterMap();

        return masterMap;

    }

    public void masterJsonAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(masterMap);
        Writer out = response.getWriter();
        out.write(json);
        System.out.println(json);
        json=null;
        out.flush();
        out.close();
        //    return json;
    }

//        public static void main(String[] args) throws Exception {
//        MasterAction master=new MasterAction();
//        Map<String,String>map=null;
//        map=master.masterMap();
//        Iterator<Map.Entry<String, String>> i = map.entrySet().iterator(); 
//        while(i.hasNext()){
//        String key = i.next().getKey();
//        System.out.println("key "+key+", value "+map.get(key));
//        }
//             System.out.println(master.masterJson()); 
//        }
}
