package com.nano.data.nav

data class NavigationEntry(
    val title: String,
    val url: String,
    val iconName: String
) {
    fun toHtmlElement(isActive: Boolean): String {
        return """
            <a href="$url" class="hover:text-gray-100 transition-colors flex items-center gap-2 ${if (isActive) "active" else ""}">
            <span class="material-icons">$iconName</span>
            $title
            </a>
        """.trimIndent()
    }
}

val navigationData = listOf(
    NavigationEntry("О себе", "/", "home"),
//    NavigationEntry("Заметки", "/articles", "article")
)