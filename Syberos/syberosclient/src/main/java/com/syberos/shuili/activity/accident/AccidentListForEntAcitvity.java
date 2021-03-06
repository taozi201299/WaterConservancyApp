package com.syberos.shuili.activity.accident;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.google.gson.Gson;
import com.shuili.callback.ErrorInfo;
import com.shuili.callback.RequestCallback;
import com.syberos.shuili.R;
import com.syberos.shuili.SyberosManagerImpl;
import com.syberos.shuili.activity.dangermanagement.InvestigationEngineForEntActivity;
import com.syberos.shuili.base.BaseActivity;
import com.syberos.shuili.config.BusinessConfig;
import com.syberos.shuili.config.GlobleConstants;
import com.syberos.shuili.entity.accident.ObjAcci;
import com.syberos.shuili.entity.basicbusiness.OrgInfo;
import com.syberos.shuili.entity.common.DicInfo;
import com.syberos.shuili.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by jidan on 18-5-22.
 * 企事业版本快报事故activity
 */

public class AccidentListForEntAcitvity extends BaseActivity implements View.OnClickListener,AdapterView.OnItemClickListener{

   private final String TAG = AccidentListForEntAcitvity.class.getSimpleName();
   private final String Title = "快报事故";
    /**
     * accident unit type
     */
   private DicInfo m_unitTypeDic;
    /**
     * accident type
     */
   private DicInfo m_accidentTypeDic;
   private ArrayList<ObjAcci> reportInfos = new ArrayList<>();
    /**
     * accident task object
     */
   private ObjAcci objAccis;
    ArrayList<ObjAcci>datas = new ArrayList<>();
    /**
     * unit information
     */
   private OrgInfo orgInfo;

   private AccidentListAdapter accidentListAdapter;

    /**
     * view obj
     */
    @BindView(R.id.xRefreshView)
    XRefreshView xRefreshView;
    @BindView(R.id.stickListHeadersListView)
    StickyListHeadersListView stickyListHeadersListView;
    @BindView(R.id.ll_commit)
    LinearLayout ll_commit;

   public static final String SEND_BUNDLE_KEY = "ObjAcci";
   public static final String DIC_UNIT_KEY = "dicUnitKey";
   public static final String DIC_ACCIDENT_KEY = "dicAccidentKey";

   private int iSucessCount = 0;
   private int iFailedCount = 0;




    @Override
    public int getLayoutId() {
        return R.layout.activity_enterprise_express_accident_stickheader_list;
    }
    @Override
    public void initListener() {
        ll_commit.setOnClickListener(this);
        stickyListHeadersListView.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        datas.clear();
        reportInfos.clear();
        iSucessCount = 0;
        iFailedCount = 0;
        showDataLoadingDialog();
        getAccidentUnitType();
    }

    @Override
    public void initView() {
        BusinessConfig.saveLog2Server(GlobleConstants.IConstants.Acci_Ent);
        setInitActionBar(true);
        setActionBarRightVisible(View.INVISIBLE);
        showTitle(Title);
        accidentListAdapter =  new AccidentListAdapter(this);
        stickyListHeadersListView.setAdapter(accidentListAdapter);
        xRefreshView.setPullRefreshEnable(true);
        xRefreshView.setPullLoadEnable(false);
        xRefreshView.setMoveHeadWhenDisablePullRefresh(false);
        xRefreshView.setDividerPadding(20);
        xRefreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                initData();
            }

            @Override
            public void onRefresh(boolean isPullDown) {
            }

