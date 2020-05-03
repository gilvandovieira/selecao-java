import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';

import { Historico, Usuario, MediaPorMunicipio, MediaPorBandeira } from './model';
import { AppService } from './app.service';
import { MatTableDataSource } from '@angular/material/table';


const USUARIOS_ELEMENT_DATA: Usuario[] = []

@Component({
  selector: 'app-root',
  templateUrl: './app.template.html',
  styleUrls: ['./app.component.css'],
  providers: [AppService]
})
export class AppComponent implements OnInit {

  nomeUsuarioControl: FormControl = new FormControl("")
  emailUsuarioControl = new FormControl("")
  arquivoCsv: FormGroup
  usuarioForm: FormGroup
  usuarios: Usuario[] = []
  usuariosColumns: string[] = ['id', 'nome', 'email']

  historicoForm: FormGroup

  siglaRegiao: FormControl = new FormControl()
  siglaEstado: FormControl = new FormControl()
  municipio: FormControl = new FormControl()
  nomeRevenda: FormControl = new FormControl()
  CNPJ: FormControl = new FormControl()
  produto: FormControl = new FormControl()
  dataDaColeta: FormControl = new FormControl()
  valorDeVenda: FormControl = new FormControl()
  valorDeCompra: FormControl = new FormControl()
  unidadeDeMedida: FormControl = new FormControl()
  bandeira: FormControl = new FormControl()


  siglasRegiao: string[]
  distribuidoras: string[]
  municipios: string[]

  historicoColumns: string[] = ['siglaRegiao', 'siglaEstado', 'municipio', 'nomeRevenda', 'CNPJ', 'produto', 'dataDaColeta', 'valorDeVenda', 'valorDeCompra', 'unidadeDeMedida', 'bandeira']

  sigla = 'CO'
  historicoPorSigla: Historico[] = []

  distribuidora = ''
  historicoPorDistribuidora: Historico[] = []

  historicoPorDataDaColeta: Historico[] = []
  dataDaColetaInput:Date = new Date()

  mediaPorMunicipioColumns = ['municipio', 'produto', 'mediaVenda', 'mediaCompra']
  mediaPorMunicipio: MediaPorMunicipio[] = []

  mediaPorBandeiraColumns = ['bandeira','mediaVenda', 'mediaCompra']
  mediaPorBandeira: MediaPorBandeira[] = []
  constructor(private service: AppService, private formBuilder: FormBuilder) { }
  ngOnInit(): void {
    this.arquivoCsv = this.formBuilder.group({
      arquivo: ['']
    });

    this.usuarioForm = this.formBuilder.group({
      nome: this.nomeUsuarioControl,
      email: this.emailUsuarioControl
    });

    this.historicoForm = this.formBuilder.group({
      siglaRegiao: '',
      siglaEstado: '',
      municipio: '',
      nomeRevenda: '',
      CNPJ: '',
      produto: '',
      dataColeta: '',
      valorDeVenda: 0,
      valorDeCompra: 0,
      unidadeDeMedia: '',
      bandeira: ''
    })

  }

  onFileSelect(event) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.arquivoCsv.get('arquivo').setValue(file);
    }
  }

  onCsvSubmit() {
    const formData = new FormData();

    formData.append('arquivo', this.arquivoCsv.get('arquivo').value)
    this.service.postArquivoCsv(formData).subscribe()
  }

  onNovoUsuarioSubmit() {
    const formData = new FormData();

    formData.append('nome', this.usuarioForm.get('nome').value)
    formData.append('email', this.usuarioForm.get('email').value)

    this.service.postNovoUsuario(formData).subscribe();
    this.usuarioForm.setValue({ nome: '', email: '' })
    this.onUsuarioAtualiza()

  }

  onNovoDadoHistorico() {
    const formData = new FormData();

    formData.append('siglaRegiao', this.historicoForm.get('siglaRegiao').value)
    formData.append('siglaEstado', this.historicoForm.get('siglaEstado').value)
    formData.append('municipio', this.historicoForm.get('municipio').value)
    formData.append('nomeRevenda', this.historicoForm.get('nomeRevenda').value)
    formData.append('CNPJ', this.historicoForm.get('CNPJ').value)
    formData.append('produto', this.historicoForm.get('produto').value)
    formData.append('dataDaColeta', Date.parse(this.historicoForm.get('dataDaColeta').value).toLocaleString('pt-BR'))
    formData.append('valorDeVenda', this.historicoForm.get('valorDeVenda').value)
    formData.append('valorDeCompra', this.historicoForm.get('valorDeCompra').value)
    formData.append('unidadeDeMedida', this.historicoForm.get('unidadeDeMedida').value)
    formData.append('bandeira', this.historicoForm.get('bandeira').value)

    this.service.postNovoHistorico(formData).subscribe()
    this.historicoForm.setValue({
      siglaRegiao: '',
      siglaEstado: '',
      municipio: '',
      nomeRevenda: '',
      CNPJ: '',
      produto: '',
      dataDaColeta: '',
      valorDeVenda: '',
      valorDeCompra: '',
      unidadeDeMedida: '',
      bandeira: '',
    })
  }

  onUsuarioAtualiza() {

    this.service.getAllUsuarios().subscribe(u =>
      this.usuarios = u)
  }

  refresh() {
    this.service.getAllSiglaRegiao().subscribe((siglas: string[]) => this.siglasRegiao = siglas)
    this.service.getAllMunicipios().subscribe((municipios: string[]) => this.municipios = municipios)
    this.service.getAllRevendas().subscribe((revendas) => this.distribuidoras = revendas)
  }

  listaRegiaoPorSigla() {
    this.service.getAllHistoricoPorSigla(this.sigla).subscribe((historicos) => { this.historicoPorSigla = historicos })
  }

  listaHistoricoPorDistribuidora(){
    this.service.getAllHistoricoPorDistribuidora(this.distribuidora).subscribe((historicos) => this.historicoPorDistribuidora = historicos)
  }

  onDataColetaInputSubmit(){
    
    this.service.getHistoricoPorDataDaColeta(this.dataDaColetaInput.toLocaleString('pt-BR').split('-').reverse().join('/')).subscribe((historicos) => this.historicoPorDataDaColeta = historicos)
  }

  buscaMediasPorMunicipio(){
    this.service.buscaMediasPorMunicipio().subscribe((medias)=> this.mediaPorMunicipio = medias)
  }

  buscaMediaPorBandeira(){
    this.service.buscaMediasPorBandeira().subscribe((medias) => this.mediaPorBandeira = medias)
  }
}
