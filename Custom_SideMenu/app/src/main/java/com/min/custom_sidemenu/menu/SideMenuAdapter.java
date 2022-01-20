package com.min.custom_sidemenu.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.min.custom_sidemenu.R;

import java.util.ArrayList;
import java.util.List;

import androidx.drawerlayout.widget.DrawerLayout;

public class SideMenuAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private DrawerLayout mDrawer;
    private int groupLayout = 0;
    private int childLayout = 0;
    private ArrayList<SideMenuItemGroup> itemList;
    private LayoutInflater myinf = null;

    public SideMenuAdapter(Context context, DrawerLayout mDrawer, int groupLayout, int childLayout, ArrayList<SideMenuItemGroup> itemList) {
        this.itemList = itemList;
        this.mDrawer = mDrawer;
        this.groupLayout = groupLayout;
        this.childLayout = childLayout;
        this.mContext = context;
        this.myinf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = myinf.inflate(this.groupLayout, parent, false);
        }
        TextView groupName = convertView.findViewById(R.id.menu_txt);
        groupName.setText(itemList.get(groupPosition).groupTxt);

        // 선택된 group에 효과 주기
        // child view 가 없는 경우에 사용 가능
        // child view가 없기 때문에 expandableListView 대신 recyclerview 사용해도 됨
//        convertView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        v.findViewById(R.id.menu_item_wrapper).setBackgroundColor(Color.parseColor("#f6f7fd"));
//                        ((TextView) v.findViewById(R.id.menu_txt)).setTextColor(Color.parseColor("#000000"));
//                        return true;
//                    case MotionEvent.ACTION_CANCEL:
//                        v.findViewById(R.id.menu_item_wrapper).setBackgroundColor(Color.parseColor("#ffffff"));
//                        ((TextView) v.findViewById(R.id.menu_txt)).setTextColor(Color.parseColor("#606060"));
//                        return true;
//                    case MotionEvent.ACTION_UP:
//                        v.findViewById(R.id.menu_item_wrapper).setBackgroundColor(Color.parseColor("#ffffff"));
//                        ((TextView) v.findViewById(R.id.menu_txt)).setTextColor(Color.parseColor("#606060"));
//                        return false;
//                }
//
//                return false;
//            }
//        });

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = myinf.inflate(this.childLayout, parent, false);
        }

        List<String> indexes = new ArrayList<>(itemList.get(groupPosition).childMap.keySet());
        String childKey = indexes.get(childPosition);

        TextView childName = convertView.findViewById(R.id.child_item_txt);
        childName.setText(itemList.get(groupPosition).childMap.get(childKey));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, groupPosition + " / " + childPosition, Toast.LENGTH_SHORT).show();
                mDrawer.closeDrawer(SideMenu.DRAWER_GRAVITY);
            }
        });
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<String> indexes = new ArrayList<>(itemList.get(groupPosition).childMap.keySet());
        String childKey = indexes.get(childPosition);

        return itemList.get(groupPosition).childMap.get(childKey);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return itemList.get(groupPosition).childMap.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return itemList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return itemList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
}