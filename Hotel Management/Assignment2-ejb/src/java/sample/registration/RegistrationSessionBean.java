/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample.registration;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sample.entity.TblAccount;
import sample.entity.TblRoom;

/**
 *
 * @author haryoken
 */
@Stateless
public class RegistrationSessionBean implements RegistrationSessionBeanLocal, RegistrationSessionBeanRemote {
    @PersistenceContext(unitName = "Assignment2-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean checkLogin(String username, String password) {
        String jpql = "Select a from TblAccount a where a.username = :username And a.password =:password";
        Query query = em.createQuery(jpql);
        query.setParameter("username", username);
        query.setParameter("password", password);
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public int checkRole(String username) {
       //Tim theo key truoc
        TblAccount account = em.find(TblAccount.class, username);
        if(account!=null){
            return account.getRole();
        }
        return -1;
    }

    @Override
    public List searchByRoomID(String RoomID) {
        Query query = em.createNamedQuery("TblRoom.findByRoomID");
        query.setParameter("roomID", RoomID);
        List result = query.getResultList();
        return result;
    }

    @Override
    public List getAllRoomDetails() {
        Query query = em.createQuery("Select a from TblRoom a");
        List result = query.getResultList();
        return result;
    }

    @Override
    public List getAllCategoryName() {
        Query query = em.createQuery("Select a from TblCategory a");
        List result = query.getResultList();
        return result;
    }

    @Override
    public String businessMethod(String RoomID) {
        
        TblRoom room = em.find(TblRoom.class, RoomID);
        String categoryID = room.getCategoryID();
        return "";
    }
    
}
