package fr.insalyon.h4112.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by siyingjiang on 2018/4/25.
 */
@Entity
@Table(name = "voter")
public class Voter {
    private Integer id;
    private String familyName;
    private String givenName;
    private String login;
    private String hashPassword;
    private Set<Election> votedElections;
    private String address;
    private Date birthDay;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "VOTER_ID", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Column(name = "VOTER_LOGIN", unique = true, nullable = false)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "VOTER_HASH", unique = false, nullable = false)
    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }


    public Voter() {
    }
    @Column(name = "VOTER_FAMILIYNAME", unique = false, nullable = false)
    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
    @Column(name = "VOTER_GIVENNAME", unique = false, nullable = false)
    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }
    @Column(name = "VOTER_ADDRESS", unique = false, nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Column(name = "VOTER_BIRTHDAY", unique = false, nullable = false)
    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Voter(String familyName, String givenName, String login, String hashPassword, String address, Date birthDay) {
        this.familyName = familyName;
        this.givenName = givenName;
        this.login = login;
        this.hashPassword = hashPassword;
        this.address = address;
        this.birthDay = birthDay;
    }

    @OneToMany(fetch=FetchType.EAGER)
    public Set<Election> getVotedElections() {
        return votedElections;
    }

    public void setVotedElections(Set<Election> votedElections) {
        this.votedElections = votedElections;
    }

    public void addVotedElection (Election election) {
        votedElections.add(election);
    }
}
