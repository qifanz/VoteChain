package fr.insalyon.h4112.model;

import javax.persistence.*;

/**
 * Created by siyingjiang on 2018/4/25.
 */
@Entity
@Table(name = "pubkey")
public class PubKey {
    private Integer id;
    private String value;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PUBKEY_ID", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name = "PUBKEY_VALUE", unique = true, nullable = false)
    public String getValue() {
        return value;
    }
    public void setValue(String value){
        this.value=value;
    }
    public PubKey(String value) {
        this.value = value;
    }

    public PubKey() {
    }
}
