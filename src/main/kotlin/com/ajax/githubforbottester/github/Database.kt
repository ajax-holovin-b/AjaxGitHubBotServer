package com.ajax.githubforbottester.github

import org.springframework.stereotype.Component

@Component
class Database {
    private val mapChatIdToGitHubLogin = mutableMapOf<Long, String>()

    fun setLogin(chatId: Long, login: String) {
        mapChatIdToGitHubLogin[chatId] = login
    }

    fun getLogin(chatId: Long): String? {
        return mapChatIdToGitHubLogin[chatId]
    }

    fun getAllMap(): MutableMap<Long, String> {
        return mapChatIdToGitHubLogin
    }
}
