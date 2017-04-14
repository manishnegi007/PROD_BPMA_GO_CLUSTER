package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.stereotype.Component;

import common.Commons;
import common.XTrustProvider;

@Component
public class APIConsumerService {
	public static ResourceBundle resProp = ResourceBundle.getBundle("errorMessages");

	// @Cacheable(value="maxserviceCache", key="#policyNo.concat('_OTP')",
	// unless="#result == null" )
	public Map<String, String> getPolicyOtp(String policyNo, int counter) {
		String output = new String();
		StringBuilder result = new StringBuilder();
		// String DevMode = "Y";
		String pUrl = "https://gatewayuat.maxlifeinsurance.com/apimgm/dev/soa/policyotp/v2";
		String soaCorrelationId = "ApiConsumer-" + policyNo + "-" + System.currentTimeMillis();
		String soaMsgVersion = "1.0";
		String soaAppID = "BOT";
		String soaUserID = "BOTDEV123";
		String soaUserPswd = "Qk9UMTIzREVW";
		Map<String, String> otpDescMap = new HashMap<String, String>();
		String policyOtp = "";
		String proposerName = "";
		HttpURLConnection conn = null;
		try {
			XTrustProvider trustProvider = new XTrustProvider();
			trustProvider.install();
			URL url = new URL(pUrl);

			/*
			 * if(DevMode!=null && !DevMode.equalsIgnoreCase("") &&
			 * DevMode.equalsIgnoreCase("Y")) { Proxy proxy = new
			 * Proxy(Proxy.Type.HTTP, new
			 * InetSocketAddress("cachecluster.maxlifeinsurance.com", 3128));
			 * conn = (HttpURLConnection) url.openConnection(proxy); } else {
			 * 
			 * }
			 */
			conn = (HttpURLConnection) url.openConnection();
			HttpsURLConnection.setFollowRedirects(true);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			StringBuilder requestdata = new StringBuilder();
			requestdata.append(" 	{	 ");
			requestdata.append(" 	   \"request\": {	 ");
			requestdata.append(" 	      \"header\": {	 ");
			requestdata.append(" 	         \"soaCorrelationId\": \"").append(soaCorrelationId).append("\",	 ");
			requestdata.append(" 	         \"soaMsgVersion\": \"").append(soaMsgVersion).append("\",	 ");
			requestdata.append(" 	         \"soaAppId\": \"").append(soaAppID).append("\",	 ");
			requestdata.append(" 	         \"soaUserId\": \"").append(soaUserID).append("\",	 ");
			requestdata.append(" 	         \"soaPassword\": \"").append(soaUserPswd).append("\"	 ");
			requestdata.append(" 	      },	 ");
			requestdata.append(" 	      \"requestData\": {	 ");
			requestdata.append(" 	         \"policyNumber\": \"").append(policyNo).append("\"	 ");
			requestdata.append(" 	      }	 ");
			requestdata.append(" 	   }	 ");
			requestdata.append(" 	}	 ");

			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(requestdata.toString());
			writer.flush();
			try {
				writer.close();
			} catch (Exception e1) {
			}

			int apiResponseCode = conn.getResponseCode();

			System.out.println("apiResponseCode is : " + apiResponseCode + " Output is : " + result.toString());
			if (apiResponseCode == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				while ((output = br.readLine()) != null) {
					result.append(output);
				}
				conn.disconnect();
				br.close();
				Map resultData = Commons.getGsonData(result.toString());
				String soaStatusCode = ((Map) ((Map) resultData.get("response")).get("responseData"))
						.get("soaStatusCode").toString();
				if (soaStatusCode != null && !soaStatusCode.equalsIgnoreCase("")
						&& soaStatusCode.equalsIgnoreCase("200")) {
					try {
						policyOtp = ((Map) ((Map) resultData.get("response")).get("responseData")).get("otp")
								.toString();
						proposerName = ((Map) ((Map) resultData.get("response")).get("responseData"))
								.get("proposerName").toString();
						System.out.println("proposerName :" + proposerName);
					} catch (Exception ec) {
						System.out.println("unable to get required data" + ec.getMessage());
					}
					otpDescMap.put("policyotp", policyOtp);
					otpDescMap.put("proposerName", proposerName);
					if (counter == 0)
						otpDescMap.put("Message", resProp.getString("getOtpSuccessfully").concat(" " + policyOtp));
					else
						otpDescMap.put("Message", resProp.getString("getOtpRegenSuccessfully").concat(" " + policyOtp));
				}

				else if (soaStatusCode != null && !soaStatusCode.equalsIgnoreCase("")
						&& soaStatusCode.equalsIgnoreCase("999")) {
					String soaMessage = ((Map) ((Map) resultData.get("response")).get("responseData")).get("soaMessage")
							.toString();
					if ("Unable to fetch client Id from Policy Info backend service.".equals(soaMessage)) {
						otpDescMap.put("Message",
								resProp.getString("PolicyNumberNotFound") + policyNo + " " + resProp.getString("PolicyNumberNotFound1"));
					} else if ("Unable to fetch Mobile number from Client Info backend service.".equals(soaMessage)) {
						otpDescMap.put("Message", resProp.getString("MobileNumberRegardingPolicy"));
					}
					// Set message if required
					System.out.println("soaStatusCode is : " + soaStatusCode);
				} else {
					System.out.println("soaStatusCode is : " + soaStatusCode);
				}

			} else {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
				while ((output = br.readLine()) != null) {
					result.append(output);
				}
				conn.disconnect();
				br.close();
				Map resultData = Commons.getGsonData(result.toString());
				String soaStatusCode = ((Map) ((Map) resultData.get("response")).get("responseData"))
						.get("soaStatusCode").toString();
				if (soaStatusCode != null && !soaStatusCode.equalsIgnoreCase("")
						&& soaStatusCode.equalsIgnoreCase("999")) {
					String soaMessage = ((Map) ((Map) resultData.get("response")).get("responseData")).get("soaMessage")
							.toString();
					if ("Unable to fetch client Id from Policy Info backend service.".equals(soaMessage)) {
						otpDescMap.put("Message",
								resProp.getString("PolicyNumberNotFound") + " number " + policyNo + " in our records" + resProp.getString("PolicyNumberNotFound1"));
					} else if ("Unable to fetch Mobile number from Client Info backend service.".equals(soaMessage)) {
						otpDescMap.put("Message", resProp.getString("MobileNumberRegardingPolicy"));
					}
					// Set message if required
					System.out.println("soaStatusCode is : " + soaStatusCode);

				} else {
					otpDescMap.put("Message", resProp.getString("GenericBackendErrorMessage"));
				}
				// Set message if required
			}
		} catch (Exception e) {
			System.out.println("We are in exception while calling API : " + soaCorrelationId + e);
		}

		return otpDescMap;
	}

