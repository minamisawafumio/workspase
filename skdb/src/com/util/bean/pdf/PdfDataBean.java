package com.util.bean.pdf;

import java.util.List;
import java.util.Map;

import org.apache.pdfbox.pdmodel.PDDocument;

public class PdfDataBean {
	private String fileName;
	private List<Map<String, Object>> excelLineList;
	private List<Map<String, Object>> excelMojiList;
	private List<Map<String, Object>> pdfImageFileBeanList;
	private List<Map<String, Object>> pdfFillBoxMapList;
	private List<List<Object>> mojiDataList;


	private PDDocument pDDocument;
	private Integer maxX;
	private Integer maxY;
	private Map<String, List<Integer>> excelMojiMap;

	public List<Map<String, Object>> getExcelLineList() {
		return excelLineList;
	}
	public void setExcelLineList(List<Map<String, Object>> excelLineList) {
		this.excelLineList = excelLineList;
	}
	public List<Map<String, Object>> getExcelMojiList() {
		return excelMojiList;
	}
	public void setExcelMojiList(List<Map<String, Object>> excelMojiList) {
		this.excelMojiList = excelMojiList;
	}
	public Integer getMaxX() {
		return maxX;
	}
	public void setMaxX(Integer maxX) {
		this.maxX = maxX;
	}
	public Integer getMaxY() {
		return maxY;
	}
	public void setMaxY(Integer maxY) {
		this.maxY = maxY;
	}
	public Map<String, List<Integer>> getExcelMojiMap() {
		return excelMojiMap;
	}
	public void setExcelMojiMap(Map<String, List<Integer>> excelMojiMap) {
		this.excelMojiMap = excelMojiMap;
	}
	public List<Map<String, Object>> getPdfImageFileMapList() {
		return pdfImageFileBeanList;
	}
	public void setPdfImageFileMapList(List<Map<String, Object>> pdfImageFileBeanList) {
		this.pdfImageFileBeanList = pdfImageFileBeanList;
	}
	public List<Map<String, Object>> getPdfFillBoxMapList() {
		return pdfFillBoxMapList;
	}
	public void setPdfFillBoxMapList(List<Map<String, Object>> pdfFillBoxBeanList) {
		this.pdfFillBoxMapList = pdfFillBoxBeanList;
	}
	public PDDocument getpDDocument() {
		return pDDocument;
	}
	public void setpDDocument(PDDocument pDDocument) {
		this.pDDocument = pDDocument;
	}
	public List<List<Object>> getMojiDataList() {
		return mojiDataList;
	}
	public void setMojiDataList(List<List<Object>> mojiDataList) {
		this.mojiDataList = mojiDataList;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
