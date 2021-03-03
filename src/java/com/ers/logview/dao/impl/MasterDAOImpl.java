/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ers.logview.dao.impl;

import com.envestnet.dbconnection.domain.ConnProfile;
import com.envestnet.dbconnection.domain.DbConnection;
import com.envestnet.dbconnection.domain.MySQLDbConnection;
import com.ers.logview.dao.inf.MasterDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author envtraining
 */
public class MasterDAOImpl implements MasterDAO {

    private final char COLUMN_SEPARATOR = '#';
    private final char RECORD_SEPARATOR = '\n';

    @Override
    public String masterTable() throws Exception {
        StringBuilder masterStringBuilder = new StringBuilder();
        String masterTableData = "null";
        String sql = "select LogType,TableName from mastertable";
        Connection conn = null;
        PreparedStatement pst = null;

        try {
            DbConnection db = new MySQLDbConnection();
            ConnProfile Profile = new ConnProfile();
            Profile.loadConnProperties();
            conn = db.createConnection(Profile);
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                masterStringBuilder.append(rs.getString("LogType"));
                masterStringBuilder.append(COLUMN_SEPARATOR);
                masterStringBuilder.append(rs.getString("TableName"));
                masterStringBuilder.append(RECORD_SEPARATOR);
            }
            masterTableData = masterStringBuilder.toString();
            sql = null;
            rs.close();
            pst.close();
        } catch (SQLException exp) {
            exp.printStackTrace();
        } finally {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
        return masterTableData;
    }

//public static void main(String [] args) throws Exception{
//MasterDAO master=new MasterDAOImpl();
// String s=master.masterTable();
//    System.out.println(s);
//}
}
