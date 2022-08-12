package com.example.pepsico.addNewStory.user.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.pepsico.addNewStory.user.UserStory;

//@Component
//@RestController
public class ExcelImport {
	private XSSFSheet workSheet;
	private XSSFWorkbook workbook;

//	@PathVariable String filePath
	@SuppressWarnings("null")
//	@GetMapping("/hello")
	public List<UserStory> excelConvertor() throws IOException {
		List<UserStory> allUserStory = new ArrayList<>();
		String filePath="C:\\Users\\938322\\Downloads\\S26 Stories (1).xlsx";
		FileInputStream in = new FileInputStream(filePath);
		this.workbook = new XSSFWorkbook(in);
		this.workSheet = workbook.getSheet("S26WK3");
		DataFormatter format = new DataFormatter();
		Iterator<Row> row = this.workSheet.iterator();
		Row row1 = row.next();
		while (row.hasNext()) {
			UserStory u = new UserStory();
			int c = 0;
			row1 = row.next();
			Iterator<Cell> column = row1.iterator();
			while (column.hasNext()) {
				Cell value = column.next();
				switch (c++) {
				case 0:
					u.setId(Long.parseLong(format.formatCellValue(value)));
					break;
				case 1:
					u.setTitle(format.formatCellValue(value));
					break;
				case 2:
					u.setState(format.formatCellValue(value));
					break;
				case 3:
					u.setStoryPoint(Integer.parseInt(format.formatCellValue(value)));
					break;
				case 4:
					u.setIterationPath(format.formatCellValue(value));
					break;
				case 5:
					u.setTags(new ArrayList<String>(Arrays.asList(format.formatCellValue(value).split(";"))));
				}
			}
			if(u.getId()>0)
			allUserStory.add(u);
		}
		
		in.close();
		return allUserStory;

	}

}
