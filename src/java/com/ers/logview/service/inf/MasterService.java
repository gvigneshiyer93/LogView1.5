/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ers.logview.service.inf;

import java.util.Map;

/**
 *
 * @author envtraining
 */

/*
retrieves the data of the master table as a single string, splits it record and converts
the string to a map
 */
public interface MasterService {

    Map<String, String> masterMap() throws Exception;

}
