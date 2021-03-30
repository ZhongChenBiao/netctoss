package com.pxxy.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Descricption:
 * @Author:江灿
 * @Date:Create in 10:52 2019/6/5
 */
public class ListAdministor implements Serializable {
    private List<Administor> administorList;

    private Administor administor;

    public List<Administor> getAdministorList() {
        return administorList;
    }

    public void setAdministorList(List<Administor> administorList) {
        this.administorList = administorList;
    }

    public Administor getAdministor() {
        return administor;
    }

    public void setAdministor(Administor administor) {
        this.administor = administor;
    }

    @Override
    public String toString() {
        return "ListAdministor{" +
                "administorList=" + administorList +
                ", administor=" + administor +
                '}';
    }
}
