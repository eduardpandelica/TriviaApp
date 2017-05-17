package adrian.trivia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Adrian on 5/16/2017.
 */

public class SelectPreferences extends AppCompatActivity implements View.OnClickListener{

    private Spinner mDifficulty;
    private Spinner mType;
    private String difficulty;
    private String type;
    private Button startquestions;
    private Button selectcategory;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        difficulty = null;
        type = null;
        category = null;
        mDifficulty = (Spinner) findViewById(R.id.difficulty_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.difficulty_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mDifficulty.setAdapter(adapter);
        mDifficulty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                difficulty = getDifficultyId(mDifficulty.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        mType = (Spinner) findViewById(R.id.type_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter = ArrayAdapter.createFromResource(this,
                R.array.type_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mType.setAdapter(adapter);
        mType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                type = getTypeId(mType.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        startquestions = (Button) findViewById(R.id.get_questions);
        startquestions.setOnClickListener(this);

        selectcategory = (Button) findViewById(R.id.category);
        selectcategory.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_questions:
                get_questions();
                break;
            case R.id.category:
                Intent intent = new Intent(SelectPreferences.this, Categories.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                category = getCategoryId(data.getStringExtra("Category"));
                selectcategory.setText(data.getStringExtra("Category"));
            }
        }
    }

    private void get_questions() {
        Call<Questions> callable = null;
        System.out.println(category + " " + difficulty + " " + type);
        if(type == null) {
            if(difficulty == null) {
                if(category == null) {
                    callable = Trivia.Service.Get().getQuestionsOnlyWithAmount("10");
                } else {
                    callable = Trivia.Service.Get().getQuestionsWithCategory("10", category);
                }
            } else {
                if(category == null) {
                    callable = Trivia.Service.Get().getQuestionsWithDifficulty("10", difficulty);
                } else {
                    callable = Trivia.Service.Get().getQuestionsWithoutType("10", category, difficulty);
                }
            }
        } else {
            if(difficulty == null) {
                if(category == null) {
                    callable = Trivia.Service.Get().getQuestionsWithType("10", type);
                } else {
                    callable = Trivia.Service.Get().getQuestionsWithoutDifficulty("10", category, type);
                }
            } else {
                if(category == null) {
                    callable = Trivia.Service.Get().getQuestionsWithoutCategory("10", difficulty, type);
                } else {
                    callable = Trivia.Service.Get().getQuestionsFull("10", category, difficulty, type);
                }
            }
        }

        callable.enqueue(new Callback<Questions>() {
            @Override
            public void onResponse(Call<Questions> call, Response<Questions> response) {
                if(response.isSuccessful()) {
                    if(response.body().getResponseCode() == 0) {
                        Intent myIntent = new Intent(SelectPreferences.this, QuestionTemplate.class);
                        myIntent.putExtra("Questions", response.body());
                        startActivity(myIntent);
                        finish();
                    } else {
                        Toast.makeText(SelectPreferences.this, "Select other preferences, no questions found!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(SelectPreferences.this, "Please select preferences", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Questions> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(SelectPreferences.this, "Internet problem", Toast.LENGTH_LONG).show();
            }
        });
    }


    public String getTypeId(String type) {
        System.out.println(type);
        switch (type) {
            case "Multiple Choice":
                return "multiple";
            case "True / False":
                return "boolean";
        }
        return null;
    }

    public String getDifficultyId(String difficulty) {
        System.out.println(difficulty);
        switch(difficulty) {
            case "Easy":
                return "easy";
            case "Medium":
                return "medium";
            case "Hard":
                return "hard";
        }
        return null;
    }

    public String getCategoryId(String category) {
        switch (category) {
            case "General Knowledge":
                return "9";
            case "Entertainment: Books":
                return "10";
            case "Entertainment: Film":
                return "11";
            case "Entertainment: Music":
                return "12";
            case "Entertainment: Musicals & Theatres":
                return "13";
            case "Entertainment: Television":
                return "14";
            case "Entertainment: Video Games":
                return "15";
            case "Entertainment: Board Games":
                return "16";
            case "Science & Nature":
                return "17";
            case "Science: Computers":
                return "18";
            case "Science: Mathematics":
                return "19";
            case "Mythology":
                return "20";
            case "Sports":
                return "21";
            case "Geography":
                return "22";
            case "History":
                return "23";
            case "Politics":
                return "24";
            case "Art":
                return "25";
            case "Celebrities":
                return "26";
            case "Animals":
                return "27";
            case "Vehicles":
                return "28";
            case "Entertainment: Comics":
                return "29";
            case "Science: Gadgets":
                return "30";
            case "Entertainment: Japanese Anime & Manga":
                return "31";
            case "Entertainment: Cartoon & Animations":
                return "32";
        }
        return null;
    }

}
