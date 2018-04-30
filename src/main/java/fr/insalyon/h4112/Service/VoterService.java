package fr.insalyon.h4112.Service;

import fr.insalyon.h4112.dao.VoterDao;
import fr.insalyon.h4112.model.Voter;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Voter registerVoter (String fname, String gName, String login, String password) throws NonUniqueObjectException {
        String pHash=""+(password.hashCode());
        Voter voter=new Voter(fname,gName,login,pHash);
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
}