            @Override
            public void onLoadMore(boolean isSilence) {

            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });

    }

    /**
     * 获取事故单位类型字典
     */
    private void getAccidentUnitType(){
        String url =  GlobleConstants.strIP + "/sjjk/v1/jck/dic/dicDpc/dicRelDpcAtt/";
        urlTags.add(url);
        HashMap<String,String> params = new HashMap<>();
        params.put("attTabCode","OBJ_ACCI");
        params.put("attColCode","ACCI_WIUN_TYPE");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                m_unitTypeDic  = gson.fromJson(result,DicInfo.class);
                if(m_unitTypeDic == null || m_unitTypeDic.dataSource == null || m_unitTypeDic.dataSource.size() == 0){
                    closeDataDialog();
                    xRefreshView.stopRefresh(false);
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }else {
                    getAcciCate();
                }
            }
            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                xRefreshView.stopRefresh(false);
                ToastUtils.show(errorInfo.getMessage());


            }
        });
    }

    /**
     * 获取事故类别字典
     */
    private void getAcciCate(){
        String url =  GlobleConstants.strIP + "/sjjk/v1/jck/dic/dicDpc/dicRelDpcAtt/";
        urlTags.add(url);
        HashMap<String,String>params = new HashMap<>();
        params.put("attTabCode","OBJ_ACCI");
        params.put("attColCode","ACCI_CATE");
        SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                Gson gson = new Gson();
                m_accidentTypeDic  = gson.fromJson(result,DicInfo.class);
                if(m_accidentTypeDic == null  || m_accidentTypeDic.dataSource == null ||
                        m_accidentTypeDic.dataSource.size() == 0){
                    closeDataDialog();
                    xRefreshView.stopRefresh();
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                }else {
                    getAccidentList();
                }
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                xRefreshView.stopRefresh();
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }

    /**
     * 获取快报事故列表
     */
    private void getAccidentList(){
        String url =  GlobleConstants.strIP + "/sjjk/v1/bis/obj/getAccidentManagements/";
        urlTags.add(url);
        HashMap<String,String>param = new HashMap<>();
        param.put("acciWiunGuid", SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgId());
        SyberosManagerImpl.getInstance().requestGet_Default(url, param, url, new RequestCallback<String>() {
            @Override
            public void onResponse(String result) {
                closeDataDialog();
                Gson gson = new Gson();
                objAccis = gson.fromJson(result,ObjAcci.class);
                if(objAccis == null || !objAccis.code.equals("0")){
                    ToastUtils.show(ErrorInfo.ErrorCode.valueOf(-5).getMessage());
                    xRefreshView.stopRefresh(false);
                }else {
                    xRefreshView.stopRefresh(true);
                    parasAccidentInformation();
                    mergeData();
                    refreshUI();
                }
            }

            @Override
            public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                closeDataDialog();
                xRefreshView.stopRefresh(false);
                ToastUtils.show(errorInfo.getMessage());

            }
        });
    }
    /**
     * 事故状态 快报 补报
     * 1 pid 不为空，该数据为补报
     * 2 pid 为空 ，通过上报状态 判断是上报 还是未上报
     * 3 已快报 1 未快报2
     *
     */
    private void parasAccidentInformation(){
        for(ObjAcci item : objAccis.dataSource){
            item.setAccidentUnitName(SyberosManagerImpl.getInstance().getCurrentUserInfo().getOrgName());
            if(item.getPID() != null && !item.getPID().isEmpty()){
                item.setRepStat("1");
                reportInfos.add(item);
            }
        }
    }
    /**
     * 获取事故单位名称
     */
    private void getAccidentUnitInfo(){
        String url =  GlobleConstants.strIP + "/sjjk/v1/att/org/base/attOrgBases/";
        HashMap<String,String>params = new HashMap<>();
        for(final ObjAcci item : objAccis.dataSource){
            if(iFailedCount > 0)break;
            params.put("guid",item.getAcciWindGuid());
            SyberosManagerImpl.getInstance().requestGet_Default(url, params, url, new RequestCallback<String>() {
                @Override
                public void onResponse(String result) {
                    Gson gson = new Gson();
                    orgInfo = gson.fromJson(result,OrgInfo.class);
                    if(orgInfo != null && orgInfo.dataSource != null && orgInfo.dataSource.size() > 0){
                        item.setAccidentUnitName(orgInfo.dataSource.get(0).getOrgName());
                        iSucessCount ++;
                    }
                    if(iSucessCount  == objAccis.dataSource.size() ) {
                        closeDataDialog();

                    }
                }

                @Override
                public void onFailure(ErrorInfo.ErrorCode errorInfo) {
                    iFailedCount ++;
                    closeDataDialog();
                    ToastUtils.show(errorInfo.getMessage());

                }
            });
        }
    }
    private void mergeData(){
        DicInfo info = null;
        for(ObjAcci item: objAccis.dataSource){
            info = getUnitTypeItem(item.getAcciWiunType());
            if(info != null){
                item.setAcciWinunTypeName(info.getDcItemName());
            }
            info = getAccidentTypeItem(item.getAcciCate());
            if(info != null){
                item.setAcciCateName(info.getDcItemName());
            }
        }

    }
    private DicInfo getAccidentTypeItem(String dicCode){
        for(DicInfo dicInfo :m_accidentTypeDic.dataSource){
            if(dicInfo.getDcItemCode().equals(dicCode)){
                return dicInfo;
            }
        }
        return new DicInfo();
    }
    private DicInfo getUnitTypeItem(String dicCode){
        for(DicInfo dicInfo :m_unitTypeDic.dataSource){
            if(dicInfo.getDcItemCode().equals(dicCode)){
                return dicInfo;
            }
        }
        return new DicInfo();
    }
    private void refreshUI(){
        datas.clear();
        datas.addAll(objAccis.dataSource);
        for(ObjAcci item : objAccis.dataSource){
            if(item.getPID() != null && !item.getPID().isEmpty()){
                datas.remove(item);
            }if(String.valueOf(GlobleConstants.reportAcci_3).equals(item.getRepStat())){
                datas.remove(item);
            }
        }
        accidentListAdapter.setData(datas);
        accidentListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_commit:
                addNewAccident();
                break;
        }
    }
    private void addNewAccident(){
        Bundle bundle = new Bundle();
        bundle.putSerializable(DIC_UNIT_KEY,m_unitTypeDic);
        bundle.putSerializable(DIC_ACCIDENT_KEY,m_accidentTypeDic);
        bundle.putInt("type",GlobleConstants.NEW_ACCI);
        intentActivity(this, AccidentNewFormForEntActivity.class,false,bundle);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    public class AccidentListAdapter extends BaseAdapter implements StickyListHeadersAdapter,SectionIndexer {
        List<ObjAcci> tasks;
        private ObjAcci[] sectionIndices;
        private String[] sectionHeaders;
        HashMap<String,Integer> map = new HashMap<>();

        private Context mContext;
        public AccidentListAdapter(Context mContext){
            this.mContext = mContext;

        }
        public void setData(List<ObjAcci> data){
            if(data == null) return;
            map.clear();
            this.tasks = data;
            sectionIndices =getSectionIndices();
            if(sectionIndices == null)return;
            sectionHeaders = getSectionHeaders();
            notifyDataSetChanged();
        }

        @Override
        public View getHeaderView(int position, View convertView, ViewGroup parent) {
           HeadViewHolder viewHolder;
           if(convertView != null){
               viewHolder = (HeadViewHolder)convertView.getTag();
           }else{
               convertView = LayoutInflater.from(mContext).inflate(R.layout.item_parent_layout,null);
               viewHolder = new HeadViewHolder(convertView);
               convertView.setTag(viewHolder);
           }
           viewHolder.mHeadText.setText(""+tasks.get(position).getAccidentUnitName());
           viewHolder.mHeadText.setVisibility(View.GONE);
            
            return convertView;
        }

        @Override
        public long getHeaderId(int position) {
            return map.get(tasks.get(position).getAccidentUnitName());
        }

        @Override
        public int getCount() {
            return tasks == null ?0:tasks.size();
        }

        @Override
        public Object getItem(int position) {
            return tasks.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView != null){
                viewHolder = (ViewHolder)convertView.getTag();
            }else{
                convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_enterprises_express_accident_list_item,null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }

            final ObjAcci accidentInformation
                    = tasks.get(position);
            viewHolder.tv_name.setText(accidentInformation.getAccidentUnitName());
            viewHolder.tv_time.setText(accidentInformation.getCollTime());
            viewHolder.tv_title.setText(accidentInformation.getAcciCateName());
            String grade = accidentInformation.getAcciGrad() == null ?"1":accidentInformation.getAcciGrad();
            int type = Integer.valueOf(grade);
            switch (type) {
                case GlobleConstants.TYPE_NORMAL: {
                    viewHolder.tv_type.setText(R.string.accident_type_normal);
                    viewHolder.ll_type.setBackgroundResource(R.drawable.btn_accident_type_normal_shape);
                }
                break;
                case GlobleConstants.TYPE_BIG: {
                    viewHolder.tv_type.setText(R.string.accident_type_big);
                    viewHolder.ll_type.setBackgroundResource(  R.drawable.btn_accident_type_big_shape);
                }
                break;
                case GlobleConstants.TYPE_BIGGER: {
                    viewHolder.tv_type.setText(R.string.accident_type_bigger);
                    viewHolder.ll_type.setBackgroundResource(R.drawable.btn_accident_type_bigger_shape);
                }
                break;
                case GlobleConstants.TYPE_LARGE: {
                    viewHolder.tv_type.setText(R.string.accident_type_large);
                    viewHolder.ll_type.setBackgroundResource(R.drawable.btn_accident_type_large_shape);
                }
                break;
                default:
                    viewHolder.tv_type.setText(R.string.accident_type_normal);
                    viewHolder.ll_type.setBackgroundResource(R.drawable.btn_accident_type_normal_shape);
                    break;
            }
            String repStatus = accidentInformation.getRepStat() == null ? "2" : accidentInformation.getRepStat();
            type = Integer.valueOf(repStatus);
            // 0 未上报  1 2 已上报  3 非快报事故
            switch (type) {
                case GlobleConstants.reportAcci_0:
                    viewHolder.btn_report.setText("快报");
                    break;
                case GlobleConstants.reportAcci_1:
                case GlobleConstants.reportAcci_2:
                    viewHolder.btn_report.setText("补报");
                    break;
            }
            final int finalType = type;
            viewHolder.btn_report.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(position >=tasks.size())return;
                    Bundle bundle = new Bundle();
                    ObjAcci accidentInformation = tasks.get(position);
                    bundle.putSerializable(SEND_BUNDLE_KEY, accidentInformation);
                    bundle.putSerializable(DIC_ACCIDENT_KEY,m_accidentTypeDic);
                    bundle.putSerializable(DIC_UNIT_KEY,m_unitTypeDic);
                    bundle.putInt("type", finalType);
                    intentActivity(AccidentListForEntAcitvity.this,
                            AccidentNewFormForEntActivity.class,
                            false, bundle);
                }
            });
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(position >=tasks.size())return;
                    Bundle bundle = new Bundle();
                    ObjAcci item = tasks.get(position);
                    bundle.putSerializable(SEND_BUNDLE_KEY, item);
                    bundle.putSerializable("data",reportInfos);
                    intentActivity(AccidentListForEntAcitvity.this,
                            AccidentDetailActivity.class,
                            false, bundle);
                }
            });
            return convertView;
        }
        private ObjAcci[] getSectionIndices() {
            if(tasks == null || tasks.size() == 0) return null;
            List<ObjAcci> sectionIndices = new ArrayList<ObjAcci>();
            String accidentUnitName = tasks.get(0).getAccidentUnitName();
            sectionIndices.add(tasks.get(0));
            map.put(accidentUnitName,0);
            for (int i = 1; i < tasks.size(); i++) {
                String item = tasks.get(i).getAccidentUnitName() ;
                if (!item.equals(accidentUnitName)) {
                    accidentUnitName = item;
                    sectionIndices.add(tasks.get(i));
                    map.put(accidentUnitName,i);

                }
            }
            ObjAcci[] sections = new ObjAcci[sectionIndices.size()];
            for (int i = 0; i < sectionIndices.size(); i++) {
                sections[i] = sectionIndices.get(i);
            }
            return sections;
        }

        private String[] getSectionHeaders() {
            String[] sectionHeaders = new String[sectionIndices.length];
            for (int i = 0; i < sectionIndices.length; i++) {
                sectionHeaders[i] = ((ObjAcci)sectionIndices[i]).getAccidentUnitName();
            }
            return sectionHeaders;
        }

        @Override
        public Object[] getSections() {
            return sectionHeaders;
        }
        @Override
        public  int  getPositionForSection(int sectionIndex) {
            for (int i = 0; i < getCount(); i++) {
                String unitName = tasks.get(i).getAccidentUnitName();
                int id = map.get(unitName);
                if (id == sectionIndex) {
                    return i;
                }
            }
            return -1;
        }

        @Override
        public int getSectionForPosition(int position) {
            return position;
        }

        class ViewHolder {
            @BindView(R.id.ll_type)
            LinearLayout ll_type;
            @BindView(R.id.tv_type)
            TextView tv_type;
            @BindView(R.id.tv_title)
            TextView tv_title;
            @BindView(R.id.tv_time)
            TextView tv_time;
            @BindView(R.id.tv_name)
            TextView tv_name;
            @BindView(R.id.btn_report)
            Button btn_report;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }


        class HeadViewHolder {
            @BindView(R.id.headText)
            TextView mHeadText;

            HeadViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
