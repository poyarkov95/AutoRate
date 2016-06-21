package com.example.user.autorate;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class AutoServiceFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView autoServiceRecycler = (RecyclerView)inflater.inflate(R.layout.fragment_auto, container,false);

        String[] autoServiceNames = new String[AutoServiceInfo.autoServices.length];

        for(int i = 0; i < autoServiceNames.length;i++){
            autoServiceNames[i] = AutoServiceInfo.autoServices[i].getName();
        }

        int[] autoServiceImages = new int[AutoServiceInfo.autoServices.length];
        for(int i = 0;i<autoServiceImages.length;i++){
            autoServiceImages[i] = AutoServiceInfo.autoServices[i].getImageResourceId();
        }

        CaptionedImageAdapter adapter = new CaptionedImageAdapter(autoServiceNames,autoServiceImages);
        autoServiceRecycler.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        autoServiceRecycler.setLayoutManager(layoutManager);
        adapter.setListener(new CaptionedImageAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), AutoServiceDetailActivity.class);
                intent.putExtra(AutoServiceDetailActivity.EXTRA_SERVICE_NO, position);
                getActivity().startActivity(intent);
            }
        });
        return autoServiceRecycler;
    }
}
