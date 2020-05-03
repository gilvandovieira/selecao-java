package gilvando.vieira.selecao.service;

import gilvando.vieira.selecao.model.MediaPorBandeira;
import gilvando.vieira.selecao.model.MediaPorMunicipio;
import gilvando.vieira.selecao.model.Transacao;
import gilvando.vieira.selecao.repository.RegiaoRepository;
import gilvando.vieira.selecao.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstatisticaServiceImpl implements EstatisticaService {

    @Autowired
    RegiaoRepository regiaoRepository;

    @Autowired
    TransacaoRepository transacaoRepository;

    public EstatisticaServiceImpl() {
    }

    @Override
    public List<MediaPorMunicipio> listaValorMedioPorMunicipio() {
        return regiaoRepository.listMediaPorMunicipio();
    }

    @Override
    public List<MediaPorBandeira> listaValorMedioPorBandeira() {
        return transacaoRepository.getMediaPorBandeira();
    }

    @Override
    public List<MediaPorMunicipio> getValorMedioPorMunicipio(String municipio) {
        return regiaoRepository.getMediaPorMunicipio(municipio);
    }
}
