package com.syberos.shuili.entity.userinfo;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.MarshalDate;
import org.ksoap2.serialization.PropertyInfo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

/**
 * Created by Administrator on 2018/6/4.
 */

public class RoleExtInfo implements KvmSerializable {
    public String id;
    public String roleCode;
    public String roleName;
    public String roleDesc;
    public String roleType;
    public String appGuid;
    public String orgGuid;
    public String status;
    public String modifier;
    public String appCode;
    public String ts;
    public String remark;

    @Override
    public Object getProperty(int i) {
        switch (i){
            case 0:
                return  id;
            case 1:
                return roleCode;
            case 2:
                return roleName;
            case 3:
                return roleDesc;
            case 4:
                return roleType;
            case 5:
                return appGuid;
            case 6:
                return orgGuid;
            case 7:
                return status;
            case 8:
                return modifier;
            case 9:
                return appCode;
            case 10:
                return ts;
            case 11:
                return remark;

        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 12;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch (i){
            case 0:
                id = o.toString();
                break;
            case 1:
                roleCode = o.toString();
                break;
            case 2:
                roleName = o.toString();
                break;
            case 3:
                roleDesc = o.toString();
                break;
            case 4:
                roleType = o.toString();
                break;
            case 5:
                appGuid = o.toString();
                break;
            case 6:
                orgGuid = o.toString();
                break;
            case 7:
                status = o.toString();
                break;
            case 8:
                modifier = o.toString();
                break;
            case 9:
                appCode = o.toString();
                break;
            case 10:
                ts = o.toString();
                break;
            case 11:
                remark = o.toString();
                break;
            default:
                break;
        }

    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch (i){
            case 0:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "id";
                break;
            case 1:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "roleCode";
                break;
            case 2:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name ="roleName";
                break;
            case 3:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "roleDesc";
                break;
            case 4:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "roleType";
                break;
            case 5:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "appGuid";
                break;
            case 6:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "orgGuid";
                break;
            case 7:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "status";
                break;
            case 8:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "modifier";
                break;
            case 9:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "appCode";
                break;
            case 10:
                propertyInfo.type = PropertyInfo.STRING_CLASS;;
                propertyInfo.name = "ts";
                break;
            case  11:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name ="remark";
                break;
            default:
                break;
        }
    }
}
