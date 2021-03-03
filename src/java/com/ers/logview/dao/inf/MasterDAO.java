/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ers.logview.dao.inf;

/**
 *
 * @author envtraining
 */
//retrieves the data in the mastertable as a stirng seperating each column by '#' and each record by '\n'
public interface MasterDAO {

    String masterTable() throws Exception; //returns data from mastertable as string
}
