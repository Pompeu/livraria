package com.herokuapp.livraria.controllers.logicas;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.caelum.stella.boleto.Banco;
import br.com.caelum.stella.boleto.Beneficiario;
import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Endereco;
import br.com.caelum.stella.boleto.Pagador;
import br.com.caelum.stella.boleto.bancos.BancoDoBrasil;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;

import com.herokuapp.livraria.models.Carrinho;
import com.herokuapp.livraria.models.dao.CarrinhoDAO;
import com.herokuapp.livraria.models.dao.CarrinhoImpl;
import com.herokuapp.livraria.uteis.JdbcFactory;

public class GerarBoleto implements Logica {

	private Carrinho carrinho;
	private com.herokuapp.livraria.models.Endereco enderecoEntrega = null;
	private HttpSession session;
	private CarrinhoDAO carrinhoDao;

	public GerarBoleto() {
		carrinhoDao = new CarrinhoImpl(JdbcFactory.getInstance()
				.getConnection());
	}

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		session = req.getSession();
		
		carrinho = (Carrinho) session.getAttribute("carrinho");

		enderecoEntrega = filterEnderecoByCep(carrinho, req.getParameter("cep"));

		geracaoDoBoleto(carrinho, enderecoEntrega , res);

		Carrinho fecharCarrinho = carrinhoDao.fecharCarrinho(carrinho);
		
		if(fecharCarrinho != null){
			session.setAttribute("carrinho", null);
		}
			
		return "";
	}

	private void geracaoDoBoleto(Carrinho carrinho,
			com.herokuapp.livraria.models.Endereco enderecoEntrega,
			HttpServletResponse res) throws IOException {
		Datas datas = Datas
				.novasDatas()
				.comProcessamento(Calendar.getInstance())
				.comVencimento(LocalDate.now().getDayOfMonth() + 2,
						LocalDate.now().getMonthValue(),
						LocalDate.now().getYear());

		Endereco enderecoBeneficiario = Endereco.novoEndereco()
				.comLogradouro("Rua 24 de Junho 104").comBairro("Centro")
				.comCep("75660-000").comCidade("Buriti Alegre").comUf("GO");

		Beneficiario beneficiario = Beneficiario.novoBeneficiario()
				.comNomeBeneficiario("Livraria Pompeulimp").comAgencia("0219")
				.comDigitoAgencia("4").comCodigoBeneficiario("000014910")
				.comDigitoCodigoBeneficiario("1").comNumeroConvenio("1207113")
				.comCarteira("18").comEndereco(enderecoBeneficiario)
				.comNossoNumero("3300206");

		Endereco enderecoPagador = Endereco.novoEndereco()
				.comLogradouro(enderecoEntrega.getLogradouro())
				.comBairro(enderecoEntrega.getBairro())
				.comCep(enderecoEntrega.getCep())
				.comCidade(enderecoEntrega.getCidade())
				.comUf(enderecoEntrega.getEstado().name());

		Pagador pagador = Pagador.novoPagador()
				.comNome(carrinho.getUser().getNome())
				.comDocumento(carrinho.getUser().getCpf())
				.comEndereco(enderecoPagador);

		Banco banco = new BancoDoBrasil();

		Boleto boleto = Boleto
				.novoBoleto()
				.comBanco(banco)
				.comDatas(datas)
				.comBeneficiario(beneficiario)
				.comPagador(pagador)
				.comValorBoleto(carrinho.getTotal())
				.comNumeroDoDocumento("1234")
				.comInstrucoes("Pagavel preferencialmente no Banco do brasil",
						"apos vencimento somente Em Agencias do Banco do Brasil")
				.comLocaisDePagamento("Banco do Brasil S/A");

		GeradorDeBoleto gerador = new GeradorDeBoleto(boleto);

		InputStream is = gerador.geraPDFStream();
		OutputStream os = res.getOutputStream();

		byte[] bPDF = gerador.geraPDF();

		res.setContentType("application/pdf");
		res.addHeader("Content-Disposition", "attachment; filename="
				+ carrinho.getUser().getNome() + "boleto.pdf");

		res.setContentLength((int) bPDF.length);

		for (int nChunk = is.read(bPDF); nChunk != -1; nChunk = is.read(bPDF)) {
			os.write(bPDF, 0, nChunk);
		}
		
		os.flush();
		os.close();
	}

	private com.herokuapp.livraria.models.Endereco filterEnderecoByCep(
			Carrinho carrinho, String cep) {
		return carrinho.getUser().getEnderecos().stream()
				.filter(e -> e.getCep().equals(cep))
				.collect(Collectors.toList()).get(0);
	}

}
