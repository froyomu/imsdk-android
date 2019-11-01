package com.qunar.im.ui.view.baseView.processor;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qunar.im.base.jsonbean.MedalRemindDataBean;
import com.qunar.im.base.jsonbean.RemindDataBean;
import com.qunar.im.base.module.IMMessage;
import com.qunar.im.base.module.WorkWorldItem;
import com.qunar.im.base.util.JsonUtils;
import com.qunar.im.protobuf.common.CurrentPreference;
import com.qunar.im.ui.activity.FlutterMedalActivity;
import com.qunar.im.ui.activity.PbChatActivity;
import com.qunar.im.ui.activity.TabMainActivity;
import com.qunar.im.ui.activity.WorkWorldDetailsActivity;
import com.qunar.im.ui.view.baseView.ClickRemindView;
import com.qunar.im.ui.view.baseView.IMessageItem;
import com.qunar.im.ui.view.baseView.MedalClickRemindView;
import com.qunar.im.ui.view.baseView.ViewPool;
import com.qunar.im.utils.ConnectionUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import static com.qunar.im.ui.activity.WorkWorldDetailsActivity.WORK_WORLD_DETAILS_ITEM;

public class MedalRemindProcessor extends DefaultMessageProcessor {

    @Override
    public void processChatView(ViewGroup parent, final IMessageItem item) {
        IMMessage message = item.getMessage();
        final Context context = item.getContext();
        try {
            MedalRemindDataBean meetingDataBean = JsonUtils.getGson().fromJson(message.getExt()
                    , MedalRemindDataBean.class);
            MedalClickRemindView meetingRemindView = ViewPool.getView(MedalClickRemindView.class, context);
            SpannableStringBuilder sb = new SpannableStringBuilder();
            String[] strs = meetingDataBean.getStrMap().getAllStr().split(meetingDataBean.getStrMap().getHighlightStr());
            Spanned color = Html.fromHtml("<font color='#00cabe'>" + meetingDataBean.getStrMap().getHighlightStr() + "</font>");

            if (strs.length > 1) {
                sb.append(strs[0]);
                sb.append(color);
                sb.append(strs[1]);
            }
            meetingRemindView.setSB(sb);


            meetingRemindView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    context.startActivity(  FlutterMedalActivity.makeIntent(context,CurrentPreference.getInstance().getPreferenceUserId()));
//                    Intent intent = new Intent(context, FlutterMedalActivity.class);
//                    //填写用户id
//                    intent.putExtra(FlutterMedalActivity.USERID, CurrentPreference.getInstance().getPreferenceUserId());
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(intent);
                }
            });
            parent.setVisibility(View.VISIBLE);
            parent.addView(meetingRemindView);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
