package br.com.gesc.dao;

import br.com.gesc.model.NotaPm;
import br.com.gesc.model.sap.ordemSapPm.OrdemSapPm;

public interface INotaPmDao {
    public void criar(NotaPm nota);
    public NotaPm exibir(long numeroOrdem, long numeroNota);


}
