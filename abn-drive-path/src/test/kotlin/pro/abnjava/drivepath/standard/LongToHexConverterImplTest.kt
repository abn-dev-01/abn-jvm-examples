package pro.abnjava.drivepath.standard

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import pro.abnjava.drivepath.LongToHexConverter
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@OptIn(ExperimentalStdlibApi::class)
class LongToHexConverterImplTest {

    @Test
    fun `convert 31 to 1F`() {
        val converter: LongToHexConverter = LongToHexConverterImpl()
        val res = converter.getHexString(31L)

        assertNotNull(res, "Result should not be null")
        assertEquals("000000000000001F", res, "Result should not be null")
    }

    @Test
    fun `convert 31 to 10`() {
        val converter: LongToHexConverter = LongToHexConverterImpl()
        val res = converter.getHexString(31313131L)

        assertNotNull(res, "Result should not be null")
        assertEquals("0000000001DDCCEB", res, "Result should not be null")
    }

    @Test
    fun `convert -1 Failed`() {
        val converter: LongToHexConverter = LongToHexConverterImpl()

        assertThrows<IllegalArgumentException> {
            converter.getHexString(-1L)
        }
    }

    @Test
    fun `getPath failed when value is empty`() {
        val converter: LongToHexConverter = LongToHexConverterImpl()

        assertThrows<IllegalArgumentException> {
            converter.getPath("", "/")
        }
    }

    @Test
    fun `getPath failed when value is invalid HEX`() {
        val converter: LongToHexConverter = LongToHexConverterImpl()

        assertThrows<IllegalArgumentException> {
            converter.getPath("0123456789QQQ", "/")
        }
    }

    @Test
    fun `getPath 31 like 00-00-00-00-00-00-00-1F`() {
        val converter: LongToHexConverter = LongToHexConverterImpl()
        val value = converter.getHexString(31L)

        val res = converter.getPath(value, "/")

        Assertions.assertNotNull(res)
        assertEquals("00/00/00/00/00/00/00/1F", res, "Result should not be null")
    }

    @Test
    fun `getPath 1 like 00-00-00-00-00-00-00-01`() {
        val converter: LongToHexConverter = LongToHexConverterImpl()
        val value = converter.getHexString(1L)

        val res = converter.getPath(value, "/")

        Assertions.assertNotNull(res)
        assertEquals("00/00/00/00/00/00/00/01", res, "Result should not be null")
    }

    @Test
    fun `getPath 1 like 00-00-00-00-00-00-00-00`() {
        val converter: LongToHexConverter = LongToHexConverterImpl()
        val value = converter.getHexString(0L)

        val res = converter.getPath(value, "-")

        Assertions.assertNotNull(res)
        assertEquals("00-00-00-00-00-00-00-00", res, "Result invalid")
    }

    @Test
    fun `getPath Long MAX_VALUE `() {
        val converter: LongToHexConverter = LongToHexConverterImpl()
        val value = converter.getHexString(Long.MAX_VALUE)

        val res = converter.getPath(value, "-")

        Assertions.assertNotNull(res)
        assertEquals("7F-FF-FF-FF-FF-FF-FF-FF", res, "Result invalid")
    }
}
