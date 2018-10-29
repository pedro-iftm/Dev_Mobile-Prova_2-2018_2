package professorangoti.com.interaocomousuario.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Ped implements Serializable {

    @SerializedName("produto")
    private String produto;

    @SerializedName("valor")
    private String valor;

    public Ped() {
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}