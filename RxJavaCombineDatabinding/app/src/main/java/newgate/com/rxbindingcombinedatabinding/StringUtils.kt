package newgate.com.rxbindingcombinedatabinding

/**
 * Created by apple on 8/11/17.
 */
class StringUtils {
    companion object {
        var EMPTY = ""
        fun isNullOrEmpty(string: String): Boolean {
            return !isNullOrEmpty(string)
        }
        fun isNotNullOrEmpty(string: String): Boolean {
            return string != null && !string.equals(EMPTY)
        }
    }
}