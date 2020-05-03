package gilvando.vieira.selecao.repository;

import gilvando.vieira.selecao.model.MediaPorMunicipio;

import java.util.List;

public interface RegiaoRepositoryCustom {

    List<MediaPorMunicipio> listMediaPorMunicipio();
    List<MediaPorMunicipio> getMediaPorMunicipio(String municipio);
    List<String> findAllSiglas();
    List<String> findAllMunicipios();
}
