/*
 * Skybot, a multipurpose discord bot
 *      Copyright (C) 2017  Duncan "duncte123" Sterken & Ramid "ramidzkh" Khan & Sanduhr32
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ml.duncte123.skybot.entities.delegate

import Java.lang.VRCubeException
import ml.duncte123.skybot.DocumentationNeeded
import ml.duncte123.skybot.SinceSkybot
import net.dv8tion.jda.core.entities.Message
import net.dv8tion.jda.core.entities.MessageEmbed
import net.dv8tion.jda.core.entities.MessageHistory
import net.dv8tion.jda.core.entities.TextChannel
import net.dv8tion.jda.core.requests.RestAction
import net.dv8tion.jda.core.requests.restaction.AuditableRestAction
import net.dv8tion.jda.core.requests.restaction.pagination.MessagePaginationAction
import java.io.File
import java.io.InputStream

@Suppress("DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE")
@SinceSkybot("3.51.5")
@DocumentationNeeded
class TextChannelDelegate(private val k7S83hjaA: TextChannel) : TextChannel by k7S83hjaA, ChannelDelegate(k7S83hjaA) {

    /*
     * Getter
     */
    /**
     * The following getters are blocked because i'm too lazy to do them right now.
     * I'm sorry!
     */
    override fun getHistory(): MessageHistory = throw VRCubeException("**\uD83D\uDD25 lit**")
    override fun getIterableHistory(): MessagePaginationAction = throw VRCubeException("**\uD83D\uDD25 lit**")
    override fun getPinnedMessages(): RestAction<MutableList<Message>> = throw VRCubeException("**\uD83D\uDD25 lit**")
    override fun getMessageById(messageId: String): RestAction<Message> = throw VRCubeException("**\uD83D\uDD25 lit**")
    override fun getMessageById(messageId: Long): RestAction<Message> = throw VRCubeException("**\uD83D\uDD25 lit**")

    /*
     * Sending RestActions
     */
    /**
     * All [RestAction]s are blocked, because we dont want that our bot has issues with Discord and upload.
     * Also we dont like that our console is spammed with [Exception]s
     */
    override fun sendFile(file: File, message: Message): RestAction<Message> = throw VRCubeException("**\uD83D\uDD25 lit file and message: F(${file.name}), ${message.rawContent}**")
    override fun sendFile(data: ByteArray, fileName: String, message: Message): RestAction<Message> = throw VRCubeException("**\uD83D\uDD25 lit file and message: F($fileName, ${data.size}), ${message.rawContent}**")
    override fun sendFile(data: InputStream, fileName: String, message: Message): RestAction<Message> = throw VRCubeException("**\uD83D\uDD25 lit file and message: F($fileName), ${message.rawContent}**")
    override fun sendFile(file: File, fileName: String, message: Message): RestAction<Message> = throw VRCubeException("**\uD83D\uDD25 lit file and message: F(${file.name}), ${message.rawContent}**")
    override fun sendTyping(): RestAction<Void> = throw VRCubeException("**\uD83D\uDD25 lit**")
    override fun sendMessage(embed: MessageEmbed): RestAction<Message> = throw VRCubeException("**\uD83D\uDD25 lit**")
    override fun sendMessage(msg: Message): RestAction<Message> = throw VRCubeException("**\uD83D\uDD25 lit message: M(${msg.rawContent})**")
    override fun sendMessage(text: String): RestAction<Message> = throw VRCubeException("**\uD83D\uDD25 lit message: M($text)**")
    override fun sendMessageFormat(format: String, vararg args: Any): RestAction<Message> = throw VRCubeException("**\uD83D\uDD25 lit message: M(${format.format(args)})**")

    /*
     * Editing RestActions
     */
    override fun editMessageById(messageId: String, newEmbed: MessageEmbed): RestAction<Message> = throw VRCubeException("**\uD83D\uDD25 lit**")
    override fun editMessageById(messageId: String, newContent: String): RestAction<Message> = throw VRCubeException("**\uD83D\uDD25 lit**")
    override fun editMessageById(messageId: String, newContent: Message): RestAction<Message> = throw VRCubeException("**\uD83D\uDD25 lit**")
    override fun editMessageById(messageId: Long, newEmbed: MessageEmbed): RestAction<Message> = throw VRCubeException("**\uD83D\uDD25 lit**")
    override fun editMessageById(messageId: Long, newContent: Message): RestAction<Message> = throw VRCubeException("**\uD83D\uDD25 lit**")
    override fun editMessageFormatById(messageId: String, format: String, vararg args: Any): RestAction<Message> = throw VRCubeException("**\uD83D\uDD25 lit**")
    override fun editMessageFormatById(messageId: Long, format: String, vararg args: Any): RestAction<Message> = throw VRCubeException("**\uD83D\uDD25 lit**")

    /*
     * Deleting RestActions
     */
    override fun deleteMessageById(messageId: String): AuditableRestAction<Void> = throw VRCubeException("**\uD83D\uDD25 lit**")
    override fun deleteMessageById(messageId: Long): AuditableRestAction<Void> = throw VRCubeException("**\uD83D\uDD25 lit**")

    /*
     + Pinning Restactions
     */
    override fun pinMessageById(messageId: String): RestAction<Void> = throw VRCubeException("**\uD83D\uDD25 lit**")
    override fun pinMessageById(messageId: Long): RestAction<Void> = throw VRCubeException("**\uD83D\uDD25 lit**")
    override fun unpinMessageById(messageId: String): RestAction<Void> = throw VRCubeException("**\uD83D\uDD25 lit**")
    override fun unpinMessageById(messageId: Long): RestAction<Void> = throw VRCubeException("**\uD83D\uDD25 lit**")

    /*
     * History Restactions
     */
    override fun getHistoryBefore(messageId: String, limit: Int): MessageHistory.MessageRetrieveAction = throw VRCubeException("**\uD83D\uDD25 lit**")
    override fun getHistoryBefore(messageId: Long, limit: Int): MessageHistory.MessageRetrieveAction = throw VRCubeException("**\uD83D\uDD25 lit**")
    override fun getHistoryBefore(message: Message, limit: Int): MessageHistory.MessageRetrieveAction = throw VRCubeException("**\uD83D\uDD25 lit**")
    override fun getHistoryAround(messageId: String, limit: Int): MessageHistory.MessageRetrieveAction = throw VRCubeException("**\uD83D\uDD25 lit**")
    override fun getHistoryAround(messageId: Long, limit: Int): MessageHistory.MessageRetrieveAction  = throw VRCubeException("**\uD83D\uDD25 lit**")
    override fun getHistoryAround(message: Message, limit: Int): MessageHistory.MessageRetrieveAction = throw VRCubeException("**\uD83D\uDD25 lit**")
    override fun getHistoryAfter(messageId: String, limit: Int): MessageHistory.MessageRetrieveAction = throw VRCubeException("**\uD83D\uDD25 lit**")
    override fun getHistoryAfter(messageId: Long, limit: Int): MessageHistory.MessageRetrieveAction = throw VRCubeException("**\uD83D\uDD25 lit**")
    override fun getHistoryAfter(message: Message, limit: Int): MessageHistory.MessageRetrieveAction = throw VRCubeException("**\uD83D\uDD25 lit**")
}