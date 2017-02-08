package main.java.com.conspectus.bean.admin.menu.manager;

import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

/**
 * Created by luan vu on 2/7/2017.
 */
@ManagedBean(name = "menuManagerBean")
@ViewScoped
public class MenuManagerBean implements Serializable {
    private TreeNode root;
    private TreeNode selectedNode;
    @ManagedProperty("#{menuServiceBean}")
    private MenuServiceBean service;

    public MenuManagerBean() throws Exception {
        service = new MenuServiceBean();
        root = service.createTreeMenu();
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setService(MenuServiceBean service) {
        this.service = service;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

}
