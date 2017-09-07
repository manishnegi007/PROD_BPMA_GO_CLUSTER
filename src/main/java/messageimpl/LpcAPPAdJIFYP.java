package messageimpl;

public class LpcAPPAdJIFYP {

	public static String lpcAppAdjIfypIntent(String channel, String period, String user_region, String user_circle,
			String userzone, String real_tim_timstamp, String lpc_applied_adj_ifyp_mtd, String lpc_applied_adj_ifyp_ytd)

	{
		String finalresponse = "";
		if ("MLI".equalsIgnoreCase(channel)) {
			channel = "";
		}
		if ("Monthly".equalsIgnoreCase(period)) {
			period = "";
		}
		if ("AXIS".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(user_circle)) {
			user_region = "Circle " + user_circle;
		}
		if ("".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region)
				&& "".equalsIgnoreCase(period)) {
			finalresponse = "As of " + real_tim_timstamp + " LPC Applied Business Adj IFYP MTD for MLI is "
					+ lpc_applied_adj_ifyp_mtd + " LPC Applied Business Adj IFYP YTD for MLI is "
					+ lpc_applied_adj_ifyp_ytd
					+ " If you want to see the channel wise business numbers, please specify";
		} else if (!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region)
				&& "".equalsIgnoreCase(period)) {
			finalresponse = "As of " + real_tim_timstamp + " LPC Applied Business Adj IFYP MTD for " + channel + " is "
					+ lpc_applied_adj_ifyp_mtd + " LPC Applied Business Adj IFYP YTD for " + channel + " is "
					+ lpc_applied_adj_ifyp_ytd
					+ " If you want to see the zone/region wise business numbers, please specify";
		} else if (!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region)
				&& "".equalsIgnoreCase(period)) {
			finalresponse = "As of " + real_tim_timstamp + " LPC Applied Business Adj IFYP MTD for " + userzone
					+ " zone is " + lpc_applied_adj_ifyp_mtd + " LPC Applied Business Adj IFYP YTD for " + userzone
					+ " zone is " + lpc_applied_adj_ifyp_ytd
					+ " If you want to see the region wise business numbers, please specify";
		} else if (!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region)
				&& "".equalsIgnoreCase(period)) {
			finalresponse = "As of " + real_tim_timstamp + " LPC Applied Business Adj IFYP MTD for " + user_region
					+ " region is " + lpc_applied_adj_ifyp_mtd + " LPC Applied Business Adj IFYP YTD for " + user_region
					+ " region is " + lpc_applied_adj_ifyp_ytd;

		} else if (!"".equalsIgnoreCase(channel) && "".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region)
				&& !"".equalsIgnoreCase(period)) {
			if ("YTD".equalsIgnoreCase(period)) {
				finalresponse = "As of " + real_tim_timstamp + " LPC Applied Business Adj IFYP " + period + " for "
						+ channel + " is " + lpc_applied_adj_ifyp_ytd
						+ " If you want to see the zone/region wise business numbers, please specify";
			} else {
				finalresponse = "As of " + real_tim_timstamp + " LPC Applied Business Adj IFYP " + period + " for "
						+ channel + " is " + lpc_applied_adj_ifyp_mtd
						+ " If you want to see the zone/region wise business numbers, please specify";
			}
		} else if (!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && "".equalsIgnoreCase(user_region)
				&& !"".equalsIgnoreCase(period)) {
			if ("YTD".equalsIgnoreCase(period)) {
				finalresponse = "As of " + real_tim_timstamp + " LPC Applied Business Adj IFYP " + period + " for "
						+ userzone + " zone is " + lpc_applied_adj_ifyp_ytd
						+ " If you want to see the region wise business numbers, please specify";
			} else {
				finalresponse = "As of " + real_tim_timstamp + " LPC Applied Business Adj IFYP " + period + " for "
						+ userzone + " zone is " + lpc_applied_adj_ifyp_mtd
						+ " If you want to see the region wise business numbers, please specify";
			}
		} else if (!"".equalsIgnoreCase(channel) && !"".equalsIgnoreCase(userzone) && !"".equalsIgnoreCase(user_region)
				&& !"".equalsIgnoreCase(period)) {
			if ("YTD".equalsIgnoreCase(period)) {
				finalresponse = " As of " + real_tim_timstamp + " LPC Applied Business Adj IFYP " + period + " for "
						+ user_region + " region is " + lpc_applied_adj_ifyp_ytd + " Cr";
			} else {
				finalresponse = " As of " + real_tim_timstamp + " LPC Applied Business Adj IFYP " + period + " for "
						+ user_region + " region is " + lpc_applied_adj_ifyp_mtd + " Cr";
			}
		} else {
			if ("YTD".equalsIgnoreCase(period)) {
				finalresponse = "As of" + real_tim_timstamp + " LPC Applied Business Adj IFYP " + period
						+ " for MLI is " + lpc_applied_adj_ifyp_ytd
						+ " If you want to see the channel wise business numbers, please specify";
			} else {
				finalresponse = "As of" + real_tim_timstamp + " LPC Applied Business Adj IFYP " + period
						+ " for MLI is " + lpc_applied_adj_ifyp_ytd
						+ " If you want to see the channel wise business numbers, please specify";
			}
		}
		return finalresponse.toString();
	}
}