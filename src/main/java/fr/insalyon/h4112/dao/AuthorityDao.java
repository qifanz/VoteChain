package fr.insalyon.h4112.dao;

import fr.insalyon.h4112.Utility.HibernateUtil;
import fr.insalyon.h4112.model.Authority;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorityDao extends BaseDao{
    /**
     * persist the authority entity to the data base
     * @param authority
     */
    public void save (Authority authority) {
        //TODO:generate exception when duplicated value

        this.getSession().save(authority);
    }
}
