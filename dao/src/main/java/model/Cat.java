package model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import util.Color;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cats")
public class Cat {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid2")
    @Column(name = "id")
    private String catId;

    @Column(name = "name")
    private String    name;

    @Column(name = "birthday")
    private Date      birthday;

    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    private Color     color;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Owner     owner;

    @ManyToMany
    @JoinTable
    private Set<Cat> friends = new HashSet<>();

    public Set<Cat> getFriends() {
        return friends;
    }

    public void setFriends(Set<Cat> friends) {
        this.friends = friends;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

//    public Set<Cat> getFriends() {
//        return friends;
//    }
//
//    public void setFriends(Set<Cat> friends) {
//        this.friends = friends;
//    }
}
