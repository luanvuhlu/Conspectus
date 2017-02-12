package com.conspectus.component.primefaces;

import com.luanvv.utils.text.StringUtils;
import org.primefaces.component.commandbutton.CommandButton;

/**
 * Created by luan vu on 2/11/2017.
 */
public class BlockCommandButton extends CommandButton{
    @Override
    public String getOnclick() {
        String clickEpr = super.getOnclick();
        if(StringUtils.isNotEmpty(clickEpr)){
            clickEpr+=";";
        }
        clickEpr+="$(this).block({ message: null })";
        return clickEpr;
    }

    @Override
    public String getOncomplete() {
        String completeEpr = super.getOncomplete();
        if(StringUtils.isNotEmpty(completeEpr)){
            completeEpr+=";";
        }
        completeEpr+="$(this).unblock()";
        return completeEpr;
    }
}
