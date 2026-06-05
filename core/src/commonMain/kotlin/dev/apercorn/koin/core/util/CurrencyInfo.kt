package dev.apercorn.koin.core.util

/**
 * Metadata for a single currency.
 *
 * @property code - ISO 4217 currency code
 * @property name - Full English name of the currency
 * @property symbol - Currency sign for display
 * @property longSymbol - Currency sign with country prefix for dollars, otherwise a romanized abbreviation
 * @property decimalDigits - Number of digits after the decimal point
 * @property rounding - Rounding increment (0 means no rounding)
 */
data class CurrencyInfo(
	val code: String,
	val name: String,
	val symbol: String,
	val longSymbol: String,
	val decimalDigits: Int = 2,
	val rounding: Double = 0.0,
) {
	companion object {
		/**
		 * Lookup table derived from [currency.json].
		 * Key = uppercase ISO code. Value = [CurrencyInfo].
		 */
		val ALL: Map<String, CurrencyInfo> = currencies()
			.associateBy { it.code.uppercase() }

		/**
		 * Attempt lookup. Returns null for unknown codes.
		 */
		fun find(code: String): CurrencyInfo? = ALL[code.uppercase()]

		/**
		 * Lookup with fallback. Returns default 'dollar' for unknown codes.
		 */
		fun findOrDefault(code: String): CurrencyInfo =
			find(code) ?: CurrencyInfo(
				code = code.uppercase(),
				symbol = defaultSymbol(),
				longSymbol = defaultSymbol(),
				name = code.uppercase(),
				decimalDigits = 2,
			)

		/**
		 * Returns the default currency symbol (fallback when code unknown).
		 */
		fun defaultSymbol(): String = "$"
	}
}

