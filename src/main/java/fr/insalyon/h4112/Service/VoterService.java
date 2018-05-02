package fr.insalyon.h4112.Service;

import fr.insalyon.h4112.dao.VoterDao;
import fr.insalyon.h4112.model.Voter;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by siyingjiang on 2018/4/30.
 */

@Service
@Transactional
public class VoterService {
    @Autowired
    private VoterDao voterDao;

    /**
     * register a new User
     * @param fname
     * @param gName
     * @param login
     * @param password
     * @return return voter in case of success
     */
    public Voter registerVoter (String fname, String gName, String login, String password, String address, String birthday) throws NonUniqueObjectException {
        String pHash=""+(password.hashCode());
        Date date=null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
             date = format.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Voter voter=new Voter(fname,gName,login,pHash,address,date);
        try {
            voterDao.save(voter);
        } catch (NonUniqueObjectException noe) {
            throw noe;
        }
        return voter;
    }

    /**
     * an user login
     * @param login
     * @param password
     * @return return voter in case of success, else return null
     */
    public Voter loginVoter (String login, String password) {
        return voterDao.login(login,""+password.hashCode());
    }

    /**
     * verify if a login already exists
     * @param login
     * @return
     */
    public boolean verifyLoginExistence (String login) {
        Voter v=voterDao.findByLogin(login);
        if (v!=null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * get a voter by his id
     * @param id
     * @return
     */
    public Voter getVoter(Integer id) {
        return voterDao.findById(id);
    }

    public void updateVoter (Voter voter) {voterDao.update(voter);}

}
