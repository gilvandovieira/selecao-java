package gilvando.vieira.selecao.repository;

import gilvando.vieira.selecao.model.MediaPorMunicipio;

public interface RegiaoRepositoryCustom {

    MediaPorMunicipio getMediaPorMunicipio(String municipio);

}
