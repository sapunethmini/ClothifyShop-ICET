package org.example.bo;

import org.example.bo.custom.impl.userBoImpl;
import org.example.util.BoType;

public class BoFactory {
    public static BoFactory instance;
    private BoFactory(){}


    public static BoFactory getInstance()
    {
        return instance!=null?instance:(instance=new BoFactory());
    }

    public <T extends SuperBo>T getBo(BoType type)
    {
        switch (type)
        {
            case USER:return (T) new userBoImpl();
        }
        return null;
    }
}
