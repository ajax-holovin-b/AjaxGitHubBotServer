package com.ajax.githubforbottester.database.mongo

import com.ajax.githubforbottester.database.Database
import com.ajax.githubforbottester.database.mongo.model.ChatIdToGitHubLogin
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Service

@Service
@Primary
class MongoDatabaseService(
    private val repository: ChatIdToGitHubLoginRepository
) : Database {

    override fun setLogin(chatId: Long, login: String) {
        val optional = repository.findByChatId(chatId)
        if (optional.isPresent) {
            val chatIdToGitHubLogin = optional.get()
            chatIdToGitHubLogin.gitHubLogin = login
            repository.save(chatIdToGitHubLogin)
        } else {
            repository.save(ChatIdToGitHubLogin(chatId = chatId, gitHubLogin = login))
        }
    }

    override fun getLogin(chatId: Long): String? {
        val optional = repository.findById(chatId)
        return if (optional.isPresent) {
            optional.get().gitHubLogin
        } else {
            null
        }
    }

    override fun getAllMap(): Map<Long, String> {
        val resultMap = mutableMapOf<Long, String>()
        repository.findAll().forEach { resultMap[it.chatId] = it.gitHubLogin }
        return resultMap
    }
}