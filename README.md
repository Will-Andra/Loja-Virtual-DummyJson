# 📱 Loja Virtual de Produtos

**Objetivo: App desenvolvido com finalidade de Estudo.**

Aplicativo Android em **Kotlin** que consome dados de produtos da API DummyJason, apresentando uma listagem com 
paginação dinâmica (*Infinite Scroll*) e uma tela de detalhes. 
O projeto foi estruturado seguindo os padrões **MVVM** e princípios de **Clean Architecture**.
---
## 🚀 Funcionalidades Chave

* **Paginação Infinita:** Controle de scroll dinâmico para carregar novos dados da API sob demanda.
* **UI Performática:** Uso de `ListAdapter` + `DiffUtil` para atualizações de lista otimizadas e sem travamentos.
* **Arquitetura Limpa:** Separação estrita de responsabilidades, tornando o código modular e testável.
* **Formatação Desacoplada:** Lógica de exibição visual (como conversão para BRL) isolada da camada de UI.
---
## 🛠️ Tecnologias & Ferramentas

* **Linguagem:** Kotlin
* **Arquitetura:** MVVM + Clean Architecture
* **Componentes Nativos:** RecyclerView, CardView, ConstraintLayout e ScrollView
* **Carregamento de Imagens:** Glide
* **Comunicação entre Telas:** Intent com transferência via `Serializable`
---
## 📐 Estrutura do Projeto

O código está organizado em camadas para garantir a separação de conceitos:
* 📦 `data` → Implementação do repositório de dados (`ProdutoRepositoryImpl`).
* 📦 `domain` → Modelos de negócio (`Produtos`) e casos de uso isolados (`GetProdutosUseCase`).
* 📦 `presentation` → Camada de UI (`MainActivity`, `DetalhesProduto`, `ProdutoAdapter`) e gerenciamento de estado (`ViewModel` e `Factory`).
* 📦 `util` → Classes utilitárias helper (Formatação de moeda).
