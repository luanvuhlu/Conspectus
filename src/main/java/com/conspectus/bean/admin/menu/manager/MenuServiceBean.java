package main.java.com.conspectus.bean.admin.menu.manager;

import com.conspectus.entity.Menu;
import com.conspectus.service.MenuService;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;

/**
 * Created by luan vu on 2/7/2017.
 */
@ManagedBean(name = "menuServiceBean")
@ApplicationScoped
public class MenuServiceBean implements Serializable{

    public TreeNode createTreeMenu() throws Exception {
        MenuService service = new MenuService();
        List<Menu> menus = service.listMenuOrdered();
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
}
