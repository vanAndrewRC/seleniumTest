package com.cucumber.selenium.helpers;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.cucumber.selenium.domain.Details;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelUtility {
	private static Workbook workbook = null;
	private static final Logger logger = LogManager.getLogger();

	public static List<String> getUrls() {
		List<String> listOfUrls = new ArrayList<String>();
		try {
			Fillo fillo = new Fillo();
			Connection connection = fillo
					.getConnection("C:\\Users\\Andrew\\Desktop\\SanBernardino Project\\CA_SanBernardino_TotalDue.xls");
			String strQuery = "SELECT * FROM Sheet1";
			Recordset recordset = connection.executeQuery(strQuery);
			while (recordset.next()) {
				String url = recordset.getField("TaxSiteURL");
				listOfUrls.add(url);

			}

			recordset.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listOfUrls;
	}

	public static void updateDetails(List<Details> listOfDetails) {
		try {
			Fillo fillo = new Fillo();
			Connection connection = fillo
					.getConnection("C:\\Users\\Andrew\\Desktop\\SanBernardino Project\\CA_SanBernardino_TotalDue.xls");
			for (Details details : listOfDetails) {
				String amount = details.getAmountDue();
				String date = details.getDefaultDate();
				String url = details.getUrl();
				String strQuery = "Update Sheet1 Set BackTaxes='" + amount + "' where TaxSiteURL='" + url + "'";
				String dateQuery = "Update Sheet1 Set DefaultDate='" + date + "' where TaxSiteURL='" + url + "'";

				connection.executeUpdate(strQuery);
				connection.executeUpdate(dateQuery);

			}

			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static List<String> getPPIN() {
		List<String> listOfPPIN = new ArrayList<String>();
		try {
			Fillo fillo = new Fillo();
			Connection connection = fillo
					.getConnection("C:\\Users\\Andrew\\Desktop\\RealEstateProject\\AL_Blount_source.xls");
			String strQuery = "SELECT * FROM source";
			Recordset recordset = connection.executeQuery(strQuery);
			while (recordset.next()) {
				String url = recordset.getField("Number");
				listOfPPIN.add(url);

			}

			recordset.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listOfPPIN;
	}

	public static void updateDetailsOnTemplate(List<Details> listOfDetails) {
		int numberOfRowsAdded = 0;
		Date today = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MMddyy");
		String formattedDate = formatter.format(today);

		String detailsTemplate = "C:\\Users\\Andrew\\Desktop\\RealEstateProject\\Data_Exports_and_Comps_V0717.xls";
		try {
			System.out.println(detailsTemplate);
			workbook = Workbook.getWorkbook(new File(detailsTemplate));

			Sheet sheet = workbook.getSheet(0);

			WritableWorkbook workbookCopy = Workbook.createWorkbook(
					new File("C:\\Users\\Andrew\\Desktop\\RealEstateProject\\AL Blount Data_Exports_and_Comps_V"
							+ formattedDate + ".xls"),
					workbook);
			WritableSheet sheetToEdit = workbookCopy.getSheet(0);

			for (int i = 0; i < listOfDetails.size(); i++) {
				Details details = listOfDetails.get(i);
				int lastRow = sheetToEdit.getRows();

				Label taxSoldTo2016 = new Label(130, lastRow, details.getSoldTo2016());
				Label taxRedeemedDate2016 = new Label(131, lastRow, details.getRedeemedDate2016());
				Label taxRedeemedBy2016 = new Label(132, lastRow, details.getRedeemedBy2016());
				Label taxSoldTo2015 = new Label(133, lastRow, details.getSoldTo2015());
				Label taxRedeemedDate2015 = new Label(134, lastRow, details.getRedeemedDate2015());
				Label taxRedeemedBy2015 = new Label(135, lastRow, details.getRedeemedBy2015());
				Label taxSoldTo2014 = new Label(136, lastRow, details.getSoldTo2014());
				Label taxRedeemedDate2014 = new Label(137, lastRow, details.getRedeemedDate2014());
				Label taxRedeemedBy2014 = new Label(138, lastRow, details.getRedeemedBy2014());
				Label taxSoldTo2013 = new Label(139, lastRow, details.getSoldTo2013());
				Label taxRedeemedDate2013 = new Label(140, lastRow, details.getRedeemedDate2013());
				Label taxRedeemedBy2013 = new Label(141, lastRow, details.getRedeemedBy2013());
				Label taxSoldTo2012 = new Label(142, lastRow, details.getSoldTo2012());
				Label taxRedeemedDate2012 = new Label(143, lastRow, details.getRedeemedDate2012());
				Label taxRedeemedBy2012 = new Label(144, lastRow, details.getRedeemedBy2012());
				Label taxSoldTo2011 = new Label(145, lastRow, details.getSoldTo2011());
				Label taxRedeemedDate2011 = new Label(146, lastRow, details.getRedeemedDate2011());
				Label taxRedeemedBy2011 = new Label(147, lastRow, details.getRedeemedBy2011());
				Label taxSoldTo2010 = new Label(148, lastRow, details.getSoldTo2010());
				Label taxRedeemedDate2010 = new Label(149, lastRow, details.getRedeemedDate2010());
				Label taxRedeemedBy2010 = new Label(150, lastRow, details.getRedeemedBy2010());
				Label taxSoldTo2009 = new Label(151, lastRow, details.getSoldTo2009());
				Label taxRedeemedDate2009 = new Label(152, lastRow, details.getRedeemedDate2009());
				Label taxRedeemedBy2009 = new Label(153, lastRow, details.getRedeemedBy2009());

				Label URL = new Label(155, lastRow, details.getUrl());
				Label taxAccountLabel = new Label(154, lastRow, details.getTaxAccount());
				Label PPIN = new Label(156, lastRow, details.getPPIN());
				Label taxDue2016 = new Label(157, lastRow, details.getCurrentYearTaxDue());
				Label taxDue2015 = new Label(158, lastRow, details.getTaxDue2015());
				Label taxDue2014 = new Label(159, lastRow, details.getTaxDue2014());
				Label taxDue2013 = new Label(160, lastRow, details.getTaxDue2013());
				Label taxDue2012 = new Label(161, lastRow, details.getTaxDue2012());
				Label taxDue2011 = new Label(162, lastRow, details.getTaxDue2011());
				Label taxDue2010 = new Label(163, lastRow, details.getTaxDue2010());
				Label taxDue2009 = new Label(164, lastRow, details.getTaxDue2009());

				Label ownerLabel = new Label(166, lastRow, details.getOwner());
				Label inCareLabel = new Label(167, lastRow, details.getInCare());
				Label parcelLabel = new Label(165, lastRow, details.getParcelNumber());
				Label mailingStreetLabel = new Label(168, lastRow, details.getMailingStreet());
				Label mailingCityLabel = new Label(169, lastRow, details.getMailingCity());
				Label mailingStateLabel = new Label(170, lastRow, details.getMailingState());
				Label mailingZipCodeLabel = new Label(171, lastRow, details.getMailingZipCode());

				Label acresLabel = new Label(176, lastRow, details.getAcre());
				Label yearBuilt = new Label(180, lastRow, details.getYearBuilt());
				Label propertyAddress = new Label(183, lastRow, details.getPropertyAddress());
				Label assessedValue = new Label(189, lastRow, details.getAssessedValue());

				sheetToEdit.addCell(URL);
				sheetToEdit.addCell(taxAccountLabel);
				sheetToEdit.addCell(PPIN);
				sheetToEdit.addCell(ownerLabel);
				sheetToEdit.addCell(parcelLabel);
				sheetToEdit.addCell(mailingStreetLabel);
				sheetToEdit.addCell(mailingCityLabel);
				sheetToEdit.addCell(mailingStateLabel);
				sheetToEdit.addCell(mailingZipCodeLabel);
				sheetToEdit.addCell(inCareLabel);
				sheetToEdit.addCell(taxDue2016);
				sheetToEdit.addCell(taxDue2015);
				sheetToEdit.addCell(taxDue2014);
				sheetToEdit.addCell(taxDue2013);
				sheetToEdit.addCell(taxDue2012);
				sheetToEdit.addCell(taxDue2011);
				sheetToEdit.addCell(taxDue2010);
				sheetToEdit.addCell(taxDue2009);
				sheetToEdit.addCell(acresLabel);
				sheetToEdit.addCell(yearBuilt);
				sheetToEdit.addCell(propertyAddress);
				sheetToEdit.addCell(assessedValue);
				
				sheetToEdit.addCell(taxSoldTo2016);
				sheetToEdit.addCell(taxRedeemedBy2016);
				sheetToEdit.addCell(taxRedeemedDate2016);
				sheetToEdit.addCell(taxSoldTo2015);
				sheetToEdit.addCell(taxRedeemedBy2015);
				sheetToEdit.addCell(taxRedeemedDate2015);
				sheetToEdit.addCell(taxSoldTo2014);
				sheetToEdit.addCell(taxRedeemedBy2014);
				sheetToEdit.addCell(taxRedeemedDate2014);
				sheetToEdit.addCell(taxSoldTo2013);
				sheetToEdit.addCell(taxRedeemedBy2013);
				sheetToEdit.addCell(taxRedeemedDate2013);
				sheetToEdit.addCell(taxSoldTo2012);
				sheetToEdit.addCell(taxRedeemedBy2012);
				sheetToEdit.addCell(taxRedeemedDate2012);
				sheetToEdit.addCell(taxSoldTo2011);
				sheetToEdit.addCell(taxRedeemedBy2011);
				sheetToEdit.addCell(taxRedeemedDate2011);
				sheetToEdit.addCell(taxSoldTo2010);
				sheetToEdit.addCell(taxRedeemedBy2010);
				sheetToEdit.addCell(taxRedeemedDate2010);
				sheetToEdit.addCell(taxSoldTo2009);
				sheetToEdit.addCell(taxRedeemedBy2009);
				sheetToEdit.addCell(taxRedeemedDate2009);
				
				
				System.out.println(details.getTaxAccount());
				System.out.println(details.getOwner());
				System.out.println(details.getParcelNumber());
				System.out.println(details.getMailingStreet());
				System.out.println(details.getMailingCity());
				System.out.println(details.getMailingState());
				System.out.println(details.getMailingZipCode());

			}

			int lastRowNumberAfter = sheetToEdit.getRows();

			workbookCopy.write();
			workbookCopy.close();
			workbook.close();

		} catch (

		Exception e) {
			e.printStackTrace();
		}

		return;

	}

}
