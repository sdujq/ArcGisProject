package org.sdujq.map;

import java.util.List;

import org.sdu.gis.R;
import org.sdu.pojo.Task;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class RecordListActivity extends Activity{

	ListView listview;
	@Override
	public void onCreate(Bundle saved){
		super.onCreate(saved);
		setContentView(R.layout.recordlist);
		listview=(ListView)findViewById(R.id.listView1);
		/*BugDao rdao=new RecordDao();
		List<Record> lst=rdao.find();*/
		//listview.setAdapter(new HomeAdapter(this, lst));
		//Log.e("qq", lst.size()+"");
	}
	
	
	class HomeAdapter extends BaseAdapter {
		private Context context;
		private LayoutInflater inflater;
		private List<Task> list;

		public HomeAdapter(Context context, List<Task> list) {
			super();
			this.context = context;
			this.list = list;
			this.inflater = LayoutInflater.from(this.context);
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View itemView, ViewGroup parent) {
			HomeViewHolder currentItem = new HomeViewHolder();
			final Task data = (Task) list.get(position);
			itemView = inflater.inflate(R.layout.lst_item, null);

			currentItem.icon = (ImageView) itemView
					.findViewById(R.id.cake_icon);
			currentItem.name = (TextView) itemView.findViewById(R.id.cake_name);
			/*currentItem.activityIcon = (ImageView) itemView
					.findViewById(R.id.cake_smallicon);*/
			currentItem.timestamp = (TextView) itemView
					.findViewById(R.id.cake_timestamp);
			currentItem.price = (TextView) itemView
					.findViewById(R.id.cake_price);
			currentItem.clicker = (ImageView) itemView
					.findViewById(R.id.clicker);
			/*currentItem.brandIcon = (ImageView) itemView
					.findViewById(R.id.brandIcon);*/
			currentItem.brandName=(TextView)itemView.findViewById(R.id.cake_brand);
			if (data != null) {
				try {
					/*// TODO set
					if (data.getPhoto()!=null) {
						currentItem.icon.setImageBitmap(BitmapTool.Bytes2Bimap(data.getPhoto()));
					}
					currentItem.name.setText("π ’œ√˚≥∆");
					currentItem.timestamp.setText(new Timestamp(data.getTime()).toLocaleString());
					currentItem.price.setText("π ’œ–≈œ¢:"+data.getTag());
					itemView.setOnClickListener(new OnClickListener() {
								@Override
								public void onClick(View v) {
									MapShowActivity.currentData=data;
									Intent it=new Intent(RecordListActivity.this,MapShowActivity.class);
									RecordListActivity.this.startActivity(it);
								}
							});*/
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return itemView;
		}
	}

	static class HomeViewHolder {
		ImageView icon;
		TextView name;
		ImageView activityIcon;
		TextView timestamp;
		TextView price;
		ImageView brandIcon;
		ImageView clicker;
		TextView brandName;
	}
}
