const formulario = document.querySelector("form");
const Inome = document.querySelector("#nome");
const Icpf = document.querySelector("#cpf");
const Idata_nascimento = document.querySelector("#data_nascimento");
const Iendereco = document.querySelector("#endereco");
const Itelefone = document.querySelector("#telefone");



formulario.addEventListener('submit', (event) => {
  event.preventDefault();
  createAluno();
  //limparCamposCadastro();
});


function createAluno() {

  fetch('http://localhost:8080/pessoa', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      nome: Inome.value,
      cpf: Icpf.value,
      data_nascimento: Idata_nascimento.value,
      endereco: Iendereco.value,
      telefone: Itelefone.value
    })
  })
    .then(response => {
      if (response.ok) {
        return response.json(); // Processa a resposta como JSON
      } else {
        throw new Error('Erro na requisição'); // Lida com erros
      }
    })
    .then(data => { // Processa os dados da resposta do backend
      console.log(data);
    })
    .catch(error => { // Lida com erros
      console.error('Erro:', error);
    });
}

function limparCamposCadastro() {
  Inome.value = '';
  Icpf.value = '';
  Idata_nascimento.value = '';
  Iendereco.value = '';
  Itelefone.value = '';
}