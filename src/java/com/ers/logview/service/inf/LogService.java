/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ers.logview.service.inf;

import com.ers.logview.util.Entity;
import java.util.List;
import java.util.Map;

/**
 *
 * @author envtraining
 */
public interface LogService {

    List<List<Entity>> logList(List<String> tableList, Map<String, String> keyValueMap);

}
