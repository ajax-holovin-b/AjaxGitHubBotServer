package com.ajax.githubforbottester.database.`in`.memory

import com.ajax.githubforbottester.database.Database
import org.springframework.stereotype.Service

@Service
class InMemoryDatabase : Database {
    private val mapChatIdToGitHubLogin = mutableMapOf<Long, String>()

    override fun setLogin(chatId: Long, login: String) {
        mapChatIdToGitHubLogin[chatId] = login
    }

    override fun getLogin(chatId: Long): String? {
        return mapChatIdToGitHubLogin[chatId]
    }

    override fun getAllMap(): MutableMap<Long, String> {
        return mapChatIdToGitHubLogin
    }
}
