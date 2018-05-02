package fr.insalyon.h4112.model;

import javax.persistence.*;

/**
 * Created by siyingjiang on 2018/4/25.
 */
@Entity
@Table(name = "candidate")
public class Candidate {
    private Integer id;
    private String name;
    private PubKey pubKey;

   // private Election election;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CANDIDATE_ID", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "CANDIDATE_NAME", unique = false, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne
    @JoinColumn(name="PUBKEY_ID", unique= true, nullable=true, insertable=true, updatable=true)
    public PubKey getPubKey() {
        return pubKey;
    }

    public void setPubKey(PubKey pubKey) {
        this.pubKey = pubKey;
    }


    public Candidate() {
    }

    public Candidate(String name) {
        this.name = name;
    }
//    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
//    //   @JoinColumn(name="ELECTION_ID", unique= true, nullable=true, insertable=true, updatable=true)
//    public Election getElection() {
//        return election;
//    }
//
//    public void setElection(Election election) {
//        this.election = election;
//    }
}
