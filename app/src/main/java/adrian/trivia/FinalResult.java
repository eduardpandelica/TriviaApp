package adrian.trivia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Adrian on 5/16/2017.
 */

public class FinalResult extends AppCompatActivity implements View.OnClickListener  {

    private TextView score;
    private Button exit;
    private Button again;
    private Questions q;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        q = getIntent().getParcelableExtra("Questions");
        score = (TextView) findViewById(R.id.score);
        score.setText(q.getResponseCode().toString());
        exit = (Button) findViewById(R.id.exit);
        exit.setOnClickListener(this);
        again = (Button) findViewById(R.id.again);
        again.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.exit:
                finish();
                break;
            case R.id.again:
                Intent myIntent = new Intent(FinalResult.this, SelectPreferences.class);
                startActivity(myIntent);
                finish();
                break;
        }
    }
}
