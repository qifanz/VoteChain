package fr.insalyon.h4112.Service;

import fr.insalyon.h4112.dao.CandidateDao;
import fr.insalyon.h4112.dao.ElectionDao;
import fr.insalyon.h4112.dao.PubKeyDao;
import fr.insalyon.h4112.model.Candidate;
import fr.insalyon.h4112.model.Election;
import fr.insalyon.h4112.model.PubKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by siyingjiang on 2018/4/30.
 */
@Service
@Transactional
public class ElectionService {

    @Autowired
    private CandidateDao candidateDao;
    @Autowired
    private ElectionDao electionDao;
    @Autowired
    private PubKeyDao pubKeyDao;


    /**
     * generate pubkey from a privatekey entered by user
     * the private key is not saved anywhere
     * @param privateKey
     * @return public key
     */
    public String generatePubKey(String privateKey) {
        //TODO:complete the method
        return ""+privateKey.hashCode();
    }

    /**
     * create a candidate
     * attribute to him a public key.
     * @param name
     * @return
     */
    private Candidate createCandidate (String name, Election election){
        Candidate candidate=new Candidate(name);
        //  candidate.setElection(election);
        String privateKey=new Date()+election.getName()+"_"+name;
        String publicKey=generatePubKey(privateKey);
        PubKey pubKey=new PubKey(publicKey);
        candidate.setPubKey(pubKey);
        //TODO:api call of blockchain
        //TODO:verifier save strategy
        pubKeyDao.save(pubKey);
        candidateDao.save(candidate);
        return candidate;

    }

    /**
     *
     * @param election
     * @return
     */
    public Election registerElection (Election election)
    {
        Election election1=new Election(election.getName(),election.getStartTime(),election.getEndTime(),election.getNbCandidates(),election.getType());
        for (Candidate c:election.getCandidates())
        {
            Candidate candidate=createCandidate(c.getName(),election1);
            election1.addCandidate(candidate);
        }
        electionDao.save(election1);
        return election1;
    }

    /**
     * add a voter to an election
     * send a coin to the adress of the voter
     * @param idPubKey
     * @param idElection
     */
    public void addVoterToElection (Integer idPubKey, Integer idElection) {
        PubKey pubKey= pubKeyDao.findById(idPubKey);
        Election election=electionDao.findById(idElection);
        election.addPubKey(pubKey);
        //todo: send coin to pubkey
        electionDao.update(election);

    }

    /**
     * get all the elections
     * @return
     */
    public List<Election> getElectionList () {
        return electionDao.findAll();
    }

    public Election getElection (Integer id) {return electionDao.findById(id);}

}

