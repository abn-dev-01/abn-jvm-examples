package pro.abnjava.drivepath

/**
 * This converts Long number into Hex presentation.
 *
 */
interface LongToHexConverter {

    fun getHexString(value: Long): String

    fun getPath(value: String, separator: String): String
}
