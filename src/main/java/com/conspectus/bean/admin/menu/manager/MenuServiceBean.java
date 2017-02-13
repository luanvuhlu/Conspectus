package com.conspectus.bean.admin.menu.manager;

import com.conspectus.bean.base.BaseBean;
import com.conspectus.entity.Menu;
import com.conspectus.entity.MenuIcon;
import com.conspectus.service.MenuService;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.io.Serializable;
import java.util.List;

/**
 * Created by luan vu on 2/7/2017.
 */
@ManagedBean(name = "menuServiceBean")
@ApplicationScoped
public class MenuServiceBean extends BaseBean implements Serializable{

    public List<Menu> getMenuList() throws Exception {
        return getService().listMenuOrdered();
    }
    public TreeNode createTreeMenu() throws Exception {
        List<Menu> menus = getMenuList();
        TreeNode root = new DefaultTreeNode(new Menu(), null);
        for (Menu menu : menus) {
            TreeNode node = createNode(menu, root);
            if (menu.getChildren() == null) {
                continue;
            }
            for (Menu child : menu.getChildren()) {
                createNode(child, node);
            }
        }
        return root;
    }

    private TreeNode createNode(Menu menu, TreeNode parent) {
        return new DefaultTreeNode(menu, parent);
    }

    public boolean insertAfter(TreeNode selectedNode, Menu activeMenu){
        MenuService service = getService();
        try {
            service.addAfter(activeMenu);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertBefore(TreeNode selectedNode, Menu activeMenu){
        MenuService service = getService();
        try {
            service.addBefore(activeMenu);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public boolean delete(TreeNode selectedNode) {
        try {
            getService().delete((Menu) selectedNode.getData());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    private MenuService getService(){
        return new MenuService();
    }

    public boolean update(Menu menu) {
        try {
            getService().update(menu);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insert(Menu menu) {
        try {
            getService().add(menu);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<MenuIcon> getMenuIconList() throws Exception {
        return getService().getListMenuIcons();
    }
}
