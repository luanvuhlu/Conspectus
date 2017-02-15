package com.conspectus.bean.common;

import com.conspectus.entity.Menu;
import com.conspectus.service.MenuService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 * Created by luanvv on 15/02/2017.
 */
@ManagedBean
@ApplicationScoped
public class MenuSideBean {
    private List<Menu> menuList;
    @PostConstruct
    public void init(){
        try {
            menuList = lsMenuListForMenuSide();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO
        }
    }
    private List<Menu> lsMenuListForMenuSide() throws Exception {
        if(menuList==null) {
            menuList = getService().listMenuOrdered();
        }
        return menuList;
    }
    private MenuService getService(){
        return new MenuService();
    }

    public List<Menu> getMenuList() {
        return menuList;
    }
}
