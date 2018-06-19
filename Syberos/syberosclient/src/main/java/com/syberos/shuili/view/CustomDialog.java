package com.syberos.shuili.view;

/**
 * Created by jidan on 18-3-16.
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.syberos.shuili.R;

import java.util.ArrayList;
import java.util.List;


public class CustomDialog extends Dialog {
    /**
     * eg: dialog 退出登录
     */
    public static final int STRING_DIALOG = 0;
    /**
     * eg: dialog字体选择
     */
    public static final int LIST_DIALOG = 1;
    /**
     * eg: dialog版本升级
     */
    public static final int HAS_TITLE_LIST_DIALOG = 2;
    private int dialogType;
    private Window window = null;
    private final TextView btnConfirm, tvSubTitle, tvUpdateContent;
    private final LinearLayout llUpdate;
    private final TextView tvTitle;
    private final TextView btnCancel;
    private final TextView tvContent;
    private final View viewList;
    private final View viewTitleList;
    private final TextView tvTitleListTitle;
    private final ListView lvList;
    private final ListView lvTitleList;
    private DialogListAdapter listAdapter;
    private Context context;
    private final RelativeLayout rlCancel;

    public CustomDialog(Context context) {
        super(context, R.style.dialog);
        this.context = context;
        // 设置触摸对话框意外的地方取消对话框
        setCanceledOnTouchOutside(true);

        this.setContentView(R.layout.dialog_view_layout);
        tvTitle = (TextView) findViewById(R.id.tv_title);

        llUpdate = (LinearLayout) findViewById(R.id.ll_update);
        tvSubTitle = (TextView) findViewById(R.id.tv_dialog_sub_title);
        tvUpdateContent = (TextView) findViewById(R.id.tv_update_content);

        btnConfirm = findViewById(R.id.btn_confirm);
        btnCancel = (TextView) findViewById(R.id.btn_cancel);
        tvContent = (TextView) findViewById(R.id.tv_content);
        viewList = (View) findViewById(R.id.dialog_list);
        viewTitleList = (View) findViewById(R.id.dialog_title_list);
        tvTitleListTitle = (TextView) findViewById(R.id.tv_dialog_title_list_title);
        lvList = (ListView) findViewById(R.id.lv_dialog_list_list);
        lvTitleList = (ListView) findViewById(R.id.lv_dialog_title_list_list);
        rlCancel = (RelativeLayout) findViewById(R.id.rl_cancel);
        rlCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


    }

    /**
     * 设置Dialog的大小和Dialog中ListView的自适应
     *
     * @param dialog 对话框
     * @param list   对话框中的ListView
     */
    public void setDialog(CustomDialog dialog, ListView list) {
        if (dialog != null) {
            //得到当前dialog对应的窗体
            Window dialogWindow = dialog.getWindow();
            //管理器
            WindowManager m = ((Activity) context).getWindowManager();
            //屏幕分辨率，获取屏幕宽、高用
            Display d = m.getDefaultDisplay();
            //获取对话框当前的参数值
            WindowManager.LayoutParams p = dialogWindow.getAttributes();
            //宽度设置为屏幕的0.8
            p.width = (int) (d.getWidth() * 1);
            //获取ListView的高度和当前屏幕的0.6进行比较，如果高，就自适应改变
            if (getTotalHeightofListView(list) > d.getHeight() * 0.6) {
                //得到ListView的参数值
                ViewGroup.LayoutParams params = list.getLayoutParams();
                //设置ListView的高度是屏幕的一半
                DisplayMetrics metrics = new DisplayMetrics();
                d.getMetrics(metrics);
                params.height = (int) (metrics.heightPixels * 0.5);
                //设置
                list.setLayoutParams(params);
            }
            //设置Dialog的高度
            dialogWindow.setAttributes(p);
        }
    }

    /**
     * 获取ListView的高度
     *
     * @param list listview内容列表
     * @return ListView的高度
     */
    public int getTotalHeightofListView(ListView list) {
        //ListView的适配器
        DialogListAdapter mAdapter = (DialogListAdapter) list.getAdapter();
        if (mAdapter == null) {
            return 0;
        }
        int totalHeight = 0;
        //循环适配器中的每一项
        for (int i = 0; i < mAdapter.getCount(); i++) {
            //得到每项的界面view
            View mView = mAdapter.getView(i, null, list);
            //得到一个view的大小
            mView.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            //总共ListView的高度
            totalHeight += mView.getMeasuredHeight();
        }
        return totalHeight;
    }

    private List<String> dataList = new ArrayList<>();
    private List<ItemDataAddCheckBox> cbDataList;

    public void showUpdate() {
        tvContent.setVisibility(View.GONE);
        llUpdate.setVisibility(View.VISIBLE);

    }

    public void setUpdate(String subTitle, String content) {
        tvSubTitle.setText(tvSubTitle.getText().toString() + subTitle);
        tvUpdateContent.setText(content);
    }

    class DialogListAdapter extends BaseAdapter {
        List<ItemDataAddCheckBox> list = new ArrayList<>();

        public void setData(List<ItemDataAddCheckBox> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            if (list != null) {
                return list.size();
            } else {
                return 0;
            }
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ListViewHolder holder;
            View view = null;
            if (view == null) {
                holder = new ListViewHolder();
                view = View.inflate(context, R.layout.dialog_list_item_view, null);
                // 2.初始化控件
                holder.textView = view.findViewById(R.id.tv);
                holder.checkBox = view.findViewById(R.id.cb);
                // 3.打一个tag标记
                view.setTag(holder);
            } else {
                view = convertView;
                holder = (ListViewHolder) convertView.getTag();
            }
            holder.textView.setText(list.get(position).getItemName());
            holder.checkBox.setChecked(list.get(position).isChecked);
            return view;
        }


    }

    class ListViewHolder {
        TextView textView;
        CheckBox checkBox;
    }

    public class ItemDataAddCheckBox {

        public ItemDataAddCheckBox(String itemName, boolean isChecked) {
            this.itemName = itemName;
            this.isChecked = isChecked;
        }

        private String itemName;
        private boolean isChecked;

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }
    }

    public DialogListItemClickedListener listItemClickedListener;

    public void initDialogType(int type) {
        switch (type) {
            case STRING_DIALOG:
                tvContent.setVisibility(View.VISIBLE);
                viewList.setVisibility(View.GONE);
                viewTitleList.setVisibility(View.GONE);
                llUpdate.setVisibility(View.GONE);
                break;
            case LIST_DIALOG:
                tvContent.setVisibility(View.GONE);
                viewList.setVisibility(View.VISIBLE);
                viewTitleList.setVisibility(View.GONE);
                llUpdate.setVisibility(View.GONE);


                break;
            case HAS_TITLE_LIST_DIALOG:
                tvContent.setVisibility(View.GONE);
                viewList.setVisibility(View.GONE);
                viewTitleList.setVisibility(View.VISIBLE);
                llUpdate.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    public interface DialogListItemClickedListener {
        /**
         * CustomDialog List item点击事件
         *
         * @param position
         */
        void onListItemClicked(int position);
    }

    public void setListData(List<String> listData, int checkIndex) {
        this.dataList = listData;
        if (listData != null) {
            cbDataList = convertData((ArrayList<String>) dataList);
            listAdapter = new DialogListAdapter();
            if (checkIndex < cbDataList.size()) {
                if (checkIndex != -1) {
                    cbDataList.get(checkIndex).isChecked = true;
                }
            }
            listAdapter.setData(cbDataList);
            lvList.setAdapter(listAdapter);
            setDialog(this, lvList);
            lvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                int currentNum = -1;

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //遍历list集合中的数据
                    for (ItemDataAddCheckBox item : cbDataList) {
                        //全部设为未选中
                        item.setChecked(false);
                    }
                    Log.i("CustomDialog", "onItemClick: dialog list item :"+position);
                    //选中
                    if (currentNum == -1) {
                        cbDataList.get(position).setChecked(true);
                        currentNum = position;
                        //同一个item选中变未选中
                    }
                    else if (currentNum == position) {
//                        选择后再次点击不可取消选择
//                        for (ItemDataAddCheckBox item : cbDataList) {
//                            item.setChecked(false);
//                        }
                        cbDataList.get(position).setChecked(true);
                        currentNum = position;
//                        currentNum = -1;
                        //不是同一个item选中当前的，去除上一个选中的
                    }
                    else if (currentNum != position) {
                        for (ItemDataAddCheckBox item : cbDataList) {
                            item.setChecked(false);
                        }
                        cbDataList.get(position).setChecked(true);
                        currentNum = position;
                    }
                    listAdapter.notifyDataSetChanged();//刷新adapter
                    listItemClickedListener.onListItemClicked(position);
                }
            });
            listAdapter.notifyDataSetChanged();
        }
    }

    public int getSelectedItem(List<String> listData) {
        String str = null;
        if (this.dataList == listData) {
            for (ItemDataAddCheckBox item : cbDataList
                    ) {
                if (item.isChecked) {
                    str = item.getItemName();
                }
            }

        }
        for (int i = 0; i < dataList.size(); i++) {

            if (str != null && dataList.get(i).equals(str)) {
                return i;
            }
        }
        return 0;
    }

    public List<ItemDataAddCheckBox> convertData(ArrayList<String> list) {
        List<ItemDataAddCheckBox> cbDataList = new ArrayList<>();
        for (String st : list) {
            cbDataList.add(new ItemDataAddCheckBox(st, false));
        }
        return cbDataList;
    }

    public void setItemClicked(DialogListItemClickedListener listItemClickedListener) {
        this.listItemClickedListener = listItemClickedListener;
    }

    public void setDialogOneBtn() {
        rlCancel.setVisibility(View.GONE);
        btnConfirm.setWidth(350);
    }

    public void setDialogMessage(String title, @Nullable String strBtnCancel, @Nullable String strBtnConfirm) {
        if (title != null) {
            tvTitle.setText(title);
        }
        if (strBtnConfirm != null) {
            btnConfirm.setText(strBtnConfirm);
        }
        if (strBtnCancel != null) {
            if ("".equals(strBtnCancel)) {
                btnCancel.setVisibility(View.GONE);
            } else {
                btnCancel.setText(strBtnCancel);
            }
        }
    }
    @Override
    public void show() {
        windowDeploy(0, 0);
        super.show();
    }

    public void setOnConfirmClickListener(View.OnClickListener l) {
        btnConfirm.setOnClickListener(l);
    }

    public void setMessage(CharSequence msg) {
        tvContent.setText(msg.toString());
    }

    /**
     * dialog按设置参数的偏移量来显示，窗口外部的背景透明
     *
     * @param x x轴偏移量
     * @param y y轴
     */
    public void showDialog(int x, int y) {
        windowDeploy(x, y);
        show();
    }


    /**
     * 设置窗口显示
     *
     * @param x
     * @param y
     */
    public void windowDeploy(int x, int y) {
        // 得到对话框
        window = getWindow();
        window.setGravity(Gravity.CENTER);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        // }

        WindowManager.LayoutParams wl = window.getAttributes();
        // 根据x，y坐标设置窗口需要显示的位置
        // x小于0左移，大于0右移
        wl.x = x;
        // y小于0上移，大于0下移
        wl.y = y;
        // lp.width = 300; // 宽度
        // lp.height = 300; // 高度
        // lp.alpha = 0.7f; // 透明度
        wl.dimAmount = 0.6f;
        // wl.gravity = Gravity.BOTTOM; //设置重力
        window.setAttributes(wl);
    }

    public int getDialogType() {
        return dialogType;
    }

    public void setDialogType(int dialogType) {
        this.dialogType = dialogType;
    }
}