/** @suppress Internal helper — keeps the companion tidy. */
@PublishedApi
internal fun currencies(): List<CurrencyInfo> = listOf(
	CurrencyInfo("USD", "US Dollar", "$", "US$", 2, 0.0),
	CurrencyInfo("CAD", "Canadian Dollar", "$", "CA$", 2, 0.0),
	CurrencyInfo("EUR", "Euro", "€", "€", 2, 0.0),
	CurrencyInfo("AED", "United Arab Emirates Dirham", "د.إ.‏", "AED", 2, 0.0),
	CurrencyInfo("AFN", "Afghan Afghani", "؋", "Af", 0, 0.0),
	CurrencyInfo("ALL", "Albanian Lek", "L", "Lek", 0, 0.0),
	CurrencyInfo("AMD", "Armenian Dram", "դր.", "AMD", 0, 0.0),
	CurrencyInfo("ARS", "Argentine Peso", "$", "AR$", 2, 0.0),
	CurrencyInfo("AUD", "Australian Dollar", "$", "AU$", 2, 0.0),
	CurrencyInfo("AZN", "Azerbaijani Manat", "₼", "man.", 2, 0.0),
	CurrencyInfo("BAM", "Bosnia-Herzegovina Convertible Mark", "KM", "KM", 2, 0.0),
	CurrencyInfo("BDT", "Bangladeshi Taka", "৳", "Tk", 2, 0.0),
	CurrencyInfo("BGN", "Bulgarian Lev", "лв.", "BGN", 2, 0.0),
	CurrencyInfo("BHD", "Bahraini Dinar", "د.ب.‏", "BD", 3, 0.0),
	CurrencyInfo("BIF", "Burundian Franc", "FBu", "FBu", 0, 0.0),
	CurrencyInfo("BND", "Brunei Dollar", "$", "BN$", 2, 0.0),
	CurrencyInfo("BOB", "Bolivian Boliviano", "Bs", "Bs", 2, 0.0),
	CurrencyInfo("BRL", "Brazilian Real", "R$", "R$", 2, 0.0),
	CurrencyInfo("BWP", "Botswanan Pula", "P", "BWP", 2, 0.0),
	CurrencyInfo("BYN", "Belarusian Ruble", "руб.", "Br", 2, 0.0),
	CurrencyInfo("BZD", "Belize Dollar", "$", "BZ$", 2, 0.0),
	CurrencyInfo("CDF", "Congolese Franc", "FrCD", "CDF", 2, 0.0),
	CurrencyInfo("CHF", "Swiss Franc", "CHF", "CHF", 2, 0.05),
	CurrencyInfo("CLP", "Chilean Peso", "$", "CL$", 0, 0.0),
	CurrencyInfo("CNY", "Chinese Yuan", "CN¥", "CN¥", 2, 0.0),
	CurrencyInfo("COP", "Colombian Peso", "$", "CO$", 0, 0.0),
	CurrencyInfo("CRC", "Costa Rican Colón", "₡", "₡", 0, 0.0),
	CurrencyInfo("CVE", "Cape Verdean Escudo", "CV$", "CV$", 2, 0.0),
	CurrencyInfo("CZK", "Czech Republic Koruna", "Kč", "Kč", 2, 0.0),
	CurrencyInfo("DJF", "Djiboutian Franc", "Fdj", "Fdj", 0, 0.0),
	CurrencyInfo("DKK", "Danish Krone", "kr", "Dkr", 2, 0.0),
	CurrencyInfo("DOP", "Dominican Peso", "RD$", "RD$", 2, 0.0),
	CurrencyInfo("DZD", "Algerian Dinar", "د.ج.‏", "DA", 2, 0.0),
	CurrencyInfo("EEK", "Estonian Kroon", "kr", "Ekr", 2, 0.0),
	CurrencyInfo("EGP", "Egyptian Pound", "ج.م.‏", "EGP", 2, 0.0),
	CurrencyInfo("ERN", "Eritrean Nakfa", "Nfk", "Nfk", 2, 0.0),
	CurrencyInfo("ETB", "Ethiopian Birr", "Br", "Br", 2, 0.0),
	CurrencyInfo("GBP", "British Pound Sterling", "£", "£", 2, 0.0),
	CurrencyInfo("GEL", "Georgian Lari", "GEL", "GEL", 2, 0.0),
	CurrencyInfo("GHS", "Ghanaian Cedi", "GH₵", "GH₵", 2, 0.0),
	CurrencyInfo("GNF", "Guinean Franc", "FG", "FG", 0, 0.0),
	CurrencyInfo("GTQ", "Guatemalan Quetzal", "Q", "GTQ", 2, 0.0),
	CurrencyInfo("HKD", "Hong Kong Dollar", "$", "HK$", 2, 0.0),
	CurrencyInfo("HNL", "Honduran Lempira", "L", "HNL", 2, 0.0),
	CurrencyInfo("HRK", "Croatian Kuna", "kn", "kn", 2, 0.0),
	CurrencyInfo("HUF", "Hungarian Forint", "Ft", "Ft", 0, 0.0),
	CurrencyInfo("IDR", "Indonesian Rupiah", "Rp", "Rp", 0, 0.0),
	CurrencyInfo("ILS", "Israeli New Sheqel", "₪", "₪", 2, 0.0),
	CurrencyInfo("INR", "Indian Rupee", "₹", "Rs", 2, 0.0),
	CurrencyInfo("IQD", "Iraqi Dinar", "د.ع.‏", "IQD", 0, 0.0),
	CurrencyInfo("IRR", "Iranian Rial", "﷼", "IRR", 0, 0.0),
	CurrencyInfo("ISK", "Icelandic Króna", "kr", "Ikr", 0, 0.0),
	CurrencyInfo("JMD", "Jamaican Dollar", "$", "J$", 2, 0.0),
	CurrencyInfo("JOD", "Jordanian Dinar", "د.أ.‏", "JD", 3, 0.0),
	CurrencyInfo("JPY", "Japanese Yen", "￥", "¥", 0, 0.0),
	CurrencyInfo("KES", "Kenyan Shilling", "Ksh", "Ksh", 2, 0.0),
	CurrencyInfo("KHR", "Cambodian Riel", "៛", "KHR", 2, 0.0),
	CurrencyInfo("KMF", "Comorian Franc", "FC", "CF", 0, 0.0),
	CurrencyInfo("KRW", "South Korean Won", "₩", "₩", 0, 0.0),
	CurrencyInfo("KWD", "Kuwaiti Dinar", "د.ك.‏", "KD", 3, 0.0),
	CurrencyInfo("KZT", "Kazakhstani Tenge", "тңг.", "KZT", 2, 0.0),
	CurrencyInfo("LBP", "Lebanese Pound", "ل.ل.‏", "LB£", 0, 0.0),
	CurrencyInfo("LKR", "Sri Lankan Rupee", "SL Re", "SLRs", 2, 0.0),
	CurrencyInfo("LTL", "Lithuanian Litas", "Lt", "Lt", 2, 0.0),
	CurrencyInfo("LVL", "Latvian Lats", "Ls", "Ls", 2, 0.0),
	CurrencyInfo("LYD", "Libyan Dinar", "د.ل.‏", "LD", 3, 0.0),
	CurrencyInfo("MAD", "Moroccan Dirham", "د.م.‏", "MAD", 2, 0.0),
	CurrencyInfo("MDL", "Moldovan Leu", "MDL", "MDL", 2, 0.0),
	CurrencyInfo("MGA", "Malagasy Ariary", "MGA", "MGA", 0, 0.0),
	CurrencyInfo("MKD", "Macedonian Denar", "MKD", "MKD", 2, 0.0),
	CurrencyInfo("MMK", "Myanma Kyat", "K", "MMK", 0, 0.0),
	CurrencyInfo("MOP", "Macanese Pataca", "MOP$", "MOP$", 2, 0.0),
	CurrencyInfo("MUR", "Mauritian Rupee", "MURs", "MURs", 0, 0.0),
	CurrencyInfo("MXN", "Mexican Peso", "$", "MX$", 2, 0.0),
	CurrencyInfo("MYR", "Malaysian Ringgit", "RM", "RM", 2, 0.0),
	CurrencyInfo("MZN", "Mozambican Metical", "MTn", "MTn", 2, 0.0),
	CurrencyInfo("NAD", "Namibian Dollar", "N$", "N$", 2, 0.0),
	CurrencyInfo("NGN", "Nigerian Naira", "₦", "₦", 2, 0.0),
	CurrencyInfo("NIO", "Nicaraguan Córdoba", "C$", "C$", 2, 0.0),
	CurrencyInfo("NOK", "Norwegian Krone", "kr", "Nkr", 2, 0.0),
	CurrencyInfo("NPR", "Nepalese Rupee", "नेरू", "NPRs", 2, 0.0),
	CurrencyInfo("NZD", "New Zealand Dollar", "$", "NZ$", 2, 0.0),
	CurrencyInfo("OMR", "Omani Rial", "ر.ع.‏", "OMR", 3, 0.0),
	CurrencyInfo("PAB", "Panamanian Balboa", "B/.", "B/.", 2, 0.0),
	CurrencyInfo("PEN", "Peruvian Nuevo Sol", "S/.", "S/.", 2, 0.0),
	CurrencyInfo("PHP", "Philippine Peso", "₱", "₱", 2, 0.0),
	CurrencyInfo("PKR", "Pakistani Rupee", "₨", "PKRs", 0, 0.0),
	CurrencyInfo("PLN", "Polish Zloty", "zł", "zł", 2, 0.0),
	CurrencyInfo("PYG", "Paraguayan Guarani", "₲", "₲", 0, 0.0),
	CurrencyInfo("QAR", "Qatari Rial", "ر.ق.‏", "QR", 2, 0.0),
	CurrencyInfo("RON", "Romanian Leu", "RON", "RON", 2, 0.0),
	CurrencyInfo("RSD", "Serbian Dinar", "дин.", "din.", 0, 0.0),
	CurrencyInfo("RUB", "Russian Ruble", "₽.", "RUB", 2, 0.0),
	CurrencyInfo("RWF", "Rwandan Franc", "FR", "RWF", 0, 0.0),
	CurrencyInfo("SAR", "Saudi Riyal", "ر.س.‏", "SR", 2, 0.0),
	CurrencyInfo("SDG", "Sudanese Pound", "SDG", "SDG", 2, 0.0),
	CurrencyInfo("SEK", "Swedish Krona", "kr", "Skr", 2, 0.0),
	CurrencyInfo("SGD", "Singapore Dollar", "$", "S$", 2, 0.0),
	CurrencyInfo("SOS", "Somali Shilling", "Ssh", "Ssh", 0, 0.0),
	CurrencyInfo("SYP", "Syrian Pound", "ل.س.‏", "SY£", 0, 0.0),
	CurrencyInfo("THB", "Thai Baht", "฿", "฿", 2, 0.0),
	CurrencyInfo("TND", "Tunisian Dinar", "د.ت.‏", "DT", 3, 0.0),
	CurrencyInfo("TOP", "Tongan Paʻanga", "T$", "T$", 2, 0.0),
	CurrencyInfo("TRY", "Turkish Lira", "TL", "TL", 2, 0.0),
	CurrencyInfo("TTD", "Trinidad and Tobago Dollar", "$", "TT$", 2, 0.0),
	CurrencyInfo("TWD", "New Taiwan Dollar", "NT$", "NT$", 2, 0.0),
	CurrencyInfo("TZS", "Tanzanian Shilling", "TSh", "TSh", 0, 0.0),
	CurrencyInfo("UAH", "Ukrainian Hryvnia", "₴", "₴", 2, 0.0),
	CurrencyInfo("UGX", "Ugandan Shilling", "USh", "USh", 0, 0.0),
	CurrencyInfo("UYU", "Uruguayan Peso", "$", "U$", 2, 0.0),
	CurrencyInfo("UZS", "Uzbekistan Som", "UZS", "UZS", 0, 0.0),
	CurrencyInfo("VEF", "Venezuelan Bolívar", "Bs.F.", "Bs.F.", 2, 0.0),
	CurrencyInfo("VND", "Vietnamese Dong", "₫", "₫", 0, 0.0),
	CurrencyInfo("XAF", "CFA Franc BEAC", "FCFA", "FCFA", 0, 0.0),
	CurrencyInfo("XOF", "CFA Franc BCEAO", "CFA", "CFA", 0, 0.0),
	CurrencyInfo("YER", "Yemeni Rial", "ر.ي.‏", "YR", 0, 0.0),
	CurrencyInfo("ZAR", "South African Rand", "R", "R", 2, 0.0),
	CurrencyInfo("ZMK", "Zambian Kwacha", "ZK", "ZK", 0, 0.0),
	CurrencyInfo("ZWL", "Zimbabwean Dollar", "ZWL$", "ZWL$", 0, 0.0),
)