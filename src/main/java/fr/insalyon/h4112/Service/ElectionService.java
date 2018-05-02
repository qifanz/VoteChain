package fr.insalyon.h4112.Service;

import fr.insalyon.h4112.dao.CandidateDao;
import fr.insalyon.h4112.dao.ElectionDao;
import fr.insalyon.h4112.dao.PubKeyDao;
import fr.insalyon.h4112.dao.VoterDao;
import fr.insalyon.h4112.model.Candidate;
import fr.insalyon.h4112.model.Election;
import fr.insalyon.h4112.model.PubKey;
import fr.insalyon.h4112.model.Voter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
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
    @Autowired
    private VoterDao voterDao;


    /**
     * generate pubkey from a privatekey entered by user
     * the private key is not saved anywhere
     * @param
     * @return public key
     */
    public ECKeyPair generateKeyPair () throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        ECKeyPair ecKeyPair=Keys.createEcKeyPair();
         return ecKeyPair;
    }

    /**
     * create a candidate
     * attribute to him a public key.
     * @param name
     * @return
     */
    private Candidate createCandidate (String name, Election election) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        Candidate candidate=new Candidate(name);
        //  candidate.setElection(election);
        String publicKey=generateKeyPair().getPublicKey().toString(16);
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
    public Election registerElection (Election election) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        Election election1=new Election(election.getName(),election.getStartTime(),election.getEndTime(),election.getType(),election.getDescription());
        for (Candidate c:election.getCandidates())
        {
            Candidate candidate=createCandidate(c.getName(),election1);
            election1.addCandidate(candidate);
        }
        electionDao.save(election1);
        return election1;
    }

    /**
     *
     * @param idVoter
     * @param idPubKey
     * @param idElection
     * @return
     */
    public int addVoterToElection (Integer idVoter, Integer idPubKey, Integer idElection) {
        Voter voter=voterDao.findById(idVoter);
        PubKey pubKey= pubKeyDao.findById(idPubKey);
        Election election=electionDao.findById(idElection);
        if (voter==null||pubKey==null||election==null) {
            return 2; //not found exception
        }
        for (Election e:voter.getVotedElections()) {
            if (e.getId().equals(idElection)) {
                return 1; //already voted
            }
        }
        election.addPubKey(pubKey);
        voter.addVotedElection(election);
        //todo: send coin to pubkey
        electionDao.update(election);
        voterDao.update(voter);
        return 0;
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

