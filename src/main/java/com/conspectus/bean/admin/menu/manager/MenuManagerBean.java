package com.conspectus.bean.admin.menu.manager;

import com.conspectus.bean.base.BaseBean;
import com.conspectus.entity.Menu;
import org.primefaces.context.RequestContext;
import org.primefaces.model.TreeNode;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import java.io.Serializable;

/**
 * Created by luan vu on 2/7/2017.
 */
@ManagedBean(name = "ttMenuManagerBean")
@ViewScoped
public class MenuManagerBean extends BaseBean implements Serializable {
    private TreeNode root;
    private TreeNode selectedNode;
    private Menu activeMenu;
    @ManagedProperty("#{menuServiceBean}")
    private MenuServiceBean service;
    private MenuActionType menuActionType;

    @PostConstruct
    public void init() {
        initTree();
    }
    private void initTree(){
        try{
            root = service.createTreeMenu();
        }catch (Exception e){
            e.printStackTrace();
            // TODO
        }
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

    public Menu getActiveMenu() {
        return activeMenu;
    }

    public void setActiveMenu(Menu activeMenu) {
        this.activeMenu = activeMenu;
    }

    public void editPrepare(){
        menuActionType = MenuActionType.EDIT;
        activeMenu = new Menu((Menu) selectedNode.getData());
    }
    public void insertAfterPrepare(){
        menuActionType = MenuActionType.AFTER;
        activeMenu = new Menu();
        activeMenu.setOrder(getSelectedMenu().getOrder()+1);
    }

    public void insertBeforePrepare(){
        menuActionType = MenuActionType.BEFORE;
        activeMenu = new Menu();
        activeMenu.setOrder(getSelectedMenu().getOrder());
    }

    public void addChildPrepare(){
        menuActionType = MenuActionType.ADD_CHILD;
        Menu parentMenu = getSelectedMenu();
        activeMenu = new Menu();
        activeMenu.setParent(parentMenu);
        activeMenu.setOrder(parentMenu.getChildren().size()+1);
    }

    private Menu getSelectedMenu(){
        return (Menu) selectedNode.getData();
    }

    public void delete(){
        menuActionType = MenuActionType.DELETE;
        save();
    }
    private void showMessage(){
        switch (menuActionType){
            case BEFORE:
            case AFTER:
            case ADD_CHILD:
                addMessage("Add Menu", "Success");
                break;
            case EDIT:
                addMessage("Update Menu", "Success");
                break;
            case DELETE:
                addMessage("Delete Menu", "Success");
                break;
        }
    }
    public void save(){
        boolean res = runAction();
        if(res){
            closeMenuEditDialog();
            showMessage();
            initTree();
        }else{
            addMessage(null, "ERROR");
        }

    }
    private boolean runAction(){
        switch (menuActionType){
            case BEFORE:
                return service.insertBefore(selectedNode, activeMenu);
            case AFTER:
                return service.insertAfter(selectedNode, activeMenu);
            case EDIT:
                return service.update(activeMenu);
            case DELETE:
                return service.delete(selectedNode);
            case ADD_CHILD:
                return service.insert(activeMenu);
        }
        return true;
    }

    public MenuActionType getMenuActionType() {
        return menuActionType;
    }

    public boolean isNotEdittingMode(){
        return !MenuActionType.EDIT.equals(getMenuActionType());
    }

    public void setMenuActionType(MenuActionType menuActionType) {
        this.menuActionType = menuActionType;
    }
    public void closeMenuEditDialog() {
        RequestContext.getCurrentInstance().execute("PF('menuEditDialog').hide()");
    }
}
