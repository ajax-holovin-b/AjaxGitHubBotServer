package com.ajax.githubforbottester.telegram

import com.ajax.githubforbottester.database.Database
import com.ajax.githubforbottester.github.PullRequestService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.DefaultBotOptions
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class TelegramBot(
    private val pullRequestService: PullRequestService,
    private val database: Database,
    private val defaultBotOptions: DefaultBotOptions
) : TelegramLongPollingBot(defaultBotOptions) {

    override fun getBotToken(): String {
        return "5492484838:AAF_nvAXAI0TfcT4AOd33MDsjaDX79u92bc"
    }

    override fun getBotUsername(): String {
        return "@AjaxGitHubBot"
    }

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage()) handleMessage(update.message)
    }

    private fun handleMessage(message: Message) {
        if (message.hasEntities() && message.entities.any { "bot_command" == it.type }) {

            val messageWords = message.text.split(" ")

            when (messageWords[0]) {
                "/set_login" -> {
                    val textToSend = when (messageWords.size == 2) {
                        true -> {
                            val login = messageWords[1]
                            database.setLogin(message.chatId, login)
                            "Login set to $login"
                        }
                        false -> "You entered the command incorrectly"
                    }

                    val sendMessage = SendMessage.builder()
                        .chatId(message.chatId.toString())
                        .text(textToSend)
                        .build()

                    execute(sendMessage)
                }
                "/get_current_login" -> {
                    val loginCurrent = database.getLogin(message.chatId)
                    val textToSend = when (loginCurrent != null) {
                        true -> loginCurrent
                        false -> "You don`t have any login in bot"
                    }

                    val sendMessage = SendMessage.builder()
                        .chatId(message.chatId.toString())
                        .text(textToSend)
                        .build()

                    execute(sendMessage)
                }
                "/get_review_requests" -> {
                    val loginCurrent = database.getLogin(message.chatId)
                    val textToSend = when (loginCurrent != null) {
                        true -> {
                            pullRequestService.checkReviewRequests(loginCurrent)
                                .map { pullRequestForReviewData ->
                                    """
                                        login = ${pullRequestForReviewData.login}
                                        repos = ${pullRequestForReviewData.repos}
                                        request review url = ${pullRequestForReviewData.pullRequestUrl}
                                    """.trimIndent()
                                }
                                .toString()
                        }
                        false -> "You don`t register login in bot"
                    }

                    val sendMessage = SendMessage.builder()
                        .chatId(message.chatId.toString())
                        .text(textToSend)
                        .build()

                    execute(sendMessage)
                }
            }
        }
    }

    @Scheduled(cron = "00 30 09 * * ?", zone = "GMT+3")
    private fun dailyAlarm() {
        database.getAllMap().forEach { (chatId, login) ->
            val textToSend = pullRequestService.checkReviewRequests(login)
                .map { pullRequestForReviewData ->
                    """
                        login = ${pullRequestForReviewData.login}
                        repos = ${pullRequestForReviewData.repos}
                        request review url = ${pullRequestForReviewData.pullRequestUrl}
                    """.trimIndent()
                }
                .toString()

            val sendMessage = SendMessage.builder()
                .chatId(chatId.toString())
                .text(textToSend)
                .build()

            execute(sendMessage)
        }
    }
}