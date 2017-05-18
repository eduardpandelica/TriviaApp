package adrian.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Adrian on 5/16/2017.
 */

public class QuestionTemplate extends AppCompatActivity implements View.OnClickListener {

    private List<Result> list;
    private Questions q;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button answer;
    private Button true_button;
    private Button false_button;
    private TextView question;
    private TextView difficulty;
    private TextView category;
    private int selected_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selected_answer = 0;
        q = getIntent().getParcelableExtra("Questions");
        list = q.getResults();
        if(list.get(0).getType().equals("boolean")) {
            setContentView(R.layout.activity_question);
            true_button = (Button) findViewById(R.id.response_true);
            true_button.setOnClickListener(this);
            false_button = (Button) findViewById(R.id.response_false);
            false_button.setOnClickListener(this);
            answer = (Button) findViewById(R.id.answer);
            answer.setOnClickListener(this);
        } else {
            List<String> answers = new ArrayList<>();
            answers.add(list.get(0).getCorrectAnswer());
            answers.add(list.get(0).getIncorrectAnswers().get(0));
            answers.add(list.get(0).getIncorrectAnswers().get(1));
            answers.add(list.get(0).getIncorrectAnswers().get(2));
            Collections.shuffle(answers, new Random());

            setContentView(R.layout.activity_question_multiple);
            button1 = (Button) findViewById(R.id.response1);
            button1.setOnClickListener(this);
            button1.setText(answers.get(0));
            button2 = (Button) findViewById(R.id.response2);
            button2.setOnClickListener(this);
            button2.setText(answers.get(1));
            button3 = (Button) findViewById(R.id.response3);
            button3.setOnClickListener(this);
            button3.setText(answers.get(2));
            button4 = (Button) findViewById(R.id.response4);
            button4.setOnClickListener(this);
            button4.setText(answers.get(3));
            answer = (Button) findViewById(R.id.answer);
            answer.setOnClickListener(this);
        }

        question = (TextView) findViewById(R.id.question);
        question.setText(list.get(0).getQuestion().replaceAll("&quot;", "\"").replaceAll("&QUOT;", "\"").replaceAll("&#039;","\'").replaceAll("&rsquo;", "\'"));

        difficulty = (TextView) findViewById(R.id.difficulty);
        difficulty.setText(list.get(0).getDifficulty());

        category = (TextView) findViewById(R.id.category_text);
        category.setText(list.get(0).getCategory());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.response1:
                selected_answer = 1;
                button1.setSelected(true);
                button2.setSelected(false);
                button3.setSelected(false);
                button4.setSelected(false);
                break;
            case R.id.response2:
                selected_answer = 1;
                button1.setSelected(false);
                button2.setSelected(true);
                button3.setSelected(false);
                button4.setSelected(false);
                break;
            case R.id.response3:
                selected_answer = 1;
                button1.setSelected(false);
                button2.setSelected(false);
                button3.setSelected(true);
                button4.setSelected(false);
                break;
            case R.id.response4:
                selected_answer = 1;
                button1.setSelected(false);
                button2.setSelected(false);
                button3.setSelected(false);
                button4.setSelected(true);
                break;
            case R.id.response_true:
                selected_answer = 1;
                true_button.setSelected(true);
                false_button.setSelected(false);
                break;
            case R.id.response_false:
                selected_answer = 1;
                true_button.setSelected(false);
                false_button.setSelected(true);
                break;
            case R.id.answer:
                if(selected_answer == 1) {
                    if (!list.get(0).getType().equals("boolean")) {
                        if (button1.isSelected()) {
                            q.setResponseCode(q.getResponseCode() + 1);
                        }
                    } else {
                        if (list.get(0).getCorrectAnswer().equals("true")) {
                            if (true_button.isSelected()) {
                                q.setResponseCode(q.getResponseCode() + 1);
                            }
                        } else {
                            if (false_button.isSelected()) {
                                q.setResponseCode(q.getResponseCode() + 1);
                            }
                        }
                    }
                    Intent myIntent;
                    if (list.size() > 1) {
                        list.remove(0);
                        q.setResults(list);
                        myIntent = new Intent(QuestionTemplate.this, QuestionTemplate.class);
                    } else {
                        myIntent = new Intent(QuestionTemplate.this, FinalResult.class);
                    }
                    myIntent.putExtra("Questions", q);
                    startActivity(myIntent);
                    finish();
                } else {
                    Toast.makeText(QuestionTemplate.this, "Please select an answer!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
