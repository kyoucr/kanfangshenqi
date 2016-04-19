package com.xinwei.kanfangshenqi.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.xinwei.kanfangshenqi.BaseActivity;
import com.xinwei.kanfangshenqi.R;
import com.xinwei.kanfangshenqi.common.Const;
import com.xinwei.kanfangshenqi.model.DataList;
import com.xinwei.kanfangshenqi.request.HttpRequest;
import com.xinwei.kanfangshenqi.request.HttpRequest.RequestListener;
import com.xinwei.kanfangshenqi.response.NameOfHousesResponse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class SeachActivity extends BaseActivity implements ErrorListener {

	private EditText inKey ;   //API 11+
	private ListView listview ;
	private ArrayAdapter<String> adapter ;
	private ArrayList<DataList> loupanList;
	private LayoutInflater inflater;
	private String[] strs;
	private ArrayList<DataList> dataList = new ArrayList<DataList>();
	private ImageView image_null_show;
	private TextView text_null_show;
	private TextView btnLeft;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		isShowTitle(false);
		addChildView(R.layout.activity_search_view);
	}
	
	private void init() {
		inKey = (EditText) findViewById(R.id.inkey) ;
		listview = (ListView) findViewById(R.id.listview) ;
		image_null_show = (ImageView)findViewById(R.id.image_null_show);
		text_null_show = (TextView)findViewById(R.id.text_null_show);
		btnLeft = (TextView)findViewById(R.id.text);
		listview.setVisibility(View.GONE);
		image_null_show.setVisibility(View.VISIBLE);
		text_null_show.setVisibility(View.VISIBLE);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
		btnLeft.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		listview.setTextFilterEnabled(true) ;
		inKey.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		inKey.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if(v != null && v.getText().length() > 0) {
					getLouPan(v.getText().toString());
				}
				else{
					listview.setAdapter(null);
					listview.setVisibility(View.GONE);
				}
				return true;
			}
		});
//		inKey.setOnQueryTextListener(new OnQueryTextListener() {			
//			@Override
//			public boolean onQueryTextSubmit(String query) {
//				if(query != null && query.length() > 0) {
//					getLouPan(query);
//				}
//				return true;
//			}			
//			@Override
//			public boolean onQueryTextChange(String newText) {
//				if(newText != null && newText.length() > 0) {
////					getLouPan(newText);
//				}
//				else {
//					try {
//						listview.setAdapter(null);
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//				return true;
//			}
//		}) ;
		
	}
	private void getLouPan(String buildingName){
		showBar();
		Map<String,String> params = new HashMap<String, String>();
		params.put("buildingName", buildingName);
		HttpRequest.get(this, Const.URL_NAME_OF_HOUSES, getRequestTag(),params,null, new RequestListener() {
			
			
			@Override
			public void onSuccess(String url, String responseResult) {
				NameOfHousesResponse mNameOfHousesResponse = new Gson().fromJson(responseResult,
						NameOfHousesResponse.class);
				if(mNameOfHousesResponse.getDataList()!=null&&mNameOfHousesResponse.getDataList().size()>0){
					image_null_show.setVisibility(View.GONE);
					text_null_show.setVisibility(View.GONE);
					strs = new String[mNameOfHousesResponse.getDataList().size()];
					for(int i = 0; i<mNameOfHousesResponse.getDataList().size(); i++){
						strs[i]=mNameOfHousesResponse.getDataList().get(i).getBuildingName();
						dataList.add(mNameOfHousesResponse.getDataList().get(i));
					}
					adapter = new ArrayAdapter<String>(SeachActivity.this,android.R.layout.simple_list_item_1,strs);
					listview.setAdapter(adapter);
					listview.setVisibility(View.VISIBLE);
					listview.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
							// TODO Auto-generated method stub
							dataList.get(position).getLongitude();
							dataList.get(position).getLatitude();
							dataList.get(position).getBuildingId();
							Intent intent = getIntent();
							Bundle bundle = new Bundle();
							bundle.putSerializable("responseResult", dataList.get(position));
							bundle.putString("longitude", dataList.get(position).getLongitude());
							bundle.putString("latitude", dataList.get(position).getLatitude());
							bundle.putString("buildingName", dataList.get(position).getBuildingName());
							intent.putExtras(bundle);
							
							setResult(1,intent);
							finish();
						}
					});
				}else{
					listview.setAdapter(null);
					image_null_show.setVisibility(View.VISIBLE);
					image_null_show.setImageResource(R.drawable.image_null_show_pan_null);
					text_null_show.setVisibility(View.VISIBLE);
					text_null_show.setText("您要找的楼盘飞走了");
				}
				closeBar();
			}
			
			@Override
			public void onFailure(String url, String errorInfo) {
				closeBar();
			}
			
			@Override
			public void onError(String url, String responseResult) {
				closeBar();
			}
		});
		
	}	
		
		
		
		
		
		
		
//		
//		NameOfHousesRequest request = new NameOfHousesRequest(new Listener<NameOfHousesResponse>() {
//			@Override
//			public void onResponse(NameOfHousesResponse response) {
//				// TODO Auto-generated method stub
//			if (response != null && response.getStatus().equals("1")) {// success
//				loupanList =response.getDataList();
//				adapter = new LouPanAdapter(loupanList, inflater,SeachActivity.this);
//				listview.setAdapter(adapter);
//				
//				
//			  }
//			}
//
//		}, this);
////		request.setLongitudeSpan("0.356532");
////		request.setLatitudeSpan("0.323639");
//		request.setBuildingName(buildingName);
//		WebUtils.doPost(request);
//	}
//	

	@Override
	public void onErrorResponse(VolleyError arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChildViewLoaded() {
		inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
		init();
        //列表项的数据
	}

	@Override
	public void onReloadData() {
		
	}

	@Override
	public String getRequestTag() {
		return SeachActivity.class.getSimpleName();
	}
	    
}
