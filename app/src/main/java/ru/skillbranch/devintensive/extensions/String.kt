package ru.skillbranch.devintensive.extensions

fun String.truncate(num: Int = 16): String {
    val len: Int = this.trimEnd().length
    return when {
        len < num -> this.trimEnd()
        len >= num -> this.substring(0, num).trimEnd().plus("...")
        else -> this
    }
}

fun String.stripHtml() : String {
    return this.replace(Regex("<[^>]*>|&amp;|&lt;|&gt;|&quot;|&#39;|&nbsp;|[ ]+")) {
        matchResult ->  if (matchResult.value.isNotBlank()) "" else " "
    }.trim()
}