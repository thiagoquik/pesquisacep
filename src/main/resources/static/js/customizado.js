function limparMensagens() {
        $(".alert").each(function (a) {
          $(this).hide();
        });
      }
  
    function exibirMensagem(message, alert) {
        limparMensagens();
        if (alert) {
          alert.text(message).append('<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>').show();
        }
      }
    
		$(document).ready(function() {
			limparMensagens();
			$("#btnBuscar").click(function(e) {
				e.preventDefault();
				buscaCep();
			});

			$("#cep").mask("99999999");
		});

		function escreverEndereco(cep) {
			if (cep) {
				$('#endereco').append(
						'<strong>' + 'Logradouro: ' + cep.logradouro
								+ '<br></br></strong>');

				if (cep.bairro) {
					$('#endereco').append(
							'<strong>' + 'Bairro: ' + cep.bairro
									+ '<br></br></strong>');
				}

				$('#endereco').append(
						'<strong>' + 'Cep: ' + cep.numCep
								+ '<br></br></strong>');
				$('#endereco').append(
						'<strong>' + 'Cidade: ' + cep.cidade
								+ '<br></br></strong>');
				$('#endereco').append(
						'<strong>' + 'Estado: ' + cep.estado
								+ '<br></br></strong>');
			} else {
				$('#endereco').html('');
			}
		}

		function buscaCep() {
			var cep = $('input[name="txtCep"]').val();
			limparMensagens();
			escreverEndereco('');
			if (cep == '') {
				exibirMensagem('Você não digitou um cep.', $("#msgAlert"));
				return;
			}
			$.ajax({
				type : "GET",
				url : "/pesquisarCep/" + cep,
				success : function(json) {
					if (json) {
						if (json.numCep != cep) {
							escreverEndereco(json);
							exibirMensagem('A pesquisa encontrou o Cep '
									+ json.numCep + ' por proximidade.',
									$("#msgAlert"));
						} else {
							exibirMensagem('O Cep ' + json.numCep
									+ ' foi encontrado!', $("#msgSuccess"));
							escreverEndereco(json);
						}
					}
				},
				error : function(data) {
					var msgError;
		            if (data.responseText) {
		              var responseText = JSON.parse(data.responseText);
		              msgError = responseText["message"];
		            } else {
		              msgError = data.statusText;
		            }
		            exibirMensagem(msgError, $("#msgError"));
				}
			});
		}