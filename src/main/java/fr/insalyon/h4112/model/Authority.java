package fr.insalyon.h4112.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by siyingjiang on 2018/4/25.
 */
@Entity
@Table(name = "authority")
public class Authority {
    private Integer id;
    private String name;
    //private Set<Election> elections;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "AUTHORITY_ID", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "AUTHORITY_NAME", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @OneToMany
//    @JoinColumn(name="ELECTION_ID", unique= true, nullable=true, insertable=true, updatable=true)
//    public Set<Election> getElections() {
//        return elections;
//    }
//
//    public void setElections(Set<Election> elections) {
//        this.elections = elections;
//    }
}