	public Map getPolicyInfo(String policyNo) {
		String output = new String();
		StringBuilder result = new StringBuilder();
		// String DevMode = "Y";
		String pUrl = "https://gatewayuat.maxlifeinsurance.com/apimgm/dev/soa/policy360/policy360/policyctp/v1";
		String soaCorrelationId = "ApiConsumer-" + policyNo + "-" + System.currentTimeMillis();
		String soaMsgVersion = "1.0";
		String soaAppID = "BOT";
		String soaUserID = "BOTDEV123";
		String soaUserPswd = "Qk9UMTIzREVW";
		Map<String, String> map = new HashMap();
		String policyOtp = "";
		Map<String, Map> returnMap = new HashMap<String, Map>();
		HttpURLConnection conn = null;
		try {
			XTrustProvider trustProvider = new XTrustProvider();
			trustProvider.install();
			URL url = new URL(pUrl);

			/*
			 * if (DevMode != null && !DevMode.equalsIgnoreCase("") &&
			 * DevMode.equalsIgnoreCase("Y")) { Proxy proxy = new
			 * Proxy(Proxy.Type.HTTP, new
			 * InetSocketAddress("cachecluster.maxlifeinsurance.com", 3128));
			 * conn = (HttpURLConnection) url.openConnection(proxy); } else {
			 * 
			 * }
			 */
			conn = (HttpURLConnection) url.openConnection();
			HttpsURLConnection.setFollowRedirects(true);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			StringBuilder requestdata = new StringBuilder();
			requestdata.append(" 	{	 ");
			requestdata.append(" 	   \"request\": {	 ");
			requestdata.append(" 	      \"header\": {	 ");
			requestdata.append(" 	         \"soaCorrelationId\": \"").append(soaCorrelationId).append("\",	 ");
			requestdata.append(" 	         \"soaMsgVersion\": \"").append(soaMsgVersion).append("\",	 ");
			requestdata.append(" 	         \"soaAppId\": \"").append(soaAppID).append("\",	 ");
			requestdata.append(" 	         \"soaUserId\": \"").append(soaUserID).append("\",	 ");
			requestdata.append(" 	         \"soaPassword\": \"").append(soaUserPswd).append("\"	 ");
			requestdata.append(" 	      },	 ");
			requestdata.append(" 	      \"requestData\": {	 ");
			requestdata.append(" 	         \"policyNumber\": \"").append(policyNo).append("\"	 ");
			requestdata.append(" 	      }	 ");
			requestdata.append(" 	   }	 ");
			requestdata.append(" 	}	 ");

			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(requestdata.toString());
			writer.flush();
			try {
				writer.close();
			} catch (Exception e1) {
			}

			int apiResponseCode = conn.getResponseCode();
			System.out.println("apiResponseCode is : " + apiResponseCode + " Outpot is : " + result.toString());
			String responseString = result.toString();
			if (apiResponseCode == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				while ((output = br.readLine()) != null) {
					result.append(output);
				}
				conn.disconnect();
				br.close();
				Map resultData = Commons.getGsonData(result.toString());
				String policyBasePlanIdDesc = ((Map) ((Map) ((Map) resultData.get("response")).get("responseData"))
						.get("BasicDetails")).get("policyBasePlanIdDesc").toString();
				String ctpAmt = ((Map) ((Map) ((Map) resultData.get("response")).get("responseData"))
						.get("BasicDetails")).get("ctpAmt").toString();
				String polDueDate = ((Map) ((Map) ((Map) resultData.get("response")).get("responseData"))
						.get("BasicDetails")).get("polDueDate").toString();
				polDueDate=Commons.convertDateFormat(polDueDate);
				map.put("policyBasePlanIdDesc", policyBasePlanIdDesc);
				map.put("ctpAmt", ctpAmt);
				map.put("polDueDate", polDueDate);

				////////////////////// *******************************////////////////////////////////////
				String policyInsuranceTypeCd = ((Map) ((Map) ((Map) resultData.get("response")).get("responseData"))
						.get("BasicDetails")).get("policyInsuranceTypeCd").toString();

				if ("N".equals(policyInsuranceTypeCd) || "F".equals(policyInsuranceTypeCd)
						|| "D".equals(policyInsuranceTypeCd) || "C".equals(policyInsuranceTypeCd)) {
					Map<String, String> fvMap = new HashMap();
					fvMap.put("fundValAsonDate",
							((Map) ((Map) ((Map) resultData.get("response")).get("responseData")).get("PolicyMeasures"))
									.get("fundValAsonDate").toString());
					fvMap.put("discontinuanceFund",
							((Map) ((Map) ((Map) resultData.get("response")).get("responseData")).get("BasicDetails"))
									.get("discontinuanceFund").toString());
					fvMap.put("Message", resProp.getString("InquiringFVTrue"));
					returnMap.put("FV", fvMap);
				} else if ("8".equals(policyInsuranceTypeCd) || "1".equals(policyInsuranceTypeCd)) {
					Map<String, String> fvMap = new HashMap();
					fvMap.put("Message", resProp.getString("InquiringFVfalse"));
					returnMap.put("FV", fvMap);
				} else {
					Map<String, String> fvMap = new HashMap();
					fvMap.put("Message", resProp.getString("InquiringFVfalse"));
					returnMap.put("FV", fvMap);
				}

				if ("8".equals(policyInsuranceTypeCd) || "1".equals(policyInsuranceTypeCd)) {
					System.out.println("CSV not Applicable");
					Map<String, String> csv = new HashMap();
					csv.put("Message", resProp.getString("cashSurrenderNotApplicable"));
					returnMap.put("CSV", csv);

				}

				try {
					if (Double.parseDouble(ctpAmt) == 0) {
						//if(Commons.dateDiff(polDueDate)<0){
							Map<String, String> fvMap = new HashMap();
							fvMap.put("Message", resProp.getString("CTP_CON1_1")+" "+
									 polDueDate + resProp.getString("CTP_CON1_2")+"\n"+resProp.getString("CTP_CON1_3"));
							returnMap.put("CTP", fvMap);
						//}
					}else if(Commons.dateDiff(polDueDate)<=30){
						Map<String, String> fvMap = new HashMap();
						fvMap.put("Message", resProp.getString("CTP_CON2_1")+" "+polDueDate+" "+resProp.getString("CTP_CON2_2")+" "
								+ ctpAmt +" "+resProp.getString("CTP_CON2_3"));
						returnMap.put("CTP", fvMap);
					}else if(Commons.dateDiff(polDueDate)>30 && Commons.dateDiff(polDueDate)<=180){
						Map<String, String> fvMap = new HashMap();
						fvMap.put("Message", resProp.getString("CTP_CON3_1")+" "+ctpAmt+" "+resProp.getString("CTP_CON3_2")+" "
								+ polDueDate + resProp.getString("CTP_CON3_3"));
						returnMap.put("CTP", fvMap);
					}else if(Commons.dateDiff(polDueDate)>180 && Commons.dateDiff(polDueDate)<1095){
						Map<String, String> fvMap = new HashMap();
						fvMap.put("Message", resProp.getString("CTP_CON4_1")+" "+ctpAmt+" "+resProp.getString("CTP_CON4_2")+" "
								+ polDueDate + resProp.getString("CTP_CON4_3"));
						returnMap.put("CTP", fvMap);
					}else if(Commons.dateDiff(polDueDate)>1095){
						Map<String, String> fvMap = new HashMap();
						fvMap.put("Message", resProp.getString("CTP_CON5_1"));
						returnMap.put("CTP", fvMap);
					}
					else {
						Map<String, String> fvMap = new HashMap();

						fvMap.put("Message", resProp.getString("dueAmountPolicy1") + " " + policyNo + " "
								+ resProp.getString("dueAmountPolicy2") + " " + ctpAmt + " "
								+ resProp.getString("dueAmountPolicy3") + " " + polDueDate);
						fvMap.put("ctpAmt", ctpAmt);
						fvMap.put("polDueDate", polDueDate);
						returnMap.put("CTP", fvMap);
					}

				} catch (Exception ec) {
					System.out.println(ec.getMessage());
				}
				////////////////////// *******************************////////////////////////////////////
			} else {

				Map<String, String> fvMap = new HashMap();
				fvMap.put("Message", "Getting error :" + apiResponseCode + " while calling backend service");
				returnMap.put("ErrorMessage", fvMap);
			}
		} catch (Exception e) {
			System.out.println("We are in exception while calling API : " + soaCorrelationId + e);
		}
		return returnMap;
	}

