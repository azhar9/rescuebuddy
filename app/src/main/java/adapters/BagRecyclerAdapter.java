package adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.astudios.rescuebuddy.R;

import java.util.ArrayList;

import essential.Essential;
import model.BagItem;


public class BagRecyclerAdapter extends RecyclerView.Adapter<BagRecyclerAdapter.ViewHolder> {

    Context mContext;
    ArrayList<BagItem> dataList;
    Essential essential;


    private BagRecyclerAdapter() {
    }

    public BagRecyclerAdapter(Context context, ArrayList<BagItem> al) {
        mContext = context;
        if (al != null)
            dataList = al;
        essential = new Essential(context);
    }

    public void dataChanged(ArrayList<String> al) {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_rowbag, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
