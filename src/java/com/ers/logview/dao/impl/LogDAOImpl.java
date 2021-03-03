/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ers.logview.dao.impl;

import com.envestnet.dbconnection.domain.ConnProfile;
import com.envestnet.dbconnection.domain.DbConnection;
import com.envestnet.dbconnection.domain.MySQLDbConnection;
import com.ers.logview.dao.inf.LogDAO;
import com.ers.logview.util.Entity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author envtraining
 */
public class LogDAOImpl implements LogDAO {

    private final String SELECT_QUERY = "select * from ";
    private final String WHERE = " where ";
    private final String BETWEEN = " between ";
    private final String TIME_START = " 00:00:00' ";
    private final String AND = " and ";
    private final String TIME_END = " 23:59:59'";

    private String queryBuilder(List<String> tableList, Map<String, String> keyValueMap) throws Exception {
        StringBuilder logStringBuilder = new StringBuilder();
        logStringBuilder.append(SELECT_QUERY);
        tableList.forEach((s) -> {
            logStringBuilder.append(s);
        });        
        logStringBuilder.append(WHERE);
        Iterator<Map.Entry<String, String>> i = keyValueMap.entrySet().iterator();
        while (i.hasNext()) {
            String key = i.next().getKey();
            logStringBuilder.append(key);
            if (key.contains("Date") || key.contains("Time")) {
//         System.out.println("contains date/time");
                String[] parts = keyValueMap.get(key).split(",");
                logStringBuilder.append(BETWEEN);
                logStringBuilder.append("'");
                logStringBuilder.append(parts[0]);
                logStringBuilder.append(TIME_START);
                logStringBuilder.append(AND);
                logStringBuilder.append("'");
                logStringBuilder.append(parts[1]);
                logStringBuilder.append(TIME_END);
            } else {
//        System.out.println("does not contain stuff");
                logStringBuilder.append(" = ");
                logStringBuilder.append(keyValueMap.get(key));
            }
            logStringBuilder.append(AND);
        }
        int start = logStringBuilder.length() - 4;
        int end = logStringBuilder.length();
        logStringBuilder.delete(start, end);
//        System.out.println(logStringBuilder.toString());
        tableList.clear();
        keyValueMap.clear();
        return logStringBuilder.toString();
    }

    @Override
    public List<List<Entity>> log(List<String> tableList, Map<String, String> keyValueMap) throws Exception {
        List<Entity> entry = new ArrayList();
        List<List<Entity>> logList = new ArrayList();
        Entity entity;
        String sql = queryBuilder(tableList, keyValueMap);
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            DbConnection db = new MySQLDbConnection();
            ConnProfile Profile = new ConnProfile();
            Profile.loadConnProperties();
            conn = db.createConnection(Profile);
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                entity = new Entity();
                entity.setEntity(rsmd.getColumnLabel(i));
                entry.add(entity);
            }
            List<Entity> temp = new ArrayList(entry);
            Collections.copy(temp, entry);
            entry.clear();
            logList.add(temp);
            while (rs.next()) {
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    entity = new Entity();
                    if (rs.getString(i) == null) {
                        entity.setEntity("null");
                        entry.add(entity);
                    } else {
                        entity.setEntity(rs.getString(i));
//                    System.out.println(entity.getEntity());
                        entry.add(entity);
                    }
                }
                temp = new ArrayList(entry);
                Collections.copy(temp, entry);
                entry.clear();
                logList.add(temp);
            }
            sql = null;
            rsmd = null;
            rs.close();
            pst.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
        return logList;
    }

//    public static void main(String[] args) throws Exception {
//        LogDAOImpl dao=new LogDAOImpl();
//        List<String> list=new ArrayList();
//        Map<String,String> map=new HashMap();
//        
//        list.add("loginhistory");
//        map.put("userid","5");
//        dao.log(list, map);
//        
//    }
}
