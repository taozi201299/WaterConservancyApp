package com.syberos.shuili.service.dataprovider.sync;

import android.os.Bundle;
import android.os.Handler;

import com.syberos.shuili.service.UserInformationEntity;
import com.syberos.shuili.service.dao.DBHelper;
import com.syberos.shuili.service.dataprovider.dbconfig.def.DataOperationType;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jidan on 18-3-8.
 * ISyncManager
 */
public interface ISyncManager {

     void initTables(DBHelper helper);
     void syncUserInfo(Bundle bundle);
     void setDBAdapter(DBHelper helper);
     void syncMessage(Bundle bundle);
     void setHandler(Handler handler);
     void deleteInfo(List<String> ids, DataOperationType type);
     void setInfoStatus(List<String> messageIds,DataOperationType type);
}

