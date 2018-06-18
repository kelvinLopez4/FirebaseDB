//package com.example.owner.firebasedb;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
//public class CustomListAdapter extends ArrayList<Card> {
//    private Context mContext;
//    private int mResource;
//    private int lastPosition = -1;
//
//
//
//
//
//    /**
//     * Holds variables in a View
//     */
//    private static class ViewHolder {
//        TextView departamento;
//        TextView title;
//        TextView message;
//
//        ProgressBar dialog;
//    }
//
//    public CustomListAdapter(Context context, int resource, ArrayList<Card> objects) {
//        super(context, resource, objects);
//        mContext = context;
//        mResource = resource;
//
//
//
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        //get the persons information
//           String title = getItem(position).getTitle();
//           String departamento = getItem(position).getDepartamento();
//        //create the view result for showing the animation
//        final View result;
//
//        //ViewHolder object
//        final ViewHolder holder;
//
//        if (convertView == null) {
//            LayoutInflater inflater = LayoutInflater.from(mContext);
//            convertView = inflater.inflate(mResource, parent, false);
//            holder = new ViewHolder();
//            holder.departamento = convertView.findViewById(R.id.Departameto);
//            holder.title =  convertView.findViewById(R.id.Titulo);
//            holder.message = convertView.findViewById(R.id.Mensage);
//
//            //holder.dialog = (ProgressBar) convertView.findViewById(R.id.cardProgressDialog);
//
//
//
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//
//        }
//
//        lastPosition = position;
//
//        holder.title.setText(title);
//
//
//        return convertView;
//
//    }
//
//
//
//
//
//
//
//
//}
