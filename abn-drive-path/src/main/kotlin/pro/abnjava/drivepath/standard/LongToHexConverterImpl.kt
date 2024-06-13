package pro.abnjava.drivepath.standard

import org.apache.commons.lang3.StringUtils
import pro.abnjava.drivepath.LongToHexConverter

@ExperimentalStdlibApi
@SinceKotlin("1.9")
class LongToHexConverterImpl : LongToHexConverter {

    @OptIn(ExperimentalStdlibApi::class)
    override fun getHexString(value: Long): String {
        require(value >= 0) { "value is negative" }
        val hexFormat: HexFormat = HexFormat.UpperCase
        return value.toHexString(hexFormat)
    }

    override fun getPath(value: String, separator: String): String {
        require(StringUtils.isNotEmpty(value)) { "value is empty" }
        val regex = Regex("[^0-9ABCDEF]")
        require(!regex.containsMatchIn(value)) { "value is not a number: $value" }

        return value.chunked(2).joinToString(separator)
    }
}
