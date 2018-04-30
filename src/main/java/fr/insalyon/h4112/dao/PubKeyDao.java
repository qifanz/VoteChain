package fr.insalyon.h4112.dao;

import fr.insalyon.h4112.Utility.HibernateUtil;
import fr.insalyon.h4112.model.PubKey;
import org.springframework.stereotype.Repository;

@Repository

public class PubKeyDao extends BaseDao{
    /**
     * persist the pub key entity to the data base
     * @param pubKey
     */
    public void save (PubKey pubKey) {
        //TODO:generate exception when duplicated value
        this.getSession().save(pubKey);
    }

    public PubKey findById(Integer id) {
        PubKey pubKey;
        pubKey= (PubKey)this.getSession().get(PubKey.class,id);
        return pubKey;
    }



}
