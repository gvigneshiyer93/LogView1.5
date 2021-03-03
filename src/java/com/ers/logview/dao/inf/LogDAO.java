/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ers.logview.dao.inf;

import com.ers.logview.util.Entity;
import java.util.List;
import java.util.Map;

/**
 *
 * @author envtraining
 */
public interface LogDAO {

    List<List<Entity>> log(List<String> tableList, Map<String, String> keyValueMap) throws Exception;

}
