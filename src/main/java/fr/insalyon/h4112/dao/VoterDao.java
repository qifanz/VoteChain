package fr.insalyon.h4112.dao;

import fr.insalyon.h4112.Utility.HibernateUtil;
import fr.insalyon.h4112.model.Election;
import fr.insalyon.h4112.model.Voter;
import org.codehaus.jackson.map.Serializers;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public class VoterDao extends BaseDao{
    /**
     * persist the voter entity to the database
     * @param voter
     */
    public void save (Voter voter) {
        //TODO:generate exception when duplicated value
        this.getSession().save(voter);
    }

    public void update (Voter voter) {
        this.getSession().update(voter);
    }

    /**
     * search in the database the voter entity by its login and hash of password
     * @param login
     * @param hash
     * @return the voter found
     */
    public Voter login (String login,String hash) {
        Voter v;
        String query="SELECT v FROM Voter v WHERE v.login=:login";
        Query query1=this.getSession().createQuery(query);
        query1.setParameter("login",login);
        List<Voter> result= (List<Voter>) query1.list();
        if(!result.isEmpty()) {
            v=(Voter)query1.list().get(0);
        } else {
            System.out.println("login does not exist");
            return null;
        }
        if (v.getHashPassword().equals(hash)){
            return v;
        } else {
            System.out.println("password error");
            return null;
        }


    }

    public Voter findById(Integer id) {
        Voter v;
        v= (Voter) this.getSession().get(Voter.class,id);
        return v;
    }

    public Voter findByLogin (String login) {
        Voter v;
        String query="SELECT v FROM Voter v WHERE v.login=:login";
        Query query1=this.getSession().createQuery(query);
        query1.setParameter("login",login);
        List<Voter> result= (List<Voter>) query1.list();
        if(!result.isEmpty()) {
            v=(Voter)query1.list().get(0);
        } else {
            System.out.println("login does not exist");
            return null;
        }
        return v;

    }

    public Election findElectionByVoterAndElection (Integer voterId, Integer ElectionId) {
        Election election = null;
        Voter v = (Voter) this.getSession().get(Voter.class, voterId);
        if (v == null) {
            return null;
        } else {
            for (Election e : v.getVotedElections()) {
                if (e.getId().equals(ElectionId)) {
                    election = e;
                }
            }
        }
        return election;
    }

}
