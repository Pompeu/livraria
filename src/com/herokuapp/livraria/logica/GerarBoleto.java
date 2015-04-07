package com.herokuapp.livraria.logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.herokuapp.livraria.models.Console;

import br.com.caelum.stella.boleto.Banco;
import br.com.caelum.stella.boleto.Beneficiario;
import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Endereco;
import br.com.caelum.stella.boleto.Pagador;
import br.com.caelum.stella.boleto.bancos.BancoDoBrasil;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;

public class GerarBoleto implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		Datas datas = Datas
				.novasDatas()
				.comProcessamento(Calendar.getInstance())
				.comVencimento(LocalDate.now().getDayOfMonth() + 2,
						LocalDate.now().getMonthValue(),
						LocalDate.now().getYear());

		Endereco enderecoBeneficiario = Endereco.novoEndereco()
				.comLogradouro("Rua 24 de Junho 104")
				.comBairro("Buriti Alegre").comCep("75660-000")
				.comCidade("Buriti Alegre").comUf("GO");

		Beneficiario beneficiario = Beneficiario.novoBeneficiario()
				.comNomeBeneficiario("Livraria Pompeulimp").comAgencia("0219")
				.comDigitoAgencia("4").comCodigoBeneficiario("14910")
				.comDigitoCodigoBeneficiario("").comNumeroConvenio("1207113")
				.comCarteira("18").comEndereco(enderecoBeneficiario)
				.comNossoNumero("3300206");

		Endereco enderecoPagador = Endereco.novoEndereco()
				.comLogradouro("Av dos testes, 111 apto 333")
				.comBairro("Bairro Teste").comCep("01234-111")
				.comCidade("São Paulo").comUf("SP");

		Pagador pagador = Pagador.novoPagador().comNome("Fulano da Silva")
				.comDocumento("111.222.333-12").comEndereco(enderecoPagador);

		Banco banco = new BancoDoBrasil();

		Boleto boleto = Boleto
				.novoBoleto()
				.comBanco(banco)
				.comDatas(datas)
				.comBeneficiario(beneficiario)
				.comPagador(pagador)
				.comValorBoleto("200.00")
				.comNumeroDoDocumento("1234")
				.comInstrucoes("instrucao 1", "instrucao 2", "instrucao 3",
						"instrucao 4", "instrucao 5")
				.comLocaisDePagamento("local 1", "local 2");

		GeradorDeBoleto gerador = new GeradorDeBoleto(boleto);

		gerador.geraPDF("boleto.pdf");

		InputStream is = req.getInputStream();
		OutputStream os = res.getOutputStream();

		byte[] bPDF = gerador.geraPDF();

		res.setContentType("application/pdf");
		res.addHeader("Content-Disposition", "attachment; filename=" + bPDF);

		res.setContentLength((int) bPDF.length);

		for (int nChunk = is.read(bPDF); nChunk != -1; nChunk = is.read(bPDF)) {
			os.write(bPDF, 0, nChunk);
		}
		os.flush();
		os.close();

		return null;
	}

	private void geradorBoleto(HttpServletResponse res, byte[] bPDF)
			throws ServletException, IOException {

		String pdfFileName = "boleto.pdf";

		File pdfFile = new File(bPDF + pdfFileName);

		res.setContentType("application/pdf");
		res.addHeader("Content-Disposition", "attachment; filename=" + pdfFile);

		res.setContentLength((int) pdfFile.length());

		FileInputStream fileInputStream = new FileInputStream(pdfFile);
		OutputStream responseOutputStream = res.getOutputStream();

		int bytes;

		while ((bytes = fileInputStream.read()) != -1) {
			responseOutputStream.write(bytes);
		}
		fileInputStream.close();
	}
}
