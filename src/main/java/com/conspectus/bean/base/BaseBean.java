package com.conspectus.bean.base;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by luan vu on 2/11/2017.
 */
public class BaseBean implements IBeanMessageSender {
    @Override
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
