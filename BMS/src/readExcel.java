import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//Excel 파일에서 Data를 읽어와 Vector에 저장해주는 Class
public class readExcel {
	Vector retVec = new Vector();
	FileInputStream fis;
	XSSFSheet sheet;
	XSSFWorkbook workbook;
	Book book = new Book();
//
	public XSSFSheet getCell() {
		return sheet;
	}

	public Vector getVector() {
		return retVec;
	}

	public readExcel() {
		DAO_DB dao_deleteAll = new DAO_DB();
		dao_deleteAll.deleteAll_Book();
		fis = null;
		try {
			fis = new FileInputStream("XlsxRead.xlsx");
		} catch (FileNotFoundException e) {
			System.err.println("파일을 찾을 수 없습니다.");
		}
		workbook = null;
		try {
			workbook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			System.err.println("IOException!");
		}

		sheet = workbook.getSheetAt(0);
		// 행의 수
		int rows = sheet.getPhysicalNumberOfRows();
		for (int rowindex = 1; rowindex < rows; rowindex++) {
			// 행을읽는다
			XSSFRow row = sheet.getRow(rowindex);
			if (row != null) {
				// 셀의 수
				Vector dataVec = new Vector();
				int cells = row.getPhysicalNumberOfCells();
				if (cells == 0)
					return;
				for (int columnindex = 0; columnindex < cells; columnindex++) {
					// 셀값을 읽는다
					XSSFCell cell = row.getCell(columnindex);
					String value = "";
					int intValue = 0;
					// 셀이 빈값일경우를 위한 널체크
					if (cell == null) {
						continue;
					} else {
						// 타입별로 내용 읽기
						switch (cell.getCellType()) {
						case XSSFCell.CELL_TYPE_FORMULA:
							value = cell.getCellFormula();
							break;
						case XSSFCell.CELL_TYPE_NUMERIC:
							intValue = (int) cell.getNumericCellValue();
							value = intValue + "";
							break;
						case XSSFCell.CELL_TYPE_STRING:
							value = cell.getStringCellValue() + "";
							break;
						case XSSFCell.CELL_TYPE_BLANK:
							value = cell.getBooleanCellValue() + "";
							break;
						case XSSFCell.CELL_TYPE_ERROR:
							value = cell.getErrorCellValue() + "";
							break;
						default:
							break;
						}
					}
					System.out.println(value);
					dataVec.add(value);
				}
				book.setNum(Integer.parseInt(dataVec.elementAt(0).toString()));
				book.setName(dataVec.elementAt(1).toString());
				book.setAuthor(dataVec.elementAt(2).toString());
				book.setPub(dataVec.elementAt(3).toString());
				DAO_DB dao = new DAO_DB();
				try {
					// System.out.println(book.getNum());
					dao.insert_Book(book);
				} catch (FileNotFoundException e) {
					System.err.println("파일을 찾을 수 없습니다.");
				} catch (IOException e) {
					System.err.println("IOException!");
				}
				retVec.add(dataVec);
			}
		}
	}
}