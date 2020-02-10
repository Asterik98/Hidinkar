package com.hwp.lingkungansehat.tips

data class Tips(var id : Long?,
                var title: String?,
                var img_link: String?,
                var web_link: String?){
    companion object {
        const val TABLE_TIPS = "table_tips"
        const val ID = "id"
        const val TITLE = "title"
        const val IMG_LINK = "img_link"
        const val WEB_LINK = "web_link"
    }
}