package adrian.trivia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cristiana on 5/17/2017.
 */

public class Categories  extends AppCompatActivity {

    RecyclerView mRecyclerView;
    Adapter adapter;
    List<Category> categoryList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        /* set layout manager */
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        /* set adapter */
        adapter = new Adapter(new Adapter.Callback() {

            @Override
            public void show(Category category) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("Category", category.getName());
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });
        mRecyclerView.setAdapter(adapter);

        categoryList = new ArrayList<>();
        categoryList.add(new Category("Any Category"));
        categoryList.add(new Category("General Knowledge"));
        categoryList.add(new Category("Entertainment: Books"));
        categoryList.add(new Category("Entertainment: Film"));
        categoryList.add(new Category("Entertainment: Music"));
        categoryList.add(new Category("Entertainment: Musicals & Theatres"));
        categoryList.add(new Category("Entertainment: Television"));
        categoryList.add(new Category("Entertainment: Video Games"));
        categoryList.add(new Category("Entertainment: Board Games"));
        categoryList.add(new Category("Science & Nature"));
        categoryList.add(new Category("Science: Computers"));
        categoryList.add(new Category("Science: Mathematics"));
        categoryList.add(new Category("Mythology"));
        categoryList.add(new Category("Sports"));
        categoryList.add(new Category("Geography"));
        categoryList.add(new Category("History"));
        categoryList.add(new Category("Politics"));
        categoryList.add(new Category("Art"));
        categoryList.add(new Category("Celebrities"));
        categoryList.add(new Category("Animals"));
        categoryList.add(new Category("Vehicles"));
        categoryList.add(new Category("Entertainment: Comics"));
        categoryList.add(new Category("Science: Gadgets"));
        categoryList.add(new Category("Entertainment: Japanese Anime & Manga"));
        categoryList.add(new Category("Entertainment: Cartoon & Animations"));

        adapter.setData(categoryList);
        adapter.notifyDataSetChanged();
    }

    static class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        List<Category> mCategory = new ArrayList<>();
        Callback mCallback;

        public Adapter(Callback callback) {
            mCallback = callback;
        }

        public interface Callback {
            void show (Category category);
        }

        public void setData(List<Category> categoryList) {
            for (Category category : categoryList) {
                mCategory.add(category);
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_category, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            ((ViewHolder) holder).bind(mCategory.get(position), new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    mCallback.show(mCategory.get(position));
                }
            });
        }

        @Override
        public int getItemCount() {
            return mCategory.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView mCrtCategory;

            public ViewHolder(View itemView) {
                super(itemView);
                mCrtCategory = (TextView) itemView.findViewById(R.id.crtCategory);
            }

            public void bind(Category category, View.OnClickListener onClickListener) {
                mCrtCategory.setText(category.getName());
                itemView.setOnClickListener(onClickListener);
            }
        }
    }
}
