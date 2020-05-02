package gilvando.vieira.selecao.service;

import gilvando.vieira.selecao.model.MediaPorMunicipio;
import gilvando.vieira.selecao.repository.RegiaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstatisticaServiceImpl implements EstatisticaService {

    @Autowired
    RegiaoRepository regiaoRepository;

    public EstatisticaServiceImpl() {
    }

    @Override
    public List<MediaPorMunicipio> listaValorMedioPorMunicipio() {
        return regiaoRepository.getMediaPorMunicipio();
    }
}
