package professorangoti.com.interaocomousuario.servico;

import java.util.List;

import professorangoti.com.interaocomousuario.model.Ped;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PedidoServico {

    @GET("precos")
    Call<List<Ped>> pegaListaDePedidos();
}
