package org.merariway.saleswarehouse.entity;

import jakarta.persistence.*;

import javax.annotation.processing.Generated;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String uidFirebase;

    private String email;

    private String displayName;

    @OneToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private RoleEntity role;

    public UserEntity() {
    }

    public UserEntity(String uidFirebase, String email, String displayName) {
        this.uidFirebase = uidFirebase;
        this.email = email;
        this.displayName = displayName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUidFirebase() {
        return uidFirebase;
    }

    public void setUidFirebase(String uidFirebase) {
        this.uidFirebase = uidFirebase;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }
}
