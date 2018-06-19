package com.syberos.shuili.entity.userinfo;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Date;
import java.util.Hashtable;

/**
 * Created by Administrator on 2018/4/14.
 */

public class RoleInfo implements KvmSerializable {
    String roleId;
    String roleCode;
    String orgCode;
    String orgName;
    String roleName;
    String roleDesc;
    String roleType;
    String Scode;
    String sname;
    Date Ts;
    String Note;
    String status;
    String modifier;
    String orgJurd;
    String jurdAreaType;
    @Override
    public Object getProperty(int i) {
        switch (i){
            case 0:
                return roleId;
            case 1:
                return roleCode;
            case 2:
                return orgCode;
            case 3:
                return  orgName;
            case 4:
                return roleName;
            case 5:
                return  roleDesc;
            case 6:
                return  roleType;
            case 7:
                return Scode;
            case 8:
                return sname;
            case 9:
                return  Ts;
            case 10:
                return  Note;
            case 11:
                return status;
            case 12:
                return  modifier;
            case 13:
                return orgJurd;
            case 14:
                return jurdAreaType;
            default:
                break;

        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 15;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch (i){
            case 0:
                roleId = o.toString();
                break;
            case 1:
                roleCode = o.toString();
                break;
            case 2:
                orgCode = o.toString();
                break;
            case 3:
                orgName = o.toString();
                break;
            case 4:
                roleName = o.toString();
                break;
            case 5:
                roleDesc = o.toString();
                break;
            case 6:
                roleType = o.toString();
                break;
            case 7:
                Scode = o.toString();
                break;
            case 8:
                sname = o.toString();
                break;
            case 9:
                Ts = (Date)o;
                break;
            case 10:
                Note = o.toString();
                break;
            case 11:
                status = o.toString();
                break;
            case 12:
                modifier = o.toString();
                break;
            case 13:
                orgJurd = o.toString();
                break;
            case 14:
                jurdAreaType = o.toString();
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
                propertyInfo.name = "roleId";
                break;
            case 1:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "roleCode";
                break;
            case 2:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "orgCode";
                break;
            case 3:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "orgName";
                break;
            case 4:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "roleName";
                break;
            case 5:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "roleDesc";
                break;
            case 6:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "roleType";
                break;
            case 7:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "Scode";
                break;
            case 8:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "sname";
                break;
            case 9:
                propertyInfo.type = PropertyInfo.OBJECT_CLASS;
                propertyInfo.name = "Ts";
                break;
            case 10:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "Note";
                break;
            case 11:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "status";
                break;
            case 12:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "modifier";
                break;
            case 13:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "orgJurd";
                break;
            case 14:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "jurdAreaType";
                break;
            default:
                break;
        }
    }
}
