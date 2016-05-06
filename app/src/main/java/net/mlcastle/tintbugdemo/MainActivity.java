package net.mlcastle.tintbugdemo;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends Activity implements View.OnClickListener {
    final Random rng = new Random();

    ImageView imageView;
    Button newColorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.image_view);
        setImageViewDrawableTint(0x7f000000);
        newColorButton = (Button)findViewById(R.id.new_color_button);
        newColorButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int a = rng.nextInt(128) + 64;
        int r = rng.nextInt(256);
        int g = rng.nextInt(256);
        int b = rng.nextInt(256);
        setImageViewDrawableTint(Color.argb(a, r, g, b));
    }

    void setImageViewDrawableTint(@ColorInt int color) {
        Drawable dr = imageView.getDrawable();
        Drawable wrapped = DrawableCompat.wrap(dr);
        if (dr != wrapped)
            imageView.setImageDrawable(wrapped);

        DrawableCompat.setTint(wrapped, color);
        DrawableCompat.setTintMode(wrapped, PorterDuff.Mode.SRC_IN);

        // uncomment the following line and this will work on all platforms;
        // if it is commented out, it is broken pre-Marshmallow
        // wrapped.invalidateSelf();
    }
}
