package com.herokuapp.livraria.logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.herokuapp.livraria.models.Carrinho;
import com.herokuapp.livraria.models.Console;
import com.herokuapp.livraria.models.JdbcFactory;
import com.herokuapp.livraria.models.User;
import com.herokuapp.livraria.models.dao.EnderecoImpl;
import com.herokuapp.livraria.models.dao.EndrecoDAO;

import br.com.caelum.stella.boleto.Banco;
import br.com.caelum.stella.boleto.Beneficiario;
import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Endereco;
import br.com.caelum.stella.boleto.Pagador;
import br.com.caelum.stella.boleto.bancos.BancoDoBrasil;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;

public class GerarBoleto implements Logica {

	private EndrecoDAO endDao;
	private com.herokuapp.livraria.models.Endereco enderecoUser = null;

	public GerarBoleto() {
		endDao = new EnderecoImpl(JdbcFactory.getInstance().getConnection());
	}

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		User user = (User) req.getSession().getAttribute("usuLogado");
		Carrinho carrinho = (Carrinho) req.getSession()
				.getAttribute("carrinho");
		String cep = req.getParameter("cep");
		
		List<com.herokuapp.livraria.models.Endereco> enderecos = endDao
				.getEnderecoByUser(user)
				.stream().filter(e -> e.getCep().equals(cep))
				.collect(Collectors.toList());
		
		enderecoUser = enderecos.get(0);

		Datas datas = Datas
				.novasDatas()
				.comProcessamento(Calendar.getInstance())
				.comVencimento(LocalDate.now().getDayOfMonth() + 2,
						LocalDate.now().getMonthValue(),
						LocalDate.now().getYear());

		Endereco enderecoBeneficiario = Endereco.novoEndereco()
				.comLogradouro("Rua 24 de Junho 104")
				.comBairro("Centro").comCep("75660-000")
				.comCidade("Buriti Alegre").comUf("GO");

		Beneficiario beneficiario = Beneficiario.novoBeneficiario()
				.comNomeBeneficiario("Livraria Pompeulimp").comAgencia("0219")
				.comDigitoAgencia("4").comCodigoBeneficiario("000014910")
				.comDigitoCodigoBeneficiario("1").comNumeroConvenio("1207113")
				.comCarteira("18").comEndereco(enderecoBeneficiario)
				.comNossoNumero("3300206");

		Endereco enderecoPagador = Endereco.novoEndereco()
				.comLogradouro(enderecoUser.getLogradouro())
				.comBairro(enderecoUser.getBairro())
				.comCep(enderecoUser.getCep())
				.comCidade(enderecoUser.getCidade())
				.comUf(enderecoUser.getEstado().name());

		Pagador pagador = Pagador.novoPagador().comNome(user.getNome())
				.comDocumento(user.getCpf()).comEndereco(enderecoPagador);

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
		res.addHeader("Content-Disposition", "attachment; filename="+user.getNome()+"boleto");

		res.setContentLength((int) bPDF.length);
		
		for (int nChunk = is.read(bPDF); nChunk != -1; nChunk = is.read(bPDF)) {
			os.write(bPDF, 0, nChunk);
		}
		
		os.flush();
		os.close();

		return "";
	}

	
}
