package com.example.lab6.ColorRectangles;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lab6.R;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter<ListItemModel> {

    private List<ListItemModel> listItems;
    private Context context;

    public CustomListAdapter(Context context, List<ListItemModel> listItems) {
        super(context, 0, listItems);
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemModel item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_list_item_color_rect, parent, false);
        }

        TextView colorText = convertView.findViewById(R.id.colorText);
        LinearLayout itemLayout = convertView.findViewById(R.id.itemLayout);

        colorText.setText(item.getColorName());
        colorText.setBackgroundColor(item.getBackgroundColor());
        colorText.setTextColor(item.getTextColor());

        // Установка цвета фона элемента списка
        int backgroundColor = item.getBackgroundColor();
        itemLayout.setBackground(new ColorDrawable(backgroundColor));

        return convertView;
    }
}
