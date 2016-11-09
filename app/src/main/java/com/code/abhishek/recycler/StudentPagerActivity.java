package com.code.abhishek.recycler;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StudentPagerActivity extends FragmentActivity {
    private static final String STUDENT_ID =
            "com.code.abhishek.recycler.sId";
    private DatabaseHelper hh;
    private ViewPager mViewPager;
   private List<Student> mStudent;

    public static Intent newIntent(Context packageContext, UUID sId) {
        Intent intent = new Intent(packageContext, StudentPagerActivity.class);
        intent.putExtra(STUDENT_ID, sId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_pager);
        hh=new DatabaseHelper(this);
        UUID sId = (UUID) getIntent()
                .getSerializableExtra(STUDENT_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_student_pager);
        //mStudent=hh.getDataFromDB();
        //mStudent = StudentLab.get(this).getData();
         mStudent=hh.getData();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Student stt = mStudent.get(position);
                return StudentFragment.newInstance(stt.getsId());
            }

            @Override
            public int getCount() {
                return mStudent.size();
            }
        });

        for (int i = 0; i < mStudent.size(); i++) {
            if (mStudent.get(i).getsId().equals(sId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

}

