package fr.insalyon.h4112.dao;

import fr.insalyon.h4112.Utility.HibernateUtil;
import fr.insalyon.h4112.model.Election;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public class ElectionDao extends BaseDao{
    /**
     * persist the election entity to the data base
     * @param election
     */
    public void save (Election election) {
        //TODO:generate exception when duplicated value
        this.getSession().save(election);
    }

    public Election findById (Integer id) {
        Election election;
        election=(Election)this.getSession().get(Election.class,id);
        return election;

    }

    public void update (Election election) {
        this.getSession().update(election);
    }

    public List<Election> findAll () {
        List<Election> electionList;
        Query q= this.getSession().createQuery("FROM Election e");
        electionList=q.list();
        return electionList;
    }

}
