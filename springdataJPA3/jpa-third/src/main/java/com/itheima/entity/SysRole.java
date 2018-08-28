package com.itheima.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sys_role")
public class SysRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id")
    private Long rId;
    @Column(name = "r_name")
    private String rName;

    @ManyToMany(mappedBy = "roles")
    /*@JoinTable(name = "sys_user_role", joinColumns = @JoinColumn(name = "ur_r_id") ,
            inverseJoinColumns = @JoinColumn(name = "ur_user_id"))*/
    private Set<SysUser> users = new HashSet<>();

    public Set<SysUser> getUsers() {
        return users;
    }

    public void setUsers(Set<SysUser> users) {
        this.users = users;
    }

    public Long getrId() {
        return rId;
    }

    public void setrId(Long rId) {
        this.rId = rId;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    @Override
    public String toString() {
        return "SysRole{" +
                "rId=" + rId +
                ", rName='" + rName + '\'' +
                '}';
    }
}
