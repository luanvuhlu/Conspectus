package com.conspectus.bean.home;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Created by luan vu on 1/31/2017.
 */
@ManagedBean(name = "home")
@RequestScoped
public class HomeBean implements Serializable {
    private String name;
    public HomeBean() {
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
