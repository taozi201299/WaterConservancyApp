package com.syberos.shuili.entity.userinfo;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

/**
 * Created by Administrator on 2018/4/14.
 */

public class UserExtendInfo implements KvmSerializable {
    public String admDutyLevel;
    public String depCode;
    public String depId;
    public String depName;
    public String id ;
    public String modifier;
    public String note;
    public String orgCode;
    public String orgId;
    public String orgName;
    public String password;
    public String persId;
    public String persName;
    public String persType;
    public String phone;
    public RoleExtInfoList roleExtInfoList;
    public String status;
    public String ts;
    public String userCode;
    public String userName;
    public String userType;

    @Override
    public Object getProperty(int arg0) {
        switch (arg0) {
            case 0:
                return admDutyLevel;
            case 1:
                return depCode;
            case 2:
                return depId;
            case 3:
                return depName;
            case 4:
                return id;
            case 5:
                return modifier;
            case 6:
                return note;
            case 7:
                return orgCode;
            case 8:
                return orgId;
            case 9:
                return orgName;
            case  10:
                return password;
            case 11:
                return persId;
            case 12:
                return persName;
            case 13:
                return persType;
            case 14:
                return phone;
            case 15:
                return roleExtInfoList;
            case 16:
                return status;
            case 17:
                return ts;
            case 18:
                return userCode;
            case 19:
                return userName;
            case 20:
                return userType;
            default:
                break;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 21;
    }


    @Override
    public void setProperty(int arg0, Object arg1) {
        switch (arg0) {
            case 0:
                admDutyLevel = arg1.toString();
                break;
            case 1:
                depCode = arg1.toString();
                break;
            case 2:
                depId =  arg1.toString();
                break;
            case 3:
                depName = arg1.toString();
                break;
            case 4:
                id = arg1.toString();
                break;
            case 5:
                modifier = arg1.toString();
                break;
            case 6:
                note = arg1.toString();
                break;
            case 7:
                orgCode = arg1.toString();
                break;
            case 8:
                orgId = arg1.toString();
                break;
            case 9:
                orgName = arg1.toString();
                break;
            case 10:
                password = arg1.toString();
                break;
            case 11:
                persId = arg1.toString();
                break;
            case 12:
                persName = arg1.toString();
                break;
            case 13:
                persType = arg1.toString();
                break;
            case 14:
                phone = arg1.toString();
                break;
            case 15:
                roleExtInfoList = (RoleExtInfoList) arg1;
                break;
            case 16:
                status = arg1.toString();
                break;
            case 17:
                ts = arg1.toString();
            case 18:
                userCode = arg1.toString();
                break;
            case 19:
                userName = arg1.toString();
                break;
            case 20:
                userType = arg1.toString();
                break;
            default:
                break;
        }

    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch (i) {
            case 0:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "admDutyLevel";
                break;
            case 1:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "depCode";
                break;
            case 2:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "depId";
                break;
            case 3:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "depName";
                break;
            case 4:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "id";
                break;
            case 5:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "modifier";
                break;
            case 6:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "note";
                break;
            case 7:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "orgCode";
                break;
            case 8:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "orgId";
                break;
            case 9:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "orgName";
                break;
            case 10:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "password";
                break;
            case 11:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "persId";
                break;
            case 12:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "persName";
                break;
            case 13:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "persType";
                break;
            case 14:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "phone";
                break;
            case 15:
                propertyInfo.type = PropertyInfo.VECTOR_CLASS;
                propertyInfo.name = "roleExtInfoList";
                break;
            case 16:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "status";
                break;
            case 17:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "ts";
                break;
            case 18:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "userCode";
                break;
            case 19:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "userName";
                break;
            case 20:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "userType";
                break;
            default:
                break;
        }
    }
}
