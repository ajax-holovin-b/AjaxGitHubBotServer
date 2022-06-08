package com.ajax.githubforbottester.telegram.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.bots.DefaultBotOptions

@Configuration
class TelegramConfig {

    @Bean
    fun defaultBotOptions() = DefaultBotOptions()
}