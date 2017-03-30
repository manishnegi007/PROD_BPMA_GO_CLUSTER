package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import common.Commons;
import common.XTrustProvider;


public class CTPServiceAction {

	public String getOTP(String policyNumber) throws IOException {

		StringBuilder result = new StringBuilder();
		String output = null;
		String OTP = null;
		Map<String, Object> responsejson = null;
		try {
			URL url = new URL("https://gatewayuat.maxlifeinsurance.com/apimgm/dev/soa/policyotp/v1/real");
			XTrustProvider xTrustProvider = new XTrustProvider();
			xTrustProvider.install();
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setConnectTimeout(40000);
			conn.setReadTimeout(40000);
			final String input = "{   \"request\": {      \"header\": {         \"soaCorrelationId\": \"12345\",         \"soaMsgVersion\": \"1.0\",         \"soaAppId\": \"BOT\",         \"soaUserId\": \"BOTDEV123\",         \"soaPassword\": \"Qk9UMTIzREVW\"      },      \"requestData\": {         \"policyNumber\": \""
					+ policyNumber + "\"      }   }}";
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			if (conn.getResponseCode() == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
				while ((output = br.readLine()) != null) {
					result.append(output);
				}
				responsejson = Commons.getGsonData(result.toString());
				OTP = (String) ((Map) ((Map) responsejson.get("response")).get("responseData")).get("otp");
			} else if (conn.getResponseCode() == 500) {
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
				while ((output = br.readLine()) != null) {
					result.append(output);
				}
			}
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return OTP;
	}

}
