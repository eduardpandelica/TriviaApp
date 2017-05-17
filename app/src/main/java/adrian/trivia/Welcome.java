package adrian.trivia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome extends AppCompatActivity implements View.OnClickListener{

    private Button mStart;
    private Button mExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mStart = (Button) findViewById(R.id.start);
        mStart.setOnClickListener(this);
        mExit = (Button) findViewById(R.id.exit);
        mExit.setOnClickListener(this);

        //  Login screen should have no action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                Intent myIntent = new Intent(Welcome.this, SelectPreferences.class);
                startActivity(myIntent);
                finish();
                break;
            case R.id.exit:
                finish();
                break;
        }
    }
}
