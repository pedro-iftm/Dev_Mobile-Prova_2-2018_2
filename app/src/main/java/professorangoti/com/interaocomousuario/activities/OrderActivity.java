package professorangoti.com.interaocomousuario.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import professorangoti.com.interaocomousuario.R;
import professorangoti.com.interaocomousuario.model.Ped;
import professorangoti.com.interaocomousuario.servico.PedidoServico;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private String pedidoCli;
    private int entrega;
    private Button botao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        botao = findViewById(R.id.button);
        botao.setOnClickListener(this);
        Intent intent = getIntent();
        pedidoCli = intent.getStringExtra("mensagem");
        TextView textView = findViewById(R.id.order_textview);
        textView.setText(pedidoCli);
        Spinner spinner = findViewById(R.id.label_spinner);

        if (spinner != null) {spinner.setOnItemSelectedListener(this);}
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.labels_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (spinner != null) { spinner.setAdapter(adapter);}


    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio botao was clicked.
        switch (view.getId()) {
            case R.id.sameday:
                if (checked)
                    // Same day service
                    entrega = R.string.entrega_no_dia_seguinte;
                displayToast(getString(entrega));
                break;
            case R.id.nextday:
                if (checked)
                    // Next day delivery
                    entrega = R.string.entrega_no_dia_seguinte;
                displayToast(getString(entrega));
                break;
            case R.id.pickup:
                if (checked)
                    // Pick up
                    entrega = R.string.retirar_na_loja;
                displayToast(getString(entrega));
                break;
            default:
                // Do nothing.
                break;
        }
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        displayToast(parent.getItemAtPosition(position).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button) {
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://provaddm2018.atwebpages.com/").addConverterFactory(GsonConverterFactory.create(new Gson())).build();

            PedidoServico pedidoServico = retrofit.create(PedidoServico.class);
            Call<List<Ped>> pedidoResponse = pedidoServico.pegaListaDePedidos();

            pedidoResponse.enqueue(new Callback<List<Ped>>() {
                @Override
                public void onResponse(Call<List<Ped>> call, Response<List<Ped>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Ped pedSelecionado = new Ped();
                        List<Ped> pedList = response.body();

                        for (int i = 0; i < pedList.size(); i++) {
                            if (pedList.get(i).getProduto().equals(pedidoCli)) {
                                pedSelecionado = pedList.get(i);
                            }
                        }

                        Intent intent = new Intent(OrderActivity.this, Ped_Activity.class);
                        intent.putExtra("pedidoCli", pedSelecionado);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<List<Ped>> call, Throwable t) {

                }
            });
        }
    }
}
