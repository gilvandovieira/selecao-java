package gilvando.vieira.selecao.service;

import gilvando.vieira.selecao.model.MediaPorBandeira;
import gilvando.vieira.selecao.model.MediaPorMunicipio;

import java.util.List;

public interface EstatisticaService {

    List<MediaPorMunicipio> listaValorMedioPorMunicipio();

    List<MediaPorBandeira> listaValorMedioPorBandeira();

    List<MediaPorMunicipio> getValorMedioPorMunicipio(String municipio);
}
