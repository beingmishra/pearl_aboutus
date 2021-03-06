package com.pearl.about.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pearl.about.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class DialogView extends DialogFragment {

    Toast mTapToast = null;
    int mHitCount = 50;
    int ImgId;
    String github, telegram, xdalink, desc, name;

    public DialogView() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Bundle bundle = getArguments();

        ImgId = bundle.getInt("img");
        github = bundle.getString("github");
        telegram = bundle.getString("telegram");
        xdalink = bundle.getString("xdalink");
        desc = bundle.getString("desc");

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final ViewGroup nullParent = null;
        View view = null;
        if (inflater != null) {
            view = inflater.inflate(R.layout.dialog0, nullParent);
        }

        alertDialogBuilder.setView(view);
        alertDialogBuilder.setCancelable(false);
        final AlertDialog dialog = alertDialogBuilder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        TextView description = null;
        ImageView git = null;
        ImageView tg = null;
        ImageView xda = null;
        CircleImageView oveno = null;
        CircleImageView icon = null;

        if (view != null) {
            git = view.findViewById(R.id.github);
            tg = view.findViewById(R.id.telegram);
            xda = view.findViewById(R.id.xda);
            description = view.findViewById(R.id.txt_dia);
            icon = view.findViewById(R.id.icon);

        }

        if (ImgId == R.drawable.dev3) {
            oveno = icon;
        }

        description.setText(desc);
        icon.setImageResource(ImgId);

        if (git != null && tg != null && xda != null) {
            git.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setLink(github);
                }
            });

            tg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setLink(telegram);
                }
            });

            xda.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setLink(xdalink);
                }
            });

        }

        if (oveno != null) {
            oveno.setImageResource(ImgId);
            oveno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TriggerEasterEgg();
                }
            });
        }

        return dialog;
    }

    @Override
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);

        }
        super.onDestroyView();
    }

    private void setLink(final String link) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://" + link));
        startActivity(intent);
    }

    private void TriggerEasterEgg() {
        mHitCount--;
        if (mHitCount == 45) {
            if (mTapToast != null) {
                mTapToast.cancel();
            }
            Toast.makeText(getContext(), "Wew, you figured it out, keep tapping",
                    Toast.LENGTH_LONG).show();
        } else if (mHitCount == 30) {
            if (mTapToast != null) {
                mTapToast.cancel();
            }
            Toast.makeText(getContext(), "Close enough, keep tapping",
                    Toast.LENGTH_LONG).show();
        } else if (mHitCount == 20) {
            if (mTapToast != null) {
                mTapToast.cancel();
            }
            Toast.makeText(getContext(), "Damn Just 20 more to go, JUST DO IT!!",
                    Toast.LENGTH_LONG).show();
        } else if (mHitCount == 10) {
            if (mTapToast != null) {
                mTapToast.cancel();
            }
            Toast.makeText(getContext(), "Oof, This is exciting!!",
                    Toast.LENGTH_LONG).show();
        } else if (mHitCount <= 1) {
            if (mTapToast != null) {
                mTapToast.cancel();
            }
            mHitCount++;
            Toast.makeText(getContext(), "Congrats!! You've just wasted 10+secs of your life :)",
                    Toast.LENGTH_LONG).show();
        }
    }
}
