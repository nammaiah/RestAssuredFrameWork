package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name = "Datas")
	public String[][] getAllData() throws IOException
	{
		String filePath=System.getProperty("user.dir")+"//testData//ObjectData.xlsx";
		XLUtility xl=new XLUtility();
		
		int rowCount=xl.getRowCount(filePath, "Sheet1");
		int colCount=xl.getCellCount(filePath, "Sheet1",1);
		
		String appData[][]=new String[rowCount][colCount];
		
		for(int rowNum=1; rowNum<=rowCount;rowNum++)
		{
			for(int colNum=0;colNum<colCount;colNum++)
			{	
				appData[rowNum-1][colNum]=xl.getCellData(filePath, "Sheet1",rowNum, colNum);
			}
		}
		
		return appData;
	}
	
	@DataProvider(name = "ids")
	public String[] getAllIDs() throws IOException
	{
		String filePath = System.getProperty("user.dir") + "//testData//ObjectData.xlsx";
		XLUtility xl = new XLUtility();

		int rowCount = xl.getRowCount(filePath, "Sheet1");

		String appData[] = new String[rowCount];

		for (int rowNum = 1; rowNum <= rowCount; rowNum++) {

			appData[rowNum - 1] = xl.getCellData(filePath, "Sheet1", rowNum, 1);

		}

		return appData;
	}

	
	
}
