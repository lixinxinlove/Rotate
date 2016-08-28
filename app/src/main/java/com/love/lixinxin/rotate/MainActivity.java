package com.love.lixinxin.rotate;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private LinearLayout llPoint;
    private  List<Integer> listId;
    private  MyViewPagerApapter apapter;

    Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            handler.sendEmptyMessageDelayed(0,4000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

        for(int i=0;i<listId.size();i++){
            View view = new View(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            if(i!=0){
                params.leftMargin = 5;
            }
            view.setLayoutParams(params);
            view.setBackgroundResource(R.drawable.point_select);
            llPoint.addView(view);
        }

        apapter=new MyViewPagerApapter();
        viewPager.setAdapter(apapter);
        viewPager.setCurrentItem(Integer.MAX_VALUE/2-(Integer.MAX_VALUE/2%listId.size()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                updateIntroAndDot();
            }
            @Override
            public void onPageSelected(int position) {

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        handler.sendEmptyMessageDelayed(0,4000);
    }


    /**
     * 更新文本
     */
    private void updateIntroAndDot(){
        int currentPage = viewPager.getCurrentItem()%listId.size();
        for (int i = 0; i < llPoint.getChildCount(); i++) {
            llPoint.getChildAt(i).setEnabled(i==currentPage);
        }
    }
    private void initData() {
        listId=new ArrayList<>();
        listId.add(R.mipmap.image1);
        listId.add(R.mipmap.image2);
        listId.add(R.mipmap.image3);
        listId.add(R.mipmap.image4);
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        llPoint = (LinearLayout) findViewById(R.id.ll_point);
    }


    class MyViewPagerApapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view== object;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view=View.inflate(container.getContext(),R.layout.item_view_pager,null);
            ImageView imageView= (ImageView) view.findViewById(R.id.image);
            Glide.with(container.getContext()).load(listId.get(position%listId.size())).into(imageView);
            container.addView(view);
            return view;

        }
    }


}
