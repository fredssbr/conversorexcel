package br.com.conversor.excel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;

public class Excel {
	
	private String arquivo;	
	private LeitorProperties prop;
	private List<SolicitacaoOrigem> solicitacoesOrigem;
	private List<SolicitacaoDestino> solicitacoesDestino;
	private JProgressBar progressBar;
	/**
	 * Construtor que recebe como parâmetro o caminho do arquivo Excel de origem dos dados
	 * @param arquivoExcelOrigem
	 * @throws IOException 
	 */
	public Excel(String arquivoExcelOrigem) throws IOException{
		try{
			this.arquivo = arquivoExcelOrigem;
			this.prop = new LeitorProperties();
			this.solicitacoesDestino = new ArrayList<>();
			this.solicitacoesOrigem = new ArrayList<>();
			this.progressBar = new JProgressBar();
		}catch(IOException e){
			throw new IOException("Não foi possível ler o arquivo de configuração do programa.");
		}
		
	}
	/**
	 * Faz a leitura dos dados do arquivo Excel e os armazena em memória
	 * @param args
	 * @throws IOException
	 */
	public void lerArquivoExcel() throws IOException {
		try{
			FileInputStream file = new FileInputStream(arquivo);
			// Get the workbook instance for XLS file
			HSSFWorkbook workbook = new HSSFWorkbook(file);
	
			// Get first sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			// Get iterator to all the rows in current sheet
			Iterator<Row> rowIterator = sheet.iterator();
			
			int max = 0;
			if(sheet.getLastRowNum() > 3){
				max = sheet.getLastRowNum() - 3;
			}
			this.progressBar.setMaximum(max);
			// Iterate through each rows from first sheet
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				// pula as 3 primeiras linhas (nome da planilha e cabeçalhos)
				if (row.getRowNum() < 3) {
					continue;
				}
				
				this.progressBar.setValue(row.getRowNum() - 3);
				// Get iterator to all cells of current row
				Iterator<Cell> cellIterator = row.cellIterator();
				// For each row, iterate through each columns
				SolicitacaoOrigem solOrigem = new SolicitacaoOrigem();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					switch(cell.getColumnIndex()){
					case 1:	
						solOrigem.setTypeSymbol(cell.toString());
						break;
					case 2:	
						solOrigem.setNumeroChamado(cell.toString());
						break;
					case 3:	
						solOrigem.setStatus(cell.toString());
						break;
					case 4:	
						solOrigem.setSlaConsumido(cell.toString());
						break;
					case 5:	
						solOrigem.setNumeroProblema(cell.toString());
						break;
					case 6:	
						solOrigem.setTicketExterno(cell.toString());					
						break;
					case 7:	
						solOrigem.setSlaViolado(cell.toString());
						break;
					case 8:	
						solOrigem.setDataAbertura(cell.getDateCellValue());
						break;
					case 9:	
						solOrigem.setDataSolucao(cell.getDateCellValue());					
						break;
					case 10:	
						solOrigem.setPrioridade(cell.toString());					
						break;
					case 11:
						solOrigem.setDataFechamento(cell.getDateCellValue());					
						break;
					case 12:
						solOrigem.setSumario(cell.toString());
						break;
					case 13:
						solOrigem.setDescricao(cell.toString());					
						break;
					case 14:
						solOrigem.setGrupo(cell.toString());					
						break;
					case 15:	
						solOrigem.setCategoriaChamado(cell.toString());					
						break;
					case 16:
						solOrigem.setSlaCategoria(cell.toString());
						break;
					case 17:
						solOrigem.setResponsavel(cell.toString());
						break;
					case 18:
						solOrigem.setRelatadoPor(cell.toString());
						break;
					case 19:	
						solOrigem.setUsuarioFinalAfetado(cell.toString());					
						break;
					case 20:
						solOrigem.setLocalidade(cell.toString());					
						break;
					case 21:
						solOrigem.setGestor(cell.toString());
						break;
					case 22:
						solOrigem.setSubGestor(cell.toString());					
						break;
					case 23:
						solOrigem.setOrigem(cell.toString());
						break;
					default:
						break;
					}
				}
				if(solOrigem != null){
					this.solicitacoesOrigem.add(solOrigem);
				}			
			}
			workbook.close();
			file.close();
			
