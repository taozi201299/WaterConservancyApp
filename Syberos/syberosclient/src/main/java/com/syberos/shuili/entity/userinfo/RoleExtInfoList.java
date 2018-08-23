package com.syberos.shuili.entity.userinfo;
import com.syberos.shuili.entity.userinfo.RoleExtInfo;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;
import java.util.Vector;

public class RoleExtInfoList extends Vector<RoleExtInfo> implements KvmSerializable
{

    @Override
    public Object getProperty(int arg0) {
        // TODO Auto-generated method stub
        return this.get(arg0);
    }

    @Override
    public int getPropertyCount() {
        // TODO Auto-generated method stub
        return this.size();
    }

    @Override
    public void setProperty(int arg0, Object arg1) {
        // TODO Auto-generated method stub
        this.add((RoleExtInfo)arg1);
    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        propertyInfo.name = "RoleExtInfo";
        propertyInfo.type = new RoleExtInfo().getClass();
    }
}