	public Map<String, String> getPolicyDetails(String policyNumber) {
		String policyURL = "https://gatewayuat.maxlifeinsurance.com/apimgm/dev/soa/policyadminstration/policydetails/cashsurrendervalue/v1";
		Map<String, String> returnMap = new HashMap<String, String>();
		// String DevMode = "Y";
		String soaAppId = "BOT";
		String soaUserId = "BOTDEV123";
		String soaPassword = "Qk9UMTIzREVW";
		// String
		// requestJson="{\"request\":{\"header\":{\"soaCorrelationId\":\"12345\",\"soaMsgVersion\":\"1.0\",\"soaAppId\":\""+soaAppId+"\",\"soaUserId\":\""+soaUserId+"\",\"soaPassword\":\""+soaPassword+"\"},\"requestData\":{\"policyNumber\":\""+policyNumber+"\"}}}";
		StringBuilder requestJson = new StringBuilder();
		requestJson.append(
				"{\"request\":{\"header\":{\"soaCorrelationId\":\"12345\",\"soaMsgVersion\":\"1.0\",\"soaAppId\":\"");
		requestJson.append(soaAppId);
		requestJson.append("\",\"soaUserId\":\"");
		requestJson.append(soaUserId);
		requestJson.append("\",\"soaPassword\":\"");
		requestJson.append(soaPassword);
		requestJson.append("\"},\"requestData\":{\"policyNumber\":\"");
		requestJson.append(policyNumber);
		requestJson.append("\"}}}");

		StringBuilder result = new StringBuilder();

		HttpURLConnection conn = null;
		try {
			String output = "";

			XTrustProvider trustProvider = new XTrustProvider();
			trustProvider.install();
			String pUrl = policyURL;
			URL url = new URL(pUrl);
			/*
			 * if (DevMode != null && !DevMode.equalsIgnoreCase("") &&
			 * DevMode.equalsIgnoreCase("Y")) { Proxy proxy = new
			 * Proxy(Proxy.Type.HTTP, new
			 * InetSocketAddress("cachecluster.maxlifeinsurance.com", 3128));
			 * conn = (HttpURLConnection) url.openConnection(proxy); } else {
			 * conn = (HttpURLConnection) url.openConnection(); }
			 */
			conn = (HttpURLConnection) url.openConnection();
			HttpsURLConnection.setFollowRedirects(true);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			StringBuilder requestdata = new StringBuilder(requestJson);
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(requestdata.toString());
			// logger.info("Request is==" + requestdata.toString());
			writer.flush();
			try {
				writer.close();
			} catch (Exception e1) {
			}

			int apiResponseCode = conn.getResponseCode();
			if (apiResponseCode == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				while ((output = br.readLine()) != null) {
					result.append(output);
				}
				conn.disconnect();
				br.close();
				// logger.info(apiResponseCode + " == " + result.toString());
			} else if (apiResponseCode == 500) {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
				while ((output = br.readLine()) != null) {
					result.append(output);
				}
				conn.disconnect();
				br.close();
				// logger.info(apiResponseCode + " == " + result.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// result=new
		// StringBuilder("{\"response\":{\"header\":{\"soaCorrelationId\":\"12345\",\"soaMsgVersion\":\"1.0\",\"soaAppId\":\"BOT\"},\"responseData\":{\"soaStatusCode\":\"200\",\"soaMessage\":\"Success\",\"cashSurrenderValue\":{\"MIR-DV-EFF-DT\":\"2016-05-11\",\"MIR-CF-MKTVAL-ADJ-AMT\":\"0000000000000.00\",\"MIR-DOD-CRNT-INT-AMT\":\"000000000.00\",\"MIR-DV-APL-LOAN-AMT\":\"0000000000000.00\",\"MIR-DV-APL-INT-YTD-AMT\":\"00000000000.00\",\"MIR-DV-LOAN-AMT\":\"0000000000000.00\",\"MIR-DV-LOAN-INT-YTD-AMT\":\"00000000000.00\",\"MIR-DV-MAX-LOAN-AMT\":[\"0000000011296.66\",\"0000000011296.66\"],\"MIR-DV-MCV-ACUM-AMT\":\"0000000000000.00\",\"MIR-DV-MCV-CSV-AMT\":\"0000000022593.32\",\"MIR-DV-MTHV-ADJ-AMT\":\"0000000000000.00\",\"MIR-DV-NET-CSV-AMT\":\"0000000022593.32\",\"MIR-DV-OWN-CLI-NM\":\"DFI
		// VENTURES
		// LTD\",\"MIR-DV-POL-BASE-CV-AMT\":\"000000000022593.32\",\"MIR-DV-POL-BON-CSH-AMT\":\"0000000000000.00\",\"MIR-DV-POL-CSV-AMT\":\"000000000022593.32\",\"MIR-DV-PREM-RFND-AMT\":\"0000000000000.00\",\"MIR-DV-OS-MPREM-AMT\":\"0000000000000.00\",\"MIR-DV-SURR-CHRG-AMT\":\"0000000000000.00\",\"MIR-DV-TRMNL-DTH-AMT\":\"0000000000000.00\",\"MIR-DV-TRMNL-SURR-AMT\":\"0000000000000.00\",\"MIR-DV-VALU-PUA-AMT\":\"0000000000000.00\",\"MIR-PLAN-CSV-DT-TYP-CD\":\"E\",\"MIR-POL-CSTAT-CD\":\"1\",\"MIR-POL-CVG-REC-CTR\":\"01\",\"MIR-POL-DOD-ACUM-AMT\":\"0000000000000.00\",\"MIR-POL-MISC-SUSP-AMT\":[\"000000000000000.00\",\"000000000000000.00\"],\"MIR-POL-OS-DISB-AMT\":\"000000000000000.00\",\"MIR-POL-PDF-AMT\":\"00000000000.00\",\"MIR-POL-PDF-INT-AMT\":\"000000000.00\",\"MIR-POL-PREM-SUSP-AMT\":\"000000000000319.60\",\"MIR-SBSDRY-CO-ID\":\"S1\",\"MIR-TRM-PREM-RFND-RT\":\"0.0000\",\"MIR-POL-PDF-SUSP-AMT\":\"00000000000.00\",\"MIR-POL-DIV-SUSP-AMT\":\"00000000000.00\",\"MIR-DV-LOAN-MAX-DSCNT-PCT\":\"0000.00\",\"MIR-DV-POL-SURR-AMT\":\"000000022912.92\",\"MIR-DV-POL-ACUM-AMT\":\"000000000022912.920\",\"MIR-DV-SE-XSLD-RFND-AMT\":\"0000000000000.00\",\"MIR-POL-UL-SHRT-AMT\":\"00000000000.00\",\"MIR-DV-POL-SIDFND-AFV-AMT\":\"000000000000000.00\",\"MIR-DV-POL-PEND-ACTV-AMT\":\"0000000000000.00\",\"MIR-DV-POL-DEFR-ACTV-AMT\":\"0000000000000.00\",\"MIR-CALC-OPPB-CSV\":\"000000000000000.00\",\"MIR-MAX-TOPUP-AMT\":\"0000000000000.00\",\"MIR-ATP-SFC-GDLN\":\"0000000000000.00\",\"MIR-ADJ-POL-CSV-AMT\":\"0000000022593.32\",\"MIR-CVG-PARTL-CLM-AMT\":\"000000000.00\",\"MIR-DV-ACRU-RVSN-BON-AMT\":\"00000000000000000\",\"MIR-DV-ACUM-CPAYO-AMT\":\"00000000000000000\",\"MIR-DV-BASE-FNDVL\":\"0000000000000.00\",\"MIR-DV-BASE-GUAR-SURR-AMT\":\"0000000014399.92\",\"MIR-DV-BASE-SURR-AMT\":\"0000000022593.32\",\"MIR-DV-NEW-GUAR-SURR-AMT\":\"0000000014399.92\",\"MIR-DV-NEW-SPL-SURR-AMT\":\"0000000022593.32\",\"MIR-DV-PUA-CSH-VALU\":\"0000000000000.00\",\"MIR-DV-GUAR-PUA-CSH-VALU\":\"0000000000000.00\",\"MIR-DV-RPU-FACE-AMT\":\"00000000000000000\",\"MIR-DV-RVSN-BON-AMT\":\"00000000000000000\",\"MIR-DV-SIDFND-NET-CV-AMT\":\"000000000000000.00\",\"MIR-DV-SURR-CHRG-ST-AMT\":\"0000000000000.00\",\"MIR-DV-UNLCK-TOPUP-FNDVL\":\"0000000000000.00\",\"MIR-POL-GUAR-CV-AMT\":\"000000000014399.92\",\"MIR-POL-GUAR-DB-AMT\":\"0000000244443.32\",\"MIR-POL-GUAR-DB-SA-AMT\":\"\",\"MIR-POL-SSV-CV-AMT\":\"0000000022593.32\",\"MIR-SPL-SURR-VAL-AMT\":\"00000000000000000\",\"MIR-SRCE-OF-SURR\":\"N\",\"MIR-SSV-TRMNL-SURR-AMT\":\"00000000000000000\",\"MIR-SSV-VALU-FCT\":\"00000000000000000\",\"MIR-TRAD-ACUM-CPAYO-AMT\":\"0000000000000.00\",\"MIR-TRAD-ACUM-CPAYO-AMT-SSV\":\"0000000000000.00\",\"MIR-TRAD-TRMNL-SURR-AMT\":\"0000000000000.00\",\"MIR-UNPAID-DUE-AMT\":\"00000000000000000\",\"LSIR-RETURN-CD\":\"00\",\"MIR-RETRN-CD\":\"00\"}}}}");
		String mir_dv_pua_csh_valu = "";
		String mir_dv_eff_dt = "";
		String mir_dv_pol_csv_amt = "";
		try {
			Map resultData = Commons.getGsonData(result.toString());
			mir_dv_pua_csh_valu = ((Map) ((Map) ((Map) resultData.get("response")).get("responseData"))
					.get("cashSurrenderValue")).get("MIR-DV-PUA-CSH-VALU").toString();
			mir_dv_eff_dt = ((Map) ((Map) ((Map) resultData.get("response")).get("responseData"))
					.get("cashSurrenderValue")).get("MIR-DV-EFF-DT").toString();
			mir_dv_pol_csv_amt = ((Map) ((Map) ((Map) resultData.get("response")).get("responseData"))
					.get("cashSurrenderValue")).get("MIR-DV-POL-CSV-AMT").toString();
			returnMap.put("Message",
					resProp.getString("surrendervalue1") + " " + policyNumber + " "
							+ resProp.getString("surrendervalue2") + " " + Commons.convertDateFormat(mir_dv_eff_dt) + " "
							+ resProp.getString("surrendervalue3") + Double.parseDouble(mir_dv_pol_csv_amt) + ".");
			returnMap.put("mir_dv_pua_csh_valu", mir_dv_pua_csh_valu);
		} catch (Exception ec) {
			// logger.error(ec);
		}
		return returnMap;
	}

	public Map<String, String> getMliDocService(String policyNo) {
		String policyMliDocURL = "https://gatewayuat.maxlifeinsurance.com/apimgm/dev/soa/mlidocwebservice/v1";
		HashMap<String, String> returnMap = new HashMap();
		// String DevMode = "Y";
		String soaCorrelationId = "12345";
		String soaAppId = "BOT";
		String soaUserId = "BOTDEV123";
		String soaPassword = "Qk9UMTIzREVW";
		String docID = "PRM23";
		String SendTo = "C";
		String docDispatchMode = "E";
		String fromDate = "04/01/2016";
		String toDate = "03/31/2017";
		// String
		//
		StringBuilder requestJson = new StringBuilder();
		requestJson.append("{\"request\":{\"header\":{\"soaCorrelationId\":\"");
		requestJson.append(soaCorrelationId);
		requestJson.append("\",\"soaMsgVersion\":\"1.0\",\"soaAppId\":\"");
		requestJson.append(soaAppId);
		requestJson.append("\",\"soaUserId\":\"");
		requestJson.append(soaUserId);
		requestJson.append("\",\"soaPassword\":\"");
		requestJson.append(soaPassword);
		requestJson.append("\"},\"requestData\":{\"dispatchDocuments\":{\"policyNumber\":\"");
		requestJson.append(policyNo);
		requestJson.append("\",\"docId\":\"");
		requestJson.append(docID);
		requestJson.append("\",\"sendTo\":\"");
		requestJson.append(SendTo);
		requestJson.append("\",\"emailIdC\":\"\",\"emailIdA\":\"\",\"docDispatchMode\":\"");
		requestJson.append(docDispatchMode);
		requestJson.append("\",\"fromDate\":\"");
		requestJson.append(fromDate);
		requestJson.append("\",\"toDate\":\"");
		requestJson.append(toDate);
		requestJson.append(
				"\",\"fromYear\":\"\",\"toYear\":\"\",\"source\":\"\",\"machineIP\":\"\",\"uniqueTransId\":\"\",\"userId\":\"\",\"requestedBy\":\"\"}}}}");

		System.out.println("Request Json is=" + requestJson);

		StringBuilder result = new StringBuilder();

		HttpURLConnection conn = null;
		try {
			String output = "";

			XTrustProvider trustProvider = new XTrustProvider();
			trustProvider.install();
			String pUrl = policyMliDocURL;
			URL url = new URL(pUrl);
			/*
			 * if (DevMode != null && !DevMode.equalsIgnoreCase("") &&
			 * DevMode.equalsIgnoreCase("Y")) { Proxy proxy = new
			 * Proxy(Proxy.Type.HTTP, new
			 * InetSocketAddress("cachecluster.maxlifeinsurance.com", 3128));
			 * conn = (HttpURLConnection) url.openConnection(proxy); } else {
			 * conn = (HttpURLConnection) url.openConnection(); }
			 */
			conn = (HttpURLConnection) url.openConnection();
			HttpsURLConnection.setFollowRedirects(true);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			StringBuilder requestdata = new StringBuilder(requestJson);
			OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(requestdata.toString());
			System.out.println("Request is==" + requestdata.toString());
			writer.flush();
			try {
				writer.close();
			} catch (Exception e1) {
			}

			int apiResponseCode = conn.getResponseCode();
			if (apiResponseCode == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				while ((output = br.readLine()) != null) {
					result.append(output);
				}
				conn.disconnect();
				br.close();
				Map resultData = Commons.getGsonData(result.toString());
				String responseDescription = ((Map) ((Map) ((Map) resultData.get("response")).get("responseData"))
						.get("dispatchDocumentsResponse")).get("responseDescription").toString();
				if (responseDescription.startsWith("Failure")) {
					returnMap.put("Message", resProp.getString("mliDocServiceFailure"));
				} else {
					returnMap.put("Message", resProp.getString("mliDocServiceSuccess"));
				}
				System.out.println("The Api response Code is" + apiResponseCode);
			} else if (apiResponseCode == 500) {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
				while ((output = br.readLine()) != null) {
					result.append(output);
				}
				conn.disconnect();
				br.close();
				returnMap.put("Message", resProp.getString("mliDocServiceFailure"));
				System.out.println("The Api response Code is" + apiResponseCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnMap;
	}

}