			montaListaSolicitacaoDestino();
		}catch(IOException e){
			throw new IOException("Não foi possível ler o arquivo de origem.");
		}catch(IllegalStateException e){
			throw new IOException("Não foi possível ler o arquivo de origem.");
		}
	}
	
	private void montaListaSolicitacaoDestino() throws IOException{
		for (SolicitacaoOrigem solicitacaoOrigem : this.solicitacoesOrigem) {
			SolicitacaoDestino solicitacaoDestino = new SolicitacaoDestino();

			solicitacaoDestino.setSolicitacao("NIM110"+solicitacaoOrigem.getNumeroChamado());
			solicitacaoDestino.setDataAbertura(solicitacaoOrigem.getDataAbertura());
			solicitacaoDestino.setDataEncerramento(solicitacaoOrigem.getDataFechamento());
			solicitacaoDestino.setCriadoPor(solicitacaoOrigem.getRelatadoPor());
			solicitacaoDestino.setGrupoResponsavel(solicitacaoOrigem.getGrupo());
			solicitacaoDestino.setUsuarioResponsavel(solicitacaoOrigem.getResponsavel());
			solicitacaoDestino.setStatus(getStatusDestinoByStatusOrigem(solicitacaoOrigem.getStatus()));
			solicitacaoDestino.setPrioridade(solicitacaoOrigem.getPrioridade());			
			solicitacaoDestino.setPais(getPaisByLocalidade(solicitacaoOrigem.getLocalidade()));
			solicitacaoDestino.setLocalidade(solicitacaoOrigem.getLocalidade());			
			solicitacaoDestino.setCategoria(getCategoriaBySlaCategoria(solicitacaoOrigem.getSlaCategoria()));			
			solicitacaoDestino.setSistema(getSistemaByCategoriaChamado(solicitacaoOrigem.getCategoriaChamado()));
			solicitacaoDestino.setSolicitante(solicitacaoOrigem.getRelatadoPor());
			solicitacaoDestino.setSeveridade(getSeveridadeBySlaCategoria(solicitacaoOrigem.getSlaCategoria()));
			solicitacaoDestino.setCancelamento("");
			solicitacaoDestino.setTempoTotalAccenture(solicitacaoOrigem.getSlaConsumido());
			solicitacaoDestino.setDataMaxima(null);
			solicitacaoDestino.setDescricao(solicitacaoOrigem.getSumario());
			solicitacaoDestino.setCodigoConclusao("");			
			
			this.solicitacoesDestino.add(solicitacaoDestino);
		}
		

	}
	
	private String getStatusDestinoByStatusOrigem(String pstatus){
		String status = "";
		if(pstatus !=null && pstatus.length() > 0){
			for (int i = 0; i < this.prop.getStatusOrigem().length; i++) {
				if(pstatus.equalsIgnoreCase(this.prop.getStatusOrigem()[i])){
					status = this.prop.getStatusDestino()[i];
					break;
				}
			}
		}
		return status;
	}
	
	private String getPaisByLocalidade(String localidade){
		String pais = "";
		if(localidade !=null && localidade.length() > 0){
			for (int i = 0; i < this.prop.getLocalidade().length; i++) {
				if(localidade.equalsIgnoreCase(this.prop.getLocalidade()[i])){
					pais = this.prop.getPais()[i];
					break;
				}
			}
		}
		return pais;
	}
	
	private String getCategoriaBySlaCategoria(String slaCategoria){
		String categoria = "";
		if(slaCategoria !=null && slaCategoria.length() > 0){
			for (int i = 0; i < this.prop.getSigla().length; i++) {
				if(slaCategoria.contains(this.prop.getSigla()[i])){
					categoria = this.prop.getCategoria()[i];
				}
			}
		}
		return categoria;
	}
	
	private String getSistemaByCategoriaChamado(String categoria){
		String sistema = "";
		if(categoria !=null && categoria.length() > 0){
			for (String vsistema: this.prop.getSistema()) {
				if(categoria.contains(vsistema)){
					sistema = vsistema;
					break;
				}
			}
		}
		return sistema;
	}
	
	private String getSeveridadeBySlaCategoria(String slaCategoria){
		String severidade = "";
		if(slaCategoria !=null && slaCategoria.length() > 0){
			severidade = slaCategoria.substring(slaCategoria.length() - 2, slaCategoria.length() - 1);
			try {
				Integer.parseInt(severidade);
			} catch (Exception e) {
				severidade = "";
			}
		}	
		return severidade;
	}
	
	public List<SolicitacaoOrigem> getSolicitacoesOrigem() {
		return solicitacoesOrigem;
	}
	
	public List<SolicitacaoDestino> getSolicitacoesDestino() {
		return solicitacoesDestino;
	}
	
	public JProgressBar getProgressBar(){
		return progressBar;
	}
	
	public void escreverArquivoExcel(String arquivoExcelSaida) throws IOException{
		try{
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("CHAMIX_OPEN");
			//Create a new row in current sheet
			Row rowCabecalho = sheet.createRow(0);
			
			//Criação da primeira linha - cabeçalho
			for (int i = 0; i < this.prop.getCabecalho().length; i++) {
				//Create a new cell in current row
				Cell cell = rowCabecalho.createCell(i);
				//Set value to new value
				cell.setCellValue(this.prop.getCabecalho()[i]);			
			}
			
			CreationHelper createHelperData = workbook.getCreationHelper();
			CellStyle cellStyleData = workbook.createCellStyle();
			cellStyleData.setDataFormat(createHelperData.createDataFormat().getFormat("dd/MM/yyyy HH:mm:ss"));
			Date dataAux = null;
			this.progressBar.setMaximum(this.solicitacoesDestino.size());
			//Dados
			for (int i = 0; i < this.solicitacoesDestino.size(); i++) {
				Row rowDados = sheet.createRow(i + 1);
				this.progressBar.setValue(i + 1);
				//solicitacao
				Cell cell0 = rowDados.createCell(0);
				cell0.setCellValue(this.solicitacoesDestino.get(i).getSolicitacao());
				
				//1
				Cell cell1 = rowDados.createCell(1);
				cell1.setCellValue("");
				
				//Data Abertura Solicitação
				Cell cell2 = rowDados.createCell(2);
				dataAux = this.solicitacoesDestino.get(i).getDataAbertura();
				if(dataAux != null){
					cell2.setCellValue(dataAux);
					cell2.setCellStyle(cellStyleData);
				}else{
					cell2.setCellValue("");
				}
				
				//Data Encerramento
				Cell cell3 = rowDados.createCell(3);
				dataAux = this.solicitacoesDestino.get(i).getDataEncerramento();
				if(dataAux != null){
					cell3.setCellValue(dataAux);
					cell3.setCellStyle(cellStyleData);
				}else{
					cell3.setCellValue("");
				}
				
				//Criado Por
				Cell cell4 = rowDados.createCell(4);
				cell4.setCellValue(this.solicitacoesDestino.get(i).getCriadoPor());
				
				//Grupo Responsável
				Cell cell5 = rowDados.createCell(5);
				cell5.setCellValue(this.solicitacoesDestino.get(i).getGrupoResponsavel());
				
				//Usuário Responsável
				Cell cell6 = rowDados.createCell(6);
				cell6.setCellValue(this.solicitacoesDestino.get(i).getUsuarioResponsavel());
				
				//Status
				Cell cell7 = rowDados.createCell(7);
				cell7.setCellValue(this.solicitacoesDestino.get(i).getStatus());
				
				//Prioridade
				Cell cell8 = rowDados.createCell(8);
				cell8.setCellValue(this.solicitacoesDestino.get(i).getPrioridade());
				
				//País
				Cell cell9 = rowDados.createCell(9);
				cell9.setCellValue(this.solicitacoesDestino.get(i).getPais());
				
				//Localidade
				Cell cell10 = rowDados.createCell(10);
				cell10.setCellValue(this.solicitacoesDestino.get(i).getLocalidade());
				
				//Categoria
				Cell cell11 = rowDados.createCell(11);
				cell11.setCellValue(this.solicitacoesDestino.get(i).getCategoria());
				
				//2
				Cell cell12 = rowDados.createCell(12);
				cell12.setCellValue("");
				
				//Sistema
				Cell cell13 = rowDados.createCell(13);
				cell13.setCellValue(this.solicitacoesDestino.get(i).getSistema());
				
				//Solicitante
				Cell cell14 = rowDados.createCell(14);
				cell14.setCellValue(this.solicitacoesDestino.get(i).getSolicitante());
				
				//Severidade
				Cell cell15 = rowDados.createCell(15);
				cell15.setCellValue(this.solicitacoesDestino.get(i).getSeveridade());
				
				//3
				Cell cell16 = rowDados.createCell(16);
				cell16.setCellValue("");
				
				//4
				Cell cell17 = rowDados.createCell(17);
				cell17.setCellValue("");
				
				//5
				Cell cell18 = rowDados.createCell(18);
				cell18.setCellValue("");
				
				//Cancelamento
				Cell cell19 = rowDados.createCell(19);
				cell19.setCellValue("");
				
				//Tempo total Accenture
				Cell cell20 = rowDados.createCell(20);
				cell20.setCellValue(this.solicitacoesDestino.get(i).getTempoTotalAccenture());
				
				//7
				Cell cell21 = rowDados.createCell(21);
				cell21.setCellValue("");
				
				//Data máxima
				Cell cell22 = rowDados.createCell(22);
				cell22.setCellValue("");
				
				//Descrição
				Cell cell23 = rowDados.createCell(23);
				cell23.setCellValue(this.solicitacoesDestino.get(i).getDescricao());
				
				//Código conclusão
				Cell cell24 = rowDados.createCell(24);
				cell24.setCellValue("");
	
			}
			//Ajusta o tamanho do texto para a coluna
			for (int i = 0; i < this.solicitacoesDestino.size(); i++) {
				sheet.autoSizeColumn(i);
			}
			
			FileOutputStream out = new FileOutputStream(arquivoExcelSaida);
	        workbook.write(out);
	        workbook.close();
	        out.close();
		}catch(IOException e){
			throw new IOException("Não foi possível gravar o arquivo de gerado.");
		}        
		
	}

	public static void main(String[] args) throws IOException {
		JFrame f = new JFrame("Progresso.");
		try{
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File("."));
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.setFileFilter(new FileFilter() {
				public String getDescription() {
					return "Arquivos Excel (.xls)";
				}
				public boolean accept(File f) {
					return f.getName().toLowerCase().endsWith(".xls") || f.isDirectory();
				}
			});
			if (fileChooser.showDialog(null, "Selecione o arquivo para importação") == JFileChooser.APPROVE_OPTION) {
				File fileEntrada = fileChooser.getSelectedFile();
				Excel conversor = new Excel(fileEntrada.getPath());				
				
			    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    Container content = f.getContentPane();
			    //JProgressBar progressBar = new JProgressBar();
			    conversor.getProgressBar().setMinimum(0);
			    conversor.getProgressBar().setValue(0);
			    conversor.getProgressBar().setStringPainted(true);
			    Border borderLeitura = BorderFactory.createTitledBorder("Lendo arquivo...");
			    conversor.getProgressBar().setBorder(borderLeitura);
			    content.add(conversor.getProgressBar(), BorderLayout.NORTH);
			    f.setSize(300, 100);
			    f.setVisible(true);				
				conversor.lerArquivoExcel();
				f.setVisible(false);
				if(fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
					Border borderEscrita = BorderFactory.createTitledBorder("Salvando arquivo...");
					conversor.getProgressBar().setBorder(borderEscrita);
					conversor.getProgressBar().setValue(0);
					f.setVisible(true);
					File fileSaida = fileChooser.getSelectedFile();
					conversor.escreverArquivoExcel(fileSaida.getPath());
					f.setVisible(false);
					f.dispose();
					JOptionPane.showMessageDialog(null, conversor.getSolicitacoesDestino().size() + " registros exportados.", "Processo finalizado", JOptionPane.INFORMATION_MESSAGE);
				}		
				
			}
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, e.getMessage(), "Processo não executado.", JOptionPane.ERROR_MESSAGE);
			f.setVisible(false);
			f.dispose();
		}
		
	}
	
}
