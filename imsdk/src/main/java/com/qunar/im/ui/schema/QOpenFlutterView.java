package com.qunar.im.ui.schema;

import android.content.Intent;
import android.net.Uri;

import com.qunar.im.protobuf.common.CurrentPreference;
import com.qunar.im.ui.activity.FlutterMedalActivity;
import com.qunar.im.ui.activity.IMBaseActivity;
import com.qunar.im.ui.activity.PbChatActivity;
import com.qunar.im.ui.activity.TabMainActivity;
import com.qunar.im.ui.util.ReflectUtil;

import java.util.Map;

public class QOpenFlutterView implements QChatSchemaService {
    public final static QOpenFlutterView instance = new QOpenFlutterView();

//    private final static String[] devs = new String[]{"hubin.hu@" + QtalkNavicationService.getInstance().getXmppdomain(), "hubo.hu@" + QtalkNavicationService.getInstance().getXmppdomain(), "lihaibin.li@" + QtalkNavicationService.getInstance().getXmppdomain()};//ejabhost1

    //这个方法后面都要返回false , 否则会出现白屏
    @Override
    public boolean startActivityAndNeedWating(final IMBaseActivity context, Map<String, String> map) {


        context.startActivity(  FlutterMedalActivity.makeIntent(context,map.get(PbChatActivity.KEY_JID)));
//        Intent intent = new Intent(context,FlutterMedalActivity.class);
//        intent.putExtra(FlutterMedalActivity.USERID, map.get(PbChatActivity.KEY_JID));
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(intent);
        return false;
    }
}