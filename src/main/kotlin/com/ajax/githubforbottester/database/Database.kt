package com.ajax.githubforbottester.database

interface Database {

    fun setLogin(chatId: Long, login: String)

    fun getLogin(chatId: Long): String?

    fun getAllMap(): Map<Long, String>
}
