package com.min.custom_sidemenu.menu;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ExpandableListView;

import com.google.android.material.navigation.NavigationView;
import com.min.custom_sidemenu.R;

import java.util.ArrayList;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.drawerlayout.widget.DrawerLayout.SimpleDrawerListener;

public class SideMenu {
    // 메뉴 열리는 방향
    public static final int DRAWER_GRAVITY = GravityCompat.START;

    private Context mContext;
    private Activity mActivity;
    private DrawerLayout mDrawer;
    private NavigationView mNav;
    private SideMenuAdapter sideMenuAdapter;
    private ExpandableListView listView;

    public SideMenu(Context context) {
        this.mContext = context;
        this.mActivity = ((Activity) mContext);

        setDrawer();
    }

    private void setDrawer() {
        mDrawer = mActivity.findViewById(R.id.drawer_layout);
        mNav = mActivity.findViewById(R.id.nav_view);
        initSideMenuList();

        mActivity.findViewById(R.id.side_menu_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 사이드메뉴 보기
                mDrawer.openDrawer(DRAWER_GRAVITY);
            }
        });
        // 사이드 메뉴가 열릴 때
        // listener가 필요없으면 닫아줘야함
        mDrawer.addDrawerListener(new SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // expandableListView에 열려있는 group 모두 닫음
                for(int i = 0; i < sideMenuAdapter.getGroupCount(); i++)  {
                    listView.collapseGroup(i);
                }
            }
        });
    }

    private void initSideMenuList() {
        listView = mActivity.findViewById(R.id.side_menu_list);
        sideMenuAdapter = new SideMenuAdapter(mContext, mDrawer,
                R.layout.side_menu_item, R.layout.side_menu_item_child, initGroupItems());
        listView.setAdapter(sideMenuAdapter);
    }

    // 사이드메뉴 group data 초기화 예시
    private ArrayList<SideMenuItemGroup> initGroupItems() {
        String[] groupTitles = {"group1", "group2", "group3"};
        String[] childTitles = {"child1", "child2", "child3"};
        ArrayList<SideMenuItemGroup> sideMenuItems = new ArrayList<>();

        for (int i = 0; i < groupTitles.length; i++) {
            String title = groupTitles[i];
            sideMenuItems.add(new SideMenuItemGroup(title));
            sideMenuItems.get(i).childMap.put(i + "", childTitles[i]);
        }

        return sideMenuItems;
    }

}