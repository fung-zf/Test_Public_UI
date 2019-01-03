package com.xzkydz.function.data.uncomplete.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.xzkydz.function.data.view.DataMagerActivity;
import com.xzkydz.function.data.widget.TaskAdater;
import com.xzkydz.function.mylibrary.R;
import com.xzkydz.function.search.module.ITaskRoot;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

import static com.xzkydz.function.app.KyApp.allTaskFragment;

public class UnCompleteFragment extends Fragment {

    private ListView listView;
    private DataMagerActivity mActivity;
    public List<ITaskRoot> thisShowTaskList = new ArrayList<>();
    public TaskAdater taskAdater;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (DataMagerActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_uncomplete_task, null);
        initView(view);
        taskAdater = new TaskAdater(mActivity, thisShowTaskList);
        listView.setAdapter(taskAdater);
        initEvent();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    private void initEvent() {
        // 测试
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mActivity.etInputKey.getWindowToken(), 0); //强制隐藏键盘

                String[] items = {"查看测试数据", "删除测试任务"};
                String[] items2 = {"查看测试数据", "任务状态设为已完成", "删除测试任务"};
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                //设置标题
                builder.setTitle("");
                /*设置图标*/
                builder.setIcon(R.drawable.vector_drawable_icon);
                if (thisShowTaskList.get(position).getTaskStatue()) {
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int j) {

                            switch (j) {
                                case 0:
                                    Intent intent = new Intent();
                                    intent.putExtra("TASKID", thisShowTaskList.get(position).getTaskId());
                                    intent.setClass(mActivity, mActivity.getDataDetailActivity());
                                    startActivity(intent);
                                    break;
                                case 1:
                                    mActivity.initPopupWindow(thisShowTaskList.get(position), false);
                                    break;
                            }
                        }
                    });
                } else {
                    builder.setItems(items2, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int j) {
                            switch (j) {
                                case 0:
                                    Intent intent = new Intent();
                                    intent.putExtra("TASKID", thisShowTaskList.get(position).getTaskId());
                                    intent.setClass(mActivity, mActivity.getDataDetailActivity());
                                    startActivity(intent);
                                    break;
                                case 1:
                                    if (mActivity.setTaskComplete(thisShowTaskList.get(position))) {
                                        mActivity.getDataList(mActivity.allTaskList);
                                        allTaskFragment.pushListView();
                                        thisShowTaskList.remove(thisShowTaskList.get(position));
                                        pushListView();
                                        Toasty.success(mActivity, "任务状态设为已完成。", 3).show();

                                    }
                                    break;
                                case 2:
                                    mActivity.initPopupWindow(thisShowTaskList.get(position), false);
                                    break;
                            }
                        }
                    });
                }

                builder.create();
                builder.show();

            }
        });
    }


    private void initView(View view) {
        listView = view.findViewById(R.id.listview);
    }

    public void pushListView(List<ITaskRoot> iTaskRoots) {
        thisShowTaskList.clear();
        thisShowTaskList.addAll(iTaskRoots);
        if (taskAdater != null)
            taskAdater.notifyDataSetChanged();
        Log.d("asdfghjk", "iTaskRoots.size(): " + iTaskRoots.size());
    }


    /**
     * 初始化界面
     */
    public void pushListView() {
        taskAdater.notifyDataSetChanged();
    }

}
