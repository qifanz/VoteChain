package fr.insalyon.h4112.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by siyingjiang on 2018/4/25.
 */
@Entity
@Table(name = "election")
public class Election {
    private Integer id;
    private String name;
    private Date startTime;
    private Date endTime;
    private Set<Candidate> candidates;
    private Set<PubKey> pubKeys;
    private Integer nbCandidates;
    private Integer type; //1 for simple, 2 for delegation, 3 for condorcet, 4 for majoritaire

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ELECTION_ID", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "ELECTION_NAME", precision = 6)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "ELECTION_START_TIME", precision = 6)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Column(name = "ELECTION_END_TIME", precision = 6)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @OneToMany(fetch=FetchType.EAGER)
    //@JoinColumn(name="CANDIDATE_ID", unique= true, nullable=true, insertable=true, updatable=true)
    public Set<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(Set<Candidate> candidates) {
        this.candidates = candidates;
    }

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="PUBKEY_ID", unique= true, nullable=true, insertable=true, updatable=true)
    public Set<PubKey> getPubKeys() {
        return pubKeys;
    }


    public void setPubKeys(Set<PubKey> pubKeys) {
        this.pubKeys = pubKeys;
    }
    public void addPubKey (PubKey pubKey) {
        pubKeys.add(pubKey);
    }
    public Election() {
    }

    public void addCandidate(Candidate candidate) {
        candidates.add(candidate);
    }

    @Column(name = "ELECTION_NBCANDIDATES")
    public Integer getNbCandidates() {
        return nbCandidates;
    }

    public void setNbCandidates(Integer nbCandidates) {
        this.nbCandidates = nbCandidates;
    }

    @Column(name = "ELECTION_TYPE")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Election(String name, Date startTime, Date endTime, Integer nbCandidates, Integer type) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.nbCandidates = nbCandidates;
        this.type = type;
        candidates=new HashSet<>();
        pubKeys=new HashSet<>();
    }
}
