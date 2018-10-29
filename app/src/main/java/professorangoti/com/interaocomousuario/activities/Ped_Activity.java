package professorangoti.com.interaocomousuario.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import professorangoti.com.interaocomousuario.R;
import professorangoti.com.interaocomousuario.model.Ped;

public class Ped_Activity extends AppCompatActivity {

    private TextView precoPed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela3);

        precoPed = findViewById(R.id.text_valor);

        Bundle bundle = getIntent().getExtras();
        Ped ped = null;

        if (bundle != null) {
            ped = (Ped) bundle.getSerializable("ped");
        }

        if (ped != null) {
            precoPed.setText(ped.getValor());
        }

    }
}
