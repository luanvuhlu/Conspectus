package main.java.com.conspectus.bean.common;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;

/**
 * Created by luan vu on 1/31/2017.
 */
@ManagedBean(name = "navigationController", eager = true)
@RequestScoped
public class NavigationController implements Serializable {
    private static final String HOME_PAGE = "hello";
    @ManagedProperty(value = "#{param.pageId}")
    private String pageId;
    public String showPage(){
        if (pageId == null){
            return HOME_PAGE;
        }
        switch (pageId){
            case "1":
                return "page1";
        }
        return HOME_PAGE;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }
}
