import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

import { Historico, Usuario, MediaPorMunicipio, MediaPorBandeira } from './model';

@Injectable()
export class AppService {

    constructor(private http: HttpClient) { }

    getAllSiglaRegiao() {
        return this.http.get<string[]>("http://localhost:8080/api/helpers/siglas")
    }

    getAllRevendas() {
        return this.http.get<string[]>("http://localhost:8080/api/helpers/revendas")
    }

    getAllMunicipios() {
        return this.http.get<string[]>("http://localhost:8080/api/helpers/municipios")
    }

    postArquivoCsv(arquivo) {
        return this.http.post<Historico>("http://localhost:8080/api/historico/csv", arquivo)
    }

    postNovoUsuario(formData: FormData) {
        return this.http.post<Usuario>("http://localhost:8080/api/usuarios", { nome: formData.get('nome'), email: formData.get('email') })
    }

    getAllUsuarios() {
        return this.http.get<Usuario[]>("http://localhost:8080/api/usuarios")
    }

    postNovoHistorico(formData: FormData) {
        return this.http.post("http://localhost:8080/api/historico", {
            siglaRegiao: formData.get('siglaRegiao'),
            siglaEstado: formData.get('siglaEstado'),
            municipio: formData.get('municipio'),
            nomeRevenda: formData.get('nomeRevenda'),
            cnpj: formData.get('CNPJ'),
            produto: formData.get('produto'),
            dataDaColeta: formData.get('dataDaColeta'),
            valorDeVenda: formData.get('valorDeVenda'),
            valorDeCompra: formData.get('valorDeCompra'),
            unidadeDeMedida: formData.get('unidadeDeMedida'),
            bandeira: formData.get('bandeira')
        })
    }

    getAllHistoricoPorSigla(s: string) {
        return this.http.get<Historico[]>(`http://localhost:8080/api/historico/sigla/${s}`)
    }

    getAllHistoricoPorDistribuidora(s: string) {

        return this.http.get<Historico[]>(`http://localhost:8080/api/historico/distribuidora/${s}`)
    }

    getMediaPorMunicipio(s: string) {

        return this.http.get<MediaPorMunicipio>(`http://localhost:8080/api/estatisticas/${s}`)
    }

    getHistoricoPorDataDaColeta(data: string) {
        return this.http.get<Historico[]>("http://localhost:8080/api/historico/data-coleta", { params: { 'data': data } })

    }

    buscaMediasPorMunicipio(){
        return this.http.get<MediaPorMunicipio[]>("http://localhost:8080/api/estatiscas/media-municipios")
    }

    buscaMediasPorBandeira(){
        return this.http.get<MediaPorBandeira[]>("http://localhost:8080/api/estatiscas/media-bandeira")
    }
}