/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.registration;

import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author haryoken
 */
@Remote
public interface RegistrationSessionBeanRemote {

    boolean checkLogin(String username, String password);

    int checkRole(String username);

    List searchByRoomID(String RoomID);

    List getAllRoomDetails();

    List getAllCategoryName();

    String businessMethod(String RoomID);
    
}
