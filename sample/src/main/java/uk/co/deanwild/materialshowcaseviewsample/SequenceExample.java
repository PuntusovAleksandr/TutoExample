package uk.co.deanwild.materialshowcaseviewsample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;


public class SequenceExample extends AppCompatActivity implements View.OnClickListener {

    private Button mButtonOne;
    private Button mButtonTwo;
    private Button mButtonThree;

    private Button mButtonReset;

    private static final String SHOWCASE_ID = "sequence example";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sequence_example);
        mButtonOne = findViewById(R.id.btn_one);
        mButtonOne.setOnClickListener(this);

        mButtonTwo = findViewById(R.id.btn_two);
        mButtonTwo.setOnClickListener(this);

        mButtonThree = findViewById(R.id.btn_three);
        mButtonThree.setOnClickListener(this);

        mButtonReset = findViewById(R.id.btn_reset);
        mButtonReset.setOnClickListener(this);

        presentShowcaseSequence(); // one second delay
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_one || v.getId() == R.id.btn_two || v.getId() == R.id.btn_three) {
            presentShowcaseSequence();
        } else if (v.getId() == R.id.btn_reset) {
            MaterialShowcaseView.resetSingleUse(this, SHOWCASE_ID);
            Toast.makeText(this, "Showcase reset", Toast.LENGTH_SHORT).show();
        }
    }

    private void presentShowcaseSequence() {

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.question_mark512);
        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(5); // half second between each showcase view
        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this, SHOWCASE_ID);
        sequence.setOnItemShownListener(new MaterialShowcaseSequence.OnSequenceItemShownListener() {
            @Override
            public void onShow(MaterialShowcaseView itemView, int position) {
                Toast.makeText(itemView.getContext(), "Item #" + position, Toast.LENGTH_SHORT).show();
            }
        });
        sequence.setConfig(config);
        sequence.addSequenceItem(mButtonOne, "This is button one", "GOT IT");
        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setTarget(mButtonOne)
                        .setContentText("This is button one")
//                        .setDismissText("GOT IT")
                        .setImageBitmap(bm)
                        .setMaskBitmap(bm, getWindowManager().getDefaultDisplay())
                        .setDismissOnTouch(true)
                        .build());

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setSkipText("SKIP")
                        .setTarget(mButtonTwo)
//                        .setDismissText("GOT IT")
                        .setContentText("This is button two")
                        .withRectangleShape(true)
                        .setImageGif(R.raw.gif_example)
                        .setDismissOnTouch(true)
                        .build()
        );

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setSkipText("SKIP")
                        .setTarget(mButtonTwo)
//                        .setDismissText("GOT IT")
                        .setContentText("This is button two")
                        .setMaskBitmap(bm, getWindowManager().getDefaultDisplay())
                        .withRectangleShape(true)
                        .setImageBitmap(bm)
                        .setImageGif(R.raw.large_giphy_logo)
                        .setDismissOnTouch(true)
                        .build()
        );

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setImageBitmap(bm)
                        .setTarget(mButtonThree)
                        .setDismissText("GOT IT")
                        .setContentText("This is button three")
                        .withRectangleShape()
                        .setDismissOnTouch(true)
                        .setImageGif(R.raw.large_giphy_logo)
                        .setMaskBitmap(bm, getWindowManager().getDefaultDisplay())
                        .build());

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setImageBitmap(bm)
                        .setTarget(mButtonThree)
                        .setDismissText("GOT IT")
                        .setContentText("This is button three")
                        .withRectangleShape()
                        .setImageGif(R.raw.swipe)
                        .setDismissOnTouch(true)
                        .setMaskBitmap(bm, getWindowManager().getDefaultDisplay())
                        .build());

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setImageBitmap(bm)
                        .setTarget(mButtonThree)
                        .setDismissText("GOT IT")
                        .setContentText("This is button three")
                        .setImageGif(R.raw.large_giphy_logo)
                        .setDismissOnTouch(true)
                        .setMaskBitmap(bm, getWindowManager().getDefaultDisplay())
                        .build()
        );

        sequence.start();

    }

}
