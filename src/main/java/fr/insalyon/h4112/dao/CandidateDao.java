package fr.insalyon.h4112.dao;

import fr.insalyon.h4112.Utility.HibernateUtil;
import fr.insalyon.h4112.model.Candidate;
import org.springframework.stereotype.Repository;

@Repository

public class CandidateDao extends BaseDao{
    /**
     * persist the candidate entity to the data base
     * @param candidate
     */
    public void save (Candidate candidate) {
        //TODO:generate exception when duplicated value
        this.getSession().save(candidate);

    }
}
