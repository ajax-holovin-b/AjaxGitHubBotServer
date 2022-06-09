package com.ajax.githubforbottester.database.mongo

import com.ajax.githubforbottester.database.mongo.model.ChatIdToGitHubLogin
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.Optional

interface ChatIdToGitHubLoginRepository : MongoRepository<ChatIdToGitHubLogin, Any> {

    fun findByChatId(chatId: Long): Optional<ChatIdToGitHubLogin>
}