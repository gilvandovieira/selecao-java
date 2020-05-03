export interface Historico {
    siglaRegiao: string
    siglaEstado: string
    municipio: string
    nomeRevenda: string
    CNPJ: string
    produto: string
    dataDaColeta: string
    valorDeVenda: string
    valorDeCompra: string
    unidadeDeMedida: string
    bandeira: string
}

export interface Usuario {
    id:number
    nome:string
    email:string
}

export interface MediaPorMunicipio{
    municipio:string
    produto:string
    mediaVenda:number
    mediaCompra:number
}

export interface MediaPorBandeira{
    bandeira:string
    mediaVenda:number
    mediaCompra:number
}